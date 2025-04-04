import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class HomePane extends GraphicPane {
	public HomePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	// Uses from GraphicPane to add and remove items
	@Override
	public void showContent() {
		addBlackBackground();
		addLogo();
		addPlayButton();
		addHelpButton();
	}
	
	// Hides all content
	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	private void addBlackBackground() {
	    GRect background = new GRect(mainScreen.getWidth(), mainScreen.getHeight());
	    background.setFilled(true);
	    background.setColor(java.awt.Color.BLACK);
	    
	    contents.add(background);
	    mainScreen.add(background); // Add background first so it's in the back
	}
	
	
	// Logo for Game
	private void addLogo() {
		GImage logoImage = new GImage("media/MathXlogo.png", 100, 100);
		logoImage.scale(0.5, 0.5);
		logoImage.setLocation((mainScreen.getWidth() - logoImage.getWidth()) / 2, (mainScreen.getHeight() - logoImage.getHeight()) / 5);
		
		contents.add(logoImage);
		mainScreen.add(logoImage);
	}
	
	// Play Button
	private void addPlayButton() {
		GImage playButton = new GImage("media/playbutton.png", 100, 200); // change button image later
		playButton.scale(0.5, 0.5);
		playButton.setLocation((mainScreen.getWidth() - playButton.getWidth()) / 2, (mainScreen.getHeight() - playButton.getHeight()));
		
		contents.add(playButton);
		mainScreen.add(playButton);
	}
	
	// Help Button
	private void addHelpButton() {
		GImage helpButton = new GImage("media/HelpButton.png", 100, 200); // change help image later
		helpButton.scale(0.5, 0.40);
		helpButton.setLocation((mainScreen.getWidth() - helpButton.getWidth()) / 2, (mainScreen.getHeight() - helpButton.getHeight()));
		
		contents.add(helpButton);
		mainScreen.add(helpButton);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
}
