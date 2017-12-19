public abstract class Moving extends GameElement{
  public void moveUp(){
    if (super.getPositionY() != 0)  {
      if (!(Table.mapLists[super.getPositionX()][super.getPositionY()-1] instanceof Wall)) {
        super.setPositionY(super.getPositionY() - 1);
      }
    }
  }
  public void moveDown(){
    if (super.getPositionY() < 9) {
      if ((Table.mapLists[super.getPositionX()][super.getPositionY()+1] instanceof Floor)) {
        super.setPositionY(super.getPositionY() + 1);
      }
    }
  }
  public void moveRight(){
    if (super.getPositionX() < 9) {
      super.setPositionX(super.getPositionX() + 1);
    }
  }
  public void moveLeft(){
    if(super.getPositionX() != 0) {
      super.setPositionX(super.getPositionX() - 1);
    }
  }
}
