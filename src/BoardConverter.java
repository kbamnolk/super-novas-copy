/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class BoardConverter {
	public static String createString(Board b) {
		char[][] board = new char[b.getNumRows()][b.getNumCols()];
		initBoard(board, b);
		populateBoard(board, b);
		return convertBoardToString(board, b);
	}

	private static void initBoard(char[][] board, Board b) {
		for(int row = 0; row < b.getNumRows(); row++) {
			for(int col = 0; col < b.getNumCols(); col++) {
				board[row][col] = '.';
			}
		}
	}

	private static void populateBoard(char[][] chboard, Board b) {
		for(int row = 0; row < b.getNumRows(); row++) {
			for(int col = 0; col < b.getNumCols(); col++) {
				if(b.getGameItem(new Space(row, col)) != null) {
					chboard[row][col] = b.getGameItem(new Space(row, col)).getType().toString().charAt(0);
				}
			}
		}
	}

	private static String convertBoardToString(char[][] board, Board b) {
		String result = "";
		for(int row = 0; row < b.getNumRows(); row++) {
			for(int col = 0; col < b.getNumCols(); col++) {
				result+=board[row][col];
			}
			result+="\n";
		}
		return result;
	}
}