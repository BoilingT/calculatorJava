package calculator.src.calculator;

import java.awt.Dimension;

import javax.swing.*;

public class WindowHandler extends JFrame{
	
	public WindowHandler(int width, int height, String title) {
		this.setTitle(title);
		//G�r en panel s� att ramen anpassar sin storlek efter panelens storlek.
		//P� s� s�tt f�r ritomr�det samma dimensioner som den givna dimensionen.
		//-----------------------------------------------------
		//JPanel panel = new JPanel();
		//panel.setOpaque(false);
		//panel.setPreferredSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		//-----------------------------------------------------
		//add(panel);
		/*G�r s� att anv�ndaren inte kan �ndra storleken p� f�nstret, 
		 * annars m�ste panelen anpassa sin storlek till den nya storleken 
		 * vilket �r en funktion som jag inte vill l�gga till
		 */
		//�ndrar storleken p� f�nstret s� att allting inuti f�nstret f�r plats och syns
		//pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void Show() {
		setVisible(true);
	}
}
