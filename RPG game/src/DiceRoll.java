public class DiceRoll {
  
  private int posX;
  private int posY;
  
  public int dice(){
    int max = 6;
    int min = 1;
    int range = (max-min) + 1;
    return (int)(Math.random()*range) + min;
  }
  
  public void genRandStartPos(){
    do{
      randomPosX();
      randomPosY();
    }
    while ((Table.mapLists[posX][posY] instanceof Floor) && posX + posY != 0 && posX + posY != 1 &&
                   Table.monsterList.get(0).getPositionX() != posX && Table.monsterList.get(0).getPositionY() != posY);
  }
  //TODO monsters shouldnt appear on each other.
  
  public void randomPosX(){
     int posX = (int)(Math.random()*10);
     this.posX = posX;
  }
  public void randomPosY(){
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
