package UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import GameOfLife.Board;
import GameOfLife.Cell;

/**
 * Basic Unit Tests
 * 
 * @author Artur
 * 
 */
public class BasicUnitTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldCellExistWhenWasAdded() {
		Board b = new Board();

		b.addCell(1, 1);

		assertTrue(b.isCellExist(1, 1));
	}

	@Test
	public void shouldCellNoneExistWhenWasRemoved() {
		Board b = new Board();

		b.addCell(1, 1);
		b.removeCell(1, 1);

		assertFalse(b.isCellExist(1, 1));
	}

	@Test
	public void shouldOneCellExistWhenAddedTwoTimesTheSameCell() {
		Board b = new Board();

		b.addCell(1, 1);
		b.addCell(1, 1);

		assertEquals(1, b.countCells());
	}

	@Test
	public void shouldCellDieWhenIsLonely() {
		Board b = new Board();

		b.addCell(1, 1);
		b.nextState();

		assertFalse(b.isCellExist(1, 1));
	}

	@Test
	public void shouldLivingCellStillLiveWhenHasTwoOrThreeNeighbours() {
		// test for two neighbours
		Board b = new Board();

		b.addCell(2, 2);
		b.addCell(3, 2);
		b.addCell(2, 3);
		b.nextState();

		assertTrue(b.isCellExist(2, 2));

		// test for three neighbours
		b = new Board();

		b.addCell(2, 2);
		b.addCell(3, 2);
		b.addCell(2, 3);
		b.addCell(3, 3);
		b.nextState();

		assertTrue(b.isCellExist(2, 2));
	}

	@Test
	public void shouldLivingCellDieWhenHasLessThanTwoNeighbours() {
		Board b = new Board();

		b.addCell(2, 2);
		b.addCell(3, 2);
		b.nextState();

		assertFalse(b.isCellExist(2, 2));
	}

	@Test
	public void shouldLivingCellDieWhenHasMoreThanThreeNeighbours() {
		Board b = new Board();

		b.addCell(2, 2);
		b.addCell(3, 2);
		b.addCell(2, 3);
		b.addCell(3, 3);
		b.addCell(1, 3);
		b.nextState();

		assertFalse(b.isCellExist(2, 2));
	}

	@Test
	public void countCellNeighbours() {
		Board b = new Board();

		b.addCell(2, 2);
		b.addCell(3, 2);

		assertEquals(2, b.countNeighbours(3, 3));
	}

	@Test
	public void countCells() {
		Board b = new Board();

		b.addCell(-1, 34);
		b.addCell(2, 2);
		b.addCell(3, 2);
		b.addCell(23, -2);

		assertEquals(4, b.countCells());
	}

	@Test
	public void shouldCellsBeEqualWhenHaveTheSameCoordinates() {
		Cell c1 = new Cell(1, 2);
		Cell c2 = new Cell(1, 2);

		assertEquals(c1, c2);
	}

	@Test
	public void shouldCellsBeNotEqualWhenHaveDifferentCoordinates() {
		Cell c1 = new Cell(1, 2);
		Cell c2 = new Cell(1, 3);

		assertNotSame(c1, c2);
	}

}
