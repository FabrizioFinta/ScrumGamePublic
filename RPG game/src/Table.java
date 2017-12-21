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
  static GameElement[][] fieldLists = new GameElement[10][10];
  
  private ArrayList<GameElement> elementList = new ArrayList<>();
  private ArrayList<Monster> monsterList = new ArrayList<>();
  private Hero hero = new Hero();
  private Monster boss;
  private int currentStage = 1;
  
  Table(){
  }
  
  void fill(String mapSource){
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
            elementList.add(element);
          } else {
            GameElement element = new Floor(posXCounter, posYCounter);
            fieldLists[subIndex][index] = element;
            elementList.add(element);
          }
          posXCounter += 1;
        }
        posXCounter = 0;
        posYCounter += 1;
      }
      monsterGenerator(NUMBER_OF_MONSTERS);
      elementList.addAll(monsterList);
      elementList.add(hero);
      elementList.add(boss);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  void drawTable(Graphics graphics){
    for (GameElement anElementList : elementList) {
      anElementList.draw(graphics);
    }
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
  
  void stageComplated(){
    currentStage++;
    for (int i = 0; i < monsterList.size(); i++) {
      monsterList.get(i).levelUp();
    }
    hero.restoreHeroHP();
  }
  int getCurrentStage(){
    return currentStage;
  }
  
  private boolean battle(Moving attacker, Moving defender){
    //TODO set a third type picture to illustrate the battle
    boolean heroIsWinner = true;
    int roundCounter = 0;
    do {
      double attackerSV = attacker.getStrikeP() + randomNumber.dice()*2;
      double defenderSV = defender.getStrikeP() + randomNumber.dice()*2;
      if (roundCounter % 2 == 0){
        if (attackerSV > defender.getDefendP()){
          defender.setCurrentHP(defender.getCurrentHP() - (attackerSV - defender.getDefendP()));
        } else {
          if (defenderSV > attacker.getDefendP()) {
            attacker.setCurrentHP(attacker.getCurrentHP() - (defenderSV - attacker.getDefendP()));
          }
        }
      }
      if (attacker.getCurrentHP() <= 0){
        if (attacker instanceof Hero) {
          heroIsWinner = false;
        }
        attacker.killIt();
      }
      if (defender.getCurrentHP() <= 0) {
        if (defender instanceof Hero) {
          heroIsWinner = false;
      }
        defender.killIt();
      }
      roundCounter ++;
    } while(!(attacker.isDead()) && !(defender.isDead()));
    return heroIsWinner;
  }
  
  void checkKeyEvent(KeyEvent e) {
    moveHero(e);
    checkCharacters(e);
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
        if (hero.getPositionX() == monster.getPositionX() && hero.getPositionY() == monster.getPositionY()) {
          hero.setSourceIMG("heroSword.png");
          if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (!(battle(hero, monster))) {
              //Game should end.
            } //TODO else check the monster had key, if it had then give it to the hero
          }
          break;
        }
        //if (hero.getPositionX() == monster.getPositionX() && hero.getPositionY() == monster.getPositionY() && monster.getPrevPositionX() != monster.getPositionX() || monster.getPrevPostitionY() != monster.getPositionY()){
          //battle(monster, hero);
          //break;
       // }
      }
    }
  }
}
