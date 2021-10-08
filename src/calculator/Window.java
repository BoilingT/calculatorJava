package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends Window_Design{
	
	private static String latestOperation = "";
	
	private static double a = 0;
	private static double b = 0;
	private static double result = 0;
	private int mouseX;
	private int mouseY;
	private int width;
	private int height;
	private int margin = 10;
	
	public void Init() {
//		ArrayList<Token> tokens = Token.tokenize("sin(30)");
//		for (Token token : tokens) {
//			System.out.println("Token: " + token.type.toString() + "(" + token.value + ")");
//		}
//		
		//System.out.println("Result: " + Parser.parseExpression(tokens));
		InitializeComponents();
	}
	
	public void set(int ints) {
		ints = 1;
	}
	
	@Override
	public void MouseDragged(MouseEvent e) {
		Component component = e.getComponent();
		int x=e.getXOnScreen();
		int y=e.getYOnScreen();
		if (component == topBorderLabelPanel) {
			
			window.setLocation(x-mouseX, y-mouseY);
			
		}else if (component == window.getRootPane()) {
			int diffx = x - (int) (window.getLocation().getX() + width);
			int diffy = y - (int) (window.getLocation().getY() + height);
			int w = width + diffx;
			int h = height + diffy;
			window.setSize(new Dimension(w, h));
		}
	}
	
	@Override
	public void MouseMoved(MouseEvent e) {
		Component component = e.getComponent();
		if (component == topBorderLabelPanel) {
			//component.setCursor(new Cursor(Cursor.MOVE_CURSOR));

		}else if (component == window.getRootPane()) {
			Point pos = window.getMousePosition();
			if (pos != null) {
				//System.out.println(pos);
//				if(pos.getX() < 4 && pos.getY() < 4) { //NW
//					component.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
//					
//				}else if(pos.getX() < 4 && pos.getY() > window.getHeight() - 4) { //SW
//					component.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));	
//					
//				}
				if(pos.getX() > window.getWidth() - margin && pos.getY() > window.getHeight() - margin) { //SE
					component.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));	
				}
//				else if(pos.getX() > window.getWidth() - 4 && pos.getY() < 4) { //NE
//					component.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));	
//					
//				} else if(pos.getX() < 4 || pos.getX() > window.getWidth()-4) { //E
//					component.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));	
//					
//				}else if(pos.getY() < 4 || pos.getY() > window.getHeight()-4) { //N
//					component.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));	
//					
//				}
				else {
					component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}else {
			component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	@Override
	public void MousePressed(MouseEvent e) {
        
		width = (int) window.getBounds().getWidth();
		height = (int) window.getBounds().getHeight();
		mouseX=e.getX();
		mouseY=e.getY();  

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
	}
	
	@Override
	public void keyRelease(KeyEvent e) {
		calcBtnClicked();
	}
	
	@Override
	public void numbBtnClicked(Object obj) {
		JButton btn = (JButton) obj;
//		if (textArea.getText().equals("0") && !btn.getText().equals(".")) {
//			textArea.setText(null);
//		}
		textArea.insert(btn.getText(), textArea.getCaretPosition());
		calcBtnClicked();
	}
	
	@Override
	public void divideBtnClicked() {
		
		textArea.insert("/", textArea.getCaretPosition());
		latestOperation = "\\/";
	}
	
	@Override
	public void multBtnClicked() {
		textArea.insert("*", textArea.getCaretPosition());
		latestOperation = "\\*";
	}
	
	@Override
	public void subtractBtnClicked() {
		textArea.insert("-", textArea.getCaretPosition());
		latestOperation = "\\-";
	}
	
	@Override
	public void addBtnClicked() {
		textArea.insert("+", textArea.getCaretPosition());
		latestOperation = "\\+";
	}
	
	@Override
	public void sqrtBtnClicked() {
		textArea.insert("sqrt(", textArea.getCaretPosition());
		latestOperation = "sqrt";
	}
	
	@Override
	public void squareBtnClicked() {
		textArea.insert("²", textArea.getCaretPosition());
		latestOperation = "square";
		calcBtnClicked();
	}
	
	@Override
	public void openPBtnClicked() {
		textArea.insert("(", textArea.getCaretPosition());
		calcBtnClicked();
	}
	
	@Override
	public void closePBtnClicked() {
		textArea.insert(")", textArea.getCaretPosition());
		calcBtnClicked();
	}
	
	@Override
	public void clearBtnClicked() {
		textArea.setText("0");
		textResult.setText(null);
		//System.out.println("Parsed: " + Parser.parseExpression("27+5"));
	
	}
		
	@Override
	public void calcBtnClicked() {
		
		String inputText = textArea.getText().trim().toLowerCase();
		if (inputText.length() > 0) {
			
			System.out.println("text: " + inputText);
			inputText = inputText.replace("\\--", "+").replace("\\+-", "-").replace(',', '.').replace('×', '*').replace('−', '-').replace('÷', '/').replace("²", "^2");
			
			System.out.println("new text: " + inputText);
			String resultStr = "";
			
			if (checkBox.isSelected()) {
				try {
					//resultStr = String.valueOf(Parser.calculateExpression(inputText)); // = 17 - 10 = 7 "12+5-5*2"
					resultStr = String.valueOf(Parser.parse(inputText)); // = 17 - 10 = 7 "12+5-5*2"
					textResult.setText("= " + resultStr);
					
				}catch (Exception e) {
					//if(e.getMessage().toString().toLowerCase().contains("out of bounds")) return;
					textResult.setText(e.getMessage());
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
	
	
}
