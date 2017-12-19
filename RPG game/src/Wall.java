public class Wall extends NotMoving {
  public Wall(int coordX, int coordY){
    super.setPositionX(coordX);
    super.setPositionY(coordY);
    super.setSourceIMG("wall.png");
  }
}
