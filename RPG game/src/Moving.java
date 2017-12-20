public abstract class Moving extends GameElement{
  
  public int level = 1;
  private double MaxHP;
  private double CurrentHP;
  private double DefendP;
  private double StrikeP;
  private boolean isDead;
  int heroMoveCounter = 0;
  
  public void moveUp(){
    if (super.getPositionY() != 0)  {
      if (!(Table.fieldLists[super.getPositionX()][super.getPositionY()-1] instanceof Wall)) {
        super.setPositionY(super.getPositionY() - 1);
      }
    }
  }
  public void moveDown(){
  
    if (super.getPositionY() < 9) {
      if (!(Table.fieldLists[super.getPositionX()][super.getPositionY()+1] instanceof Wall)) {
        super.setPositionY(super.getPositionY() + 1);
      }
    }
  }
  public void moveRight(){
    if (super.getPositionX() < 9) {
      if (!(Table.fieldLists[super.getPositionX()+1][super.getPositionY()] instanceof Wall)) {
        super.setPositionX(super.getPositionX() + 1);
      }
    }
  }
  public void moveLeft(){
    if(super.getPositionX() != 0) {
      if (!(Table.fieldLists[super.getPositionX()-1][super.getPositionY()] instanceof Wall)) {
        super.setPositionX(super.getPositionX() - 1);
      }
    }
  }
  public double getMaxHP() {
    return MaxHP;
  }
  public void setMaxHP(double maxHP) {
    MaxHP = maxHP;
  }
  
  public double getCurrentHP() {
    return CurrentHP;
  }
  public void setCurrentHP(double currentHP) {
    CurrentHP = currentHP;
  }
  
  public double getDefendP() {
    return DefendP;
  }
  public void setDefendP(double defendP) {
    DefendP = defendP;
  }
  
  public double getStrikeP() {
    return StrikeP;
  }
  public void setStrikeP(double strikeP) {
    StrikeP = strikeP;
  }
  
  public boolean isDead() {
    return isDead;
  }
  public void setDead() {
    isDead = true;
  }
}