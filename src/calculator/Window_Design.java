package calculator;

import java.awt.*;

import javax.swing.*;

public class WindowStructure {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");
	
	public void Init() {
		
		
		JPanel numbBtnPanel = new JPanel();
		JButton[] numbBtns = new JButton[10];
		
		JButton addBtn = new JButton();
		JButton subtractBtn = new JButton();
		JButton divideBtn = new JButton();
		JButton multBtn = new JButton();
		
		window.setLayout(new GridBagLayout());

		//numbBtnPanel
		numbBtnPanel.setLayout(new GridBagLayout());
		GridBagConstraints numbBtnC = new GridBagConstraints();
		numbBtnC.ipady = 50;
		numbBtnC.ipadx = 50;
		numbBtnC.insets = new Insets(2,2,2,2);
		
		//numbBtnPanel Numeric Buttons
		int spacing = 5;
		int total = 0;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {

				numbBtns[i] = new JButton();
				numbBtns[i].setName("numBtn"+(9-total++));
				numbBtns[i].setText(String.valueOf(j));
				numbBtnC.gridx = j;
				numbBtnC.gridy = i+1;

				numbBtnPanel.add(numbBtns[i], numbBtnC);
			}
		}
		
		numbBtns[9] = new JButton();
		numbBtns[9].setName("numBtn0");
		numbBtns[9].setText(String.valueOf(0));
		numbBtnC.gridx = 2;
		numbBtnC.gridy = 4;

		numbBtnPanel.add(numbBtns[9], numbBtnC);
		
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
		numbBtnC.gridx = 0;
		numbBtnC.gridy = 2;
		window.add(numbBtnPanel, numbBtnC);
		window.Show();
	}
}
