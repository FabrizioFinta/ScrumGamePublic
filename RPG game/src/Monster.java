class Monster extends Moving {
  
  private int prevPositionX;
  private int prevPostitionY;
  
  Monster() {
    Table.randomNumber.genRandStartPos(true);
    setPosition();
    setSourceIMG("skeleton.png");
    setPoints();
  }
  
  void moveMonsterRandomDir() {
    //TODO need update: monsters have to move to the direction of the hero && shouldnt move in that dir where the wall is && shouldnt move on each other
    boolean chooseAnotherPosition;
    do {
      int direction = Table.randomNumber.getRandomMoveDirection();
      if (direction == 1) {
        storePositions();
        moveUp();
        chooseAnotherPosition = false;
      } else if (direction == 2) {
        storePositions();
        moveRight();
        chooseAnotherPosition = false;
      } else if (direction == 3) {
        storePositions();
        moveDown();
        chooseAnotherPosition = false;
      } else {
        storePositions();
        moveLeft();
        chooseAnotherPosition = false;
      }
    } while (chooseAnotherPosition);
  }
  
  void setPosition(){
    setPositionX(Table.randomNumber.getPosX());
    setPositionY(Table.randomNumber.getPosY());
  }
  void storePositions() {
    prevPositionX = getPositionX();
    prevPostitionY = getPositionY();
  }
  public int getPrevPositionX() {
    return prevPositionX;
  }
  public int getPrevPositionY() {
    return prevPostitionY;
  }
  
  void setPoints(){
    setMaxHP(2 * level * Table.randomNumber.dice());
    setDefendP(level / 2 * Table.randomNumber.dice());
    setStrikeP(level * Table.randomNumber.dice());
    restoreHP();
  }
  
  @Override
  void levelUp() { //TODO after a stage level should be bigger level up
    int luckyNumber = Table.randomNumber.genNumber(1,10);
    if(luckyNumber == 1){
      level = Table.getCurrentStage() + 2;
    } else if (luckyNumber <= 5){
      level = Table.getCurrentStage() + 1;
    } else {
      level = Table.getCurrentStage();
    }
    setPoints();
  }
  
  @Override
  void execute() {
    setSourceIMG("skeletonDead.png");
    super.execute();
  }
  
  @Override
  void resurrect() {
    super.resurrect();
    setSourceIMG("skeleton.png");
  }
}
