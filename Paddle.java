

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * <strong> "PADDLE" </strong> 
 * <li> Creates Paddle object </li>
 * <li> Abstract class </li>
 */

abstract class Paddle implements Runnable
{ 
  // x position, y position, y direction
  protected int xPos, yPos, yDir;
  
  // final sleep timer (milliseconds)
  private final int SLEEP = 2;

  // final bounds values (optimized)
  private final int TOP_BOUNDS = 30, BOTTOM_BOUNDS = 643;

  // final width and height of paddles 
  protected final int PADDLE_WIDTH = 15, PADDLE_HEIGHT = 65;
  
  // Rectangle paddle (to be drawn)
  protected Rectangle paddle;

  Paddle()
  {
    xPos = 0;
    yPos = 0;
    paddle = new Rectangle(0, 0, 0, 0);
  }

  protected void drawPaddle(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect(paddle.x, paddle.x, PADDLE_WIDTH, PADDLE_HEIGHT);
  }

  @Override
  public void run()
  {
    while (true)
    {
      move();
      thread(SLEEP);
    }
  }

  /**
   * Method "move()": to move the ball. Changes the x and y position of the
   * Rectangle "ball"
   */
  
  protected void move()
  {
    changeLoc();
    checkBounds();
  }

  /**
   * Method "thread(int speed)" for sleep time (speed) of paddle
   * 
   */

  protected void thread(int speed)
  {
    try
    {
      Thread.sleep(speed);
    } catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Collision system for ball and boundaries
   */

  protected void checkBounds()
  {
    if (paddle.y <= TOP_BOUNDS)
    {
      paddle.y = TOP_BOUNDS;
    }

    if (paddle.y >= BOTTOM_BOUNDS)
    {
      paddle.y = BOTTOM_BOUNDS;
    }
  }

  /**
   * @param y
   *          : an integer needed for movement, either -2 or 2
   */

  protected void setYDir(int y)
  {
    yDir = y;
  }

  /**
   * Changes the x and y coordinates of the ball
   */

  protected void changeLoc()
  {
    paddle.y += yDir;
  }

  // Abstract methods
  abstract protected void keyPressed(KeyEvent e, char ID);
  abstract protected void keyPressed(KeyEvent e, int x);
  abstract protected void keyReleased(KeyEvent e, char ID);
  abstract protected void keyReleased(KeyEvent e, int x);
  
} // end class
