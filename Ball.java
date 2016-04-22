

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 * <strong> "BALL" </strong> 
 * <li>Creates the ball object</li> 
 * <li>Randomizes and repositions ball</li>
 */
// Start "Ball" class; implements Runnable
public class Ball implements Runnable
{
  // x position, y position, x direction ,y direction, frame width, frame height
  private int xPos, yPos, xDir, yDir, width, height;

  // initial scores
  public int scoreP1 = 0, scoreP2 = 0;

  // final width and height of ball
  private static final int BALL_WIDTH = 14, BALL_HEIGHT = 14;

  // final sleep timer (milliseconds) and score
  private final int SLEEP = 3;
  private final int MAX_SCORE = 7;

  // Randomizer object
  private Random rand = new Random();

  // File (for sound)
  private File file;

  // Rectangle ball (to be drawn)
  private Rectangle ball;

  /**
   * <strong> Ball Constructor </strong>
   * 
   * @param width
   *          : width of the JFrame "GUI()"
   * @param height
   *          : height of the JFrame "GUI()"
   */

  Ball(int width, int height)
  {
    this.width = width;
    this.height = height;

    set();
    randomXY();
  }

  /**
   * Overrides "run()" from Runnable interface
   */

  @Override
  public void run()
  {
    while (true)
    {
      move();
      thread(SLEEP);
    } // end while
  }

  /**
   * @param x
   *          : an integer needed for movement, either -1 or 1
   */

  private void setX(int x)
  {
    xDir = x;
  }

  /**
   * @param y
   *          : an integer needed for movement, either -1 or 1
   */

  private void setY(int y)
  {
    yDir = y;
  }

  /**
   * @param db
   *          : Graphics "draw ball" object, draws the ball
   */

  void drawBall(Graphics db)
  {
    db.setColor(Color.WHITE);
    db.fillRect(ball.x, ball.y, ball.width, ball.height);
  }

  /**
   * Method "move()": to move the ball. Changes the x and y position of the
   * Rectangle "ball"
   */

  private void move()
  {
    checkHit();
    changeLoc();
    checkBounds();
  }

  /**
   * Method "thread(int speed)" for sleep time (speed) of ball
   * 
   */

  private void thread(int speed)
  {
    try
    {
      // Speed of the ball; smaller value, less sleep, faster movement
      Thread.sleep(speed);
    } catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Randomizes the ball's y direction and speed
   */
  private void randomY(int id)
  {
    if (id == 1)
    {
      int yRDir = rand.nextInt(2);
      
      if (yRDir == 0)
        setY(1);
      else if (yRDir == 1)
        setY(2);
    }
    
    else if (id == 2)
    {
      int yRDir = rand.nextInt(2);
      
      if (yRDir == 0)
        setY(-1);
      else if (yRDir == 1)
        setY(-2);
      
    }
    else
    {
      int yRDir = rand.nextInt(5);
      
      if (yRDir == 0)
        setY(0);
      else if (yRDir == 1)
        setY(1);
      else if (yRDir == 2)
        setY(-1);
      else if (yRDir == 3)
        setY(2);
      else
        setY(-2);
    }
      
  }
  
  /**
   * Randomizes the ball's x direction and speed
   */
  private void randomX(int id)
  { 
    if (id == 1)
    {
      int x = rand.nextInt(2);
      
      if (x == 0)
        setX(1);
      else
        setX(2);
    } else 
    {
      int x = rand.nextInt(2);
      
      if (x == 0)
        setX(-1);
      else
        setX(-2);
    }
  }

  /**
   * Collision system for paddle and ball objects "setX" sets the x coordinate's
   * direction
   */

  private void checkHit()
  {
    if (ball.intersects(GUI.p1.paddle))
    { 
      randomY(3);
      randomX(1);
      
      playSound(1);
    }

    if (ball.intersects(GUI.p2.paddle))
    {
      
      randomY(3);
      randomX(2);
      
      playSound(1);
    }
  }

  /**
   * Collision system for ball and boundaries All bounds are hard coded and have
   * been used in test Bounds were tested for optimal use
   */

  private void checkBounds()
  {
    /**
     * X coordinates are checked for scoring system. If any x coordinate of the
     * ball hits 0, score is added to player 2 (score(2)) If any x coordinate of
     * the ball hits [width - 15] (optimized), score is added to player 1
     * (score(1)) "set()" resets game to origins (x, y) -> (600, 0)
     */

    if (ball.x <= 0)
    {
      score(2);
      set();
    } // end if

    if (ball.x >= width - 15)
    {
      score(1);
      set();
    } // end if

    /**
     * Y coordinates are checked for hit detection.
     * 
     */

    if (ball.y <= 15)
    {
      randomY(1);
      playSound(3);
    } // end if

    if (ball.y >= height - 15)
    {
      randomY(2);
      playSound(3);
    } // end if
  }

  /**
   * Randomizer Randomizes a number between 0 and 1 if the random number is
   * zero, make it -1.
   */

  private void randomXY()
  {
    int r = rand.nextInt(2) + 1;
    
    if (r == 0)
    {
      setX(-1);
      setY(1);
    }
    
    else
    {
      setX(1);
      setY(1);
    }
  }

  /**
   * Scoring system; adds score respectively Plays the sound effect 
   * (2 for "score-sound.wav")
   * 
   * The last player that scored serves again
   * 
   * @param ID
   *          : checks which player to score; adds score respectively
   */

  private void score(int ID)
  {
    if (ID == 1)
    {
      scoreP1++;
      
      setX(-1);
      setY(+1);
      
      playSound(2);

      if (scoreP1 == MAX_SCORE)
      {
        playSound(2);
        JOptionPane.showMessageDialog(null, "Player One wins!", "GAME OVER",
            JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
      } // end if
    }

    else
    {
      scoreP2++;
      
      setX(+1);
      setY(+1);
      
      playSound(2);

      if (scoreP2 == MAX_SCORE)
      {
        playSound(2);
        JOptionPane.showMessageDialog(null, "Player Two wins!", "GAME OVER",
            JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
      } // end if

    } // end if-else

  }

  /**
   * Changes the x and y coordinates of the ball
   */

  private void changeLoc()
  {
    ball.x += xDir;
    ball.y += yDir;
  }

  /**
   * "set()" returns the ball's position to the original; Starts the game (in
   * the beginning) Resets the game (during a score is added)
   */

  private void set()
  {
    xPos = (width - BALL_WIDTH) / 2;
    yPos = 15;
    ball = new Rectangle(xPos, yPos, BALL_WIDTH, BALL_HEIGHT);
    thread(1000);
  }

  /**
   * @param x
   *          : Identifier; used as parameter for if-else if-else statements.
   *          Checks which sound is to be played BUG REPORT: Plays audio
   *          "paddle-sound.wav" in loop during "checkHit()"
   */

  public void playSound(int x)
  {
    try
    {
        AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource("/resources/paddle-sound.wav"));
        if (x == 1)
        	audio = AudioSystem.getAudioInputStream(getClass().getResource("/resources/paddle-sound.wav"));
          else if (x == 2)
        	  audio = AudioSystem.getAudioInputStream(getClass().getResource("/resources/score-sound.wav"));
          else
        	  audio = AudioSystem.getAudioInputStream(getClass().getResource("/resources/border-sound.wav"));

      Clip clip = AudioSystem.getClip();
      clip.open(audio);
      clip.start();
    } catch (Exception e)
    {
      System.out.println("Error with playing sound.");
    }
  }

} // end class
