package calculator;

import java.util.ArrayList;

public class Token{
	
	public Type type = null;
	public Object value = null;
	private static char[] symbols = {'+', '-', '*', '/', '(', ')', '^'};
	private static String[] Functions = {"sin", "cos","tan","sqrt","abs"};
	
	public static enum Type{
		Symbol,
		Identifier,
		Number,
		Func,
		Stop
	}
	
	public Token(Type _type, Object _value) {
		type = _type;
		if (type == Type.Number) {
			value = _value;
		}else {
			value = String.valueOf(_value);
		}
	}
	
	public boolean isNumb() {
		return type == Type.Number;
	}
	
	private static boolean isNumb(char Char) {
		try {
			Double.parseDouble(String.valueOf(Char));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isSymbol(String Symbol) {
		return type == Type.Symbol && value.toString().equals(Symbol);
	}
	
	public boolean isIdentifier() {
		return type == Type.Identifier;
	}
	
	public boolean isFunc() {
		return type == Type.Func;
	}
	
	public boolean isFunc(String func) {
		return type == Type.Func && value.equals(func);
	}
	
	private static boolean isFunc(String Func, String[] funcs) {
		for (int i = 0; i < funcs.length; i++) {
			if (funcs[i].equals(Func)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isStop() {
		return type == Type.Stop;
	}
	
	private static boolean isSymbol(char symbol, char[] symbols) {
		for (int i = 0; i < symbols.length; i++) {
			if (symbols[i] == symbol) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Token> tokenize(String expression) {
		ArrayList<Token> tokens = new ArrayList<>();
		
		System.out.println("Expression: \"" + expression + "\" Length: " + expression.length());
		for (int i = 0; i < expression.length(); i++) {
			char Char = expression.charAt(i);
			if (Char != ' ') {
				if (isNumb(Char)) {
					String numb = "";
					for (int j = i; j < expression.length(); j++) {
						char tempChar = expression.charAt(j);
						if (!isNumb(tempChar) && tempChar != '.') {break;}
						numb += tempChar;
					}
					i += numb.length() > 0 ? numb.length()-1 : 0;
					tokens.add(new Token(Type.Number, numb));
					
				}else if (isSymbol(Char, symbols)) {
					tokens.add(new Token(Type.Symbol, String.valueOf(Char)));
					
				}else { //Identifier or func
					String identifier = "";
					for (int j = i; j < expression.length(); j++) {
						char tempChar = expression.charAt(j);
						if(isSymbol(tempChar, symbols)) {break;}
						identifier += tempChar;
					}
					i += identifier.length() > 0 ? identifier.length()-1 : 0;
					if(isFunc(identifier, Functions)) {
						tokens.add(new Token(Type.Func, identifier));						
					}else {
						tokens.add(new Token(Type.Identifier, identifier));						
					}
				}
			}
		}
		tokens.add(new Token(Type.Stop, "Stop"));
		
//		Token[] tokenList = new Token[tokens.size()];
//		
//		for (int i = 0; i < tokenList.length; i++) {
//			tokenList[i] = tokens.get(i);
//		}
		
//		return tokenList;
		return tokens;
	}
}
