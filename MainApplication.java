import acm.graphics.GObject;
import acm.program.*;

import java.awt.event.MouseEvent;

public class MainApplication extends GraphicsProgram {
	
	private static final int WINDOW_HEIGHT = 1080;
	private static final int WINDOW_WIDTH = 1920;
    
	public MainApplication() {
		super();
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}