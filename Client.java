

/**
 * @since 2014-06-12
 * @version 3.5.0
 * @author C.J. Caba
 */

/**
 * <strong> PONG: </strong> 
 * <li> Inspired from one of the oldest video games in history </li>
 * <li> Two player game, where each player moves a paddle up and down. </li>
 * <li> The first player to reach 7 points wins. </li>
 */

// Start "Client" class;
public class Client
{
  // Start main()
  public static void main(String[] args)
  {
    new GUI();
    startGame();
  } // end main()

  public static void startGame()
  {
    Thread ballThread = new Thread(GUI.ball);
    Thread p1 = new Thread(GUI.p1);
    Thread p2 = new Thread(GUI.p2);

    ballThread.start();
    p1.start();
    p2.start();
  }

} // end class
