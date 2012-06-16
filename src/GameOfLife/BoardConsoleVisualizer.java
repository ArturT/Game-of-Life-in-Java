package GameOfLife;

import sun.applet.AppletIllegalArgumentException;

/**
 * Game implemented in console form.
 * 
 * @author Artur
 * 
 */
public class BoardConsoleVisualizer extends Board implements IBoardVisualizer {

	/**
	 * Size of board in vertical. Horizontal size is 4 times bigger than
	 * AreaSize.
	 */
	public int areaSize = 5;

	/**
	 * Milliseconds between next refresh of game state.
	 */
	public int timeSleep = 250;

	/**
	 * If true then write on console information about coordinates of all cells
	 * after each state of loop in PlayGame method.
	 */
	public boolean displayCurrentCoordinates = false;

	/**
	 * Default constructor.
	 */
	public BoardConsoleVisualizer() {
	}

	/**
	 * Constructor with settings.
	 * 
	 * @param areaSize
	 *            Integer.
	 * @param timeSleep
	 *            In miliseconds.
	 * @param displayCurrentCoordinates
	 *            boolean
	 */
	public BoardConsoleVisualizer(int areaSize, int timeSleep, boolean displayCurrentCoordinates) {
		this.areaSize = areaSize;

		if (timeSleep > 0)
			this.timeSleep = timeSleep;
		else
			throw new AppletIllegalArgumentException(
					"timeSleep must be bigger then zero.");

		this.displayCurrentCoordinates = displayCurrentCoordinates;
	}

	/**
	 * Write on console information about coordinates of all cells.
	 */
	public void printCurrentStateCoordinates() {
		System.out.println();

		for (Cell cell : _currentState) {
			System.out.println("X: " + cell.x + ", Y: " + cell.y);
		}

		System.out.println("Cells on board: " + _currentState.size());
	}

	/**
	 * Write on console all cells in visual way.
	 */
	@Override
	public void displayCurrentStateOfBoard() {
		System.out.println();

		for (int i = (int) -areaSize; i < areaSize * 2; i++) {
			for (int j = (int) -areaSize * 4; j < areaSize * 4; j++) {
				if (isCellExist(j, i)) {
					System.out.print("O");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		System.out.println("Cells on board: " + _currentState.size());
	}

	/**
	 * Play game in infinity loop
	 */
	@Override
	public void playGame() {
		// board after set all cells
		displayCurrentStateOfBoard();

		if (displayCurrentCoordinates)
			printCurrentStateCoordinates();

		try {
			Thread.sleep(timeSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			System.out.flush();

			nextState();
			displayCurrentStateOfBoard();

			if (displayCurrentCoordinates)
				printCurrentStateCoordinates();

			try {
				Thread.sleep(timeSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
