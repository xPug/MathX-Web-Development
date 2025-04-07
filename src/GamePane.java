import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class GamePane extends GraphicPane {
	
	public GamePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {}
	
	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
}
