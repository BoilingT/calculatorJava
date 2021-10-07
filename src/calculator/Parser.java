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
	
//	(abc12+27 * 23.0(12abc34
//	Token: Symbol(()
//	Token: Identifier(abc12)
//	Token: Symbol(+)
//	Token: Number(27.0000)
//	Token: Symbol(*)
//	Token: Number(23.0000)
//	Token: Symbol(()
//	Token: Number(12.0000)
//	Token: Identifier(abc34)
//	Token: Stop

	public static Double parseExpression(String expression) {
		char[] expressionArr = "7+53+3".toCharArray();
		//Integer index = new Integer(0);
		String term1 = parseTerm(0, expressionArr);
		double result = Double.parseDouble(term1);
		
//		for (int i = 0; i < token.length(); i++) {
//			char t = token.charAt(i);
//			double term2 = parseTerm(token.substring(i));
//			if (t == '+') {
//				result += term2;
//			}else {
//				result -= term2;
//			}
//		}
//		return result;
		int index = term1.length();
		System.out.println(index);
		char t = expressionArr[index];
		while (t == '+' || t == '-') {
			index++;
			//expressionArr = expressionArr.toString().substring(index).toCharArray();
			double term2 = Double.parseDouble(parseTerm(index, expressionArr));
			if (t == '+') {
				result += term2;
			}else {
				result -= term2;
			}
			t = expressionArr[index];
		}
		
		return result;
	}
	
	private static boolean isNumb(char Char) {
		try {
			Double.parseDouble(String.valueOf(Char));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean isSymbol(char symbol, char[] symbols) {
		for (int i = 0; i < symbols.length; i++) {
			if (symbols[i] == symbol) {
				return true;
			}
		}
		return false;
	}
	
	public static String[] tokenize(String expression) {
		ArrayList<String> tokens = new ArrayList<>();
		char[] symbols = {'+', '-', '*', '/'};
		
		System.out.println("Length: " + expression.length());
		for (int i = 0; i < expression.length(); i++) {
			char Char = expression.charAt(i);
			
			if (isNumb(Char)) {
				String numb = "";
				for (int j = i; j < expression.length(); j++) {
					char tempChar = expression.charAt(j);
					if (!isNumb(tempChar)) {break;}
					numb += tempChar;
				}
				i += numb.length()-1;
				tokens.add("Number(" + numb + ")");
				
			}else if (isSymbol(Char, symbols)) {
				tokens.add("Symbol(" + Char + ")");
				
			}else { //Identifier
				String identifier = "";
				for (int j = i; j < expression.length(); j++) {
					char tempChar = expression.charAt(j);
					if(isNumb(tempChar) || isSymbol(tempChar, symbols)) {break;}
					identifier += tempChar;
				}
				i += identifier.length()-1;
				tokens.add("Identifier(" + identifier + ")");
			}
		}
		tokens.add("Stop");
		
		String[] tokenList = new String[tokens.size()];
		
		for (int i = 0; i < tokenList.length; i++) {
			tokenList[i] = tokens.get(i);
		}
		
		return tokenList;
	}
	
	public static String parseTerm(int startIndex, char[] token) {
		String str = "";
		String factor1 = parseFactor(0, token);
		for (; startIndex < token.length; startIndex++) {
			char Char = token[startIndex];
			try {
				str += String.valueOf(Integer.parseInt(String.valueOf(Char)));
			} catch (Exception e) {
				break;
			}
		}
		
		System.out.println("Split: " + str);
		return str;
	}
	
	public static String parseFactor(int startIndex, char[] token) {
		String str = "";
		
		return str;
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
