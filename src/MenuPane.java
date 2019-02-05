import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

import acm.graphics.*;

/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class MenuPane extends GraphicsPane {
	private MainApplication program;
	private GLabel welcomeLabel;
	private GButton instructButton;
	private GButton playButton;
	private GButton storeButton;
	private GButton exitButton;
	private GImage background;
	private ArrayList<GObject> menuArrayList = new ArrayList<GObject>();
	private Color changeColor = new Color(153, 255, 102);
	
	public MenuPane(MainApplication app) {
		this.program = app;
		double x = WINDOW_WIDTH * .10;
		double y = WINDOW_HEIGHT * .12;
	
		background = new GImage("MenuScreen.png", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		background.sendToBack();
		instructButton = new GButton("Instructions", x , y + 100, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		instructButton.setFillColor(changeColor);
		playButton = new GButton("Play", x, y + 200, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		playButton.setFillColor(changeColor);
		storeButton = new GButton("Boozy Store", x ,y + 300, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		storeButton.setFillColor(changeColor);
		exitButton = new GButton("Exit", x, y + 400, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		exitButton.setFillColor(changeColor);

		Font font = new Font("Dialog", Font.BOLD, 40);
		welcomeLabel = new GLabel("Welcome to Boozy Frog! TESTING", 100, 200);
		welcomeLabel.setFont(font);
		welcomeLabel.setLocation(WINDOW_WIDTH / 2 - welcomeLabel.getWidth() / 2, WINDOW_HEIGHT * .10);
		welcomeLabel.setColor(changeColor);

		menuArrayList.add(background);
		menuArrayList.add(instructButton);
		menuArrayList.add(playButton);
		menuArrayList.add(storeButton);
		menuArrayList.add(exitButton);
		menuArrayList.add(welcomeLabel);
	}

	@Override
	public void showContents() {
		for (int i = 0; i < menuArrayList.size(); i++) {
			program.add(menuArrayList.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == exitButton) {
			System.exit(0);
		}
		if (obj == playButton) {
			program.switchToInstructions();
		}
		if (obj == instructButton) {
			program.switchToInstructions();
		}
		if (obj == storeButton) {
			program.switchToStore();
		}
	}
}