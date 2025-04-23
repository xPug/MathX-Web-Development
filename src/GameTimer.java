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
	
	// Starts the timer and counts down
	public void startCountdown(){
		timeLeft = 60; // Game duration will be 1 minute
		countdownTimer = new Timer();
		countdownTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				timeLeft--;
				// gamePane.updateTimerLabel(formatTime()); will update timer when label is created
				if(timeLeft <= 0) {
					countdownTimer.cancel();
				}
			}
		}, 1000, 1000);
	}
	
	// math to make the timer change between minutes and seconds
    private String formatTime() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        
        if (seconds < 10) {
        	return minutes + ":0" + seconds;
        }
        return minutes + ":" + seconds;
    }
}
