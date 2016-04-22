

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * <strong> "GUI" </strong> 
 * <li>Creates all the graphic user interface</li> 
 * <li>Double buffering implemented in the system</li>
 */

// Start "GUI" class; extends JFrame
public class GUI extends JFrame
{
  private static final long serialVersionUID = 1L;

  /**
   * Frame's final integer sizes: <li>WIDTH : static final</li> <li>HEIGHT :
   * static final</li>
   */
  private static final int WIDTH = 1200, HEIGHT = 700;

  // Double buffering Image and Graphics
  private Image dbIMG;
  private Graphics dbg;

  // Accessed by "Client" class for Thread; static object; Ball object
  static Ball ball = new Ball(WIDTH, HEIGHT);

  // Accessed by "Client" class for Thread; static objects; Paddle objects
  static PaddleOne p1 = new PaddleOne(20, 320, 'a');
  static PaddleTwo p2 = new PaddleTwo(1165, 320, 1);

  /**
   * GUI() constructor: <li>All attributes of JFrame are listed.</li> 
   * <li>"addKeyListener()" added to use keyboard for user input.</li>
   */
  
  GUI()
  {
    setTitle("PONG");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setResizable(false);
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setFocusable(true);

    this.setBackground(Color.BLACK);
    this.addKeyListener(new KeyFinder());
  }

  // Double Buffering Images
  public void paint(Graphics g)
  {
    dbIMG = createImage(getWidth(), getHeight());
    dbg = dbIMG.getGraphics();
    build(dbg);
    g.drawImage(dbIMG, 0, 0, this);
  }

  // Building the buffered images
  private void build(Graphics g)
  {
    // Builds all images
    ball.drawBall(g);
    p1.drawPaddle(g);
    p2.drawPaddle(g);

    // Score System
    Font font = new Font("Consolas", Font.BOLD, 40);
    g.setFont(font);
    g.drawString(Integer.toString(ball.scoreP1), 529, 65);
    g.drawString(Integer.toString(ball.scoreP2), 651, 65);

    // Draws the 2D Dashed Line
    Graphics2D g2 = (Graphics2D) g;
    float dash1[] = { 7.0f };
    BasicStroke dashed = new BasicStroke(5.0f, BasicStroke.JOIN_MITER,
        BasicStroke.JOIN_MITER, 10.0f, dash1, 3.0f);
    g2.setStroke(dashed);
    g2.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

    repaint();
  }

  // "KeyFinder" class for finding all keyboard presses
  private class KeyFinder extends KeyAdapter
  {
    public void keyPressed(KeyEvent e)
    {
      p1.keyPressed(e, 'a');
      p2.keyPressed(e, 1);
    }

    public void keyReleased(KeyEvent e)
    {
      p1.keyReleased(e, 'a');
      p2.keyReleased(e, 1);
    }
  } // end "KeyFinder" class

} // end class
