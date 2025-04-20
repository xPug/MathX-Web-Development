
public class Score {
	private int playerScore;
	private GamePane gamePane;
	
	public Score (GamePane gameScreen) {
		this.gamePane = gameScreen;
	}
	
	public void startScore() {
		playerScore = 0;
	}
	
	public void correctAnswer() {
		playerScore++;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
}
