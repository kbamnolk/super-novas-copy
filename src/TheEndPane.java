import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

/**
 * @author Ivan Lopez, Edgar Berber, Karen Bamnolker, Dorothy Luu
 */
public class TheEndPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to all of the GraphicsProgram calls
	private GImage background;
	private GImage frog;
	private GImage alc;
	private GLabel congratsLabel;
	private GButton exitButton;
	private Color changeColor = new Color(153, 255, 102);
	private ArrayList<GObject> endArrayList = new ArrayList<GObject>();
	private Font font = new Font("Dialog", Font.BOLD, 28);
	
	public TheEndPane(MainApplication app) {
		this.program = app;
		background = new GImage("end_game.png", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frog = new GImage("partyfrog.png", WINDOW_WIDTH/4, WINDOW_HEIGHT/3);
		frog.setSize(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
		
		alc = new GImage("alc.png", WINDOW_WIDTH/4, WINDOW_HEIGHT/3.5);
		alc.setSize(WINDOW_WIDTH/6, WINDOW_HEIGHT/6);
		
		congratsLabel = new GLabel("Congratulations! Boozy has reached the Cantina!", 0, 0);
		congratsLabel.setFont(font);
		congratsLabel.setLocation(WINDOW_WIDTH / 2 - congratsLabel.getWidth() / 2, WINDOW_HEIGHT/3.5);
		exitButton = new GButton("Exit", WINDOW_WIDTH/2 - (BUTTON_SIZE_X * 2), BUTTON_SIZE_Y + 500, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		exitButton.setFillColor(changeColor);
		exitButton.setLocation(WINDOW_WIDTH/2 - exitButton.getWidth()/2, WINDOW_HEIGHT* .65);
		
		endArrayList.add(background);
		endArrayList.add(alc);
		endArrayList.add(congratsLabel);
		endArrayList.add(exitButton);
	}

	@Override
	public void showContents() {
		for (int i = 0; i < endArrayList.size(); i++) {
			program.add(endArrayList.get(i));
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
