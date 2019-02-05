/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	private MenuPane menu;
	private LevelPane level1_FrontEnd;
	private InstructionsPane instruct;
	private StorePane store;
	private TheEndPane theEnd;
	private LosePane lose;	
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		menu = new MenuPane(this);
		instruct = new InstructionsPane(this);
		store = new StorePane(this);
		level1_FrontEnd = new LevelPane(this);
		lose = new LosePane(this);
		theEnd = new TheEndPane(this);
		AudioPlayer.getInstance().playSound("sounds", "MainMenu.m4a");
		switchToMenu();
	}

	/**************************
	 * MENU SWITCHING METHODS *
	 **************************/
	
	public void playButtonSound() {
		AudioPlayer.getInstance().playSound("sounds", "buttonSound.wav");
	}
	
	public void switchToMenu() {
		playButtonSound();
		switchToScreen(menu);
	}

	public void switchToInstructions() {
		playButtonSound();
		switchToScreen(instruct);
	}

	public void switchToStore() {
		playButtonSound();
		switchToScreen(store);
	}

	public void switchToLevelOne() {
		playButtonSound();
		menu.hideContents();
		switchToScreen(level1_FrontEnd);
	}

	public void switchToTheEnd() {
		playButtonSound();
		switchToScreen(theEnd);
	}

	public void switchToLose() {
		playButtonSound();
		switchToScreen(lose);
	}
}

