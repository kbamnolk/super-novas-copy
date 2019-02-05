import java.util.*;
import java.util.ArrayList;

/**
 * This represents the board. Really what it is going to do is just have an
 * array of the vehicles on the board, and it will be in charge of doing the
 * bounds type checking for doing any of the moves. It will also have a method
 * display board which will give back a string representation of the board
 * 
 * @authors Ivan Lopez, Karen Bamnolker, Edgar Berber, Dorothy Luu
 */

public class Board {
	private GameItem[][] board;
	private int numRows;
	private int numCols;
	private ArrayList<GameItem> list;

	/**
	 * Constructor for the board which sets up an empty board of size rows by
	 * columns Use the first array index as the rows and the second index as the
	 * columns
	 * 
	 * @param rows
	 *            number of rows on the board
	 * @param cols
	 *            number of columns on the board
	 */
	public Board(int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;
		this.board = new GameItem[numRows][numCols];
		this.list = new ArrayList<GameItem>(240);

	}

	public GameItem[][] getBoard() {
		return board;
	}

	/**
	 * @return number of columns the board has
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * @return number of rows the board has
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * Grabs the GameItem present on a particular space if any is there If a
	 * GameItem that occupies three spaces, the same GameItem pointer should be
	 * returned for all three spaces
	 * 
	 * @param s
	 *            the desired space where you want to look to see if a vehicle is
	 *            there
	 * @return a pointer to the Vehicle object present on that space, if no Vehicle
	 *         is present, null is returned
	 */
	public GameItem getGameItem(Space s) {
		return board[s.getRow()][s.getCol()];
	}

	/**
	 * adds a GameItem to the board. It would be good to do some checks for a legal
	 * placement here.
	 * 
	 * @param type
	 *            type of the GameItem
	 * @param startCol
	 *            column for for location of vehicle leftmost space
	 * @param numSpaces
	 *            number of spaces the GameItem occupies on the board
	 * @param startRow
	 *            row for location of vehicle's top
	 */
	/**
	 * @param type
	 * @param startCol
	 * @param numSpaces
	 * @param startRow
	 */
	public void addGameItem(int startRow, int startCol,int length, GameItemType type) {

		GameItem tempItem = new GameItem(startRow, startCol, length, type);
		if (tempItem.getType().toString() == "car") {

			Space[] spaceArr = tempItem.spacesOccupied();
			
			for (int j = 0; j < spaceArr.length ; ++j) {
				board[spaceArr[j].getRow()][spaceArr[j].getCol()] = tempItem;
				this.list.add(tempItem);
			}
		} else {
			board[startRow][startCol] = tempItem;
		}
	}

