import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestClass2 extends JComponent implements KeyListener {
  
  private final int WINDOW_SIZE = 720;
  private static final String MAP_SOURCE = "field.txt";
  private static Table table = new Table();
  private int status = 0;
  
  private TestClass2() {
    setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    setVisible(true);
  }
  
  @Override
  public void paint(Graphics graphics) { //Here is the motor of the graphics
    super.paint(graphics);
    if (table.isNotGameOver){
    table.drawTable(graphics);
    table.drawStats(graphics);
    } else {
      table.drawGameOver(graphics);
    }
  }
  
  public static void main(String[] args) {
    table.fillMap(MAP_SOURCE);
    table.fillMapWithCharacters();
    makeGraphicsInterface();
    }
  
  private static void makeGraphicsInterface() {
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
    if (table.isNotGameOver) {
      table.checkKeyEvent(e);
      repaint();
    } else {
        checkDecision(e);
    }
    
  }
  
  private void checkDecision(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER){
      table.reset();
      table.fillMap(MAP_SOURCE);
      table.fillMapWithCharacters(); //not working perfect still get some elements in the memory
      repaint();
    }
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
      System.exit(1);
    }
  }
}