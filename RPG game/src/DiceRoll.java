import java.util.ArrayList;
import java.util.Arrays;

public class DiceRoll {
  
  private int posX;
  private int posY;
  private ArrayList<Integer> usedPosX = new ArrayList<>(Arrays.asList(0));
  private ArrayList<Integer> usedPosY = new ArrayList<>(Arrays.asList(0));
  
  public int dice(){
    int max = 6;
    int min = 1;
    int range = (max-min) + 1;
    return (int)(Math.random()*range) + min;
  }
  
  public void genRandStartPos(){
    do{
      genRandomCoordinatePairs();
    }
    while (Table.fieldLists[posX][posY] instanceof Wall || (usedPosX.contains(posX) && usedPosY.contains(posY)));
    usedPosX.add(posX);
    usedPosY.add(posY);
  }
  
  private void genRandomCoordinatePairs(){
    int posX = (int)(Math.random()*10);
    this.posX = posX;
    int posY = (int)(Math.random()*10);
    this.posY = posY;
  }
  public int getPosX() {
    return posX;
  }
  public int getPosY() {
    return posY;
  }
  
  public int getRandomMoveDirection(){
    return (int)(Math.random()*4)+1;
  }
}
