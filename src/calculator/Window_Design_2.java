package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Window_Design_2 {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;
	
	private static WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");
	
	public static JPanel mainPanel = new JPanel();
	public static JPanel headPanel = new JPanel();
	public static JPanel bottomPanel = new JPanel();
	public static JPanel numbBtnPanel = new JPanel();
	public static JPanel operationBtnPanel = new JPanel();
	public static JPanel funcBtnPanel = new JPanel();
	
	public static JButton addBtn = new JButton();
	public static JButton subtractBtn = new JButton();
	public static JButton divideBtn = new JButton();
	public static JButton multBtn = new JButton();
	public static JButton calcBtn = new JButton();
	public static JButton clearBtn = new JButton();
	
	public static JCheckBox checkBox = new JCheckBox();
	
	public static JTextArea textArea = new JTextArea();
	
	public void InitializeComponents() {
		
		//window
		window.setLayout(new BorderLayout());
		
		//mainPanel
		mainPanel.setLayout(new GridLayout(2, 1));
		
		//headPanel
		headPanel.setLayout(new GridLayout(1, 1));
		
		//bottomPanel
		bottomPanel.setLayout(new GridLayout(0, 2));
		
		//funcBtnPanel
		funcBtnPanel.setLayout(new GridLayout(1, 5));
		
		//numbBtnPanel
		numbBtnPanel.setLayout(new GridLayout(3, 3));
		
		//operationBtnPanel
		operationBtnPanel.setLayout(new GridLayout(5, 1));
		
		//textArea
		textArea.setText("");
		textArea.setFont(new Font("Arial", Font.PLAIN, 25));
		headPanel.add(textArea);
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				keyType(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				keyRelease(e);

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keyPress(e);
			}
		});
		
		//clearBtn
		clearBtn.setText("AC");
		clearBtn.setBackground(Color.white);
		clearBtn.setPreferredSize(new Dimension(10, 50));
		funcBtnPanel.add(clearBtn);
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearBtnClicked();
			}
		});
		
		//numericBtns
		addNumericButtons(numbBtnPanel); //Add buttons from 1 to 9 in a 3x3 pattern
		
		//operationBtns
		//Divide
		divideBtn.setText("/");
		divideBtn.setBackground(Color.white);
		
		operationBtnPanel.add(divideBtn);
		divideBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				divideBtnClicked();
			}
		});
		
		//Mult
		multBtn.setText("*");
		multBtn.setBackground(Color.white);
		operationBtnPanel.add(multBtn);
		multBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				multBtnClicked();
			}
		});
		
		//Subtract
		subtractBtn.setText("-");
		subtractBtn.setBackground(Color.white);
		operationBtnPanel.add(subtractBtn);
		subtractBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subtractBtnClicked();
			}
		});
		
		
		//Add
		addBtn.setText("+");
		addBtn.setBackground(Color.white);
		operationBtnPanel.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBtnClicked();
			}
		});
		

		//CalcBtn
		calcBtn.setText("=");
		calcBtn.setBackground(new Color(96, 214, 0));
		operationBtnPanel.add(calcBtn);
		calcBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calcBtnClicked();
			}
		});
		
		//Btns
		
		//checkBox
		checkBox.setText("Advanced mode");
		//bottomPanel.add(checkBox);
		
		//Panels
		
		//funcBtnPanel
		funcBtnPanel.setBorder(BorderFactory.createLineBorder(Color.PINK));
		bottomPanel.add(funcBtnPanel);
		
		//numbBtnPanel
		numbBtnPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		bottomPanel.add(numbBtnPanel);
		
		//operationBtnPanel
		operationBtnPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		bottomPanel.add(operationBtnPanel);
		
		//headPanel
		headPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(headPanel);

		//bottomPanel
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(bottomPanel);

		mainPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
		window.add(mainPanel, BorderLayout.CENTER);

		//Show
		window.pack();
		window.Show();
	}
	
	public void divideBtnClicked() {}
	public void multBtnClicked() {}
	public void subtractBtnClicked() {}
	public void addBtnClicked() {}
	public void clearBtnClicked() {}
	public void calcBtnClicked() {}
	public void numbBtnClicked(Object obj) {}
	public void keyType(KeyEvent e) {}
	public void keyRelease(KeyEvent e) {}
	public void keyPress(KeyEvent e) {}
	
	private void addNumericButtons(JPanel panel) {
		
		int value = 7;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton btn = new JButton();
				String text = String.valueOf(value++);
				btn.setText(text);
				btn.setName("numbBtn" + value);
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						numbBtnClicked(btn);
					}
				});
				panel.add(btn);
			}
			value -= 6;
		}
	}
}
