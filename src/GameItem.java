import java.util.*;

/**
 * @author IvanLopez, KarenBamnolker, DorothyLuu, EdgarBerber 
 */
public class GameItem {

	private int startRow, startCol;
	private Space start;
	private int length;
	private GameItemType type;

	/**
	 * @param row
	 * @param col
	 * @param length
	 */
	GameItem(int row, int col, int l, GameItemType type) {
		this.startRow = row;
		this.startCol = col;
		this.setType(type);
		this.start = new Space(row, col);
		this.length = l;
	}

	/**
	 * @return start
	 */
	public Space getStart() {
		return start;
	}

	/**
	 * @param start
	 */
	public void setStart(Space start) {
		this.start = start;
	}

	/**
	 * @return startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return startCol
	 */
	public int getStartCol() {
		return startCol;
	}

	/**
	 * @param startCol
	 */
	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	/**
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	public GameItemType getType() {
		return type;
	}

	public void setType(GameItemType type) {
		this.type = type;
	}

	public Space[] spacesOccupied() {
		Space[] spacesArr = new Space[this.length];
		// loop was causing trouble before
		for (int i = 0; i < this.length; i++) {
			spacesArr[i] = new Space(start.getRow(), start.getCol() + i);
		}
		return spacesArr;
	}

	public Space[] spacesOccupiedOnTrail(int numSpaces) {
		Space[] spacesArr = new Space[this.length];

		for (int i = 0; i < this.length; i++) {
			spacesArr[i] = new Space(start.getRow(), start.getCol() + (numSpaces + i));
		}
		return spacesArr;
	}

	/**
	 * Method to move left and right
	 * 
	 * @param numSpaces
	 * @param m
	 */
	public void moveHorizontal(int numSpaces, String m) {
		if (m == "left") {
			this.start = new Space(start.getRow(), start.getCol() - numSpaces);
		}

		else {
			this.start = new Space(start.getRow(), start.getCol() + numSpaces);
		}
	}

	/**
	 * Method to move up and down
	 * 
	 * @param numSpaces
	 * @param m
	 */
	public void moveVertical(int numSpaces, String m) {
		if (m == "up") {
			this.start = new Space(start.getRow() - numSpaces, start.getCol());
		}

		else {
			this.start = new Space(start.getRow() + numSpaces, start.getCol());
		}
	}
}
