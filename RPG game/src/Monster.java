public class Monster extends Moving {
  
  private boolean hasKey;
  
  public Monster(){
    diceRoll.genRandStartPos();
    super.setPositionX(diceRoll.getPosX());
    super.setPositionY(diceRoll.getPosY());
    super.setSourceIMG("skeleton.png");
    super.setMaxHP(2 * level * diceRoll.dice());
    super.setDefendP(level/2 * diceRoll.dice());
    super.setStrikeP(level * diceRoll.dice());
  }
}
