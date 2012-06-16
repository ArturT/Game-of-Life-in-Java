package GameOfLife;

import java.util.*;

/**
 * Board. Place where game happens.
 * 
 * @author Artur
 * 
 */
public class Board {

	/**
	 * Current state of cells on board. A HashSet is a collection that contains
	 * no duplicate elements.
	 */
	protected Set<Cell> _currentState = new HashSet<Cell>();

	/**
	 * Temporary HashSet used to checking if new cell should be born.
	 */
	private Set<Cell> _tmpCheckedCell = new HashSet<Cell>();

	/**
	 * Temporary queue used to collect all cells which will be born in next
	 * state. LinkedList use FIFO.
	 */
	private Queue<Cell> _nextStateGiveLife = new LinkedList<Cell>();

	/**
	 * Temporary queue used to collect all cells which will be kill in next
	 * state.
	 */
	private Queue<Cell> _nextStateKillLife = new LinkedList<Cell>();

	/**
	 * Temporary cell used in many situations.
	 */
	private Cell tmpCell = new Cell(0, 0);

	/**
	 * Counter of method run at one stage.
	 */
	private int _counterMethod_giveLifeToNeighboursIfPossible;

	public int getCounterMethod_giveLifeToNeighboursIfPossible() {
		return _counterMethod_giveLifeToNeighboursIfPossible;
	}

	/**
	 * Constructor.
	 */
	public Board() {
		_counterMethod_giveLifeToNeighboursIfPossible = 0;
	}

	/**
	 * Add new cell to board if it isn't there.
	 * 
	 * @param x
	 * @param y
	 */
	public void addCell(int x, int y) {
		_currentState.add(new Cell(x, y));
	}

	/**
	 * Remove cell from board.
	 * 
	 * @param x
	 * @param y
	 */
	public void removeCell(int x, int y) {
		_currentState.remove(new Cell(x, y));
	}

	/**
	 * Check is cell exist on board.
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isCellExist(int x, int y) {
		return _currentState.contains(new Cell(x, y));
	}

	/**
	 * Return current amount of cells on board.
	 * 
	 * @return int
	 */
	public int countCells() {
		return _currentState.size();
	}

	/**
	 * Calculate next state of game and set it as current state.
	 */
	public void nextState() {
		// set to zero every time when we run NextState method
		_counterMethod_giveLifeToNeighboursIfPossible = 0;

		int tmpNeighbours = 0;
		for (Cell cell : _currentState) {
			tmpNeighbours = countNeighbours(cell.x, cell.y);
			if (tmpNeighbours < 2 || tmpNeighbours > 3)
				// add cell to kill queue
				addCellToNextStateKillLife(cell);

			// checking if dead neighbours can be born
			giveLifeToNeighboursIfPossible(cell.x, cell.y);

			// clear temp HashSet used in above method
			_tmpCheckedCell.clear();
		}

		// kill cells
		while (_nextStateKillLife.size() > 0) {
			// Retrieves and removes the head of this queue, or returns null if
			// this queue is empty.
			tmpCell = _nextStateKillLife.poll();
			removeCell(tmpCell.x, tmpCell.y);
		}

		// born cells
		while (_nextStateGiveLife.size() > 0) {
			tmpCell = _nextStateGiveLife.poll();
			addCell(tmpCell.x, tmpCell.y);
		}
	}

	/**
	 * Check if dead neighbours can be born. Only dead cells which had 3 living
	 * neighbours can be born.
	 * 
	 * @param x
	 * @param y
	 */
	private void giveLifeToNeighboursIfPossible(int x, int y) {
		_counterMethod_giveLifeToNeighboursIfPossible++;

		// 1,1 2,1 3,1
		// 1,2 2,2 3,2
		// 1,3 2,3 3,3
		int tmpX = 0;
		int tmpY = 0;

		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				tmpX = x - 1;
				tmpY = y - 1;
				break;
			case 1:
				tmpX = x;
				tmpY = y - 1;
				break;
			case 2:
				tmpX = x + 1;
				tmpY = y - 1;
				break;
			case 3:
				tmpX = x - 1;
				tmpY = y;
				break;
			case 4:
				tmpX = x + 1;
				tmpY = y;
				break;
			case 5:
				tmpX = x - 1;
				tmpY = y + 1;
				break;
			case 6:
				tmpX = x;
				tmpY = y + 1;
				break;
			case 7:
				tmpX = x + 1;
				tmpY = y + 1;
				break;
			}

			// checking is cell already checked and negative of cell because
			// checking if only dead cell can be born
			if (!_tmpCheckedCell.contains(new Cell(tmpX, tmpY)) && !isCellExist(tmpX, tmpY)	&& countNeighbours(tmpX, tmpY) == 3) {
				// born dead cell
				addCellToNextStateGiveLife(new Cell(tmpX, tmpY));
			}

			// mark tmpX tmpY as cell that we already checked
			_tmpCheckedCell.add(new Cell(tmpX, tmpY));
		}
	}

	/**
	 * Count neighbours of cell.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int countNeighbours(int x, int y) {
		// 1,1 2,1 3,1
		// 1,2 2,2 3,2
		// 1,3 2,3 3,3
		int neighbours = 0;

		if (isCellExist(x - 1, y - 1))
			neighbours++;

		if (isCellExist(x, y - 1))
			neighbours++;

		if (isCellExist(x + 1, y - 1))
			neighbours++;

		if (isCellExist(x - 1, y))
			neighbours++;

		if (isCellExist(x + 1, y))
			neighbours++;

		if (isCellExist(x - 1, y + 1))
			neighbours++;

		if (isCellExist(x, y + 1))
			neighbours++;

		if (isCellExist(x + 1, y + 1))
			neighbours++;

		return neighbours;
	}

	/**
	 * Adding cell to cells queue. This cells will be kill in next state of
	 * game.
	 * 
	 * @param cell
	 */
	private void addCellToNextStateKillLife(Cell cell) {
		// if not contain cell then add cell to queue
		if (!_nextStateKillLife.contains(new Cell(cell.x, cell.y)))
			_nextStateKillLife.offer(cell); // adding
	}

	/**
	 * Adding cell to cells queue. This cells will be born in next state of
	 * game.
	 * 
	 * @param cell
	 */
	private void addCellToNextStateGiveLife(Cell cell) {
		// if not contain cell then add cell to queue
		if (!_nextStateGiveLife.contains(new Cell(cell.x, cell.y)))
			_nextStateGiveLife.offer(cell);
	}

}
