import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class Level {
	public static final int ROWS = 13;
	public static final int COLS = 14;
	private Board b;
	private int lives;
	private ArrayList<Space> bottles = new ArrayList<Space>();
	private ArrayList<Space> coins= new ArrayList<Space>();
	private int rowCounter;
	private Space exitTile;
	private int lastRow;
	private int lastCol;
	private Space LifeUp;
	
	/* For testing only of this game */
	public boolean ignoreLevelCheckFlag;

	/**
	 * Constructor
	 * 
	 * @param chosenLevel
	 */
	public Level(String chosenLevel) {
		ignoreLevelCheckFlag = false;
		b = new Board(ROWS, COLS);
		this.setLives(3);
		ReadFile(chosenLevel);
		this.rowCounter = 0;
	}

	/*********************************************
	 * FILE READER AND BOARD INSERTING FUNCTIONS *
	 *********************************************/
	/**
	 * File reader
	 * 
	 * @param chosenLevel
	 */
	public void ReadFile(String chosenLevel) {
		String line;
		try {
			FileReader fr = new FileReader(chosenLevel + ".txt");
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				parseLine(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("cannot find file");
		}
	}

	private void parseLine(String line) {
		char[] charArray = line.toCharArray();
		int charCount = 0;
		for (int i = 0; i < charArray.length; i++) {

			if (charArray[i] == 'N' || charArray[i] == 'C' || charArray[i] == 'b' || charArray[i] == 'U'
					|| charArray[i] == 'E') {
				this.lastCol = charCount;
				this.lastRow = rowCounter;
				spaceArrayAdder(charArray[i]);
				charCount++;

			} else {
				b.addGameItem(rowCounter, charCount, charToLength(charArray[i]), charToType(charArray[i]));
				this.lastCol = charCount;
				this.lastRow = rowCounter;
				charCount += charToLength(charArray[i]);
			}
		}

		rowCounter++;
	}

	private void spaceArrayAdder(char c) {

		switch(c) {
		case 'C':
			Space coin = new Space(this.lastRow, this.lastCol);
			coins.add(coin);
			break;
		case 'b':
			Space bottle = new Space(this.lastRow, this.lastCol);
			bottles.add(bottle);
			break;
		case 'U':
			setLifeUp(new Space(this.lastRow, this.lastCol));
			break;
		case 'E':
			exitTile = new Space(this.lastRow, this.lastCol);
			break;
		}
	}

	/******************
	 * HELPER METHODS *
	 ******************/
	private GameItemType charToType(char c) {

		switch (c) {
		case 'c':
			return GameItemType.CAR;
		case 'B':
			return GameItemType.BUSH;
		case 'Z':
			return GameItemType.BOOZY;
		case 'C':
			Space coin = new Space(this.lastRow, this.lastCol);
			coins.add(coin);
			return null;
		case 'b':
			Space bottle = new Space(this.lastRow, this.lastCol);
			bottles.add(bottle);
			return null;
		case 'U':
			setLifeUp(new Space(this.lastRow, this.lastCol));
			return null;
		case 'E':
			exitTile = new Space(this.lastRow, this.lastCol);
			return null;

		case 'N':
			return null;
		}
		return null;
	}

	private int charToLength(char c) {

		switch (c) {
		case 'c':
			return 2;
		case 'B':
			return 1;
		case 'Z':
			return 1;
		case 'C':
			return 0;
		case 'b':
			return 0;
		case 'U':
			return 0;
		case 'E':
			return 0;
		case 'N':
			return 0;
		}
		return 0;
	}

	/**
	 * Moves GameItem
	 * 
	 * @param space
	 * @param numSpaces
	 * @param m
	 * @return
	 */
	public boolean MoveSpaces(Space space, int numSpaces, String m) {
		return b.moveNumSpaces(space, numSpaces, m);
	}


	public boolean passedLevel(Space boozySpace) {
		if (this.exitTile == boozySpace) {
			return true;
		}
		return false;
	}

	public boolean isGameItemOnSpace(Space s) {
		boolean check = b.isGameItemOnSpace(s);
		return check;
	}

	/***********
	 * Getters *
	 ***********/

	public GameItem[][] getGameItemBoard() {
		return b.getGameItemBoard();
	}

	public int getColumns() {
		return b.getNumCols();
	}

	public int getRows() {
		return b.getNumRows();
	}

	public GameItem getGameItem(Space s) {
		return b.getGameItem(s);
	}

	public Space getExitTile() {
		return exitTile;
	}

	public int getLives() {
		return lives;
	}

	public ArrayList<Space> getBottles() {
		return bottles;
	}

	public ArrayList<Space> getCoins() {
		return coins;
	}

	public ArrayList<GameItem> getGameItemOnLevel() {
		return b.getGameItemOnBoard();
	}

	public Space getLifeUp() {
		return LifeUp;
	}

	/***********
	 * SETTERS *
	 ***********/

	public void setExitTile(Space exitTile) {
		this.exitTile = exitTile;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setBottles(ArrayList<Space> bottles) {
		this.bottles = bottles;
	}

	public void setCoins(ArrayList<Space> coins) {
		this.coins = coins;
	}

	public void setLifeUp(Space lifeUp) {
		LifeUp = lifeUp;
	}

	/***********************
	 * CONSOLE BOARD MAKER *
	 ***********************/
	/**
	 * generates the string representation of the level, including the row and
	 * column headers to make it look like a table
	 * 
	 * @return the string representation
	 */
	public String toString() {
		String result = generateColHeader(getColumns());
		result += addRowHeader(b.toString());
		return result;
	}

	/**
	 * This method will add the row information needed to the board and is used by
	 * the toString method
	 * 
	 * @param origBoard
	 *            the original board without the header information
	 * @return the board with the header information
	 */
	private String addRowHeader(String origBoard) {
		String result = "";
		String[] elems = origBoard.split("\n");
		for (int i = 0; i < elems.length; i++) {
			result += (char) ('A' + i) + "|" + elems[i] + "\n";
		}
		return result;
	}

	/**
	 * This one is responsible for making the row of column numbers at the top and
	 * is used by the toString method
	 * 
	 * @param cols
	 *            the number of columns in the board
	 * @return if the # of columns is five then it would return "12345\n-----\n"
	 */
	private String generateColHeader(int cols) {
		String result = "  ";
		for (int i = 1; i <= cols; i++) {
			result += i;
		}
		result += "\n  ";
		for (int i = 0; i < cols; i++) {
			result += "-";
		}
		result += "\n";
		return result;
	}

	/********
	 * MAIN *
	 ********/
	public static void main(String[] args) {

		Level level = new Level("level1");
		System.out.println(level.b);

	}

	public boolean ignoreLiveCheck() {
		return ignoreLevelCheckFlag;
	}
	
	public void setIgnoreLiveCheck() {
		ignoreLevelCheckFlag = true;
	}
}