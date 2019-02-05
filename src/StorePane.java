import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class StorePane extends GraphicsPane {
	private MainApplication program; 
	private Color changeColor = new Color(153, 255, 102);
	private GButton backButton;
	private GImage power1;
	private GLabel p1Label;
	private GImage power2;
	private GLabel p2Label;
	private GImage myStore;
	private GImage clock;
	private GLabel clockLabel;
	private GImage shirt;
	private GLabel shirtLabel;
	private GImage soon;
	private GImage background;

	private ArrayList<GObject> storeArrayList = new ArrayList<GObject>();
	
	public StorePane(MainApplication app) {
		this.program = app;
		
		int middleW = WINDOW_WIDTH / 2;
		int xLabel = WINDOW_WIDTH - 500;
		Font font = new Font("Dialog", Font.BOLD, 17);
		
		background = new GImage("newback.jpg",0,0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		myStore = new GImage("storeWelcome.jpg", (WINDOW_WIDTH/2)-50, WINDOW_HEIGHT - 650);
		myStore.setSize(100,50);
		
		backButton = new GButton("Back to Menu", middleW - (BUTTON_SIZE_X * 2), BUTTON_SIZE_Y + 500, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		backButton.setFillColor(changeColor);
		backButton.setLocation(middleW - backButton.getWidth()/2, WINDOW_HEIGHT* .80);
		
		power1 = new GImage("jumptransparent.png", WINDOW_WIDTH-620, WINDOW_HEIGHT-525);
		power1.setSize(100,50);
		p1Label = new GLabel("Long Jump! Get an extra kick with every move! 50 coins", xLabel, WINDOW_HEIGHT - 500);
		p1Label.setFont(font);

		power2 = new GImage("extralives.png", WINDOW_WIDTH - 620, WINDOW_HEIGHT - 425);
		power2.setSize(100,50);
		p2Label = new GLabel("Want some extra juice? Pick this up for 100 coins!", xLabel, WINDOW_HEIGHT - 400);
		p2Label.setFont(font);
		
		clock = new GImage("clocktransparent.png", WINDOW_WIDTH - 620, WINDOW_HEIGHT - 325);
		clock.setSize(100,50);
		clockLabel = new GLabel("Get this in order to slow down obstacles! 100 coins", xLabel, WINDOW_HEIGHT-300);
		clockLabel.setFont(font);
	
		shirt = new GImage("shirttransparent.png", WINDOW_WIDTH - 620, WINDOW_HEIGHT - 225);
		shirt.setSize(100,50);
		shirtLabel = new GLabel("Change Boozy's outift! 100 coins", xLabel, WINDOW_HEIGHT-200);
		shirtLabel.setFont(font);

		soon = new GImage("comingsoon.png", WINDOW_WIDTH*.05, WINDOW_HEIGHT/3);
		soon.setSize(650, 200);
		soon.sendToFront();
		
		storeArrayList.add(background);
		storeArrayList.add(myStore);
		storeArrayList.add(power1);
		storeArrayList.add(p1Label);
		storeArrayList.add(power2);
		storeArrayList.add(p2Label);
		storeArrayList.add(clock);
		storeArrayList.add(clockLabel);
		storeArrayList.add(shirt);
		storeArrayList.add(shirtLabel);
		storeArrayList.add(backButton);
		storeArrayList.add(soon);	
	}
	
	@Override
	public void showContents() {
		for (int i = 0; i < storeArrayList.size(); i++) {
			program.add(storeArrayList.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == backButton) {
			program.switchToMenu();	
		}
	}
}