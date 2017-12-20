class Boss extends Monster {
  Boss(){
    super();
    setSourceIMG("boss.png");
    setMaxHP(2 * level * Table.diceRoll.dice() + Table.diceRoll.dice());
    setDefendP(level/2 * Table.diceRoll.dice() + Table.diceRoll.dice()/2);
    setStrikeP(level * Table.diceRoll.dice() + level);
  }
}
