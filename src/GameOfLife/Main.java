package GameOfLife;

import java.util.Random;

/**
 * Main class of Game of Life.
 * 
 * @author Artur
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// new object of console game of life
		BoardConsoleVisualizer b = new BoardConsoleVisualizer(5, 250, false);

		// set your own size of board in constructor or override it here
		b.areaSize = 7;

		// Set cells coordinates here

		// toad vertical
		// http://en.wikipedia.org/wiki/File:Game_of_life_toad.gif
		b.addCell(1, 1);
		b.addCell(1, 2);
		b.addCell(1, 3);
		b.addCell(2, 2);
		b.addCell(2, 3);
		b.addCell(2, 4);

		// tub
		// http://pl.wikipedia.org/wiki/Plik:JdlV_bloc_4.9.gif
		b.addCell(-5, 0);
		b.addCell(-5, 2);
		b.addCell(-6, 1);
		b.addCell(-4, 1);

		// glider
		// http://en.wikipedia.org/wiki/File:Game_of_life_animated_glider.gif
		b.addCell(-18, -5);
		b.addCell(-18, -4);
		b.addCell(-18, -3);
		b.addCell(-19, -3);
		b.addCell(-20, -4);

		// blinker
		// http://en.wikipedia.org/wiki/File:Game_of_life_blinker.gif
		b.addCell(10, 0);
		b.addCell(11, 0);
		b.addCell(12, 0);

		// blinker
		b.addCell(-10, 5);
		b.addCell(-10, 6);
		b.addCell(-10, 7);

		// some random cells
		Random random = new Random();
		int randomX, randomY;

		// 100 random cells
		for (int i = 0; i < 100; i++) {
			// random.nextInt(max - min + 1) + min;
			randomX = random.nextInt(20 - -20 + 1) + -20;
			randomY = random.nextInt(20 - -20 + 1) + -20;

			b.addCell(randomX, randomY);
		}

		/* START RunGameExample1 */
		// // display board
		// b.displayCurrentStateOfBoard();
		//
		// // display coordinates of cells
		// b.printCurrentStateCoordinates();
		//
		// // calculate next state of game
		// b.nextState();
		//
		// // and print board :)
		// b.displayCurrentStateOfBoard();
		//
		// // pause befor start game
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		/* END RunGameExample2 */

		/* START RunGameExample2 */
		// run game in infinity loop
		b.playGame();
		/* END RunGameExample2 */

		/* START RunGameExample3 */
		// shows amount of cells and attempts to give life at step
		// for(int i = 0; i < 100; i++)
		// {
		// b.nextState();
		// System.out.println("Step " + i + ", Cells on board: " +
		// b.countCells() + ", Attempts to give life: " +
		// b.getCounterMethod_giveLifeToNeighboursIfPossible());
		// }
		/* END RunGameExample3 */
	}

}
