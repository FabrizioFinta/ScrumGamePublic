import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass extends JComponent implements KeyListener {
  
  private final int SIZE = 720;
  private final int maxFieldInRow = SIZE / 72;
  private final String MAP_LAYOUT_SOURCE = "field.txt";
  
  int heroX;
  int heroY;
  
  public TestClass() {
    heroX = 0;
    heroY = 0;
    
    // set the size of your draw board
    setPreferredSize(new Dimension(SIZE, SIZE));
    setVisible(true);
  }
  
  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    // here you have a 720x720 canvas
    // you can create and draw an image using the class below e.g.
    drawMap(graphics);
    PositionedImage hero = new PositionedImage("hero-down.png", heroX, heroY);
    hero.draw(graphics);
  }
  
  public void drawMap(Graphics graphics) {
    Map map = new Map(MAP_LAYOUT_SOURCE);
    for (int index = 0; index < maxFieldInRow; index++) {
      for (int subIndex = 0; subIndex < maxFieldInRow; subIndex++) {
        map.getFieldList()[index][subIndex].drawField(graphics);
      }
    }
  }
  
  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("RPG Game");
    TestClass board = new TestClass();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener
  }
  
  public int getMaxFieldInRow() {
    return maxFieldInRow;
  }
  
  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {
  
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
  
  }
  
  // But actually we can use just this one for our goals here
  @Override
  public void keyReleased(KeyEvent e) {
    // When the up or down keys hit, we change the position of our box
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      heroY -= 72;
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      heroY += 72;
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      heroX -= 72;
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      heroX += 72;
    }
    // and redraw to have a new picture with the new coordinates
    repaint();
  }
}