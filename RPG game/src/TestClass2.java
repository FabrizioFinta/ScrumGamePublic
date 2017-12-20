import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestClass2 extends JComponent implements KeyListener {
  
  private final int WINDOW_SIZE = 720;
  private static final String MAP_SOURCE = "field.txt";
  private static Table table = new Table();
  
  private TestClass2() {
    setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    setVisible(true);
  }
  
  @Override
  public void paint(Graphics graphics) { //Here is the motor of the graphics
    super.paint(graphics);
    table.drawTable(graphics);
  }
  
  public static void main(String[] args) {
    table.fill(MAP_SOURCE);
    JFrame frame = new JFrame("RPG Game");
    TestClass2 board = new TestClass2();
    frame.add(board);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    frame.addKeyListener(board);
  }
  
  @Override
  public void keyTyped(KeyEvent e) {
  
  }
  @Override
  public void keyPressed(KeyEvent e) {
  
  }
  @Override
  public void keyReleased(KeyEvent e) {
    table.checkKeyEvent(e);
    repaint();
  }
}