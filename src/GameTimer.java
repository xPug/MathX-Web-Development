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
				gamePane.updateTimerLabel(formatTime());
				if(timeLeft <= 0) {
					countdownTimer.cancel();
				}
			}
		}, 1000, 1000);
	}
		
		//Timer stops when it reaches to 0
		public void stopCountdown(){
			if (countdownTimer != null) {
	            countdownTimer.purge();
	        }
		}
		
		//Get the remaining time for count down
		public Timer getCountdownTimer() {
			if (countdownTimer == null) {
				return null;
			}
			return countdownTimer;
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
