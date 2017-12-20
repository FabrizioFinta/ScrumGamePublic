class Monster extends Moving {
  
  private boolean hasKey;
  
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
    if(direction == 1){
      moveUp();
    } else if ( direction == 2) {
      moveRight();
    } else if (direction == 3) {
      moveDown();
    } else {
      moveLeft();
    }
  }
  
  void giveKey(){
    this.hasKey = true;
    setSourceIMG("skeletonWithKey.png");
  }
}
