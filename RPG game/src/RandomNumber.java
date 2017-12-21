import java.util.ArrayList;
import java.util.Arrays;

class RandomNumber {
  
  private int posX;
  private int posY;
  private ArrayList<Integer> usedPosX = new ArrayList<>(Arrays.asList(0));
  private ArrayList<Integer> usedPosY = new ArrayList<>(Arrays.asList(0));
  
  int dice(){
    int max = 6;
    int min = 1;
    int range = (max-min) + 1;
    return (int)(Math.random()*range) + min;
  }
  
  void genRandStartPos(boolean keepThePreviousPositions){
    if (keepThePreviousPositions) {
      generateNotSamePositionPairs();
    } else{
      usedPosX.removeAll(usedPosX);
      usedPosY.removeAll(usedPosY);
      usedPosY.add(0);
      usedPosX.add(0);
      do {
        genRandomCoordinatePairs();
      }
      while (Table.fieldLists[posX][posY] instanceof Wall || (usedPosX.contains(posX) && usedPosY.contains(posY)));
      usedPosX.add(posX);
      usedPosY.add(posY);
    }
  }
  
  private void generateNotSamePositionPairs() {
    do {
      genRandomCoordinatePairs();
    }
    while (Table.fieldLists[posX][posY] instanceof Wall || (usedPosX.contains(posX) && usedPosY.contains(posY)));
    usedPosX.add(posX);
    usedPosY.add(posY);
  }
  
  private void genRandomCoordinatePairs(){
    this.posX = (int)(Math.random()*10);
    this.posY = (int)(Math.random()*10);
  }
  int getPosX() {
    return posX;
  }
  int getPosY() {
    return posY;
  }
  
  int getRandomMoveDirection(){
    return (int)(Math.random()*4)+1;
  }
  
  int genNumber(int min, int max){
    return (int)((Math.random()*(max-min)+1)+min);
  }
}
