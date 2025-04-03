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
	
	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}
}