	// graphic->Level:GameItem isGameItemOnSpace(Space clicked)->
	public boolean isGameItemOnSpace(Space clicked) {
		if (board[clicked.getRow()][clicked.getCol()] != null) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<GameItem> getGameItemOnBoard() {
		return this.list;
	}

	/**
	 * @param start
	 * @param numSpaces
	 * @param m
	 * @return
	 */
	public boolean moveNumSpaces(Space start, int numSpaces, String m) {
		// use method to boundary check and if spaces are occupied by other vehicles
		GameItem itemToMove = getGameItem(start);
		Space[] tempArr = itemToMove.spacesOccupied();

		if (!(canMoveNumSpaces(start, numSpaces))) {
			return false;
		}

		for (int i = 0; i < tempArr.length; ++i) {
			board[tempArr[i].getRow()][tempArr[i].getCol()] = null;
		}
		// MOVES HORIZONTAL
		if (m == "left" || m == "right") {
			itemToMove.moveHorizontal(numSpaces, m);
		}
		// MOVES VERTICALLY
		if (m == "up" || m == "down") {
			itemToMove.moveVertical(numSpaces, m);
		}

		// sets new spaces
		tempArr = itemToMove.spacesOccupied();
		for (int i = 0; i < tempArr.length; ++i) {
			board[tempArr[i].getRow()][tempArr[i].getCol()] = itemToMove;
		}
		return true;
	}

	/**
	 * This method just checks to see if the item on a certain row/column can move a
	 * specific number of spaces, though it will not move the vehicle. You should
	 * use this when you wish to move or want to see if you can move a vehicle
	 * numSpaces without going out of bounds or hitting another vehicle
	 * 
	 * @param start
	 *            the starting row/column of the vehicle in question
	 * @param numSpaces
	 *            number of spaces to be moved by the vehicle (positive or negative)
	 * @return whether or not the move is possible
	 */
	public boolean canMoveNumSpaces(Space start, int numSpaces) {
		Space[] tempArray;
		GameItem tempItem;
		tempItem = getGameItem(start);

		if (tempItem == null) {
			return false;
		}
		tempArray = tempItem.spacesOccupiedOnTrail(numSpaces);

		for (Space moveTest : tempArray) {
			if (moveTest.getCol() < 0 || moveTest.getRow() < 0 || moveTest.getRow() >= numRows
					|| moveTest.getCol() >= numCols || (board[moveTest.getRow()][moveTest.getCol()] != null
							&& board[moveTest.getRow()][moveTest.getCol()] != tempItem)) {
				return false;
			}
		}
		return true;
	}

	// This method helps create a string version of the board
	// You do not need to call this at all, just let it be
	public String toString() {
		return BoardConverter.createString(this);
	}

	/*
	 * Testing methods down here for testing the board make sure you run the board
	 * and it works before you write the rest of the program!
	 */

	public static void main(String[] args) {
		Board b = new Board(10, 10);

		// adding Boozy in
		b.addGameItem(9, 4, 1,GameItemType.BOOZY);
		b.addGameItem(7, 1, 2,GameItemType.CAR);
		b.addGameItem(7, 5, 2,GameItemType.CAR);
		b.addGameItem(8, 3, 2,GameItemType.CAR);
		b.addGameItem(3, 1, 2,GameItemType.CAR);
		b.addGameItem(4, 5, 2,GameItemType.CAR);
		b.addGameItem(3, 7, 2,GameItemType.CAR);
		System.out.println(b);
		testCanMove(b);
		testMoving(b);
		System.out.println(b);

	}

	public static void testMoving(Board b) {
		System.out.println("Moving things up, down left, right");
		b.moveNumSpaces(new Space(9, 4), 3, "right");
		b.moveNumSpaces(new Space(8, 3), 2, "left");
		b.moveNumSpaces(new Space(7, 1), 2, "right");
		b.moveNumSpaces(new Space(9, 7), 1, "up");
		b.moveNumSpaces(new Space(8, 1), 1, "down");
	}

	public static void testCanMove(Board b) {
		System.out.println("Ok, now testing some moves...");
		System.out.println("These should all be true");
		System.out.println("Moving Boozy 2 spaces right: " + b.canMoveNumSpaces(new Space(9, 4), 2));
		System.out.println("Moving car3 to the right 2 spaces: " + b.canMoveNumSpaces(new Space(8, 3), 2));
		System.out.println("Moving car3 to the left 2 spaces:" + b.canMoveNumSpaces(new Space(8, 3), -2));
		System.out.println("Moving car1 to the right 1 space: " + b.canMoveNumSpaces(new Space(7, 1), 1));
		System.out.println("Moving car1 to the left 1 space: " + b.canMoveNumSpaces(new Space(7, 1), -1));

		System.out.println("\n");
		System.out.println("And these should all be false");

		System.out.println("Moving car1 to the left " + b.canMoveNumSpaces(new Space(7, 1), -2));
		System.out.println("Moving car1 to the right " + b.canMoveNumSpaces(new Space(7, 1), 3));

	}

	public GameItem[][] getGameItemBoard() {
		return board;
	}

}