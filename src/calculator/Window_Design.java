package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window_Design {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;
	
	private static WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");
	
	public static JPanel mainPanel = new JPanel();
	public static JPanel headPanel = new JPanel();
	public static JPanel numbBtnPanel = new JPanel();
	public static JButton[][] numbBtns = new JButton[4][3];
	
	public static JButton addBtn = new JButton();
	public static JButton subtractBtn = new JButton();
	public static JButton divideBtn = new JButton();
	public static JButton multBtn = new JButton();
	public static JButton calcBtn = new JButton();
	public static JButton clearBtn = new JButton();
	
	public static JCheckBox checkBox = new JCheckBox();
	
	public static JTextField textField = new JTextField();
	
	public void InitializeComponents() {
		
		//window
		window.setLayout(new GridBagLayout());
		GridBagConstraints windowC = new GridBagConstraints();
		
		//mainPanel
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints mainPanelC = new GridBagConstraints();
		
		//headPanel
		headPanel.setLayout(new GridBagLayout());
		GridBagConstraints headPanelC = new GridBagConstraints();
		
		//numbBtnPanel
		numbBtnPanel.setLayout(new GridBagLayout());
		GridBagConstraints numbBtnC = new GridBagConstraints();
		numbBtnC.weightx = 1f;
		numbBtnC.weighty = 1f;
		numbBtnC.ipadx = 30;
		numbBtnC.ipady = 30;
		
		numbBtnC.insets = new Insets(1,1,1,1);
		
		//numbBtnPanel Numeric Buttons
		createNumBtns(0, 1, numbBtnC); //Create all buttons from 1 to 9
		
		//numBtn0
		numbBtns[0][0] = new JButton();
		numbBtns[0][0].setName("numBtn0");
		numbBtns[0][0].setText("0");
		numbBtnC.gridx = 0;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(numbBtns[0][0], numbBtnC);
		
		//Positive/Negative
		numbBtns[0][1] = new JButton();
		numbBtns[0][1].setName("numBtn1");
		numbBtns[0][1].setText("+/-");
		numbBtnC.gridx = 1;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(numbBtns[0][1], numbBtnC);
		
		//,
		numbBtns[0][2] = new JButton();
		numbBtns[0][2].setName("numBtn2");
		numbBtns[0][2].setText(",");
		numbBtnC.gridx = 2;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(numbBtns[0][2], numbBtnC);
		
		//numbBtnPanel Operation Buttons
		numbBtnC.gridx = 3;
		
		//Divide
		divideBtn.setText("/");
		numbBtnC.gridy = 0;
		numbBtnC.gridx = 3;
		numbBtnPanel.add(divideBtn, numbBtnC);
		divideBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				divideBtnClicked();
			}
		});
		
		//Mult
		multBtn.setText("*");
		numbBtnC.gridy = 1;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(multBtn, numbBtnC);
		multBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				multBtnClicked();
			}
		});
		
		//Subtract
		subtractBtn.setText("-");
		numbBtnC.gridy = 2;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(subtractBtn, numbBtnC);
		subtractBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subtractBtnClicked();
			}
		});
		
		
		//Add
		addBtn.setText("+");
		numbBtnC.gridy = 3;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(addBtn, numbBtnC);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBtnClicked();
			}
		});
		

		//CalcBtn
		calcBtn.setText("=");
		numbBtnC.gridy = 4;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(calcBtn, numbBtnC);
		calcBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calcBtnClicked();
			}
		});
		
		//clearBtn
		clearBtn.setText("Cl");
		numbBtnC.gridy = 0;
		numbBtnC.gridx = 0;
		numbBtnC.fill = GridBagConstraints.BOTH;
		numbBtnPanel.add(clearBtn, numbBtnC);
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clearBtnClicked();
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
		
		//textField
		textField.setText("");
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		//textField.setPreferredSize(new Dimension(100, 50));
		headPanelC.anchor = GridBagConstraints.FIRST_LINE_START;
		textField.setBorder(BorderFactory.createLineBorder(Color.red));
		headPanelC.gridx = 0;
		headPanelC.gridy = 0;
		headPanelC.gridwidth = 1;
		headPanelC.gridheight = 2;
		headPanelC.weightx = 1;
		headPanelC.fill = GridBagConstraints.BOTH;
		//headPanelC.weightx = 1;
		headPanel.add(textField, headPanelC);
		
		//headPanel
		headPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//headPanel.setPreferredSize(new Dimension(WIDTH, 100));
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 0;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.fill = GridBagConstraints.BOTH;
		mainPanel.add(headPanel, mainPanelC);
		
		//numbBtnPanel
		numbBtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//numbBtnPanel.setPreferredSize(new Dimension(WIDTH, 400));
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 3;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 4;
		mainPanelC.fill = GridBagConstraints.BOTH;
		mainPanel.add(numbBtnPanel, mainPanelC);
		
		//windowC.fill = GridBagConstraints.BOTH;
		windowC.weightx = 1;
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.add(mainPanel, windowC);
		window.pack();
		window.Show();
	}
	
	public void divideBtnClicked() {}
	public void multBtnClicked() {}
	public void subtractBtnClicked() {}
	public void addBtnClicked() {}
	public void clearBtnClicked() {}
	public void calcBtnClicked() {}

	private void createNumBtns(int col, int row, GridBagConstraints numbBtnC) {
		int total = 1;
		int yOffset = row;
		int xOffset = col;

		numbBtnC.gridy = yOffset;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int numb = 10 - total++;
				int y = 2-i;
				int x = 2-j;
				System.out.print("x: " + x + " y: " + y + " = " + numb + "\n");
				numbBtns[y][x] = new JButton();
				numbBtns[y][x].setName("numBtn"+numb);
				numbBtns[y][x].setText(String.valueOf(numb));
				numbBtnC.gridx = x + xOffset;
				numbBtnC.gridwidth = 1;
				numbBtnC.fill = GridBagConstraints.BOTH;

				numbBtnPanel.add(numbBtns[y][x], numbBtnC);
			}
			numbBtnC.gridy = yOffset + i+1;
		}
		
		
		String result = "";
		for (int i = 0; i < numbBtns.length; i++) {
			for (int j = 0; j < numbBtns[i].length; j++) {
				if(numbBtns[i][j] == null) {
					result += "-1, ";
				}else {
					result += " " + numbBtns[i][j].getText() + ", ";					
				}
			}
			result += "\n";
		}
		System.out.println(result);
	}
	
	
}
