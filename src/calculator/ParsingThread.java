package calculator;

import java.util.concurrent.CountDownLatch;

import javax.swing.JTextArea;

public class ParsingThread implements Runnable{

	private Parser parser;
	private CountDownLatch latch;
	private JTextArea textArea;
	private String expression;
	
	public ParsingThread(Parser _parser, String _expression, JTextArea _textArea, CountDownLatch _latch) {
		textArea = _textArea;
		expression = _expression;
		parser = _parser;
		latch = _latch;
	}
	
	@Override
	public void run() {
		try {
			//value = parser.parse(expression);
			double value = parser.parse(expression);
			textArea.setText("= " + String.valueOf(value));
		} catch (Exception e) {
			//value = e.getMessage();
			textArea.setText(e.getMessage());
		}
		latch.countDown();
	}
	
}
