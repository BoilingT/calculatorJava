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

public class Window_Design {

	//Window default properties
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	public static final int BORDERWIDTH = 4;
	public static final WindowHandler window = new WindowHandler(WIDTH, HEIGHT, "Calculator");

	//Colors
	private static float alpha = 0.95f;
	private static Color borderColor = new Color(41, 41, 41);
	private static Color backgroundColor = new Color(41, 41, 41);
	private static Color numbBtnColor = Color.black;
	private static Color operationBtnColor = new Color(28, 28, 28);
	private static Color funcBtnColor = new Color(28, 28, 28);
	private static Color calcBtnColor = new Color(0, 99, 5);
	private static Color fieldColor = new Color(41, 41, 41);
	private static Color foregroundColor = Color.white;
	
	//Panels
	public static JPanel windowTitlePanel = new JPanel();
	public static JPanel windowControlPanel = new JPanel();
	public static JPanel mainPanel = new JPanel();
	public static JPanel IOPanel = new JPanel();
	public static JPanel inputPanel = new JPanel();
	public static JPanel topFuncPanel = new JPanel();
	public static JPanel btnPanel = new JPanel();
	public static JPanel bottomFuncPanel = new JPanel();
	public static GraphCanvas graphPanel;
	
	//Buttons
	public static JButton[][] numbBtns = new JButton[4][3];
	
	//Window
	public static JButton minimizeBtn = new JButton();
	public static JButton maximizeBtn = new JButton();
	public static JButton closeBtn = new JButton();

	//Operations
	public static JButton addBtn = new JButton();
	public static JButton subtractBtn = new JButton();
	public static JButton divideBtn = new JButton();
	public static JButton multBtn = new JButton();
	
	//Functions
	public static JButton sqrtBtn = new JButton();
	public static JButton squareBtn = new JButton();
	public static JButton openPBtn = new JButton(); //parentheses: (
	public static JButton closePBtn = new JButton(); //parenthese: )
	public static JButton calcBtn = new JButton();
	public static JButton clearBtn = new JButton(); //AC
	public static JButton angleBtn = new JButton();
	
	public static JCheckBox checkBox = new JCheckBox();
	
	//Outputs
	public static JLabel windowTitleLabel = new JLabel(window.getTitle());
	public static JTextArea textArea = new JTextArea();
	public static JScrollPane textAreaScrollPane = new JScrollPane();
	public static JTextArea textResult = new JTextArea();
	public static JScrollPane textResultScrollPane = new JScrollPane();

	//Listeners
	private static MouseListener mouseListener = null;
	private static MouseMotionListener mouseMotionListener = null;
			
	//Get all the components ready for use and then show them.
	public void InitializeComponents() {
			
		mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {MouseReleased(e);}
			
			@Override
			public void mousePressed(MouseEvent e) { MousePressed(e);}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		};
		
		mouseMotionListener = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {MouseMoved(e);}
			
