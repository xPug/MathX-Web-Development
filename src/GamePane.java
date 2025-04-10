import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class GamePane extends GraphicPane {
	private GRect backGround;
	private GLine gameDivider;
	
	public GamePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		backGround();
		gameLine();
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
	
	public void gameLine() {
		gameDivider = new GLine(mainScreen.getWidth() / 2, 0, mainScreen.getWidth() / 2, mainScreen.getHeight());
		gameDivider.setColor(Color.white);
		
		contents.add(gameDivider);
		mainScreen.add(gameDivider);
	}
}
