
public enum QuestionType {
	ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION;
	
	public String toString() {
		switch(this) {
		case ADDITION: return "+";
		case SUBTRACTION: return "-";
		case MULTIPLICATION: return "x";
		case DIVISION: return "/";
		}
		
		return null;
	}
}
