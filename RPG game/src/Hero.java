class Hero extends Moving{
  
  boolean hasKey;
  
  Hero(){
    super.setPositionX(0);
    super.setPositionX(0);
    super.setSourceIMG("hero-down.png");
    setMaxHP(20 + 3 * Table.randomNumber.dice());
    setCurrentHP(getMaxHP());
    setDefendP(2 * Table.randomNumber.dice());
    setStrikeP(5 + Table.randomNumber.dice());
  }
  
  void giveKey(){
    hasKey = true;
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
  @Override
  void levelUp(){
    setMaxHP(getMaxHP() + Table.randomNumber.dice());
    setDefendP(getDefendP() + Table.randomNumber.dice());
    setStrikeP(getStrikeP() + Table.randomNumber.dice());
    super.levelUp();
  }
}
