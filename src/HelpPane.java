import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class HelpPane extends GraphicPane {

	public HelpPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
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
	
}
