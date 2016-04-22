

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * <strong> "PADDLETWO" </strong> 
 * <li> Creates the second paddle (Player two) </li>
 */

public class PaddleTwo extends Paddle
{
  PaddleTwo(int x, int y, int a)
  {
    xPos = x;
    yPos = y;
    paddle = new Rectangle(xPos, yPos, PADDLE_WIDTH, PADDLE_HEIGHT);
  }

  @Override
  protected void drawPaddle(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
  }
  
  /**
   * @param 
   *          e : Key pressed event
   * @param 
   *          a : Identifier
   */

  protected void keyPressed(KeyEvent e, int a)
  {
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      setYDir(-2);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      setYDir(2);
    }
  }
  
  /**
   * @param 
   *          e : Key released event
   * @param 
   *          a : Identifier
   */
  
  protected void keyReleased(KeyEvent e, int a)
  {
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      setYDir(0);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      setYDir(0);
    }
  }

  // UNUSED ABSTRACT METHODS
  protected void keyPressed(KeyEvent e, char ID)
  {
  }
  protected void keyReleased(KeyEvent e, char ID)
  {
  }

} // end class
