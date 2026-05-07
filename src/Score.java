
public class Score {
	private int playerScore;
	
	public Score () {}
	
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
