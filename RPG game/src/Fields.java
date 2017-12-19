import java.awt.*;

public class Fields extends GameDisplay{
  
  private final static String WALL_SOURCE = "wall.png";
  private final static String FLOOR_SOURCE = "floor.png";
  private boolean isWall;
  private int startPositionX;
  private int startPositionY;
  
  Fields(int startPositionX, int startPositionY, boolean isWall) {
    this.startPositionX = startPositionX;
    this.startPositionY = startPositionY;
    this.isWall = isWall;
}
  
  public void setStartPositionX(int startPositionX) {
    this.startPositionX += startPositionX;
  }
  public void setStartPositionY(int startPositionY) {
    this.startPositionY += startPositionY;
  }
  
  public boolean getIsWall(){
    return this.isWall;
  }
  
  public void setWall(){
    this.isWall = true;
  }
  
  public void drawField(Graphics graphics){
    PositionedImage aField;
    if (isWall){
      aField = new PositionedImage(WALL_SOURCE, startPositionX, startPositionY);
      aField.draw(graphics);
    } else {
      aField = new PositionedImage(FLOOR_SOURCE, startPositionX, startPositionY);
      aField.draw(graphics);
    }
  }
}
