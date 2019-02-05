/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public enum GameItemType {
	CAR, BUSH, BOOZY, COIN, LIFEUP, BOTTLE, EXIT_TILE;

	public String toString() {
		switch(this) {
			case CAR: return "car";
			case BOOZY: return "boozy";
			case COIN: return "coin";
			case LIFEUP: return "lifeUp";
			case BOTTLE: return "bottle";
			case BUSH: return "bush";
			case EXIT_TILE: return "exit";
		}
		return "n/a";
	}
}
