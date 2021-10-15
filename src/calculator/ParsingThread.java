package calculator;

import javax.swing.JTextArea;

public class ParsingThread implements Runnable{

	private Parser parser;
	private JTextArea textArea;
	private String expression;
	
	public ParsingThread(Parser _parser, String _expression, JTextArea _textArea) {
		textArea = _textArea;
		expression = _expression;
		parser = _parser;
	}
	
	@Override
	public void run() { // This is called by default by the thread, I only manipulate it to run my code.
		try {
			double value = parser.parse(expression);
			textArea.setText("= " + String.valueOf(value)); // Instead of making the entire program wait for the result, set it when it is done.
		} catch (Exception e) {
			//value = e.getMessage();
			textArea.setText(e.getMessage()); // Set the error message in the textarea.
			e.printStackTrace(); // For debugging.
		}
	}
	
}
