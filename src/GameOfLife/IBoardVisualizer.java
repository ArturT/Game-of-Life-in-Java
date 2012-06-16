package GameOfLife;

/**
 * Interface to visual implementation of game.
 * 
 * @author Artur
 * 
 */
public interface IBoardVisualizer {

	/**
	 * Display current state of board on the screen.
	 */
	public void displayCurrentStateOfBoard();

	/**
	 * Play game in infinity loop
	 */
	public void playGame();

}
