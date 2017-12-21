class Hero extends Moving{
  
  Hero(){
    super.setPositionX(0);
    super.setPositionX(0);
    super.setSourceIMG("hero-down.png");
    setMaxHP(20 + 3 * Table.randomNumber.dice());
    restoreHP();
    setDefendP(2 * Table.randomNumber.dice());
    setStrikeP(5 + Table.randomNumber.dice());
  }
  
  void restoreHeroHP(){
    int luckyNumber = Table.randomNumber.genNumber(1,10);
    if(luckyNumber == 1){
      restoreHP();
    } else if (luckyNumber <= 5){
      setCurrentHP(getCurrentHP() + getMaxHP()/3);
    } else if (luckyNumber <= 10) {
      setCurrentHP(getCurrentHP() + getMaxHP()/10);
    }
  }
  
  @Override
  protected void moveUp() {
    super.setSourceIMG("hero-up.png");
    super.moveUp();
    super.heroMoveCounter++;
  }
  
  @Override
  protected void moveDown() {
    if(hasKey) {
      super.setSourceIMG("hero-down-key.png");
    } else {
      super.setSourceIMG("hero-down.png");
    }
    super.moveDown();
    super.heroMoveCounter++;
  }
  
  @Override
  protected void moveRight() {
    if(hasKey) {
      super.setSourceIMG("hero-right-key.png");
    } else {
      super.setSourceIMG("hero-right.png");
    }
    super.moveRight();
    super.heroMoveCounter++;
  }
  
  @Override
  protected void moveLeft() {
    if(hasKey) {
      super.setSourceIMG("hero-left-key.png");
    } else {
      super.setSourceIMG("hero-left.png");
    }
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
