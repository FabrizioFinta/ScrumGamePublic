public class Hero extends Moving{
  
  public Hero(){
    super.setPositionX(0);
    super.setPositionX(0);
    super.setSourceIMG("hero-down.png");
  }
  
  @Override
  public void moveUp() {
    super.setSourceIMG("hero-up.png");
    super.moveUp();
    super.heroMoveCounter++;
  }
  
  @Override
  public void moveDown() {
    super.setSourceIMG("hero-down.png");
    super.moveDown();
    super.heroMoveCounter++;
  }
  
  @Override
  public void moveRight() {
    super.setSourceIMG("hero-right.png");
    super.moveRight();
    super.heroMoveCounter++;
  }
  
  @Override
  public void moveLeft() {
    super.setSourceIMG("hero-left.png");
    super.moveLeft();
    super.heroMoveCounter++;
  }
}
