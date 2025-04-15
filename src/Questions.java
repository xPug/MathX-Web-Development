import java.util.Random;

public class Questions {
	private QuestionType type;
	private int valueOne;
	private int valueTwo;
	
	public Questions(QuestionType type) {
		this.type = type;
		
	}
	
	public void oneDigitGenerator() {
	    valueOne = (int)(Math.random() * 10 + 1);
	    valueTwo = (int)(Math.random() * 10 + 1);
	    
	}
	
	public void twoDigitGenerator() {
	    valueOne = (int)(Math.random() * 100 + 1);
		valueTwo = (int)(Math.random() * 100 + 1);
	}
	
	
	public String additionQuestion() {
		return valueOne + " + " + valueTwo;
	}
	
	public String subtractionQuestion() {
		return valueOne + " - " + valueTwo;
	}
	
	public String multiplicationQuestion() {
		return valueOne + " x " + valueTwo;
	}
	
	public String divisionQuestion() {
		return valueOne + " / " + valueTwo;
	}
	
	public QuestionType getType() {
		return type;
	}
}
