package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Window_Design {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;
	
	private static WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");
	
	public static JPanel mainPanel = new JPanel();
	public static JPanel topPanel = new JPanel();
	public static JPanel bottomPanel = new JPanel();
	public static JPanel topFuncPanel = new JPanel();
	public static JPanel bottomFuncPanel = new JPanel();
	public static JPanel btnPanel = new JPanel();
	public static JButton[][] numbBtns = new JButton[4][3];
	
	public static JButton addBtn = new JButton();
	public static JButton subtractBtn = new JButton();
	public static JButton divideBtn = new JButton();
	public static JButton multBtn = new JButton();
	public static JButton sqrtBtn = new JButton();
	public static JButton squareBtn = new JButton();
	public static JButton openPBtn = new JButton();
	public static JButton closePBtn = new JButton();
	public static JButton calcBtn = new JButton();
	public static JButton clearBtn = new JButton();
	
	public static JCheckBox checkBox = new JCheckBox();
	
	public static JTextArea textArea = new JTextArea();
	public static JScrollPane textAreaScrollPane = new JScrollPane();
	public static JTextArea textResult = new JTextArea();
	public static JScrollPane textResultScrollPane = new JScrollPane();

	
	public void InitializeComponents() {
		
		//window
		window.setLayout(new BorderLayout());
		GridBagConstraints windowC = new GridBagConstraints();
		
		//mainPanel
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints mainPanelC = new GridBagConstraints();
		
		//headPanel
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints topPanelC = new GridBagConstraints();
		
		//bottomPanel
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints bottomPanelC = new GridBagConstraints();
		
		//topPanel
		topFuncPanel.setLayout(new GridBagLayout());
		GridBagConstraints topFuncPanelC = new GridBagConstraints();
		
		//bottomFuncPanel
		bottomFuncPanel.setLayout(new GridBagLayout());
		GridBagConstraints bottomFuncPanelC = new GridBagConstraints();
		
		//numbBtnPanel
		btnPanel.setLayout(new GridBagLayout());
		GridBagConstraints btnPanelC = new GridBagConstraints();
		
		btnPanelC.weightx = 1f;
		btnPanelC.weighty = 1f;
		btnPanelC.ipadx = 30;
		btnPanelC.ipady = 30;
		
		btnPanelC.insets = new Insets(1,1,1,1);
		
		//numbBtnPanel Numeric Buttons
		btnPanelC.gridwidth = 1;
		btnPanelC.fill = GridBagConstraints.BOTH;
		addNumButtons(0, 1, btnPanelC); //Create all buttons from 1 to 9
		
		//bottomFuncPanel
		bottomFuncPanelC.weighty = 1;

		//numBtn0
		numbBtns[3][0] = new JButton();
		numbBtns[3][0].setName("numBtn0");
		numbBtns[3][0].setText("0");
		numbBtns[3][0].setBackground(new Color(191, 191, 191));
		bottomFuncPanelC.gridx = 0;
		bottomFuncPanelC.gridwidth = 1;
		bottomFuncPanelC.weightx = 1;
		bottomFuncPanelC.fill = GridBagConstraints.BOTH;
		bottomFuncPanel.add(numbBtns[3][0], bottomFuncPanelC);
		numbBtns[3][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				numbBtnClicked(numbBtns[3][0]);
			}
		});
		
		//,
		numbBtns[3][1] = new JButton();
		numbBtns[3][1].setName("numBtn2");
		numbBtns[3][1].setText(".");
		numbBtns[3][1].setBackground(new Color(191, 191, 191));
		bottomFuncPanelC.gridx = 1;
		bottomFuncPanelC.gridwidth = 1;
		bottomFuncPanelC.weightx = 1;
		bottomFuncPanelC.fill = GridBagConstraints.BOTH;
		bottomFuncPanel.add(numbBtns[3][1], bottomFuncPanelC);
		numbBtns[3][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				numbBtnClicked(numbBtns[3][1]);
			}
		});
		
		//CalcBtn
		calcBtn.setText("=");
		calcBtn.setBackground(new Color(96, 214, 0));
		bottomFuncPanelC.gridx = 2;
		bottomFuncPanelC.gridwidth = 1;
		bottomFuncPanelC.weightx = 1;
		bottomFuncPanelC.fill = GridBagConstraints.BOTH;
		bottomFuncPanel.add(calcBtn, bottomFuncPanelC);
		calcBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calcBtnClicked();
			}
		});
		
		//numbBtnPanel Operation Buttons
		btnPanelC.gridx = 3;
		btnPanelC.weighty = 1;
		
		//Divide
		divideBtn.setText("÷");
		divideBtn.setBackground(Color.white);
		btnPanelC.gridy = 0;
		btnPanelC.gridx = 3;
		btnPanelC.gridwidth = 1;
		btnPanel.add(divideBtn, btnPanelC);
		divideBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				divideBtnClicked();
			}
		});
		
		//Mult
		multBtn.setText("×");
		multBtn.setBackground(Color.white);
		btnPanelC.gridy = 1;
		btnPanelC.fill = GridBagConstraints.BOTH;
		btnPanel.add(multBtn, btnPanelC);
		multBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				multBtnClicked();
			}
		});
		
		//Subtract
		subtractBtn.setText("−");
		subtractBtn.setBackground(Color.white);
		btnPanelC.gridy = 2;
		btnPanelC.gridwidth = 1;
		btnPanelC.fill = GridBagConstraints.BOTH;
		btnPanel.add(subtractBtn, btnPanelC);
		subtractBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subtractBtnClicked();
			}
		});
		
		
		//Add
		addBtn.setText("+");
		addBtn.setBackground(Color.white);
		btnPanelC.gridy = 3;
		btnPanelC.gridwidth = 1;
		btnPanelC.fill = GridBagConstraints.BOTH;
		btnPanel.add(addBtn, btnPanelC);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBtnClicked();
			}
		});
		
		//clearBtn
		clearBtn.setText("AC");
		clearBtn.setBackground(Color.white);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 0;
		topFuncPanelC.gridwidth = 1;
		topFuncPanelC.weightx = 1;
		topFuncPanelC.weighty = 1;
		topFuncPanelC.fill = GridBagConstraints.BOTH;
		topFuncPanel.add(clearBtn, topFuncPanelC);
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearBtnClicked();
			}
		});
		
		//sqrtBtn
		sqrtBtn.setText("√");
		sqrtBtn.setBackground(Color.white);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 1;
		topFuncPanelC.gridwidth = 1;
		topFuncPanelC.weightx = 1;
		topFuncPanelC.weighty = 1;
		topFuncPanelC.fill = GridBagConstraints.BOTH;
		topFuncPanel.add(sqrtBtn, topFuncPanelC);
		sqrtBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sqrtBtnClicked();
			}
		});
		
		//squareBtn
		squareBtn.setText("x²");
		squareBtn.setBackground(Color.white);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 2;
		topFuncPanelC.gridwidth = 1;
		topFuncPanelC.weightx = 1;
		topFuncPanelC.weighty = 1;
		topFuncPanelC.fill = GridBagConstraints.BOTH;
		topFuncPanel.add(squareBtn, topFuncPanelC);
		squareBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				squareBtnClicked();
			}
		});
		
		//openPBtn
		openPBtn.setText("(");
		openPBtn.setBackground(Color.white);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 3;
		topFuncPanelC.gridwidth = 1;
		topFuncPanelC.weightx = 1;
		topFuncPanelC.weighty = 1;
		topFuncPanelC.fill = GridBagConstraints.BOTH;
		topFuncPanel.add(openPBtn, topFuncPanelC);
		openPBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openPBtnClicked();
			}
		});
		
		//closePBtn
		closePBtn.setText(")");
		closePBtn.setBackground(Color.white);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 4;
		topFuncPanelC.gridwidth = 1;
		topFuncPanelC.weightx = 1;
		topFuncPanelC.weighty = 1;
		topFuncPanelC.fill = GridBagConstraints.BOTH;
		topFuncPanel.add(closePBtn, topFuncPanelC);
		closePBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closePBtnClicked();
			}
		});
		
		//checkBox
		checkBox.setText("Advanced mode");
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 7;
		mainPanelC.gridheight = 1;
		mainPanelC.gridwidth = 1;
		mainPanelC.fill = GridBagConstraints.BOTH;
		mainPanel.add(checkBox, mainPanelC);
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAdvanced(checkBox.isSelected());
			}
		});
		
		//textArea
		textArea.setText("");
		textArea.setFont(new Font("Arial", Font.PLAIN, 25));
		textArea.setLineWrap(true);
		
		textArea.setBorder(BorderFactory.createLineBorder(Color.red));
		topPanelC.gridx = 0;
		topPanelC.gridy = 0;
		topPanelC.gridwidth = 1;
		topPanelC.gridheight = 1;
		topPanelC.weightx = 1;
		topPanelC.weighty = 1;

		topPanelC.fill = GridBagConstraints.BOTH;
		//headPanelC.weightx = 1;
		textAreaScrollPane.setViewportView(textArea);
		textAreaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textAreaScrollPane.setMinimumSize(new Dimension(100, 100));
		textAreaScrollPane.setPreferredSize(new Dimension(WIDTH, 100));
		textAreaScrollPane.setMaximumSize(new Dimension(WIDTH, 100));
		
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
		
		topPanel.add(textAreaScrollPane, topPanelC);
		
		//textResult
		textResult.setText("");
		textResult.setFont(new Font("Arial", Font.PLAIN, 25));
		textResult.setLineWrap(true);
		
		textResult.setBorder(BorderFactory.createLineBorder(Color.red));
		topPanelC.gridx = 0;
		topPanelC.gridy = 1;
		topPanelC.gridwidth = 1;
		topPanelC.gridheight = 1;
		topPanelC.weightx = 1;
		topPanelC.weighty = 1;

		topPanelC.fill = GridBagConstraints.BOTH;
		//headPanelC.weightx = 1;
		textResultScrollPane.setViewportView(textResult);
		textResultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textResultScrollPane.setMinimumSize(new Dimension(100, 50));
		textResultScrollPane.setPreferredSize(new Dimension(WIDTH, 50));
		textResultScrollPane.setMaximumSize(new Dimension(WIDTH, 50));
		topPanel.add(textResultScrollPane, topPanelC);

		//headPanel.setPreferredSize(new Dimension(WIDTH, 100));

		//topFuncPanel
		topFuncPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		btnPanelC.gridx = 0;
		btnPanelC.gridy = 0;
		btnPanelC.gridwidth = 3;
		btnPanelC.gridheight = 1;
		btnPanelC.weightx = 1.0;
		btnPanelC.weighty = 1.0;
		btnPanel.add(topFuncPanel, btnPanelC);
		
		//bottomFuncPanel
		bottomFuncPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		btnPanelC.gridx = 0;
		btnPanelC.gridy = 5;
		//btnPanelC.gridwidth = GridBagConstraints.REMAINDER;
		btnPanelC.gridwidth = 4;
		btnPanelC.gridheight = 1;
		btnPanelC.weightx = 1.0;
		btnPanelC.weighty = 1.0;
		btnPanel.add(bottomFuncPanel, btnPanelC);
		
		//numbBtnPanel
		btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//numbBtnPanel.setPreferredSize(new Dimension(WIDTH, 400));
		bottomPanelC.gridx = 0;
		bottomPanelC.gridy = 3;
		bottomPanelC.gridwidth = 1;
		bottomPanelC.gridheight = 4;
		bottomPanelC.weightx = 1.0;
		bottomPanelC.weighty = 1.0;
		bottomPanelC.fill = GridBagConstraints.BOTH;
		bottomPanel.add(btnPanel, bottomPanelC);
		
		//topPanel
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 0;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 1.0;
		mainPanelC.weighty = 0;
		mainPanelC.fill = GridBagConstraints.BOTH;
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(topPanel, mainPanelC);
		
		//bottomPanel
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 2;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 1.0;
		mainPanelC.weighty = 1.0;
		mainPanelC.fill = GridBagConstraints.BOTH;
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(bottomPanel, mainPanelC);
		
		//windowC.fill = GridBagConstraints.BOTH;
		//windowC.weightx = 1;
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
		window.add(mainPanel, BorderLayout.CENTER);
