import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HelpPane extends GraphicPane {
	GImage backButton;
	GRect backGround;
	GLabel rulesText;

	public HelpPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		backGround();
		backButton();
		rulesContent();
	}
	
	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	public void backGround() {
		backGround = new GRect(mainScreen.getWidth(), mainScreen.getHeight());
	    backGround.setFilled(true);
	    backGround.setColor(java.awt.Color.BLACK);
	    
	    contents.add(backGround);
	    mainScreen.add(backGround);
	}
	
	
	
	public void backButton() {
		backButton = new GImage("media/BackButton.png");
		backButton.scale(0.5, 0.5);
		backButton.setLocation(10, 10);
		
		contents.add(backButton);
		mainScreen.add(backButton);
	}
	
	public void rulesContent() {
		rulesText = new GLabel("The Rules go here!");
		rulesText.setFont("Arial-60");
		rulesText.setColor(Color.white);
		rulesText.setLocation((mainScreen.getWidth() - rulesText.getWidth()) / 2, mainScreen.getHeight() / 2);
		
		contents.add(rulesText);
		mainScreen.add(rulesText);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(1)) {
			mainScreen.switchToHomePane();
		}
	}
}
