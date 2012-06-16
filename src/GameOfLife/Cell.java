package GameOfLife;

/**
 * Structure of cell. Contains coordinates x and y.
 * 
 * @author Artur
 * 
 */
public final class Cell {

	public int x;
	public int y;

	/**
	 * Constructor.
	 * 
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		final Cell cell = (Cell) obj;

		if (this.x == cell.x && this.y == cell.y)
			return true;
		else
			return false;
	}

}