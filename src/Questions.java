import java.util.Random;

public class Questions {
	private QuestionType type;
	private int valueOne;
	private int valueTwo;
	private int answer;
	
	public Questions(QuestionType type) {
		this.type = type;
		
	}
	
	public void oneDigitGenerator() {
	    valueOne = (int)(Math.random() * 9);
	    valueTwo = (int)(Math.random() * 9);
	}
	
	public void twoDigitGenerator() {
	    valueOne = (int)(Math.random() * 89 + 10);
		valueTwo = (int)(Math.random() * 89 + 10);
	}
	
	
	public String additionQuestion() {
		answer = valueOne + valueTwo;
		return valueOne + " + " + valueTwo;
	}
	
	public String subtractionQuestion() {
		if (valueOne < valueTwo) {
			int temp = valueOne;
			valueOne = valueTwo;
			valueTwo = temp;
		}
		answer = valueOne - valueTwo;
		return valueOne + " - " + valueTwo;
	}
	
	public String multiplicationQuestion() {
		answer = valueOne * valueTwo;
		return valueOne + " x " + valueTwo;
	}
	
	public String divisionQuestion() {
		while (valueOne % valueTwo != 0) {
			valueOne = (int)(Math.random() * 89 + 10);
			valueTwo = (int)(Math.random() * 99);
		}
		answer = valueOne / valueTwo;
		return valueOne + " / " + valueTwo;
	}
	
	public int getAnswer() {
		return answer;
	}
	
	public QuestionType getType() {
		return type;
	}
}
