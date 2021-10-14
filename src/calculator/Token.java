package calculator;

import java.util.ArrayList;

public class Token{
	
	public Type type = null;
	public Object value = null;
	private static final char[] SYMBOLS = {'+', '-', '*', '/', '(', ')', '^'};
	private static final String[] FUNCTIONS = {"sin", "cos","tan","sqrt","abs", "log10", "log", "lg", "ln"};
	
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
		}else { //The value is allowed to have text.
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
	
	public boolean isSymbol() {
		return type == Type.Symbol;
	}
	
	public boolean isIdentifier() {
		return type == Type.Identifier;
	}
	
//	private static boolean isIdentifier(char id) {
//		return id >= 'a' && id <= 'z';
//	}
	
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
	
//  Example on different tokens taken from an expression
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
	
	/*Parse an expression into useful parts by looking at each character,
	 * depending on what type they are, and converting a certain number of them 
	 * into their unique tokens in a "tokenlist".
	 * */
	public static ArrayList<Token> tokenize(String expression) {
		ArrayList<Token> tokens = new ArrayList<>();
		
		for (int i = 0; i < expression.length(); i++) { //Go through each character in the expression.
			char Char = expression.charAt(i); //Save the current character in a variable for easier use.
			if (Char != ' ') { //Don't parse empty spaces, they will just be ignored.
				if (isNumb(Char)) {
					/*
					 * If the current char's type is of type "number"
					 * add it and the next following characters of type "number" it can find.
					 * Stop when the current character "tempchar" isn't a number anymore and add the token to the tokenlist.
					 */
					String numb = "";
					for (int j = i; j < expression.length(); j++) {
						char tempChar = expression.charAt(j);
						//If the character would be a dot I wouldn't want to skip it if the current number contained decimals.
						if (!isNumb(tempChar) && tempChar != '.') {break;}
						numb += tempChar;
					}
					/*
					 * Because we don't want to look at the characters it already has seen
					 * skip ahead by the length of the last number that was added to the tokenlist.
					 *
					 * If the string inside the number variable would for some reason be empty we don't want to have a number length of '0'
					 * and then going back by '1', because then this loop would be endless and would eventually crash the program
					 * because of not enough system resources
					 */
					i += numb.length() > 0 ? numb.length()-1 : 0;
					tokens.add(new Token(Type.Number, numb));
					
				}else if (isSymbol(Char, SYMBOLS)) {
					//My definiton of a symbol makes that a symbol will always only be one character long,
					//therefor I just add the token as it is.
					tokens.add(new Token(Type.Symbol, String.valueOf(Char)));

					if(tokens.size() > 1 && tokens.get(tokens.size()-1).isSymbol("(") && tokens.get(tokens.size()-2).isNumb()) {
						tokens.add(tokens.size()-1, new Token(Token.Type.Symbol, "*"));							
					}
					
				}else { //Identifier or func
					/*
					 * If the program came this far there could only be two possibilites left:
					 * * Either the token will be an identifier (a variable).
					 * * Or it would be a function (sin, cos, tan, sqrt)
					 */
					String str = "";
					for (int j = i; j < expression.length(); j++) {
						char tempChar = expression.charAt(j);
						//Only stop adding the characters if it reaches a symbol.
						//This is because we want to add the numbers that follows after the characters aswell, 
						//they are also a part of the name.
						if(isSymbol(tempChar, SYMBOLS)) {break;}
						str += tempChar;
					}
					if(tokens.size() > 0 && tokens.get(tokens.size()-1).isNumb()) {tokens.add(new Token(Token.Type.Symbol, "*"));}
					i += str.length() > 0 ? str.length()-1 : 0; //Same thing here, we don't want to be stuck in an endless loop.
					if(isFunc(str, FUNCTIONS)) { //If the functions contain a function that matches the string then add it.
						tokens.add(new Token(Type.Func, str));
					}else {
						tokens.add(new Token(Type.Identifier, str));						
					}
				}
			}
		}
//		for (Token token : tokens) {
//			System.out.print("last[" + token.value + ", " + token.type.toString() + "]");
//		}
		tokens.add(new Token(Type.Stop, "Stop")); //A way for knowing when the expression ends is by adding a stop token to the list.
		return tokens;
	}
}
