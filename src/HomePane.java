import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class HomePane extends GraphicPane {
	
	private GRect backGround;
	private GImage logoImage;
	private GImage helpButton;
	private GImage playButton;
	private SoundPlayer ButtonSound = new SoundPlayer();
	
	public HomePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen; 
	}
	
	// Uses from GraphicPane to add and remove items
	@Override
	public void showContent() {
		addBlackBackground();
		addHelpButton();
		addPlayButton();
		addLogo();
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
	    backGround = new GRect(mainScreen.getWidth(), mainScreen.getHeight());
	    backGround.setFilled(true);
	    backGround.setColor(java.awt.Color.BLACK);
	    
	    contents.add(backGround);
	    mainScreen.add(backGround); // Add background first so it's in the back
	}
	
	
	// Logo for Game
	private void addLogo() {
		logoImage = new GImage("media/MathXlogo.png", 100, 100);
		logoImage.scale(0.5, 0.5);
		logoImage.setLocation((mainScreen.getWidth() - logoImage.getWidth()) / 2, (mainScreen.getHeight() - logoImage.getHeight()) / 5);
		
		contents.add(logoImage);
		mainScreen.add(logoImage);
	}
	// Help Button
	private void addHelpButton() {
		helpButton = new GImage("media/helpButton.png", 200, 400); // change help image later
		helpButton.scale(0.65, 0.6);
		helpButton.setLocation((mainScreen.getWidth() - helpButton.getWidth()) / 2, (mainScreen.getHeight() - helpButton.getHeight()) - 150);
			
		contents.add(helpButton);
		mainScreen.add(helpButton);
	}
		
	// Play Button
	private void addPlayButton() {
		playButton = new GImage("media/playButton.png", 200, 400); // change button image later
		playButton.scale(0.65, 0.6);
		playButton.setLocation((mainScreen.getWidth() - playButton.getWidth()) / 2, (mainScreen.getHeight() - playButton.getHeight()) - 300);
		
		contents.add(playButton);
		mainScreen.add(playButton);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
		if (clicked == helpButton) {
			mainScreen.switchToHelpPane();
			ButtonSound.playSoundOnce("media/ButtonClick.wav");
		}
		else if (clicked == playButton) {
			mainScreen.switchToGamePane();
			ButtonSound.playSoundOnce("media/ButtonClick.wav");
		}
	}
}
