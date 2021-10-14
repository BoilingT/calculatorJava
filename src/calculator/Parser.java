package calculator;

import java.util.ArrayList;

public class Parser {
	private boolean degree = true;
	
	//Set wether angles should be calculated in degrees or radians
	public void setDegree(boolean state) {
		degree = state;
	}
	
	public boolean isDegree() {
		return degree;
	}

	//This does it all, it tries to add or subtract the given tokens but to make sure there are just terms it parses them.
	public double parseExpression(ArrayList<Token> tokens) throws Exception{
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
		}
		
		return result;
	}
	
	//Returns a term from dividing or multiplying two values from the given token list.
	public double parseTerm(ArrayList<Token> tokens) throws Exception{
		double result = parseFactor(tokens);
		
		Token token = tokens.get(0);
		
		while (token.isSymbol("*") || token.isSymbol("/")) {
			tokens.remove(0);
			double term = parseFactor(tokens);
			if (token.isSymbol("*")) {
				System.out.print(result + " * " + term);
				result *= term;
			} else {
				System.out.print(result + " / " + term);
				result /= term;
			}
			token = tokens.get(0);
		}
		
		return result;
	}
	
	//Returns as a result a factor from converting the given list into numbers, trig functions, powers, etc.
	public double parseFactor(ArrayList<Token> tokens) throws Exception{
		Token token = tokens.get(0);
		double sign = token.isSymbol("-") ? -1 : 1;
		if (token.isSymbol("+") || sign < 0) {
			tokens.remove(0);
		}
		double result = parseItem(tokens);

		if (tokens.get(0).isSymbol("^")) {
			tokens.remove(0);
			double factor = parseFactor(tokens);
			if((factor < 1 && factor > -1 && factor != 0) && result < 0) {
				throw new Exception("Imaginary");
			}
			result = Math.pow(result, factor);
		}
		return result * sign;
	}
	
	//Returns a number that has been processed by functions.
	public double parseItem(ArrayList<Token> tokens) throws Exception {
		Token token = tokens.get(0);
		tokens.remove(0);
		
		if (token.isNumb()) {
			return Double.parseDouble(token.value.toString());
		}
		if (token.isIdentifier()) {
			return parseIdentifier(token);
		}
		if (token.isFunc()) {
			return parseFunction(token, tokens);
		}
		if (!token.isSymbol("(")) {
			throw new Exception("...");				
		}
		double expression = parseExpression(tokens);
		if (!tokens.get(0).isSymbol(")")) {
			throw new Exception("Expected: ')'");
		}
		tokens.remove(0);
		return expression;
	}
	
	//Returns variable constants.
	public double parseIdentifier(Token identifier) throws Exception {
		String tokenValue = identifier.value.toString();
		if (tokenValue.equals("e")) {
			return Math.E;
		}else if (tokenValue.toLowerCase().equals("pi") || tokenValue.equals("π")) {
			return Math.PI;
		}else {
			return 0;
		}
	}
	
	//Returns the value from processing certain values with functions.
	public double parseFunction(Token funcToken, ArrayList<Token> tokens) throws Exception{
		if(tokens.get(0).isStop()) {throw new Exception("Expected: '('");}
		tokens.remove(0);
		double value = parseExpression(tokens);
		if (!tokens.get(0).isSymbol(")")) {
			throw new Exception("Expected: ')'");
		}
		tokens.remove(0);
		
		if (funcToken.isFunc("sin") || funcToken.isFunc("cos") || funcToken.isFunc("tan")) {
			double angle = degree ? Math.toRadians(value) : value;
			if (funcToken.isFunc("sin")) {return Math.sin(angle);}
			else if(funcToken.isFunc("cos")){return Math.cos(angle);}
			else if(funcToken.isFunc("tan")){return Math.tan(angle);}
		}else if(funcToken.isFunc("sqrt")){
			if (value > 0) {
				return Math.sqrt(value);					
			}else {
				throw new Exception("Imaginary");
			}
		}else if(funcToken.isFunc("abs")){return Math.abs(value);}
		else if(funcToken.isFunc("log10") || funcToken.isFunc("lg")){ return Math.log10(value);}
		else if(funcToken.isFunc("log") || funcToken.isFunc("ln")){return Math.log(value);}
		return 0;
	}
	
	//Start the parsing process
	public double parse(String expression) throws Exception{
		return parseExpression(Token.tokenize(expression));
	}
	
	//Start the parsing process but with a variable
	public double parse(String expression, String var, double value) throws Exception{
		//If there is a variable inside the expression, replace it with the given value.
		//eg: x^2, x=1 <=> x^2=1^2=1
		return parseExpression(Token.tokenize(expression.replace(var, "(" + String.valueOf(value) + ")")));
	}
}
