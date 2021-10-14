package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends Window_Design{
	
	private static Parser parser = new Parser();
	private int prevX, prevY;
	private int mouseX;
	private int mouseY;
	private int margin = BORDERWIDTH;
	
	public void Init() {
		InitializeComponents();
		//graphPanel.addGraph(new Graph("sin(x*180/pi)", parser, 0.1f, graphPanel.getWidth(), 0));
		//graphPanel.addGraph(new Graph("(x)^2", parser, 0.1f, graphPanel.getWidth(), 0));
		graphPanel.addGraph(new Graph("x^3-4.5x^2+11", parser, 0.1f, graphPanel.getWidth(), 0));
		graphPanel.addGraph(new Graph("x^e", parser, 0.1f, graphPanel.getWidth(), 0));
		graphPanel.draw();
	}
	
	@Override
	public void MouseDragged(MouseEvent e) {
		Component component = e.getComponent();
		final int x=e.getXOnScreen();
		final int y=e.getYOnScreen();
		if (component == windowTitleLabel) {
			
			window.setLocation(x-mouseX-margin, y-mouseY-margin);
			
		}else if (component == window.getRootPane()) {
			int diffx = x - (int) (window.getLocation().getX());
			int diffy = y - (int) (window.getLocation().getY());
			int w =  diffx;
			int h = diffy;
			graphPanel.draw();
			window.setSize(new Dimension(w, h));
		}else if (component == graphPanel){
			int X = prevX + (e.getX() - graphPanel.getWidth()/2);
			int Y = prevY + (e.getY() - graphPanel.getHeight()/2);
			//System.out.println("x: " + X + "\n y: " + Y);
			graphPanel.draw(X, Y);
		}
	}
	
	@Override
	public void MouseMoved(MouseEvent e) {
		Component component = e.getComponent();
		if (component == windowTitleLabel) {
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
        
//		width = (int) window.getBounds().getWidth();
//		height = (int) window.getBounds().getHeight();
		mouseX=e.getX();
		mouseY=e.getY(); 
		//graphPanel.draw();
		prevX = (int) (graphPanel.getOffset()[0] - mouseX + graphPanel.getWidth()/2);
		prevY = (int) (graphPanel.getOffset()[1] - mouseY + graphPanel.getHeight()/2);
	}
	
	@Override
	public void MouseReleased(MouseEvent e) {
		if (e.getComponent() == graphPanel) {
			graphPanel.normalize(graphPanel.getOffset()[0], graphPanel.getOffset()[1]);
		}
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
	}
	
	@Override
	public void keyRelease(KeyEvent e) {
		int key = e.getKeyCode();
		if (key != KeyEvent.VK_RIGHT && key != KeyEvent.VK_LEFT && key != KeyEvent.VK_UP && key != KeyEvent.VK_DOWN && !e.isAltDown() && !e.isControlDown()) {
			calcBtnClicked();			
//			//int prevCarPos = textArea.getCaretPosition();
//			textArea.setText(textArea.getText().replace("pi", "π").replace("^2", "²"));
		}
	}
	
	@Override
	public void numbBtnClicked(Object obj) {
		JButton btn = (JButton) obj;
		textArea.insert(btn.getText(), textArea.getCaretPosition());
		calcBtnClicked();
	}
	
	@Override
	public void divideBtnClicked() {
		
		textArea.insert("/", textArea.getCaretPosition());
	}
	
	@Override
	public void multBtnClicked() {
		textArea.insert("*", textArea.getCaretPosition());
	}
	
	@Override
	public void subtractBtnClicked() {
		textArea.insert("-", textArea.getCaretPosition());
	}
	
	@Override
	public void addBtnClicked() {
		textArea.insert("+", textArea.getCaretPosition());
	}
	
	@Override
	public void sqrtBtnClicked() {
		textArea.insert("sqrt(", textArea.getCaretPosition());
	}
	
	@Override
	public void squareBtnClicked() {
		textArea.insert("²", textArea.getCaretPosition());
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
		textArea.setText("");
		textResult.setText("Previous answer\n" + textResult.getText());
	}
	
	@Override
	public void degreeBtnClicked() {
		if (parser.isDegree()) {
			angleBtn.setText("Rad");
			angleBtn.setBackground(new Color(156, 91, 0));
		}else {
			angleBtn.setText("Deg");
			angleBtn.setBackground(new Color(0, 99, 156));
		}
		parser.setDegree(!parser.isDegree());
		calcBtnClicked();
	}
	
	@Override
	public void calcBtnClicked() {
		
		String inputText = textArea.getText().trim();
		if (inputText.length() > 0) {
			
			System.out.println("text: " + inputText);
			inputText = inputText.replace("\\--", "+").replace("\\+-", "-").replace(',', '.').replace('×', '*').replace('−', '-').replace('÷', '/').replace("²", "^(2)");
			
			System.out.println("new text: " + inputText);
			
			try {
				ParsingThread task = new ParsingThread(parser, inputText, textResult);
				Thread thread = new Thread(task);
				thread.start();					
			}catch (Exception e) {
				return;
			}
		}
	}
}
