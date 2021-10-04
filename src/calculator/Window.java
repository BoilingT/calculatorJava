package calculator;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends Window_Design{
	
	private static String latestOperation = "";
	
	private static double a = 0;
	private static double b = 0;
	private static double result = 0;
	
	public void Init() {
		InitializeComponents();
	}
	
	@Override
	public void closeBtnClick() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}
	
	@Override
	public void maximizeBtnClick() {
		if (window.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
			window.setExtendedState(JFrame.NORMAL);
		}else {
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);			
		}
	}
	
	@Override
	public void minimizeBtnClick() {
			window.setState(JFrame.ICONIFIED);			
	}
	
	@Override
	public void keyPress(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			calcBtn.doClick();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			latestOperation = "";
		}
		else if (e.getKeyCode() == KeyEvent.VK_PLUS) latestOperation = "\\+";
		else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) latestOperation = "\\-";
		else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) latestOperation = "\\*";
		else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) latestOperation = "\\/";
		
		
		/*
		if (e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_3) { //49 <= x <= 51
			int difference = KeyEvent.VK_3 - e.getKeyCode();
			int x = 2-difference;
			int y = 2;
			numbBtns[y][x].doClick();
		}
		
		if (e.getKeyCode() >= KeyEvent.VK_4 && e.getKeyCode() <= KeyEvent.VK_6) { //52 <= x <= 54
			int difference = KeyEvent.VK_6 - e.getKeyCode();
			int x = 2-difference ;
			int y = 1;
			numbBtns[y][x].doClick();
		}
		
		if (e.getKeyCode() >= KeyEvent.VK_7 && e.getKeyCode() <= KeyEvent.VK_9) { //55 <= x <= 57
			int difference = KeyEvent.VK_9 - e.getKeyCode();
			int x = 2-difference ;
			int y = 0;
			numbBtns[y][x].doClick();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_0) { //x = 48
			numbBtns[3][0].doClick();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			String text = textArea.getText();
			if(text.length() > 0) {
				textArea.setText(text.substring(0, text.length()-1));				
			}
		}*/
	}
	
	@Override
	public void keyRelease(KeyEvent e) {
		calcBtnClicked();
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
	public void sqrtBtnClicked() {
		textArea.setText(textArea.getText() + "sqrt(");
		latestOperation = "sqrt";
	}
	
	@Override
	public void squareBtnClicked() {
		textArea.setText(textArea.getText() + "²");
		latestOperation = "square";
	}
	
	@Override
	public void openPBtnClicked() {
		textArea.setText(textArea.getText() + "(");
	}
	
	@Override
	public void closePBtnClicked() {
		textArea.setText(textArea.getText() + ")");
	}
	
	@Override
	public void clearBtnClicked() {
		textArea.setText(null);
	}
	
	@Override
	public void calcBtnClicked() {
		
		String inputText = textArea.getText().trim();
		if (inputText.length() > 0) {
			
			System.out.println("text: " + inputText);
			inputText = inputText.replace("\\--", "+").replace("\\(-", "(0-").replace("\\+-", "-").replace(',', '.').replace('×', '*').replace('−', '-').replace('÷', '/');
			System.out.println("new text: " + inputText);
			String resultStr = "";
			
			if (checkBox.isSelected()) {
				try {
					resultStr = parseAddition(inputText.trim()); // = 17 - 10 = 7 "12+5-5*2"
					textResult.setText("= " + resultStr);
					
				}catch (Exception e) {
					textResult.setText("= ???");
				}
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
				textResult.setText("= " + String.valueOf(result));
			}
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
