package GameOfLife;

/**
 * Structure of cell. Contains coordinates X and Y.
 * @author Artur
 *
 */
public final class Cell {
	public int X;
	public int Y;
	
	public Cell(int x, int y)
	{
		this.X = x;
		this.Y = y;
	}
	
	public boolean equals(Cell obj)
	{
		if(this == obj)
			return true;
		
		if(this.X == obj.X && this.Y == obj.Y)
			return true;
		else
			return false;				
	}
}