class Boss extends Monster {
  Boss(){
    super();
    setSourceIMG("boss.png");
    setMaxHP(2 * level * Table.randomNumber.dice() + Table.randomNumber.dice());
    restoreHP();
    setDefendP(level/2 * Table.randomNumber.dice() + Table.randomNumber.dice()/2);
    setStrikeP(level * Table.randomNumber.dice() + level);
  }
  
  @Override
  void resurrect() {
    super.resurrect();
    setSourceIMG("boss.png");
  }
  
  @Override
  void levelUp() {
    super.levelUp();
    setMaxHP(getMaxHP() + Table.randomNumber.dice());
    setDefendP(getDefendP() + Table.randomNumber.dice()/2);
    setStrikeP(getStrikeP() + Table.getCurrentStage());
  }
}
