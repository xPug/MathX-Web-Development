import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HelpPane extends GraphicPane {
	GImage backButton;
	GRect backGround;

	public HelpPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		backGround();
		backButton();
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
		GImage backButton = new GImage("media/BackButton.png");
		backButton.scale(0.5, 0.5);
		backButton.setLocation(10, 10);
		
		contents.add(backButton);
		mainScreen.add(backButton);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mainScreen.getElementAtLocation(e.getX(), e.getY()) == contents.get(1)) {
			mainScreen.switchToHomePane();
		}
	}
}
