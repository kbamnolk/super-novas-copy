import java.awt.Color;
import java.awt.Font;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRoundRect;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class Render {
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	public Random rgen;
	private GImage Boozy;
	private ArrayList<GObject> all = new ArrayList<GObject>();
	private ArrayList<GObject> leftMovingCars = new ArrayList<GObject>();
	private ArrayList<GObject> rightMovingCars = new ArrayList<GObject>();
	private ArrayList<GImage> life = new ArrayList<GImage>();
	public ArrayList<GImage> getLife() {
		return life;
	}

	public void setLife(ArrayList<GImage> life) {
		this.life = life;
	}

	private ArrayList<GObject> coinsArray = new ArrayList<GObject>();
	private ArrayList<GImage> bottle = new ArrayList<GImage>();
	private ArrayList<GImage> bottlesMenu = new ArrayList<GImage>();
	private Font f;
	private GLabel labelTimer;
	public ArrayList<GImage> getBottlesMenu() {
		return bottlesMenu;
	}

	private GImage exit;
	private GImage lifeup;
	private GImage tequilaMessage;
	public GImage getTequilaMessage() {
		return tequilaMessage;
	}

	public GImage getLifeup() {
		return lifeup;
	}

	public Render(Level l, GameItem[][] board) {

		GameItemType type;
		Space start;
		drawBackground();
		for (int i = 0; i < l.getRows(); i++) {
			for (int j = 0; j < l.getColumns(); j++) {
				if (board[i][j] != null) {
					type = board[i][j].getType();
					start = board[i][j].getStart();
					drawItem(type, start);
				}
			}
		}
		drawCollectibles(l);
		drawMenu();
		drawExit(l);
		drawTimerLabel(3, 0, 0);
		drawTequilaMessage();
		drawLifeCounter(l.getLives());
		
	}
	
	private void drawTequilaMessage() {
		this.tequilaMessage = new GImage("AlcoholMessage.png", 0, 0);
		this.tequilaMessage.setSize(300, 143);
		this.tequilaMessage.setLocation(WINDOW_WIDTH/2 - tequilaMessage.getWidth()/2, WINDOW_HEIGHT*.25);
		
	}

	public ArrayList<GObject> getCoinsArray() {
		return coinsArray;
	}

	public void setCoinsArray(ArrayList<GObject> coinsArray) {
		this.coinsArray = coinsArray;
	}

	public ArrayList<GImage> getBottle() {
		return bottle;
	}

	public void setBottle(ArrayList<GImage> bottle) {
		this.bottle = bottle;
	}

	public ArrayList<GObject> getAllList(){
		return this.all;
	}

	public ArrayList<GObject> getLeftList(){
		return this.leftMovingCars;
	}

	public ArrayList<GObject> getRightList(){
		return this.rightMovingCars;
	}

	public GImage getBoozy() {
		return this.Boozy;
	}

	public GLabel getLabelTimer() {
		return labelTimer;
	}

	public void setLabelTimer(GLabel labelTimer) {
		this.labelTimer = labelTimer;
	}

	/*******************
	 * DRAWING METHODS *
	 *******************/
	private void drawBackground() {
		GImage background = new GImage("backgroundlevel1.png", 100, 100);
		background.setLocation(0, 0);
		background.setSize(WINDOW_WIDTH, (WINDOW_HEIGHT));
		background.sendToBack();
		all.add(background);	
	}

	public GImage animateBoozy(String movement) {
		switch (movement) {
		case "up":
			Boozy.setImage("B_movingUp.gif");
			Boozy.setSize(spaceWidth(), spaceHeight());
			break;
		case "down":
			Boozy.setImage("B_movingDown.gif");
			Boozy.setSize(spaceWidth(), spaceHeight());
			break;
		case "left":
			Boozy.setImage("B_movingLeft.gif");
			Boozy.setSize(spaceWidth(), spaceHeight());
			break;
		case "right":
			Boozy.setImage("B_movingRight.gif");
			Boozy.setSize(spaceWidth(), spaceHeight());
			break;
		}
		return Boozy;
	}

	public void drawItem(GameItemType t, Space s) {
		switch (t) {
		case CAR:
			drawCar(s);
			break;
		case BUSH:
			drawBush(s);
			break;
		case BOOZY:
			drawBoozy(s);
			break;
		default:
			break;
		}
	}
	
	private void drawCollectibles(Level l) {

		ArrayList<Space> coinsArr = l.getCoins();
		ArrayList<Space> bottlesArr = l.getBottles();
		Space space;
		for (int i = 0; i < l.getCoins().size() -1; i++) {
			space = coinsArr.get(i);
			GImage coin = new GImage("coin.gif", spaceWidth(), spaceHeight());
			coin.setSize(spaceWidth(), spaceHeight());
			coin.setLocation(space.getCol() * spaceHeight(), space.getRow() * spaceWidth());
			this.coinsArray.add(coin);
		}
		for (int i = 0; i < l.getCoins().size() -1; i++) {
			space = bottlesArr.get(i);
			GImage bottle = new GImage("bottle.png", spaceWidth(), spaceHeight());
			bottle.setSize(spaceWidth(), spaceHeight());
			bottle.setLocation(space.getCol() * spaceHeight(), space.getRow() * spaceWidth());
			this.bottle.add(bottle);
		}
		space = l.getLifeUp();
		lifeup = new GImage("lifeUp.png", spaceWidth(), spaceHeight());
		lifeup.setSize(spaceWidth(), spaceHeight());
		lifeup.setLocation(space.getCol() * spaceHeight(), space.getRow() * spaceWidth());
		
	}

	private void drawCar(Space s) {
		Random rgen = new Random();

		int n = rgen.nextInt(5) + 1;
		String nString = Integer.toString(n);

		if (s.getRow() % 2 == 0) {
			GImage carL = new GImage("car" + nString + "L.png", spaceWidth(), spaceHeight());
			carL.setSize(spaceWidth() * 2, spaceHeight());
			carL.setLocation(s.getCol() * spaceHeight(), s.getRow() * spaceWidth());
			leftMovingCars.add(carL);
		} else {
			GImage car = new GImage("car" + nString + ".png", spaceWidth(), spaceHeight());
			car.setSize(spaceWidth() * 2, spaceHeight());
			car.setLocation(s.getCol() * spaceHeight(), s.getRow() * spaceWidth());
			rightMovingCars.add(car);
		}
	}

	private void drawBoozy(Space s) {
		this.Boozy = new GImage("BoozyUp.png", 0, 0);
		this.Boozy.setSize(spaceWidth(), spaceHeight());
		this.Boozy.setLocation(s.getCol() * spaceHeight(), s.getRow() * spaceWidth());
	}

	private void drawBush(Space s) {
		GImage bush = new GImage("bush.png", spaceWidth(), spaceHeight());
		bush.setSize(spaceWidth(), spaceHeight());
		bush.setLocation(s.getCol() * spaceHeight(), s.getRow() * spaceWidth());
		all.add(bush);
	}

	private void drawExit(Level l) {
		Space s = l.getExitTile();
		exit = new GImage("exit.png", spaceWidth(), spaceHeight());
		exit.setSize(spaceWidth(), spaceHeight());
		exit.setLocation(s.getCol() * spaceHeight(), s.getRow() * spaceWidth());
	}

	private void drawMenu() {
		double locationX2 = 340;
		double locationY = 650;
		f = new Font("Serif", Font.BOLD, 16);

		GRoundRect main = new GRoundRect(100, 100);
		main.setLocation(0, 650);
		main.setSize(WINDOW_WIDTH, 50);
		main.setFilled(true);
		Color changeColor = new Color(255, 114, 0);
		main.setFillColor(changeColor);
		all.add(main);

		GLabel livesLabel = new GLabel("Lives: ", 10, 675);
		livesLabel.setColor(Color.WHITE);
		livesLabel.setFont(f);
		all.add(livesLabel);

		GLabel bottlesLabel = new GLabel("Bottles left: ", 270, 675);
		bottlesLabel.setColor(Color.WHITE);
		bottlesLabel.setFont(f);
		all.add(bottlesLabel);

		for (int i = 0; i < 5; i++) {
			GImage bottlesMenu = new GImage("bottle.png", 50, 50);
			bottlesMenu.setSize(50, 50);
			bottlesMenu.setLocation(locationX2, locationY);
			bottlesMenu.sendToFront();
			this.bottlesMenu.add(bottlesMenu);
			locationX2 += 30;
		}
	}

	private void drawTimerLabel(int Min, int decaSec, int Sec) {
		GLabel countdown = new GLabel(
				"Time Left: " + Integer.toString(Min) + ":" + Integer.toString(decaSec) + Integer.toString(Sec), 550,
				675);
		countdown.setColor(Color.white);
		countdown.setFont(f);
		countdown.sendForward();
		this.setLabelTimer(countdown);

	}
	public void drawLifeCounter(int num){
		double locationX = 55;
		double locationY = 650;
		for (int i = 0; i < num; i++) {
			GImage lifeupMenu = new GImage("lifeUp.png", 50, 50);
			lifeupMenu.setSize(50, 50);
			lifeupMenu.setLocation(locationX, locationY);
			lifeupMenu.sendToFront();
			life.add(lifeupMenu);
			locationX += 50;
		}
	}
	
	/**
	 * @return the width (in pixels) of a single space in the grid
	 */

	public double spaceWidth() {
		return 700 / 14;
	}

	/**
	 * @return the height in pixels of a single space in the grid
	 */
	public double spaceHeight() {
		return 700 / 14;
	}
	public GImage getExit() {
		return exit;
	}
	
}