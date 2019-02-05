/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class Space {
	
	private int rows;
	private int columns;
	

	public Space(int row, int col) {
		rows = row;
		columns = col;
	}
	
	public int getRow() {
		return rows;
	}
	
	public int getCol() {
		return columns;
	}
	
	public void setRow(int row) {
		this.rows = row;
	}

	public void setCol(int col) {
		this.columns = col;
		
	}
	
	public static void main(String[] args) {
		Space one = new Space(3, 4);
		Space two = new Space(1, 6);
		two.setRow(two.getRow()+1);
		two.setCol(two.getCol()-1);
		System.out.println("one r: " + one.getRow() + ", c: " + one.getCol());
		System.out.println("two r: " + two.getRow() + ", c: " + two.getCol());
	}
	
}