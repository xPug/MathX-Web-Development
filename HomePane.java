import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class HomePane extends GraphicPane {
	public HomePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
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
	
	private void addLogo() {
		GImage logoImage = new GImage("MathXlogo.png", 100, 100);
		logoImage.scale(1, 1);
		logoImage.setLocation((mainScreen.getWidth() - logoImage.getWidth()) / 2, (mainScreen.getHeight() - logoImage.getHeight()) / 2);
		
		contents.add(logoImage);
		mainScreen.add(logoImage);
	}
}
