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
public class InstructionsPane extends GraphicsPane {
	private MainApplication program;
	private GLabel welcomeLabel;
	private GLabel instructions1;
	private GLabel instructions2;
	private GLabel instructions3;
	private GLabel instructions4;
	private GButton backButton;
	private GButton playGameButton;
	private GImage instructions;
	private GImage background;
	private GImage controls;

	private Color changeColor = new Color(153, 255, 102);
	private ArrayList<GObject> instructionsArrayList = new ArrayList<GObject>();
	
	public InstructionsPane(MainApplication app) {
		this.program = app;

		background = new GImage("newback.jpg", 0, 0);
		background.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		background.sendToBack();
		
		instructions = new GImage("instructions.png", (WINDOW_WIDTH/2)-100, WINDOW_HEIGHT - 600);
		instructions.setSize(200,50);
		instructions.sendToFront();
		
		controls = new GImage("controls.png", 25, WINDOW_HEIGHT-250);
		controls.setSize(220, 200);
	
		
		double middleW = WINDOW_WIDTH / 2;

		welcomeLabel = new GLabel("Instructions", WINDOW_WIDTH / 4 + BUTTON_SIZE_X / 2, WINDOW_HEIGHT * .10);
		Font font = new Font("Dialog", Font.BOLD, 18);
		welcomeLabel.setFont(font);

		welcomeLabel.setColor(changeColor);

		backButton = new GButton("Back to Menu", middleW - BUTTON_SIZE_X / 2, WINDOW_HEIGHT * .80, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		backButton.setFillColor(changeColor);
		
		playGameButton = new GButton("Play Game", WINDOW_WIDTH-225, WINDOW_HEIGHT * .80, BUTTON_SIZE_X, BUTTON_SIZE_Y);
		playGameButton.setFillColor(changeColor);

		String str1 = "1. Use the arrow keys to move";
		String str2 = "2. Traffic is pretty dangerous... ";
		String str3 = "3. Collect all the bottles to win";
		String str4 = "4. Be careful! If you run out of lives or time ends, the game is over.";

		instructions1 = new GLabel(str1, WINDOW_WIDTH, 200);
		instructions2 = new GLabel(str2, WINDOW_WIDTH * .10, 250);
		instructions3 = new GLabel(str3, WINDOW_WIDTH * .10, 400);
		instructions4 = new GLabel(str4, WINDOW_WIDTH * .10, 450);


		Font font2 = new Font("Dialog", Font.BOLD, 19);
		
		instructions1.setFont(font2);
		instructions2.setFont(font2);
		instructions3.setFont(font2);
		instructions4.setFont(font2);

		instructions1.setLocation(WINDOW_WIDTH / 2 - instructions1.getWidth() / 2, WINDOW_HEIGHT * .25 + 100);
		instructions2.setLocation(WINDOW_WIDTH / 2 - instructions2.getWidth() / 2, WINDOW_HEIGHT * .25 + 150);
		instructions3.setLocation(WINDOW_WIDTH / 2 - instructions3.getWidth() / 2, WINDOW_HEIGHT * .25 + 200);
		instructions4.setLocation(WINDOW_WIDTH / 2 - instructions4.getWidth() / 2, WINDOW_HEIGHT * .25 + 250);
		
		instructionsArrayList.add(background);
		instructionsArrayList.add(instructions);
		instructionsArrayList.add(controls);
		instructionsArrayList.add(backButton);
		instructionsArrayList.add(playGameButton);
		instructionsArrayList.add(instructions1);
		instructionsArrayList.add(instructions2);
		instructionsArrayList.add(instructions3);
		instructionsArrayList.add(instructions4);
	}

	@Override
	public void showContents() {
		for (int i = 0; i < instructionsArrayList.size(); i++) {
			program.add(instructionsArrayList.get(i));
		}
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == backButton) {
			program.switchToMenu();
		}
		if (obj == playGameButton) {
			program.switchToLevelOne();
		}
	}
}