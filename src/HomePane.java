import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class HomePane extends GraphicPane {
	public HomePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	// Uses from GraphicPane to add and remove items
	@Override
	public void showContent() {
		addLogo();
	}
	
	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	// Logo for Game
	private void addLogo() {
		GImage logoImage = new GImage("media/MathXlogo.png", 100, 100);
		logoImage.scale(0.5, 0.5);
		logoImage.setLocation((mainScreen.getWidth() - logoImage.getWidth()) / 2, (mainScreen.getHeight() - logoImage.getHeight()) / 5);
		
		contents.add(logoImage);
		mainScreen.add(logoImage);
	}
}
