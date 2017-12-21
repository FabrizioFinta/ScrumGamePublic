class Boss extends Monster {
  Boss(){
    super();
    setSourceIMG("boss.png");
    setMaxHP(2 * level * Table.randomNumber.dice() + Table.randomNumber.dice());
    setCurrentHP(getMaxHP());
    setDefendP(level/2 * Table.randomNumber.dice() + Table.randomNumber.dice()/2);
    setStrikeP(level * Table.randomNumber.dice() + level);
  }
}
