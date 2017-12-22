class Hero extends Moving{
  
  Hero(){
    super.setPositionX(0);
    super.setPositionX(0);
    super.setSourceIMG("hero-down.png");
    // default setMaxHP(20 + 3 * Table.randomNumber.dice());
    setMaxHP(1); //test
    restoreHP(); //test
    setDefendP(1); //test
    // default setStrikeP(1 + Table
    // default setDefendP(2 * Table.randomNumber.dice());.randomNumber.dice());
    setStrikeP(1); //test
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
  } //TODO game optimalization: Current HP couldnt get boost.
  
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
    super.levelUp(); //TODO game optimalization
    setMaxHP(getMaxHP() + Table.randomNumber.dice());
    setDefendP(getDefendP() + Table.randomNumber.dice());
    setStrikeP(getStrikeP() + Table.randomNumber.dice());
  }
}
