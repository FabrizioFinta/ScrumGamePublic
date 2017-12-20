class Hero extends Moving{
  
  Hero(){
    super.setPositionX(0);
    super.setPositionX(0);
    super.setSourceIMG("hero-down.png");
  }
  
  @Override
  protected void moveUp() {
    super.setSourceIMG("hero-up.png");
    super.moveUp();
    super.heroMoveCounter++;
  }
  
  @Override
  protected void moveDown() {
    super.setSourceIMG("hero-down.png");
    super.moveDown();
    super.heroMoveCounter++;
  }
  
  @Override
  protected void moveRight() {
    super.setSourceIMG("hero-right.png");
    super.moveRight();
    super.heroMoveCounter++;
  }
  
  @Override
  protected void moveLeft() {
    super.setSourceIMG("hero-left.png");
    super.moveLeft();
    super.heroMoveCounter++;
  }
}
