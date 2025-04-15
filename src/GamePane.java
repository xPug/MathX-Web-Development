import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class GamePane extends GraphicPane {
	private GRect backGround;
	private GLine gameDivider;
	private GLabel displayQuestion;

	public GamePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		backGround();
		gameLine();
		getQuestion();
	}
	
	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	public void backGround() {
		backGround = new GRect(mainScreen.getWidth(), mainScreen.getHeight());
	    backGround.setFilled(true);
	    backGround.setColor(java.awt.Color.BLACK);
	    
	    contents.add(backGround);
	    mainScreen.add(backGround);
	}
	
	public void getQuestion() {
		int randomType = (int)(Math.random() * 4);
		QuestionType questionType;
		Questions gameQuestion;
		
		if (randomType == 0) {
			questionType = QuestionType.ADDITION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.additionQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-40");
			displayQuestion.setColor(Color.YELLOW);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 50);
		}
		else if (randomType == 1) {
			questionType = QuestionType.SUBTRACTION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.subtractionQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-40");
			displayQuestion.setColor(Color.YELLOW);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 50);
		}
		else if (randomType == 2) {
			questionType = QuestionType.MULTIPLICATION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.multiplicationQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-40");
			displayQuestion.setColor(Color.YELLOW);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 50);
		}
		else {
			questionType = QuestionType.DIVISION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.divisionQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-40");
			displayQuestion.setColor(Color.YELLOW);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 50);
		}
		
		contents.add(displayQuestion);
		mainScreen.add(displayQuestion);
	}
	
	public void gameLine() {
		gameDivider = new GLine(mainScreen.getWidth() / 2, 0, mainScreen.getWidth() / 2, mainScreen.getHeight());
		gameDivider.setColor(Color.white);
		
		contents.add(gameDivider);
		mainScreen.add(gameDivider);
	}
}
