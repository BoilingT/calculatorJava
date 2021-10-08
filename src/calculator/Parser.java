package calculator;

import java.util.ArrayList;

public class Parser {
	public static Double calculateExpression(String exp) {
		Double result = (double) 0;
		
		String addition = parseAddition(exp);
		//String[] subtraction = parseSubtraction(addition);
		//String[] multiplication = parseMultiplication(subtraction);
		
		result = Double.parseDouble(addition);
		
		return result;
	}

	public static Double parseExpression(ArrayList<Token> tokens) {
		//Integer index = new Integer(0);
		for (Token token : tokens) {
			System.out.print("[" + token.value + ", " + token.type.toString() + "]");
		}
		System.out.println();
		double result = parseTerm(tokens);

		Token token = tokens.get(0);
		while (token.isSymbol("+") || token.isSymbol("-")) {
			tokens.remove(0);
			double term = parseTerm(tokens);
			if (token.isSymbol("+")) {
				result += term;
			} else {
				result -= term;
			}
			token = tokens.get(0);
			System.out.println("Token value: " + token.value);
		}
		
		return result;
	}
	
	public static Double parseTerm(ArrayList<Token> tokens) {
		double result = parseFactor(tokens);
		
		Token token = tokens.get(0);
		
		if (token.type == Token.Type.Number) {
			return Double.parseDouble(token.value.toString());
		}
		while (token.isSymbol("*") || token.isSymbol("/")) {
			tokens.remove(0);
			double term = parseFactor(tokens);
			if (token.isSymbol("*")) {
				result *= term;
			} else {
				result /= term;
			}
			token = tokens.get(0);
		}
		
		return result;
	}
	
	public static Double parseFactor(ArrayList<Token> tokens) {
		Token token = tokens.get(0);
		double sign = token.isSymbol("-") ? -1 : 1;
		if (token.isSymbol("+") || sign < 0) {
			tokens.remove(0);
		}
		double result = parseItem(tokens);
		System.out.println("res: " + result);
		while (tokens.get(0).isSymbol("^")) {
			tokens.remove(0);
			double factor = parseFactor(tokens);
			System.out.println("Factor: " + factor);
			result = Math.pow(result, factor);
		}
		return result * sign;
	}
	
	public static Double parseItem(ArrayList<Token> tokens) {
		Token token = tokens.get(0);
		tokens.remove(0);
		
		if (token.isNumb()) {
			return Double.parseDouble(token.value.toString());
		}
		if (token.isIdentifier()) {
			if (token.value.toString().toLowerCase().equals("e")) {
				return Math.E;
			}else if (token.value.toString().toLowerCase().equals("pi")) {
				return Math.PI;
			}
		}
		if (token.isFunc()) {
			tokens.remove(0);
			System.out.println("func token: " + token.value);
			System.out.println();
			double expression = parseExpression(tokens);
			tokens.remove(0);
			System.out.println();
			System.out.println("func expression: " + expression);
			if (token.isFunc("sin")) {
				return Math.sin(Math.toRadians(expression));
			}else if(token.isFunc("cos")){
				return Math.cos(Math.toRadians(expression));
			}else if(token.isFunc("tan")){
				return Math.tan(Math.toRadians(expression));
			}else if(token.isFunc("sqrt")){
				if (expression > 0) {
					return Math.sqrt(expression);					
				}else {
					System.err.println("---------------");
				}
			}else if(token.isFunc("abs")){
				return Math.abs(expression);
			}
		}
		
		if (!token.isSymbol("(")) {
			System.err.println("Error, '(' not found");
		}
		double expression = parseExpression(tokens);
		if (!tokens.get(0).isSymbol(")")) {
			System.err.println("Error, ')' not found");
		}
		tokens.remove(0);
		return expression;
	}
	
	public static Double parse(String expression) {
		return parseExpression(Token.tokenize(expression));
	}
	
	public static String[] split(String expression, char regex) {
		ArrayList<String> chunks = new ArrayList<>();
		ArrayList<Character> currentChunk = new ArrayList<>();
		
		String exp = expression;
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
	
	public static String parseAddition(String expression) {
		
		String[] additionSplit = split(expression, '+'); //Old expressions
		String[] exp = parseSubtractions(additionSplit); //The new expressions after they have been processed by other parsers
		double result = 0;
		
		System.out.print("Addition split results: ");
		for (int i = 0; i < additionSplit.length; i++) { //Print the result of the addition split
			System.out.print("{ " + additionSplit[i] + (i < additionSplit.length-1 ? " }, " : " }\n"));
		}
		
		for (int i = 0; i < exp.length; i++) { //Add all values together
			result += Double.parseDouble(exp[i]);
		}
		
		System.out.print("Addition results: { " + result + " }\n\n" );
		
		return String.valueOf(result);
	}
	
	public static String[] parseSub(String[] expressions) {
		final char splitter = '-';
		String[] numbs = new String[expressions.length]; //The new expressions or numbers if they had completely been parsed

		//Parse for other possible operations in the expressions
		String[] parsedExpressions = parseMultiplication(expressions); // -3 * 5 - 3 => (-3 * 5) (-3)
		
		//Because there can be multiple new parsed expressions loop through every single one and parse them
		for (int i = 0; i < parsedExpressions.length; i++) {
			String[] subtractionSplit = split(parsedExpressions[i], splitter);
			
		}
		
		//Subtract all values that can be subtracted
		for (int i = 0; i < parsedExpressions.length; i++) {
			
		}
		
		return numbs;
	}

	public static String[] parseSubtractions(String[] expressions) {
		
		String[] numbs = new String[expressions.length]; //The new expressions or numbers if they had completely been parsed
		
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
		}
		System.out.print("Subtraction results: ");
		for (int j = 0; j < numbs.length; j++) {
			System.out.print("{ " + numbs[j] + (j < numbs.length-1 ? " }, " : " }\n\n"));
		}
		return numbs;
	}

	public static String[] parseMultiplication(String[] expressions) { //(12) | (5) (5*2)
				

		String[] numbs = new String[expressions.length];
		for (int i = 0; i < expressions.length; i++) {
			String[] multiplicationSplit = split(expressions[i], '*'); //3(2-1) // (3) (3 / 3)
			System.out.print("Multiplication split results: ");
			for (int j = 0; j < multiplicationSplit.length; j++) {
				System.out.print("{ " + multiplicationSplit[j] + (j < multiplicationSplit.length-1 ? " }, " : " }\n"));
			}
						
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
	
	public static String[] parseDivisonSplit(String[] expressions) {
	
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
				String exp = divisonSplit[0].substring(1, divisonSplit[0].length()-1);
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
					String exp = divisonSplit[j].substring(1, divisonSplit[j].length()-1);
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