			@Override
			public void mouseDragged(MouseEvent e) {MouseDragged(e);}
		};
		
		//window
		window.setLayout(new GridBagLayout());
		GridBagConstraints windowC = new GridBagConstraints();
		window.dispose();
		window.setUndecorated(true);
		window.setOpacity(alpha);
		
		window.setBackground(backgroundColor);

		//topBorderPanel
		windowTitlePanel.setLayout(new GridBagLayout());
		GridBagConstraints windowTitlePanelC = new GridBagConstraints();
		windowTitlePanel.setBackground(borderColor);
		
		//topBorderBtnPanel
		windowControlPanel.setLayout(new GridBagLayout());
		GridBagConstraints topBorderBtnPanelC = new GridBagConstraints();
		windowControlPanel.setBackground(borderColor);
		
		//mainPanel
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints mainPanelC = new GridBagConstraints();
		mainPanel.setBackground(backgroundColor);

		//IOPanel
		IOPanel.setLayout(new GridBagLayout());
		GridBagConstraints IOPanelC = new GridBagConstraints();
		IOPanel.setBackground(backgroundColor);

		//inputPanel
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints inputPanelC = new GridBagConstraints();
		inputPanel.setBackground(backgroundColor);

		//topFuncPanel
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
		topFuncPanelC.ipadx = 1;
		topFuncPanelC.gridy = 0;

		//degreeBtn
		angleBtn.setText("Deg");
		angleBtn.setBackground(new Color(0, 99, 156));
		angleBtn.setForeground(foregroundColor);
		angleBtn.setFocusable(false);
		topFuncPanelC.gridx = 0;
		topFuncPanelC.gridwidth = 1;
		topFuncPanelC.weightx = 0.5;
		topFuncPanelC.weighty = 1;
		topFuncPanelC.fill = GridBagConstraints.BOTH;
		topFuncPanel.add(angleBtn, topFuncPanelC);
		angleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				degreeBtnClicked();
			}
		});
		
		//clearBtn
		clearBtn.setText("AC");
		clearBtn.setBackground(funcBtnColor);
		clearBtn.setForeground(foregroundColor);
		topFuncPanelC.gridx = 1;
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
		topFuncPanelC.gridx = 2;
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
		topFuncPanelC.gridx = 3;
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
		topFuncPanelC.gridx = 4;
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
		topFuncPanelC.gridx = 5;
		topFuncPanel.add(closePBtn, topFuncPanelC);
		closePBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closePBtnClicked();
			}
		});
		
		//checkBox
		checkBox.setText("Advanced mode");
		checkBox.setBackground(backgroundColor);
		checkBox.setForeground(foregroundColor);
		checkBox.setFocusable(false);
		mainPanelC.gridy = 7;
		mainPanelC.fill = GridBagConstraints.HORIZONTAL;
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
		IOPanelC.gridy = 0;
		IOPanelC.weighty = 1;

		IOPanelC.fill = GridBagConstraints.BOTH;
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
		
		IOPanel.add(textAreaScrollPane, IOPanelC);
		
		//textResult
		textResult.setText("");
		textResult.setFont(new Font("Arial", Font.PLAIN, 25));
		textResult.setLineWrap(true);
		textResult.setForeground(foregroundColor);
		textResult.setBackground(fieldColor);
		textResult.setEditable(false);
		IOPanelC.gridy = 1;
		IOPanelC.weightx = 1;
		IOPanelC.weighty = 0.5;
		IOPanelC.fill = GridBagConstraints.BOTH;

		textResultScrollPane.setBorder(BorderFactory.createEmptyBorder());
		textResultScrollPane.setBackground(fieldColor);
		textResultScrollPane.setViewportView(textResult);
		textResultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textResultScrollPane.setMinimumSize(new Dimension(100, 50));
		textResultScrollPane.setPreferredSize(new Dimension(WIDTH, 50));
		textResultScrollPane.setMaximumSize(new Dimension(WIDTH, 50));
		IOPanel.add(textResultScrollPane, IOPanelC);
		
		//topBorderBtnPanel content:
		topBorderBtnPanelC.ipadx = 50;
		topBorderBtnPanelC.ipady = 15;
		//closeBtn
		closeBtn.setText("×");
		closeBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		closeBtn.setBackground(borderColor);
		closeBtn.setForeground(foregroundColor);
		closeBtn.setBorder(BorderFactory.createLineBorder(backgroundColor));
		closeBtn.setFocusable(false);
		topBorderBtnPanelC.gridx = 2;
		topBorderBtnPanelC.gridy = 0;

		windowControlPanel.add(closeBtn, topBorderBtnPanelC);
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
		windowControlPanel.add(maximizeBtn, topBorderBtnPanelC);
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
		windowControlPanel.add(minimizeBtn, topBorderBtnPanelC);
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
		//windowTitleLabel.setBorder(BorderFactory.createLineBorder(Color.pink));
		windowTitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));

		//topBorderPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
		windowTitlePanelC.fill = GridBagConstraints.BOTH;
		windowTitlePanelC.anchor = GridBagConstraints.LINE_START;
		windowTitlePanelC.weightx = 1;
		windowTitlePanelC.weighty = 0;
		windowTitlePanel.add(windowTitleLabel, windowTitlePanelC);
		windowTitleLabel.addMouseListener(mouseListener);
		windowTitleLabel.addMouseMotionListener(mouseMotionListener);
		
		//topBorderBtnPanel
		//topBorderBtnPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
		windowControlPanel.setBackground(borderColor);
		
		windowTitlePanelC.fill = GridBagConstraints.NONE;
		windowTitlePanelC.anchor = GridBagConstraints.LINE_END;
		windowTitlePanelC.weightx = 0;
		windowTitlePanelC.gridx = 1;
		windowTitlePanel.add(windowControlPanel, windowTitlePanelC);
		
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
		inputPanelC.gridx = 0;
		inputPanelC.gridy = 3;
		inputPanelC.gridwidth = 1;
		inputPanelC.gridheight = 4;
		inputPanelC.weightx = 1.0;
		inputPanelC.weighty = 1.0;
		inputPanelC.fill = GridBagConstraints.BOTH;
		inputPanel.add(btnPanel, inputPanelC);
		
		//topBorderPanel
		
		//topBorderPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 0;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 0;
		mainPanelC.weighty = 0;
		mainPanelC.fill = GridBagConstraints.HORIZONTAL;
		mainPanelC.anchor = GridBagConstraints.CENTER;
		mainPanel.add(windowTitlePanel, mainPanelC);

		//IOPanel
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 1;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 1.0;
		mainPanelC.weighty = 0.6;
		mainPanelC.fill = GridBagConstraints.BOTH;
		//IOPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
		mainPanel.add(IOPanel, mainPanelC);
		
		//inputPanel
		mainPanelC.gridx = 0;
		mainPanelC.gridy = 2;
		mainPanelC.gridwidth = 1;
		mainPanelC.gridheight = 1;
		mainPanelC.weightx = 1.0;
		mainPanelC.weighty = 0.4;
		mainPanelC.fill = GridBagConstraints.BOTH;
		//inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(inputPanel, mainPanelC);
		
		
		//window
		windowC.gridx = 0;
		windowC.weightx = 0.1;
		windowC.weighty = 1;
		windowC.fill = GridBagConstraints.BOTH;
		//mainPanel
		mainPanel.setSize(new Dimension(WIDTH, HEIGHT));
		window.add(mainPanel, windowC);
		
		//graphPanel
		//graphPanel
		graphPanel = new GraphCanvas(WIDTH+300, HEIGHT);
		graphPanel.setLayout(new BorderLayout());
		graphPanel.setBackground(Color.white);
		windowC.gridx = 1;
		windowC.weightx = 0.9;
		windowC.weighty = 1;
		window.add(graphPanel, windowC);
		graphPanel.addMouseListener(mouseListener);
		graphPanel.addMouseMotionListener(mouseMotionListener);
		//graphPanel.draw(graphPanel.getGraphics());


		//windowC.fill = GridBagConstraints.BOTH;
		//windowC.weightx = 1;
		//mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); Needed?
		
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
		window.getContentPane().addMouseListener(mouseListener);

		window.getContentPane().addMouseMotionListener(mouseMotionListener);
		window.getRootPane().setBorder(BorderFactory.createEmptyBorder(BORDERWIDTH, BORDERWIDTH, BORDERWIDTH, BORDERWIDTH));
		
		window.getRootPane().addMouseListener(mouseListener);
		
		window.getRootPane().addMouseMotionListener(mouseMotionListener);
		//window.pack();
		showAdvanced(false);
		//checkBox.doClick();
		window.Show();
	}
	
	private void showAdvanced(boolean isChecked) {
		openPBtn.setVisible(isChecked);
		closePBtn.setVisible(isChecked);
		
		if (isChecked) {
			graphPanel.setVisible(true);
			//mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth() + graphPanel.getWidth(), mainPanel.getHeight()));
			window.setSize(new Dimension(window.getWidth() + graphPanel.getWidth(), window.getHeight()));

			textArea.setCaretColor(Color.orange);
		}else {
			graphPanel.setVisible(false);
			//mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth() - graphPanel.getWidth(), mainPanel.getHeight()));
			window.setSize(new Dimension(window.getWidth() - graphPanel.getWidth(), window.getHeight()));

			textArea.setCaretColor(foregroundColor);
		}
	}
	
	//All events
	public void divideBtnClicked() {}
	public void multBtnClicked() {}
	public void subtractBtnClicked() {}
	public void addBtnClicked() {}
	public void sqrtBtnClicked() {};
	public void squareBtnClicked() {};
	public void openPBtnClicked() {};
	public void closePBtnClicked() {};
	public void clearBtnClicked() {}
	public void degreeBtnClicked() {}
	public void calcBtnClicked() {}
	public void numbBtnClicked(Object obj) {}
	public void keyType(KeyEvent e) {}
	public void keyRelease(KeyEvent e) {}
	public void keyPress(KeyEvent e) {}
	public void closeBtnClick() {}
	public void minimizeBtnClick() {}
	public void maximizeBtnClick() {}
	public void MousePressed(MouseEvent e) {}                              
	public void MouseReleased(MouseEvent e) {}                              
	public void MouseDragged(MouseEvent e) {}
	public void MouseMoved(MouseEvent e) {}  
	
	//Quick way to add all numeric buttons from 1 to 9
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
	}
}
