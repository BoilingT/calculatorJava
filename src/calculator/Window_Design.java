package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Window_Design {

	private static final int WIDTH = 500;
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
	
	public static JTextField textField = new JTextField();
	
	public void InitializeComponents() {
		
		//window
		window.setLayout(new GridBagLayout());
		GridBagConstraints windowC = new GridBagConstraints();
		window.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
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
		int total = 1;
		int yOffset = 1;
		
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
				numbBtnC.gridx = x;
				numbBtnC.gridwidth = 1;
				numbBtnC.fill = GridBagConstraints.HORIZONTAL;

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
		
		//numBtn0
		numbBtns[0][0] = new JButton();
		numbBtns[0][0].setName("numBtn0");
		numbBtns[0][0].setText("0");
		numbBtnC.gridx = 0;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(numbBtns[0][0], numbBtnC);
		
		//Positive/Negative
		numbBtns[0][1] = new JButton();
		numbBtns[0][1].setName("numBtn1");
		numbBtns[0][1].setText("+/-");
		numbBtnC.gridx = 1;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(numbBtns[0][1], numbBtnC);
		
		//,
		numbBtns[0][2] = new JButton();
		numbBtns[0][2].setName("numBtn2");
		numbBtns[0][2].setText(",");
		numbBtnC.gridx = 2;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(numbBtns[0][2], numbBtnC);
		
		//numbBtnPanel Operation Buttons
		numbBtnC.gridx = 3;
		
		//Divide
		divideBtn.setText("/");
		numbBtnC.gridy = 0;
		numbBtnC.gridx = 3;
		numbBtnPanel.add(divideBtn, numbBtnC);
		
		//Mult
		multBtn.setText("*");
		numbBtnC.gridy = 1;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(multBtn, numbBtnC);
		
		//Subtract
		subtractBtn.setText("-");
		numbBtnC.gridy = 2;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(subtractBtn, numbBtnC);
		
		//Add
		addBtn.setText("+");
		numbBtnC.gridy = 3;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(addBtn, numbBtnC);

		//CalcBtn
		calcBtn.setText("=");
		numbBtnC.gridy = 4;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(calcBtn, numbBtnC);
		
		//clearBtn
		clearBtn.setText("Cl");
		numbBtnC.gridy = 0;
		numbBtnC.gridx = 0;
		numbBtnC.fill = GridBagConstraints.HORIZONTAL;
		numbBtnPanel.add(clearBtn, numbBtnC);
		
		//textField
		textField.setText("");
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		textField.setPreferredSize(new Dimension(200, 50));
		//headPanelC.anchor = GridBagConstraints.FIRST_LINE_START;
		textField.setBorder(BorderFactory.createLineBorder(Color.red));
		headPanelC.gridx = 2;
		headPanelC.gridy = 0;
		headPanelC.gridwidth = 1;
		headPanelC.gridheight = 1;
		//headPanelC.weightx = 1;
		headPanel.add(textField, headPanelC);
		
		//headPanel
		headPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		windowC.gridx = 0;
		windowC.gridy = 0;
		windowC.gridwidth = 1;
		windowC.gridheight = 1;
		windowC.fill = GridBagConstraints.BOTH;
		window.add(headPanel, windowC);
		
		//numbBtnPanel
		numbBtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		windowC.gridx = 0;
		windowC.gridy = 3;
		windowC.gridwidth = 1;
		windowC.gridheight = 4;
		windowC.fill = GridBagConstraints.BOTH;
		window.add(numbBtnPanel, windowC);
		window.Show();
	}
}
