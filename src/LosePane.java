import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class LosePane extends GraphicsPane {
	private MainApplication program; 
	private GImage background;
	private GImage frog;
	private GImage cantina;
	private GLabel loseLabel;
	private GButton exitButton;
	private Color changeColor = new Color(153, 255, 102);
	private ArrayList<GObject> loseArrayList = new ArrayList<GObject>();
	private Font font = new Font("Dialog", Font.BOLD, 28);
	
	public LosePane(MainApplication app) {
		this.program = app;
		
		background = new GImage("newback.jpg", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frog = new GImage("sadfrog.png", WINDOW_WIDTH/1.5, WINDOW_HEIGHT/2);
		frog.setSize(WINDOW_WIDTH/3.5, WINDOW_HEIGHT/3.5);
		
		cantina = new GImage("cantina.png", WINDOW_WIDTH/10, WINDOW_HEIGHT/2);
		cantina.setSize(WINDOW_WIDTH/2, WINDOW_WIDTH/5);
		
		loseLabel = new GLabel("Sorry, you have lost!", 0, 0);
		loseLabel.setFont(font);
		loseLabel.setLocation(WINDOW_WIDTH / 2 - loseLabel.getWidth()/2, WINDOW_HEIGHT/2.5);

		exitButton = new GButton("Exit", 0, 0, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		exitButton.setFillColor(changeColor);
		exitButton.setLocation(WINDOW_WIDTH/2 - exitButton.getWidth()/2, WINDOW_HEIGHT* .80);
	
		loseArrayList.add(background);
		loseArrayList.add(frog);
		loseArrayList.add(cantina);
		loseArrayList.add(loseLabel);
		loseArrayList.add(exitButton);
	}

	@Override
	public void showContents() {
		for (int i = 0; i < loseArrayList.size(); i++) {
			program.add(loseArrayList.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.removeAll();

	}

	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == exitButton) {
			System.exit(0);
		}
	}
}
