import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
	private int timeLeft;
	private Timer countdownTimer;
	private GamePane gamePane;
	
	// Can call methods from GamePane
	public GameTimer(GamePane gamePane) {
        this.gamePane = gamePane;
	}
}
