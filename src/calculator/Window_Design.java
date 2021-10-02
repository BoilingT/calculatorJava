package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window_Design {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private static WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");
	
	public static JPanel headPanel = new JPanel();
	public static JPanel numbBtnPanel = new JPanel();
	public static JButton[][] numbBtns = new JButton[4][3];
	
	public static JButton addBtn = new JButton();
	public static JButton subtractBtn = new JButton();
	public static JButton divideBtn = new JButton();
	public static JButton multBtn = new JButton();
	
	public static JTextField textField = new JTextField();
	
	public void InitializeComponents() {
		
		window.setLayout(new GridBagLayout());
		
		GridBagConstraints headPanelC = new GridBagConstraints();
		headPanel.setLayout(new GridBagLayout());
		
		//numbBtnPanel
		numbBtnPanel.setLayout(new GridBagLayout());
		GridBagConstraints numbBtnC = new GridBagConstraints();
		numbBtnC.ipady = 50;
		numbBtnC.ipadx = 50;
		numbBtnC.insets = new Insets(2,2,2,2);
		
		//numbBtnPanel Numeric Buttons
		int total = 1;
		
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int numb = 10 - total++;
				int y = 2-i;
				int x = 2-j;
				System.out.print("x: " + x + " y: " + y + " = " + numb + "\n");
				numbBtns[y][x] = new JButton();
				numbBtns[y][x].setName("numBtn"+numb);
				numbBtns[y][x].setText(String.valueOf(numb));
				numbBtnC.gridx = x;

				numbBtnPanel.add(numbBtns[y][x], numbBtnC);
			}
			numbBtnC.gridy = i+1;
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
		
		//numBtn0
		numbBtns[0][0] = new JButton();
		numbBtns[0][0].setName("numBtn0");
		numbBtns[0][0].setText("0");
		numbBtnC.gridx = 0;
		numbBtnPanel.add(numbBtns[0][0], numbBtnC);
		
		//Positive/Negative
		numbBtns[0][1] = new JButton();
		numbBtns[0][1].setName("numBtn1");
		numbBtns[0][1].setText("+/-");
		numbBtnC.gridx = 1;
		numbBtnPanel.add(numbBtns[0][1], numbBtnC);
		
		//,
		numbBtns[0][2] = new JButton();
		numbBtns[0][2].setName("numBtn2");
		numbBtns[0][2].setText(",");
		numbBtnC.gridx = 2;
		numbBtnPanel.add(numbBtns[0][2], numbBtnC);
		
		//numbBtnPanel Operation Buttons
		numbBtnC.gridx = 4;
		
		//Divide
		numbBtnC.gridy = 0;
		divideBtn.setText("/");
		numbBtnPanel.add(divideBtn, numbBtnC);
		
		//Mult
		numbBtnC.gridy = 1;
		multBtn.setText("*");
		numbBtnPanel.add(multBtn, numbBtnC);
		
		//Subtract
		numbBtnC.gridy = 2;
		subtractBtn.setText("-");
		numbBtnPanel.add(subtractBtn, numbBtnC);
		
		//Add
		numbBtnC.gridy = 3;
		addBtn.setText("+");
		numbBtnPanel.add(addBtn, numbBtnC);

		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		
		//Field
		textField.setText("Hello world!");
		textField.setPreferredSize(new Dimension(50, 100));
		


		headPanelC.gridwidth = 2;
		headPanelC.gridx = 3;
		headPanelC.fill = GridBagConstraints.HORIZONTAL;
		
		window.add(textField, headPanelC);
		window.add(numbBtnPanel, numbBtnC);
		window.Show();
	}
}
