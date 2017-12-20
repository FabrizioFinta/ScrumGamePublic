import java.util.ArrayList;

public class DiceRoll {
  
  private int posX;
  private int posY;
  private ArrayList<Integer> prevPosX;
  private ArrayList<Integer> prevPosY;
  
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
    while ((Table.mapLists[posX][posY] instanceof Floor)&& prevPosY.contains(posY) && prevPosX.contains(posX));
    prevPosX.add(posX);
    prevPosY.add(posY);
  }
  //TODO monsters shouldnt appear on each other.
  
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
}
