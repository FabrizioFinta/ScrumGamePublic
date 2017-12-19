import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestClass extends JComponent implements KeyListener {
  
  private final int SIZE = 720;
  private final int maxFieldInRow = SIZE / 72;
  private final String MAP_LAYOUT_SOURCE = "field.txt";
  GameDisplay display = new GameDisplay(MAP_LAYOUT_SOURCE, maxFieldInRow);
  Actor hero = new Actor();
  
  public TestClass() {
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
    PositionedImage temp = new PositionedImage("hero-down.png",hero.getPositionX(), hero.getPositionY());
    temp.draw(graphics);
  }
  
  public void drawMap(Graphics graphics) {
    for (int index = 0; index < maxFieldInRow; index++) {
      for (int subIndex = 0; subIndex < maxFieldInRow; subIndex++) {
        display.getFieldList()[index][subIndex].drawField(graphics);
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
      hero.moveUp();
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      hero.moveDown();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      hero.moveLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      hero.moveRight();
    }
    // and redraw to have a new picture with the new coordinates
    repaint();
  }
}