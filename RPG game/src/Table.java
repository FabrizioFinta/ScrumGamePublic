import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Table {
  
  
  private final int NUMBER_OF_MONSTERS = 5;
  static DiceRoll diceRoll = new DiceRoll();
  static GameElement[][] fieldLists = new GameElement[10][10];
  ArrayList<GameElement> elementList = new ArrayList<>();
  ArrayList<Monster> monsterList = new ArrayList<>();
  Hero hero = new Hero();
  Boss boss = new Boss();
  
  
  public Table(){
  }
  
  public void fill(String MAP_SOURCE){
    Path file = Paths.get(MAP_SOURCE);
  
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
      monsterList.add(boss);
      elementList.addAll(monsterList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void drawTable(Graphics graphics){
    for (int index = 0; index < elementList.size(); index++) {
      elementList.get(index).draw(graphics);
    }
  }
  
  public void moveHero(KeyEvent e){
    
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
      for (int i = 0; i < monsterList.size(); i++) {
        monsterList.get(i).moveMonsterRandomDir();
      }
    }
  }
  
  public void monsterGenerator(int numberOfMonsters){
    for (int i = 1; i <= numberOfMonsters; i++) {
      Monster monster = new Monster();
      monsterList.add(monster);
    }
  }
  
}
