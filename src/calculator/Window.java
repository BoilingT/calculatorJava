package calculator;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class Window extends Window_Design{
	
	private static ArrayList<Double> numbers = new ArrayList<Double>();
	private static ArrayList<Character> operations = new ArrayList<Character>();
	private static String latestOperation = "";
	private static String latestResult = "";
	
	private static double a = 0;
	private static double b = 0;
	private static double result = 0;
	
	public void Init() {
		InitializeComponents();
	}
	
	@Override
	public void keyPress(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER) {
			calcBtn.doClick();
		}
	}
	
	@Override
	public void numbBtnClicked(Object obj) {
		JButton btn = (JButton) obj;
		textArea.setText(textArea.getText() + btn.getText());
	}
	
	@Override
	public void divideBtnClicked() {
		
		textArea.setText(textArea.getText() + "/");
		latestOperation = "\\/";
	}
	
	@Override
	public void multBtnClicked() {
		textArea.setText(textArea.getText() + "*");
		latestOperation = "\\*";
	}
	
	@Override
	public void subtractBtnClicked() {
		textArea.setText(textArea.getText() + "-");
		latestOperation = "\\-";
	}
	
	@Override
	public void addBtnClicked() {
		textArea.setText(textArea.getText() + "+");
		latestOperation = "\\+";
	}
	
	@Override
	public void clearBtnClicked() {
		textArea.setText(null);
	}
	
	@Override
	public void calcBtnClicked() {
		
		String inputText = textArea.getText().trim();
		System.out.println("text: " + inputText);
		inputText = inputText.replaceAll("\\--", "+").replaceAll("\\(-", "(0-").replaceAll("\\+-", "-").replace(',', '.');
		System.out.println("new text: " + inputText);
		String resultStr = "";
		
		if (checkBox.isSelected()) {
			resultStr = parseAddition(inputText.trim()); // = 17 - 10 = 7 "12+5-5*2"
			textArea.setText(inputText + "\n" + "= " + resultStr);
		}else {
			String[] inputs = inputText.split(String.valueOf(latestOperation));
			if (inputs.length >= 2) {
				try{
					
					for (int i = 0; i < inputs.length; i++) {
						System.out.println(inputs[i]);
					}

					a = Double.parseDouble(inputs[0]);
					b = Double.parseDouble(inputs[1]);
					
				}catch (Exception e) {
					System.out.println(e);
					return;
				}
			}else {
				a = result;
			}
	
			if (latestOperation.contains("+")) result = a + b;
			else if(latestOperation.contains("-")) result = a - b;
			else if(latestOperation.contains("*")) result = a * b;
			else if(latestOperation.contains("/")) result = a / b;
			textArea.setText(inputText + "\n" + "= " + String.valueOf(result));
		}
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
			result[i] = chunks.get(i).length() > 0 ? chunks.get(i) : "0";
		}
		
		return result;
	}
	
	private static String parseAddition(String expression) {
		
		double result = 0;
		String[] numbers = split(expression, '+'); // (12) (5-5*2)
		
		System.out.print("Addition split results: ");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print("{ " + numbers[i] + (i < numbers.length-1 ? " }, " : " }\n"));
		}

		String[] exp = parseSubtraction(numbers);
		
		
		for (int i = 0; i < exp.length; i++) {
			result += Double.parseDouble(exp[i]);
		}
		

		System.out.print("Addition results: { " + result + " }\n\n" );
		
		return String.valueOf(result);
	}

	private static String[] parseSubtraction(String[] expressions) {// (12) (5-5*2)
		
		int totalSize = 0;
		String[] numbs = new String[expressions.length];
		
		for (int i = 0; i < expressions.length; i++) {
			String[] substractionSplit = split(expressions[i], '-'); // (12) | (5) (5*2)
			System.out.print("Subtraction split results: ");
			for (int j = 0; j < substractionSplit.length; j++) {
				System.out.print("{ " + substractionSplit[j] + (j < substractionSplit.length-1 ? " }, " : " }\n"));
			}
			
			String[] parsedMultiplication = parseMultiplication(substractionSplit);
			
			double firstValue = Double.parseDouble(parsedMultiplication[0]);
			for(int j = 1; j < parsedMultiplication.length; j++) {
				firstValue -= Double.parseDouble(parsedMultiplication[j]);
			}
			numbs[i] = (String.valueOf(firstValue));
			totalSize++;
		}
		System.out.print("Subtraction results: ");
		for (int j = 0; j < numbs.length; j++) {
			System.out.print("{ " + numbs[j] + (j < numbs.length-1 ? " }, " : " }\n\n"));
		}
		return numbs;
	}

	private static String[] parseMultiplication(String[] expressions) { //(12) | (5) (5*2)
				

		String[] numbs = new String[expressions.length];
		for (int i = 0; i < expressions.length; i++) {
			String[] multiplicationSplit = split(expressions[i], '*'); //3(2-1) // (3) (3 / 3)
			System.out.print("Multiplication split results: ");
			for (int j = 0; j < multiplicationSplit.length; j++) {
				System.out.print("{ " + multiplicationSplit[j] + (j < multiplicationSplit.length-1 ? " }, " : " }\n"));
			}
			
			//3*(2-1) => (3) ((2-1))
			double firstVal = 1;
			for (int j = 0; j < multiplicationSplit.length; j++) {
				
				if(multiplicationSplit[j].startsWith("(")) {
					String exp = multiplicationSplit[j];
					if(exp.contains("/")) {
						exp = parseDivisonSplit(new String[] {exp})[0];
					}else {
						System.out.println("Starts with (");
						System.out.println("Original string: " + multiplicationSplit[j]);
						exp = multiplicationSplit[j].substring(1, multiplicationSplit[j].lastIndexOf(')'));
						System.out.println("New string: " + exp);
					}
					System.out.println("Recursion from multiplication");
					String splitValue = parseAddition(exp);
					System.out.println("Recursion from multiplication stopped");
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
		System.out.print("Multiplication results: ");
		for (int j = 0; j < numbs.length; j++) {
			System.out.print("{ " + numbs[j] + (j < numbs.length-1 ? " }, " : " }\n\n"));
		}
		
		return numbs;
	}
	
private static String[] parseDivisonSplit(String[] expressions) {
	
		String[] numbs = new String[expressions.length];
		
		
		for (int i = 0; i < expressions.length; i++) {
			String[] divisonSplit = split(expressions[i], '/');
			
			System.out.print("Divison split results: ");
			for (int j = 0; j < divisonSplit.length; j++) {
				System.out.print("{ " + divisonSplit[j] + (j < divisonSplit.length-1 ? " }, " : " }\n"));
			}
			
			double firstVal = 0;
			if(divisonSplit[0].startsWith("(")) {
				System.out.println("Starts with ( inside divisonfunc 0");
				System.out.println(divisonSplit[0]);
				String exp = divisonSplit[0].substring(1, divisonSplit[0].lastIndexOf(')'));
				System.out.println(exp);
				System.out.println("Recursion from divison 0");
				firstVal = Double.parseDouble(parseAddition(exp));
				System.out.println("Recursion from divison stopped 0");
			}else {
				firstVal = Double.parseDouble(divisonSplit[0]);
			}
			
			for (int j = 1; j < divisonSplit.length; j++) {
				if(divisonSplit[j].startsWith("(")) {
					System.out.println("Starts with ( inside divisonfunc");
					System.out.println(divisonSplit[j]);
					String exp = divisonSplit[j].substring(1, divisonSplit[j].indexOf(')'));
					System.out.println(exp);
					System.out.println("Recursion from divison");
					firstVal /= Double.parseDouble(parseAddition(exp));
					System.out.println("Recursion from divison stopped");
				}else {
					firstVal /= Double.parseDouble(divisonSplit[j]);
				}
			}
			numbs[i] = String.valueOf(firstVal);
		}
		System.out.print("Divison results: ");
		for (int j = 0; j < numbs.length; j++) {
			System.out.print("{ " + numbs[j] + (j < numbs.length-1 ? " }, " : " }\n\n"));
		}
		return numbs;
	}
}
