import javafx.scene.control.Tab;

public class Monster extends Moving {
  
  private boolean hasKey;
  
  public Monster(){
    Table.diceRoll.genRandStartPos();
    super.setPositionX(Table.diceRoll.getPosX());
    super.setPositionY(Table.diceRoll.getPosY());
    super.setSourceIMG("skeleton.png");
    super.setMaxHP(2 * level * Table.diceRoll.dice());
    super.setDefendP(level/2 * Table.diceRoll.dice());
    super.setStrikeP(level * Table.diceRoll.dice());
  }
}
