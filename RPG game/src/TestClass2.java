import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestClass2 extends JComponent implements KeyListener {
  
  int testBoxX;
  int testBoxY;
  private final int SIZE = 720;
  private static final String MAP_SOURCE = "field.txt";
  private static Table table = new Table();
  
  public TestClass2() {
    
    // set the size of your draw board
    setPreferredSize(new Dimension(SIZE, SIZE));
    setVisible(true);
  }
  
  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    // here you have a 720x720 canvas
    // you can create and draw an image using the class below e.g.
    
    table.drawTable(graphics);
    
  }
  
  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    table.fill(MAP_SOURCE);
    JFrame frame = new JFrame("RPG Game");
    TestClass2 board = new TestClass2();
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
    table.moveHero(e);
    // and redraw to have a new picture with the new coordinates
    repaint();
  }
}