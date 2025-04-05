import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HelpPane extends GraphicPane {

	public HelpPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		backButton();
		disPlay();
	}
	
	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	public void disPlay() {}
	
	public void backButton() {
		GImage backButton = new GImage("media/BackButton.png");
		backButton.scale(0.5, 0.5);
		backButton.setLocation(10, 10);
		
		contents.add(backButton);
		mainScreen.add(backButton);
	}
	
}
