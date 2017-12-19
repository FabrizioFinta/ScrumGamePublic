public class Actor extends GameDisplay {
  
  int positionX = 0;
  int positionY = 0;
  int posToFieldIndexX = positionX/72;
  int posToFieldIndexY = positionY/72;
  
  void moveUp(){
    if (!(posToFieldIndexY>=10)) {
      if (super.getFieldList()[posToFieldIndexX][posToFieldIndexY + 1].getIsWall()) {
        positionY += 72;
      }
    }
  }
  void moveDown(){
    if (!(posToFieldIndexY<=0)) {
      if (super.getFieldList()[posToFieldIndexX][posToFieldIndexY -1].getIsWall()) {
        positionY -= 72;
      }
    }
  }
  void moveRight(){
    if (!(posToFieldIndexX>=10)) {
      if (super.getFieldList()[posToFieldIndexX + 1][posToFieldIndexY].getIsWall()) {
        positionX += 72;
      }
    }
  }
  void moveLeft(){
    if (!(posToFieldIndexX>=0)) {
      if (super.getFieldList()[posToFieldIndexX - 1][posToFieldIndexY].getIsWall()) {
        positionX -= 72;
      }
    }
  }
  
  public int getPositionX() {
    return positionX;
  }
  public int getPositionY() {
    return positionY;
  }
}
