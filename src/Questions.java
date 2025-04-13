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
	}
	
	public void twoDigitGenerator() {
		valueTwo = (int)(Math.random() * 100 + 1);
	}
	
	
	public String additionQuestion() {
		return "";
	}
	
	public String subtractionQuestion() {
		return "";
	}
	
	public String multiplicationQuestion() {
		return "";
	}
	
	public String divisionQuestion() {
		return "";
	}
	
	public QuestionType getType() {
		return type;
	}
}
