package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Window_Design {

	public static final int WIDTH = 400;
	private static final int HEIGHT = 500;
	
	private static float alpha = 0.9f;
	private static Color borderColor = new Color(41, 41, 41);
	private static Color backgroundColor = new Color(41, 41, 41);
	private static Color numbBtnColor = Color.black;
	private static Color operationBtnColor = new Color(28, 28, 28);
	private static Color funcBtnColor = new Color(28, 28, 28);
	private static Color calcBtnColor = new Color(0, 99, 5);
	private static Color fieldColor = new Color(41, 41, 41);
	private static Color foregroundColor = Color.white;

	public static WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");
	
	public static JPanel topBorderPanel = new JPanel();
	public static JPanel topBorderBtnPanel = new JPanel();
	public static JPanel topBorderLabelPanel = new JPanel();
	public static JPanel mainPanel = new JPanel();
	public static JPanel topPanel = new JPanel();
	public static JPanel bottomPanel = new JPanel();
	public static JPanel topFuncPanel = new JPanel();
	public static JPanel bottomFuncPanel = new JPanel();
	public static JPanel btnPanel = new JPanel();
	public static JButton[][] numbBtns = new JButton[4][3];
	
	public static JButton minimizeBtn = new JButton();
	public static JButton maximizeBtn = new JButton();
	public static JButton closeBtn = new JButton();

	
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
	
	public static JLabel windowTitleLabel = new JLabel(window.getTitle());
	public static JTextArea textArea = new JTextArea();
	public static JScrollPane textAreaScrollPane = new JScrollPane();
	public static JTextArea textResult = new JTextArea();
	public static JScrollPane textResultScrollPane = new JScrollPane();

	
	private static MouseListener mouseListener = null;
	private static MouseMotionListener mouseMotionListener = null;
	
	public void InitializeComponents() {
			
		mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				MousePressed(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		mouseMotionListener = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				MouseMoved(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				MouseDragged(e);
			}
		};
		
		//window
		window.setLayout(new BorderLayout());
		window.dispose();
		window.setUndecorated(true);
		window.setOpacity(alpha);
		
		window.setBackground(backgroundColor);

		//topBorderPanel
		topBorderPanel.setLayout(new GridBagLayout());
		GridBagConstraints topBorderPanelC = new GridBagConstraints();
		topBorderPanel.setBackground(borderColor);
		
		//topBorderBtnPanel
		topBorderBtnPanel.setLayout(new GridBagLayout());
		GridBagConstraints topBorderBtnPanelC = new GridBagConstraints();
		topBorderBtnPanel.setBackground(borderColor);
		
		//topBorderLabelPanel
		topBorderLabelPanel.setLayout(new GridBagLayout());
		GridBagConstraints topBorderLabelPanelC = new GridBagConstraints();
		topBorderLabelPanel.setBackground(borderColor);
		
		//mainPanel
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints mainPanelC = new GridBagConstraints();
		mainPanel.setBackground(backgroundColor);

		//headPanel
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints topPanelC = new GridBagConstraints();
		topPanel.setBackground(backgroundColor);

		//bottomPanel
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints bottomPanelC = new GridBagConstraints();
		bottomPanel.setBackground(backgroundColor);

		//topPanel
		topFuncPanel.setLayout(new GridBagLayout());
		GridBagConstraints topFuncPanelC = new GridBagConstraints();
		topFuncPanel.setBackground(backgroundColor);

		//bottomFuncPanel
		bottomFuncPanel.setLayout(new GridBagLayout());
		GridBagConstraints bottomFuncPanelC = new GridBagConstraints();
		bottomFuncPanel.setBackground(backgroundColor);

		//numbBtnPanel
		btnPanel.setLayout(new GridBagLayout());
		GridBagConstraints btnPanelC = new GridBagConstraints();
		btnPanel.setBackground(backgroundColor);

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
		numbBtns[3][0].setBackground(numbBtnColor);
		numbBtns[3][0].setForeground(foregroundColor);
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
		numbBtns[3][1].setBackground(numbBtnColor);
		numbBtns[3][1].setForeground(foregroundColor);
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
		calcBtn.setBackground(calcBtnColor);
		calcBtn.setForeground(foregroundColor);
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
		btnPanelC.weightx = 1;

		//Divide
		divideBtn.setText("÷");
		divideBtn.setBackground(operationBtnColor);
		divideBtn.setForeground(foregroundColor);
		btnPanelC.gridy = 0;
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
		multBtn.setBackground(operationBtnColor);
		multBtn.setForeground(foregroundColor);
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
		subtractBtn.setBackground(operationBtnColor);
		subtractBtn.setForeground(foregroundColor);
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
		addBtn.setBackground(operationBtnColor);
		addBtn.setForeground(foregroundColor);
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
		
		//topFuncPanel content
		topFuncPanelC.insets = new Insets(0,1,0,1);
		topFuncPanelC.ipadx = 30;
		//clearBtn
		clearBtn.setText("AC");
		clearBtn.setBackground(funcBtnColor);
		clearBtn.setForeground(foregroundColor);
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
		sqrtBtn.setBackground(funcBtnColor);
		sqrtBtn.setForeground(foregroundColor);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 1;
		topFuncPanel.add(sqrtBtn, topFuncPanelC);
		sqrtBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sqrtBtnClicked();
			}
		});
		
		//squareBtn
		squareBtn.setText("x²");
		squareBtn.setBackground(funcBtnColor);
		squareBtn.setForeground(foregroundColor);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 2;
		topFuncPanel.add(squareBtn, topFuncPanelC);
		squareBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				squareBtnClicked();
			}
		});
		
		//openPBtn
		openPBtn.setText("(");
		openPBtn.setBackground(funcBtnColor);
		openPBtn.setForeground(foregroundColor);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 3;
		topFuncPanel.add(openPBtn, topFuncPanelC);
		openPBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openPBtnClicked();
			}
		});
		
		//closePBtn
		closePBtn.setText(")");
		closePBtn.setBackground(funcBtnColor);
		closePBtn.setForeground(foregroundColor);
		topFuncPanelC.gridy = 0;
		topFuncPanelC.gridx = 4;
		topFuncPanel.add(closePBtn, topFuncPanelC);
		closePBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closePBtnClicked();
			}
		});
		
		//checkBox
		checkBox.setText("Advanced mode");
		checkBox.setBackground(funcBtnColor);
		checkBox.setForeground(foregroundColor);
		checkBox.setFocusable(false);
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
		
		//textfields:
		
		//textArea
		textArea.setText("");
		textArea.setFont(new Font("Arial", Font.PLAIN, 25));
		textArea.setForeground(foregroundColor);
		textArea.setCaretColor(foregroundColor);
		textArea.setLineWrap(true);
		
		textArea.setBackground(fieldColor);
		topPanelC.gridx = 0;
		topPanelC.gridy = 0;
		topPanelC.gridwidth = 1;
		topPanelC.gridheight = 1;
		topPanelC.weightx = 1;
		topPanelC.weighty = 1;

		topPanelC.fill = GridBagConstraints.BOTH;
		//headPanelC.weightx = 1;
		textAreaScrollPane.setBorder(BorderFactory.createEmptyBorder());
		textAreaScrollPane.setBackground(fieldColor);
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
		textResult.setForeground(foregroundColor);
		textResult.setBackground(fieldColor);
		textResult.setEditable(false);
		topPanelC.gridx = 0;
		topPanelC.gridy = 1;
		topPanelC.gridwidth = 1;
		topPanelC.gridheight = 1;
		topPanelC.weightx = 1;
		topPanelC.weighty = 1;

		topPanelC.fill = GridBagConstraints.BOTH;
		//headPanelC.weightx = 1;
		textResultScrollPane.setBorder(BorderFactory.createEmptyBorder());
		textResultScrollPane.setBackground(fieldColor);
		textResultScrollPane.setViewportView(textResult);
		textResultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textResultScrollPane.setMinimumSize(new Dimension(100, 50));
		textResultScrollPane.setPreferredSize(new Dimension(WIDTH, 50));
		textResultScrollPane.setMaximumSize(new Dimension(WIDTH, 50));
		topPanel.add(textResultScrollPane, topPanelC);
		
		//topBorderBtnPanel content:
		
		//closeBtn
		closeBtn.setText("×");
		closeBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		closeBtn.setBackground(borderColor);
		closeBtn.setForeground(foregroundColor);
		closeBtn.setBorder(BorderFactory.createLineBorder(backgroundColor));
		closeBtn.setFocusable(false);
		topBorderBtnPanelC.gridx = 2;
		topBorderBtnPanelC.gridy = 0;
		topBorderBtnPanelC.ipadx = 15;
		topBorderBtnPanelC.ipady = 15;

		topBorderBtnPanel.add(closeBtn, topBorderBtnPanelC);
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeBtnClick();
			}
		});
		
		//maximizeBtn
		maximizeBtn.setText(String.copyValueOf(new char[] {150}));
		maximizeBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		maximizeBtn.setBackground(borderColor);
		maximizeBtn.setForeground(foregroundColor);
		maximizeBtn.setBorder(BorderFactory.createLineBorder(backgroundColor));
		maximizeBtn.setFocusable(false);
		topBorderBtnPanelC.gridx = 1;
		topBorderBtnPanelC.gridy = 0;
		topBorderBtnPanel.add(maximizeBtn, topBorderBtnPanelC);
		maximizeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				maximizeBtnClick();
			}
		});
		
		//minimizeBtn
		minimizeBtn.setText("–");
		minimizeBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		minimizeBtn.setBackground(borderColor);
		minimizeBtn.setForeground(foregroundColor);
		minimizeBtn.setBorder(BorderFactory.createLineBorder(backgroundColor));
		minimizeBtn.setFocusable(false);
		topBorderBtnPanelC.gridx = 0;
		topBorderBtnPanelC.gridy = 0;
		topBorderBtnPanel.add(minimizeBtn, topBorderBtnPanelC);
		minimizeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				minimizeBtnClick();
			}
		});
		
		//topBorderPanel content
		
		//windowTitleLabel
		windowTitleLabel.setForeground(foregroundColor);
		windowTitleLabel.setBackground(borderColor);
		windowTitleLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
		windowTitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		topBorderLabelPanelC.anchor = GridBagConstraints.LINE_START;
		topBorderLabelPanelC.fill = GridBagConstraints.VERTICAL;
		topBorderLabelPanelC.weightx = 0;
		topBorderLabelPanelC.weighty = 1;
		topBorderLabelPanelC.gridx = 0;
		topBorderLabelPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		topBorderLabelPanel.add(windowTitleLabel, topBorderLabelPanelC);

		
		//topBorderLabelPanel
		topBorderPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
		topBorderPanelC.fill = GridBagConstraints.VERTICAL;
		topBorderPanelC.anchor = GridBagConstraints.LINE_START;
		topBorderPanelC.weightx = 1;
		topBorderPanelC.weighty = 0;
		topBorderPanel.add(topBorderLabelPanel, topBorderPanelC);
		
		//topBorderBtnPanel
		topBorderBtnPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
		topBorderBtnPanel.setBackground(borderColor);
		
		topBorderPanelC.fill = GridBagConstraints.NONE;
		topBorderPanelC.anchor = GridBagConstraints.LINE_END;
		topBorderPanelC.weightx = 0;
		topBorderPanelC.gridx = 1;
		topBorderPanel.add(topBorderBtnPanel, topBorderPanelC);
		
		//topFuncPanel
		//topFuncPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		btnPanelC.gridx = 0;
		btnPanelC.gridy = 0;
		btnPanelC.gridwidth = 3;
		btnPanelC.gridheight = 1;
		btnPanelC.weightx = 0;
		btnPanelC.weighty = 1.0;
		btnPanel.add(topFuncPanel, btnPanelC);
		
		//bottomFuncPanel
		//bottomFuncPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		btnPanelC.gridx = 0;
		btnPanelC.gridy = 5;
		//btnPanelC.gridwidth = GridBagConstraints.REMAINDER;
		btnPanelC.gridwidth = 4;
		btnPanelC.gridheight = 1;
		btnPanelC.weightx = 1.0;
		btnPanelC.weighty = 1.0;
		btnPanel.add(bottomFuncPanel, btnPanelC);
		
		//numbBtnPanel
		//btnPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		//numbBtnPanel.setPreferredSize(new Dimension(WIDTH, 400));
		bottomPanelC.gridx = 0;
		bottomPanelC.gridy = 3;
		bottomPanelC.gridwidth = 1;
		bottomPanelC.gridheight = 4;
		bottomPanelC.weightx = 1.0;
		bottomPanelC.weighty = 1.0;
		bottomPanelC.fill = GridBagConstraints.BOTH;
		bottomPanel.add(btnPanel, bottomPanelC);
		
		//topBorderPanel
		
		topBorderPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 0;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 0;
		mainPanelC.weighty = 0;
		mainPanelC.fill = GridBagConstraints.HORIZONTAL;
		mainPanelC.anchor = GridBagConstraints.CENTER;
		mainPanel.add(topBorderPanel, mainPanelC);

		//topPanel
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 1;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 1.0;
		mainPanelC.weighty = 0;
		mainPanelC.fill = GridBagConstraints.BOTH;
		//topPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
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
				
		topBorderLabelPanel.addMouseListener(mouseListener);
		
		topBorderLabelPanel.addMouseMotionListener(mouseMotionListener);
		
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
		window.add(mainPanel, BorderLayout.CENTER);
		window.getContentPane().addMouseListener(mouseListener);
		window.getContentPane().addMouseMotionListener(mouseMotionListener);
		window.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, backgroundColor));
		
		window.getRootPane().addMouseListener(mouseListener);
		
		window.getRootPane().addMouseMotionListener(mouseMotionListener);
		//window.pack();
		showAdvanced(false);
		window.Show();
	}
	
	private void showAdvanced(boolean isChecked) {
		openPBtn.setVisible(isChecked);
		closePBtn.setVisible(isChecked);
		if (isChecked) {
			textArea.setCaretColor(Color.orange);
		}else {
			textArea.setCaretColor(foregroundColor);
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
	public void closeBtnClick() {}
	public void minimizeBtnClick() {}
	public void maximizeBtnClick() {}
	public void MousePressed(MouseEvent e) {}                              
	public void MouseDragged(MouseEvent e) {}
	public void MouseMoved(MouseEvent e) {}  
	
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
				numbBtns[y][x].setBackground(numbBtnColor);
				numbBtns[y][x].setForeground(foregroundColor);
				numbBtns[y][x].setFocusable(false);
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
