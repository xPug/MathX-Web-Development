import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.graphics.*;

public class GamePane extends GraphicPane {
	private GRect backGround;
	// private GLine gameDivider;
	private GLabel displayQuestion;
	private GRect inputBox;
	private GLabel userInputLabel;
	private String userInput = "";

	public GamePane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		userInput = "";
		backGround();
		// gameLine();
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
			displayQuestion.setFont("Arial-Bold-100");
			displayQuestion.setColor(Color.WHITE);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 100);
		}
		else if (randomType == 1) {
			questionType = QuestionType.SUBTRACTION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.subtractionQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-100");
			displayQuestion.setColor(Color.WHITE);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 100);
		}
		else if (randomType == 2) {
			questionType = QuestionType.MULTIPLICATION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.multiplicationQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-100");
			displayQuestion.setColor(Color.WHITE);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 100);
		}
		else {
			questionType = QuestionType.DIVISION;
			gameQuestion = new Questions(questionType);
			gameQuestion.twoDigitGenerator();
			displayQuestion = new GLabel(gameQuestion.divisionQuestion(), 100, 100);
			displayQuestion.setFont("Arial-Bold-100");
			displayQuestion.setColor(Color.WHITE);
			displayQuestion.setLocation((mainScreen.getWidth() - displayQuestion.getWidth()) / 2, 100);
		}
		
		contents.add(displayQuestion);
		mainScreen.add(displayQuestion);
		
		inputBox = new GRect(200, 100);  // width, height of input box
		inputBox.setFilled(true);
		inputBox.setFillColor(Color.WHITE);
		inputBox.setColor(Color.GRAY);
		inputBox.setLocation((mainScreen.getWidth() - inputBox.getWidth()) / 2, 200);

		// Create user input label
		userInputLabel = new GLabel("", inputBox.getX() + 10, inputBox.getY() + 65);  // Y + 65 for vertical centering
		userInputLabel.setFont("Impact-50");
		userInputLabel.setColor(Color.BLACK);

		contents.add(inputBox);
		contents.add(userInputLabel);
		mainScreen.add(inputBox);
		mainScreen.add(userInputLabel);
	}
	
	/* public void gameLine() {
		gameDivider = new GLine(mainScreen.getWidth() / 2, 0, mainScreen.getWidth() / 2, mainScreen.getHeight());
		gameDivider.setColor(Color.white);
		
		contents.add(gameDivider);
		mainScreen.add(gameDivider);
	}
	
	*/
	
	private void centerUserInputLabel() {
		double centerX = inputBox.getX() + (inputBox.getWidth() - userInputLabel.getWidth()) / 2;
		double baseY = inputBox.getY() + (inputBox.getHeight() + userInputLabel.getAscent()) / 2 - 10;
		userInputLabel.setLocation(centerX, baseY);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		char ch = e.getKeyChar();
		if (Character.isDigit(ch) && userInput.length() < 5) {
			userInput += ch;
			userInputLabel.setLabel(userInput);
			centerUserInputLabel();
		} else if (ch == '\b' && userInput.length() > 0) { // Backspace
			userInput = userInput.substring(0, userInput.length() - 1);
			userInputLabel.setLabel(userInput);
			centerUserInputLabel();
		}
	}
}
