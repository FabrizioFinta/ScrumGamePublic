class Monster extends Moving {
  
  boolean hasKey;
  private int prevPositionX;
  private int prevPostitionY;
  
  Monster(){
    Table.randomNumber.genRandStartPos();
    setPositionX(Table.randomNumber.getPosX());
    setPositionY(Table.randomNumber.getPosY());
    setSourceIMG("skeleton.png");
    setMaxHP(2 * level * Table.randomNumber.dice());
    setDefendP(level/2 * Table.randomNumber.dice());
    setStrikeP(level * Table.randomNumber.dice());
  }
  
  void moveMonsterRandomDir(){
    //TODO need update: monsters have to move to the direction of the hero && shouldnt move in that dir where the wall is && shouldnt move on each other
    int direction = Table.randomNumber.getRandomMoveDirection();
    if (direction == 1) {
      storePositions();
      moveUp();
    } else if (direction == 2) {
      storePositions();
      moveRight();
    } else if (direction == 3) {
      storePositions();
      moveDown();
    } else {
      storePositions();
      moveLeft();
    }
  }
  
  void giveKey(){
    this.hasKey = true;
    setSourceIMG("skeletonWithKey.png");
  }
  
  void storePositions(){
    prevPositionX = getPositionX();
    prevPostitionY = getPositionY();
  }
  
  public int getPrevPositionX() {
    return prevPositionX;
  }
  
  public int getPrevPostitionY() {
    return prevPostitionY;
  }
  
  @Override
  void levelUp() {
    super.levelUp();
    restoreHP();
  }
  
  @Override
  void killIt(){
    setSourceIMG("skeletonDead.png");
    super.killIt();
  }
}
