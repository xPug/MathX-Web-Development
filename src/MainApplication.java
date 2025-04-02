import acm.graphics.GObject;
import acm.program.*;

import java.awt.event.MouseEvent;

public class MainApplication extends GraphicsProgram {
	// Optimized to most popular
	private static final int WINDOW_HEIGHT = 1080;
	private static final int WINDOW_WIDTH = 1920;
    
	// All screen panes go here
	private GraphicPane currentScreen;
	private HomePane homePane;
	
	public MainApplication() {
		super();
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Running");
		
		// Initializes Panes
		homePane = new HomePane(this);
		
		switchToScreen(homePane);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
	
	public void switchToHomePane() {
		switchToScreen(homePane);
	}
	
	protected void switchToScreen(GraphicPane newScreen) {
		if(currentScreen != null) {
			currentScreen.hideContent();
		}
		newScreen.showContent();
		currentScreen = newScreen;
	}
}