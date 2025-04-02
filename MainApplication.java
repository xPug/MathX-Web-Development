import acm.graphics.GObject;
import acm.program.*;

import java.awt.event.MouseEvent;

public class MainApplication extends GraphicsProgram {
	
	private static final int WINDOW_HEIGHT = 1080;
	private static final int WINDOW_WIDTH = 1920;
    
	private GraphicPane currentScreen;
	private HomePane homePane;
	
	public MainApplication() {
		super();
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}