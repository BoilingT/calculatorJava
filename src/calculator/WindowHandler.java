package calculator;

import java.awt.Dimension;

import javax.swing.*;

public class WindowHandler extends JFrame{
	
	public WindowHandler(int width, int height, String title) {
		this.setTitle(title);
		//Gör en panel så att ramen anpassar sin storlek efter panelens storlek.
		//På så sätt får ritområdet samma dimensioner som den givna dimensionen.
		//-----------------------------------------------------
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(width, height));
		//-----------------------------------------------------
		add(panel);
		/*Gör så att användaren inte kan ändra storleken på fönstret, 
		 * annars måste panelen anpassa sin storlek till den nya storleken 
		 * vilket är en funktion som jag inte vill lägga till
		 */
		setResizable(false);
		//Ändrar storleken på fönstret så att allting inuti fönstret för plats och syns
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void Show() {
		setVisible(true);
	}
}
