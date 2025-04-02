import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;

public class GraphicPane {
	protected MainApplication mainScreen;
	protected ArrayList<GObject> contents;
	
	public GraphicPane() {
		contents = new ArrayList<GObject>();
	}
	
	public void showContent() {}
	
	public void hideContent() {}
	
	public void mousePressed() {}
	
	public void mouseReleased() {}
}
