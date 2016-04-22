

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * <strong> "PADDLEONE" </strong> 
 * <li> Creates the first paddle (Player one) </li>
 */

public class PaddleOne extends Paddle
{
  PaddleOne(int x, int y, char ID)
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
   *          ID : Distinguisher
   */
  
  protected void keyPressed(KeyEvent e, char ID)
  {
    if (e.getKeyCode() == KeyEvent.VK_W)
    {
      setYDir(-2);
    }
    if (e.getKeyCode() == KeyEvent.VK_S)
    {
      setYDir(2);
    }
  }
  
  /**
   * @param 
   *          e : Key released event
   * @param 
   *          ID : Distinguisher
   */

  protected void keyReleased(KeyEvent e, char ID)
  {
    if (e.getKeyCode() == KeyEvent.VK_W)
    {
      setYDir(0);
    }
    if (e.getKeyCode() == KeyEvent.VK_S)
    {
      setYDir(0);
    }
  }

  // UNUSED ABSTRACT METHODS
  protected void keyPressed(KeyEvent e, int x)
  {
  }
  protected void keyReleased(KeyEvent e, int x)
  {
  }

} // end class
