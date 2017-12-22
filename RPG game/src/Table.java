import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class Table {
  
  
  private final int NUMBER_OF_MONSTERS = 5;
  static RandomNumber randomNumber = new RandomNumber();
  boolean isNotGameOver = true;
  static GameElement[][] fieldLists = new GameElement[10][10];
  private ArrayList<GameElement> elementList = new ArrayList<>();
  private ArrayList<Monster> monsterList = new ArrayList<>();
  private Hero hero = new Hero();
  private boolean heroIsWinner = true;
  private Monster boss;
  private static int currentStage = 1;
  private final Font f = new Font("Arial", Font.BOLD, 15);
  
  Table(){
  }
  
  void fillMap(String mapSource){
    Path file = Paths.get(mapSource);
  
    try {
      ArrayList<String> dataList = new ArrayList<>(Files.readAllLines(file));
      int posXCounter = 0;
      int posYCounter = 0;
      for (int index = 0; index < dataList.size(); index++) {
        String[] rows = (dataList.get(index).split(" "));
        for (int subIndex = 0; subIndex < rows.length; subIndex++) {
          if (Integer.parseInt(rows[subIndex]) == 1) {
            GameElement element = new Wall(posXCounter, posYCounter);
            fieldLists[subIndex][index] = element;
            elementList.add(element); //TODO refactor
          } else {
            GameElement element = new Floor(posXCounter, posYCounter);
            fieldLists[subIndex][index] = element;
            elementList.add(element);
          }
          posXCounter ++;
        }
        posXCounter = 0;
        posYCounter ++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  void fillMapWithCharacters() {
    monsterGenerator(NUMBER_OF_MONSTERS);
    elementList.addAll(monsterList);
    elementList.add(hero);
    elementList.add(boss);
  }
  
  void drawTable(Graphics graphics){
    for (GameElement anElementList : elementList) {
      anElementList.draw(graphics);
    }
  }
  void drawGameOver(Graphics graphics){
    PositionedImage image = new PositionedImage("GameOver.png", 0, 0);
    image.draw(graphics);
  }
  
  private void monsterMotor() {
    if (hero.heroMoveCounter%2==0){
      for (Monster aMonsterList : monsterList) {
        if (!(aMonsterList.isDead())) {
          aMonsterList.moveMonsterRandomDir();
        }
      }
    }
  }
  
  private void monsterGenerator(int numberOfMonsters){
    int indexOfMonsterWithKey = randomNumber.genNumber(1,numberOfMonsters);
    for (int i = 1; i <= numberOfMonsters; i++) {
      Monster monster = new Monster();
      if (i == indexOfMonsterWithKey) {
        monster.giveKey();
      }
      monsterList.add(monster);
    }
    boss = new Boss(); //TODO ask this solved the problem - Boss boss = new Boss(); >> Monster boss = new Boss();
    monsterList.add(boss);
  }
  
  private void stageCompleted(){
    //Show new stage subtitle
    currentStage++;
    for (Monster monster : monsterList) {
      monster.resurrect();
      randomNumber.genRandStartPos(false);
      monster.levelUp();
      monster.setPosition();
    }
    hero.restoreHeroHP();
    hero.depriveFromKey();
  }
  
  static int getCurrentStage(){
    return currentStage;
  }
  
  private void stageReset(){
    currentStage = 1;
  }
  
  private boolean battleAttackerWon(Moving attacker, Moving defender){
    //TODO set a third type picture to illustrate the battle
    int roundCounter = 0;
    do {
      double attackerSV = calculateStrikePoint(attacker);
      double defenderSV = calculateStrikePoint(defender);
      if (roundCounter % 2 == 0){
        if (attackerSV > defender.getDefendP()){
          
          defender.setCurrentHP(defender.getCurrentHP() - (attackerSV - defender.getDefendP()));
        }
      } else {
          if (defenderSV > attacker.getDefendP()) {
            attacker.setCurrentHP(attacker.getCurrentHP() - (defenderSV - attacker.getDefendP()));
          }
      }
      executioner(attacker);
      executioner(defender);
      roundCounter ++;
    } while(!(attacker.isDead()) && !(defender.isDead()));
    if (heroIsWinner){
      passKeyIfHas(attacker);
      passKeyIfHas(defender);
    }
    return heroIsWinner;
  }
  
  private double calculateStrikePoint(Moving participant) {
    return participant.getStrikeP() + randomNumber.dice()*2;
  }
  
  private void passKeyIfHas(Moving character) {
    if (character instanceof Monster && character.getHasKey()) {
      hero.giveKey();
    }
  }
  private void executioner(Moving victim){
    if (victim.getCurrentHP() <= 0) {
      if (victim instanceof Hero) {
        heroIsWinner = false;
      }
      victim.execute();
    }
  }
  
  void checkKeyEvent(KeyEvent e) {
    if(isNotGameOver) {
      moveHero(e);
      checkCharacters(e);
    }
  }
  
  private void moveHero(KeyEvent e){
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      hero.moveUp();
      monsterMotor();
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      hero.moveDown();
      monsterMotor();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      hero.moveLeft();
      monsterMotor();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      hero.moveRight();
      monsterMotor();
    }
  }
  private void checkCharacters(KeyEvent e){
    Monster monster;
    for (Monster aMonsterList : monsterList) {
      monster = aMonsterList;
      if (! (monster.isDead())) {
        if (heroMonsterSamePos(monster)) {
          hero.setSourceIMG("heroSword.png");
          if (monster.getPrevPositionX() != monster.getPositionX() || monster.getPrevPositionY() != monster.getPositionY()){
            resultBattle(battleAttackerWon(hero, monster));
            break;
          }
          if (e.getKeyCode() == KeyEvent.VK_SPACE){
            resultBattle(battleAttackerWon(hero, monster));
            break;
          }
        }
      }
    }
    if(boss.isDead() && hero.getHasKey()){
    stageCompleted();
    }
  }
  
  private void resultBattle(boolean b) {
    if (b) {
      hero.levelUp();
    } else {
      isNotGameOver = false;
    }
  }
  
  void drawStats(Graphics graphics) {
    drawStatusBar(hero, graphics);
    monsterList.stream().filter(this :: heroMonsterSamePos).forEach(monster -> drawStatusBar(monster, graphics));
  }
  
  private void drawStatusBar(Moving character, Graphics graphics) {
    graphics.setColor(Color.WHITE);
    graphics.fillRect(350,0,370,30);
    graphics.setColor(Color.BLACK);
    graphics.setFont(f);
    graphics.drawString(character.toString(), 350, 25);
  }
  
  private boolean heroMonsterSamePos(Moving monster){
    return hero.getPositionX() == monster.getPositionX() && hero.getPositionY() == monster.getPositionY();
  }
  
  void reset(){
    elementList.removeAll(elementList);
    monsterList.removeAll(monsterList);
    stageReset();
    isNotGameOver = true;
  }
}
