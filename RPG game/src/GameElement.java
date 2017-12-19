import java.awt.*;

public class GameElement {
  
  private static final int ELEMENT_SIZE = 72;
  private int positionX;
  private int positionY;
  private String sourceIMG;
  
  public GameElement() {
  }
  
  public void draw(Graphics graphics){
    PositionedImage element;
    element = new PositionedImage(sourceIMG, positionX*ELEMENT_SIZE, positionY*ELEMENT_SIZE);
    element.draw(graphics);
  }
  
  public void setSourceIMG(String sourceIMG){
    this.sourceIMG = sourceIMG;
  }
  
  public int getPositionX() {
    return positionX;
  }
  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }
  
  public int getPositionY() {
    return positionY;
  }
  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
  
}
