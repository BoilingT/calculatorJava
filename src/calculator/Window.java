package calculator;

import java.util.ArrayList;

public class Window extends Window_Design{
	
	private static ArrayList<Double> numbers = new ArrayList<Double>();
	private static ArrayList<Character> operations = new ArrayList<Character>();
	private static char latestOperation = ' ';
	private static String latestResult = "";
	
	public void Init() {
		InitializeComponents();
	}
	
	@Override
	public void divideBtnClicked() {
		textField.setText(textField.getText() + "/");
		latestOperation = '/';
	}
	
	@Override
	public void multBtnClicked() {
		textField.setText(textField.getText() + "*");
		latestOperation = '*';
	}
	
	@Override
	public void subtractBtnClicked() {
		textField.setText(textField.getText() + "-");
		latestOperation = '-';
	}
	
	@Override
	public void addBtnClicked() {
		textField.setText(textField.getText() + "+");
		latestOperation = '+';
	}
	
	@Override
	public void clearBtnClicked() {
		textField.setText(null);
	}
	
	@Override
	public void calcBtnClicked() {
		String inputText = textField.getText().trim();
		String result = "";
		System.out.println(inputText + " == " + latestResult + " : " + (inputText.equals(latestResult)));
		if (inputText.equals(latestResult)) {
			result = parseAddition(latestResult + String.valueOf(latestOperation) + latestResult);
		}else {
			result = parseAddition(textField.getText().trim()); // = 17 - 10 = 7 "12+5-5*2"
		}
		latestResult = result;
		textField.setText(result);
	}
	
	private static String[] split(String expression, char regex) {
		ArrayList<String> chunks = new ArrayList<>();
		ArrayList<Character> currentChunk = new ArrayList<>();
		
		String exp = expression.trim();
		int braces = 0;
		
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) == '(') {
				braces++;
			}else if (exp.charAt(i) == ')') {
				braces--;
			}
			
			if (braces == 0 && exp.charAt(i) == regex) {
				String str = "";
				
				for (int j = 0; j < currentChunk.size(); j++) {
					str += String.valueOf(currentChunk.get(j));
				}
				
				chunks.add(str.trim());
				currentChunk.clear();
			}else {
				currentChunk.add(expression.charAt(i));
			}
		}
		
		if (currentChunk.size() > 0) {
			String str = "";
			
			for (int j = 0; j < currentChunk.size(); j++) {
				str += String.valueOf(currentChunk.get(j));
			}
			
			chunks.add(str.trim());
		}
		
		String[] result = new String[chunks.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = chunks.get(i);
		}
		
		return result;
	}
	
	private static String parseAddition(String expression) {
		
		double result = 0;
		String[] numbers = split(expression, '+'); // (12) (5-5*2)
		String[] exp = parseSubtraction(numbers);
		
		for (int i = 0; i < exp.length; i++) {
			result += Double.parseDouble(exp[i]);
		}
		
		return String.valueOf(result);
	}

	private static String[] parseSubtraction(String[] expressions) {// (12) (5-5*2)
		
		int totalSize = 0;
		String[] numbs = new String[expressions.length];
		
		for (int i = 0; i < expressions.length; i++) {
			String[] substractionSplit = split(expressions[i], '-'); // (12) | (5) (5*2)
			for(int j = 0; j < substractionSplit.length; j++) {
				System.out.print("1: { " + substractionSplit[j] + " } ");
			}
			System.out.println("");
			
			String[] parsedMultiplication = parseMultiplication(substractionSplit);
			
			double firstValue = Double.parseDouble(parsedMultiplication[0]);
			for(int j = 1; j < parsedMultiplication.length; j++) {
				System.out.print("2: { " + parsedMultiplication[j] + " } ");
				firstValue -= Double.parseDouble(parsedMultiplication[j]);
			}
			numbs[i] = (String.valueOf(firstValue));
			System.out.println("");
			totalSize++;
		}
		
		for (int i = 0; i < numbs.length; i++) {
			System.out.println("NUMBS: " + numbs[i]);
		}
		
		//String[] exp = parseMultiplication((String[])numbs.toArray());
		
		return numbs;
	}

	private static String[] parseMultiplication(String[] expressions) { //(12) | (5) (5*2)
				

		
		String[] numbs = new String[expressions.length];
		for (int i = 0; i < expressions.length; i++) {
			String[] multiplicationSplit = split(expressions[i], '*'); //3(2-1) // (3) (3 / 3)
			//3*(2-1) => (3) ((2-1))
			double firstVal = 1;
			for (int j = 0; j < multiplicationSplit.length; j++) {
				if(multiplicationSplit[j].startsWith("(")) {
					System.out.println("Here");
					System.out.println(multiplicationSplit[j]);
					String exp = multiplicationSplit[j].substring(1, multiplicationSplit[j].length()-1);
					System.out.println(exp);
					String splitValue = parseAddition(exp);
					if(splitValue.contains("\\/")) {
						splitValue = parseDivisonSplit(new String[] {multiplicationSplit[j]})[0];
					}
					firstVal *= Double.parseDouble(splitValue);
				}else {
					String splitValue = multiplicationSplit[j];
					if(splitValue.contains("/")) {
						splitValue = parseDivisonSplit(new String[] {multiplicationSplit[j]})[0];
					}
					firstVal *= Double.parseDouble(splitValue);
				}
			}
			numbs[i] = String.valueOf(firstVal);
		}
		
		for (int i = 0; i < numbs.length; i++) {
			System.out.println((i) + " m: " + numbs[i]);
		}
				
		
		return numbs;
	}
	
private static String[] parseDivisonSplit(String[] expressions) { //(12) | (5) (5*2)
				

		
		String[] numbs = new String[expressions.length];
		for (int i = 0; i < expressions.length; i++) {
			String[] divisonSplit = split(expressions[i], '/'); // (3*3) (3)
			//3*(2-1) => (3) ((2-1))
			double firstVal = Double.parseDouble(divisonSplit[0]);
			for (int j = 1; j < divisonSplit.length; j++) {
				if(divisonSplit[j].startsWith("(")) {
					System.out.println("Here2");
					System.out.println(divisonSplit[j]);
					String exp = divisonSplit[j].substring(1, divisonSplit[j].length()-1);
					System.out.println(exp);
					firstVal /= Double.parseDouble(parseAddition(exp));
				}else {
					firstVal /= Double.parseDouble(divisonSplit[j]);
				}
			}
			numbs[i] = String.valueOf(firstVal);
		}
		
		for (int i = 0; i < numbs.length; i++) {
			System.out.println((i) + " n: " + numbs[i]);
		}
				
		
		return numbs;
	}
}
