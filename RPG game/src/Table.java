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
  protected ArrayList<Monster> monsterList = new ArrayList<>();
  private Hero hero = new Hero();
  private Monster boss;
  
  
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
      elementList.add(hero);
      elementList.add(boss);
      elementList.addAll(monsterList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  void drawTable(Graphics graphics){
    for (int index = 0; index < elementList.size(); index++) {
      elementList.get(index).draw(graphics);
    }
  }
  
  void moveHero(KeyEvent e){
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
  
  private void monsterMotor() {
    if (hero.heroMoveCounter%2==0){
      for (Monster aMonsterList : monsterList) {
        aMonsterList.moveMonsterRandomDir();
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
  
  void Batlle(GameElement monster, GameElement hero){
  
  }
}