//		window.addComponentListener(new ComponentListener() {
//			
//			
//			public void componentResized(ComponentEvent e) {
//				showAdvanced(window.getWidth() >= 600);
//			}
//
//			@Override
//			public void componentMoved(ComponentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void componentShown(ComponentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void componentHidden(ComponentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		window.pack();
		window.Show();
	}
	
	private void showAdvanced(boolean isChecked) {
		if (isChecked) {
			
		}else {

		}
	}
	
	public void divideBtnClicked() {}
	public void multBtnClicked() {}
	public void subtractBtnClicked() {}
	public void addBtnClicked() {}
	public void sqrtBtnClicked() {};
	public void squareBtnClicked() {};
	public void openPBtnClicked() {};
	public void closePBtnClicked() {};
	public void clearBtnClicked() {}
	public void calcBtnClicked() {}
	public void numbBtnClicked(Object obj) {}
	public void keyType(KeyEvent e) {}
	public void keyRelease(KeyEvent e) {}
	public void keyPress(KeyEvent e) {}

	private void addNumButtons(int col, int row, GridBagConstraints numbBtnC) {
		int total = 1;
		int yOffset = row;
		int xOffset = col + numbBtnC.gridwidth - 1;

		numbBtnC.gridy = yOffset;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int numb = 10 - total++;
				int y = i;
				int x = 2-j;
				//System.out.print("x: " + x + " y: " + y + " = " + numb + "\n");
				numbBtns[y][x] = new JButton();
				numbBtns[y][x].setName("numBtn"+numb);
				numbBtns[y][x].setText(String.valueOf(numb));
				numbBtns[y][x].setBackground(new Color(191, 191, 191));
				numbBtnC.gridx = x + xOffset*x;
				btnPanel.add(numbBtns[y][x], numbBtnC);
				numbBtns[y][x].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						numbBtnClicked(numbBtns[y][x]);
					}
				});
			}
			numbBtnC.gridy = yOffset + i+1;
		}
		
		
		String result = "";
		for (int i = 0; i < numbBtns.length; i++) {
			for (int j = 0; j < numbBtns[i].length; j++) {
				int y = i;
				int x = j;
				if(numbBtns[y][x] == null) {
					result += "-1, ";
				}else {
					result += " " + numbBtns[y][x].getText() + ", ";					
				}
			}
			result += "\n";
		}
		//System.out.println(numbBtns[2][0].getText());
		//System.out.println(result);
}
	
}
