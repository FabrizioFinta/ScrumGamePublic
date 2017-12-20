class Monster extends Moving {
  
  private boolean hasKey;
  
  Monster(){
    Table.diceRoll.genRandStartPos();
    setPositionX(Table.diceRoll.getPosX());
    setPositionY(Table.diceRoll.getPosY());
    setSourceIMG("skeleton.png");
    setMaxHP(2 * level * Table.diceRoll.dice());
    setDefendP(level/2 * Table.diceRoll.dice());
    setStrikeP(level * Table.diceRoll.dice());
  }
  
  void moveMonsterRandomDir(){
    //TODO need update: monsters have to move to the direction of the hero && shouldnt move in that dir where the wall is && shouldnt move on each other
    int direction = Table.diceRoll.getRandomMoveDirection();
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
}
