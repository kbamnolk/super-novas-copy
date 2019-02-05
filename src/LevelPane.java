import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import java.util.ArrayList;
import javax.swing.Timer;

/*
 * Pseudo code for COMP129 
 * I am going to handle three events:
 * 1) Move Frog up lanes
 * 		"X" -> goes straight to next grassy area
 * 2) Reduce the amount of cars 
 * 		"L" or "R" for less cars
 * 3) Make cars slower
 * 		"C" -> slow down number of cars per minute
 * 
 * I am finding the Key Event Handlers using the keyPressed 
 * method to change the location of the frog.
 * Using moveAllCars to reduce the speed and amount of cars for other features
 * 
 */
/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class LevelPane extends GraphicsPane implements ActionListener {
	/********************
	 * GLOBAL VARIABLES *
	 ********************/
	public MainApplication program;
	private String direction;
	private ArrayList<GObject> all = new ArrayList<GObject>();
	private ArrayList<GObject> leftMovingCars = new ArrayList<GObject>();
	private ArrayList<GObject> rightMovingCars = new ArrayList<GObject>();
	private ArrayList<GObject> coinsArray = new ArrayList<GObject>();
	private ArrayList<GImage> bottleField = new ArrayList<GImage>();
	private ArrayList<GImage> life = new ArrayList<GImage>();
	private ArrayList<GImage> bottlesMenu = new ArrayList<GImage>();
	private GLabel labelTimer;
	private Level level;
	private GameItem[][] board;
	private GImage boozy;
	private GImage exit;
	private GImage lifeup;
	private Timer movement;
	private Timer timer1;
	private Timer animationTimer;
	private boolean LifeTaker;
	private Render render;
	private int Min;
	private int DecaSec;
	private int Sec;
	private GImage tequilaMessage;
	private boolean onExitTile;

	/**********************
	 * CONSTRUCTOR
	 * 
	 * @param app
	 * @param chosenLevel
	 **********************/
	public LevelPane(MainApplication app) {
		this.program = app;
		this.level = new Level("level1");
		this.board = level.getGameItemBoard();
		this.render = new Render(level, board);
		this.all = render.getAllList();
		this.life = render.getLife();
		this.leftMovingCars = render.getLeftList();
		this.rightMovingCars = render.getRightList();
		this.coinsArray = render.getCoinsArray();
		this.bottleField = render.getBottle();
		this.bottlesMenu = render.getBottlesMenu();
		this.boozy = render.getBoozy();
		this.exit = render.getExit();
		this.tequilaMessage = render.getTequilaMessage();
		this.lifeup = render.getLifeup();
		this.labelTimer = render.getLabelTimer();
		this.Min = 3;
		this.DecaSec = 0;
		this.Sec = 0;
		this.direction = "up";
		this.timer1 = new Timer(1000, this);
		this.movement = new Timer(50, this);
		this.animationTimer = new Timer(3000, this);
		this.onExitTile = false;
		program.addKeyListeners();
		program.addMouseListeners();
		program.setSize(WINDOW_WIDTH, WINDOW_WIDTH);
		timer1.start();
	}

	/******************
	 * HELPER METHODS *
	 ******************/
	private void roadSoundEffects() {
		if (boozy.getY() > 50 || boozy.getY() < 350 || boozy.getY() > 400 || boozy.getY() < 600) {
			AudioPlayer.getInstance().playSound("sounds", "Urban Traffic.mp3");
		} else if (boozy.getY() >= 350 || boozy.getY() >= 400) {
			AudioPlayer.getInstance().pauseSound("sounds", "Urban Traffic.mp3");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer1) {
			timerCountdown();

		} else if (e.getSource() == animationTimer) {

		} else if (e.getSource() == movement) {
			moveAllCars();
			LifeTaker = collisionCheck();
			HandleDeaths(LifeTaker);
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	/****************
	 * TIMER EVENTS *
	 ****************/
	private void timerCountdown() {
		if (Min == 0 && DecaSec == 0 && Sec == 0) {
			timer1.stop();
		} else if (Min > 0 && DecaSec == 0 && Sec == 0) {
			this.Sec = 9;
			this.DecaSec = 5;
			this.Min -= 1;
		} else if (Min > 0 && DecaSec > 0 && Sec > 0) {
			this.Sec -= 1;
		} else if (Min > 0 && DecaSec > 0 && Sec == 0) {
			this.Sec = 9;
			this.DecaSec -= 1;
		} else if (Min > 0 && DecaSec == 0 && Sec > 0) {
			this.Sec -= 1;
		} else if (Min == 0 && DecaSec > 0 && Sec > 0) {
			this.Sec -= 1;
		} else if (Min == 0 && DecaSec > 0 && Sec == 0) {
			this.Sec = 9;
			this.DecaSec -= 1;
		} else if (Min == 0 && DecaSec == 0 && Sec > 0) {
			this.Sec -= 1;
		}

		labelTimer.setLabel(
				"Time Left: " + Integer.toString(Min) + ":" + Integer.toString(DecaSec) + Integer.toString(Sec));
		if (Min == 0 && DecaSec == 1 && Sec == 0) {
			AudioPlayer.getInstance().playSound("sounds", "Time Passing.mp3");
		}
		if (Min == 0 && DecaSec == 0 && Sec == 0) {
			program.switchToLose();
		}
	}

	private void moveAllCars() {
		for (GObject carLeft : this.leftMovingCars) {
			if (carLeft.getX() < -50) {
				carLeft.setLocation(725, carLeft.getY());
			}
			carLeft.move(-4, 0);
		}
		for (GObject carRight : this.rightMovingCars) {
			if (carRight.getX() > 725) {
				carRight.setLocation(-50, carRight.getY());
			}
			carRight.move(4, 0);
		}
	}

	/**********************************
	 * KEY AND MOUSE LISTENER METHODS *
	 **********************************/

	public void moveBoozyUp()
	{
		updateOnExitTile();
		setDirection("up");
		program.remove(tequilaMessage);
		if (boozy.getY() == 0 ) {
			boozy.setLocation(boozy.getX(), boozy.getY());
		}
		else {
			boozy.setLocation(boozy.getX(), boozy.getY()- 25);
		}

		boozy = render.animateBoozy(this.direction);
		checkForCollectibles();
		roadSoundEffects();
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			moveBoozyUp();

			break;
		case KeyEvent.VK_DOWN:
			setDirection("down");
			program.remove(tequilaMessage);
			boozy = render.animateBoozy(this.direction);
			updateOnExitTile();
			checkForCollectibles();
			BoundaryChecking(600, 0, 25);
			roadSoundEffects();
			break;
		case KeyEvent.VK_RIGHT:
			setDirection("right");
			program.remove(tequilaMessage);
			boozy = render.animateBoozy(this.direction);
			updateOnExitTileFromRight();
			checkForCollectibles();
			BoundaryChecking(650, 25, 0);
			roadSoundEffects();
			break;
		case KeyEvent.VK_LEFT:
			setDirection("left");
			program.remove(tequilaMessage);
			boozy = render.animateBoozy(this.direction);
			updateOnExitTile();
			checkForCollectibles();
			if (boozy.getX() == 0) {
				boozy.setLocation(boozy.getX(), boozy.getY());
			} 
			else {
				boozy.setLocation(boozy.getX() - 25, boozy.getY());
			}
			roadSoundEffects();
			break;
		
			//add new case for VK_X Key Event
			//Set the new location for (getY - 150)
			//So that the location of the frog is up a couple lanes
			
			//use 'X' key for first frog jump
		case KeyEvent.VK_X:
			System.out.println("Pressed X");
			for(int i = 0; i < 5; i++)
			{
				moveBoozyUp();
			}
			break;
		
			//use 'Y' key for second frog jump 
		case KeyEvent.VK_Y:
			System.out.println("Pressed Y");
			for(int i = 0; i < 7; i++)
			{
				moveBoozyUp();
			}
			break;		
		
			//add new case for VK_L Key Event
		case KeyEvent.VK_L:
			System.out.println("Pressed L");
			level.setIgnoreLiveCheck(); 		
			break;
			
			//add new case for VK_C Key Event
			//change speed of cars to slow
		case KeyEvent.VK_C:
			System.out.println("Pressed C");
			slowCarForTestOnly();
			break;
		}
	}

	private void slowCarForTestOnly() {
		System.out.println("Inside slowCarForTestOnly");
		this.movement.stop();
		this.movement = new Timer(200, this);
		this.movement.start();
		
	}

	private void updateOnExitTile() {
		if (((boozy.getX() > 325 && boozy.getX() < 375)  && (boozy.getY() == 0))) {
			this.onExitTile = true;
		}
	}

	private void updateOnExitTileFromRight() {
		if (((boozy.getX() >= 300 && boozy.getX() < 325)  && (boozy.getY() == 0))) {
			this.onExitTile = true;
		}
		else
			this.onExitTile = false;
	}
	
	private void checkForCollectibles() {
		for (int i = 0; i < this.coinsArray.size(); i++) {
			if (boozy.getX() == this.coinsArray.get(i).getX() && boozy.getY() == this.coinsArray.get(i).getY()) {
				program.remove(coinsArray.get(i));
				AudioPlayer.getInstance().playSound("sounds", "coinsound.wav");
			}
		}
		for (int i = 0; i < this.bottleField.size(); i++) {
			if (boozy.getX() == this.bottleField.get(i).getX() && boozy.getY() == this.bottleField.get(i).getY()) {
			program.remove(bottleField.get(i));
			program.remove(bottlesMenu.get(i));
			bottleField.remove(bottleField.get(i));
			bottlesMenu.remove(bottlesMenu.get(i));
			} 
			if (((boozy.getX() >= 350 && boozy.getX() < 400)  && boozy.getY() < 50)) {
				AudioPlayer.getInstance().playSound("sounds", "BoozyVoice.m4a");
				animationTimer.start();
				this.onExitTile = true;
				program.add(tequilaMessage);
			}
		}
		if (boozy.getX() == this.lifeup.getX() && boozy.getY() == this.lifeup.getY()) {
			level.setLives(life.size()+1);
			lifeup.setLocation(55 + life.size() * 50, 650);
			life.add(lifeup);
		}
		System.out.println("On exit tile" + onExitTile + ", bottle array size is " + bottleField.size());
		if (this.bottleField.size() == 0 && onExitTile == true) {
			timer1.stop();
			program.switchToTheEnd();
		}
	}

	public void BoundaryChecking(int Border, int x, int y) {
		if (boozy.getY() == Border || boozy.getX() == Border) {
			boozy.setLocation(boozy.getX(), boozy.getY());
		} 
		else {
			boozy.setLocation(boozy.getX() + x, boozy.getY() + y);
		}
	}

	/****************************************
	 * WINDOW SWITCHING, SHOWING AND HIDING *
	 ****************************************/
	@Override
	public void showContents() {

		for (int i = 0; i < this.all.size(); i++) {
			program.add(this.all.get(i));
		}
		for (int i = 0; i < this.bottleField.size(); i++) {
			program.add(this.bottleField.get(i));
		}
		for (int i = 0; i < this.coinsArray.size(); i++) {
			program.add(this.coinsArray.get(i));
		}
		for (int i = 0; i < this.life.size(); i++) {
			program.add(this.life.get(i));
		}
		program.add(exit);
		program.add(boozy);
		for (int i = 0; i < this.leftMovingCars.size(); i++) {
			program.add(this.leftMovingCars.get(i));
		}
		for (int i = 0; i < this.rightMovingCars.size(); i++) {
			program.add(this.rightMovingCars.get(i));
		}
		for (int i = 0; i < this.bottlesMenu.size(); i++) {
			program.add(this.bottlesMenu.get(i));
		}
		program.add(exit);
		program.add(boozy);
		program.add(lifeup);
		program.add(labelTimer);
		movement.start();
	}

	@Override
	public void hideContents() {
		AudioPlayer.getInstance().stopSound("sounds", "level1.m4a");
		movement.stop();
		program.removeAll();
	}

	private boolean collisionCheck() {
		double Boozyrow = 0;
		double Boozycol = 0;
		boolean hit;
		hit = false;
		double checkRow = 0;
		double checkCol = 0;
		Boozyrow = BoozyLocationRow(true);
		Boozycol = BoozyLocationRow(false);

		// compare boozy x and y to cars row and col, if they match boozy was hit
		// arraylist of cars...cycle through them and check each time?

		// right moving cars

		for (int i = 0; i < rightMovingCars.size(); i++) {
			checkCol = rightMovingCars.get(i).getX();
			checkRow = rightMovingCars.get(i).getY();
			checkRow = checkRow / 50; 
			checkRow = Math.floor(checkRow); 
			checkCol = checkCol / 50;
			checkCol = Math.floor(checkCol);

			if ((checkCol + 2 == Boozycol) && (checkRow == Boozyrow)) { 
				System.out.println("right collision");
				hit = true;
				return hit;
			}
		}
		for (int j = 0; j < leftMovingCars.size(); j++) {
			checkCol = leftMovingCars.get(j).getX();
			checkRow = leftMovingCars.get(j).getY();
			checkRow = checkRow / 50;
			checkRow = Math.floor(checkRow);
			checkCol = checkCol / 50;
			checkCol = Math.floor(checkCol);

			if ((checkCol == Boozycol) && (checkRow == Boozyrow)) {
				System.out.println("left collision");
				hit = true;
				return hit;
			}
		}
		return hit;
	}

	public double BoozyLocationRow(boolean WhichOne) {
		double Brow = 0;
		double Bcol = 0;

		if (WhichOne) {
			Brow = boozy.getY();
			Brow = Brow / 50;
			Brow = Math.floor(Brow); 
			return Brow; 
		} 
		else {
			Bcol = boozy.getX();
			Bcol = Bcol / 50;
			Bcol = Math.floor(Bcol);
			return Bcol;
		}
	}

	public void HandleDeaths(boolean hit) {
		if (level.ignoreLiveCheck()) {
			System.out.println("Going to ignore life check");
			return;
		}
		
		if (hit) {
			level.setLives(level.getLives() - 1);
			if (level.getLives() == 0) {
				program.switchToLose();
			} else if (level.getLives() >= 0) {
				boozy.setLocation(WINDOW_WIDTH / 2 - 50, 600);
				program.remove(boozy);
				GImage lifeup = life.remove(life.size() - 1);
				boozy = render.getBoozy();
				program.add(boozy);
				program.remove(lifeup);
			}
		}
	}

	public boolean CarInfrontBoozy() {
		double Boozyrow = 0;
		double Boozycol = 0;
		boolean hit;
		hit = false;
		double checkRow = 0;
		double checkCol = 0;
		Boozyrow = BoozyLocationRow(true);
		Boozycol = BoozyLocationRow(false);

		for (int i = 0; i < rightMovingCars.size(); i++) {
			checkCol = rightMovingCars.get(i).getX();
			checkRow = rightMovingCars.get(i).getY();
			checkRow = checkRow / 50; 
			checkRow = Math.floor(checkRow); 
			checkCol = checkCol / 50;
			checkCol = Math.floor(checkCol);

			if (((checkRow + 1 == Boozyrow) && (checkCol == Boozycol)) 
					|| ((checkCol + 1 == Boozycol) && (checkRow + 1 == Boozyrow))) { 
				hit = true;
				return hit;
			}
		}
		for (int j = 0; j < leftMovingCars.size(); j++) {
			checkCol = leftMovingCars.get(j).getX();
			checkRow = leftMovingCars.get(j).getY();
			checkRow = checkRow / 50;
			checkRow = Math.floor(checkRow);
			checkCol = checkCol / 50;
			checkCol = Math.floor(checkCol);

			if (((checkRow + 1 == Boozyrow) && (checkCol == Boozycol))
					|| ((checkCol + 1 == Boozycol) && (checkRow + 1 == Boozyrow))) { 
				System.out.println("left halt");
				hit = true;
				return hit;
			}
		}
		return hit;
	}
}