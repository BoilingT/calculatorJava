package calculator.src.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends Window_Design{
	
	public void Init() {
		InitializeComponents();
		multBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onClick(e);
			}
		});
	}
	
	public void onClick(ActionEvent e) {
		multBtn.setText("H");
		System.out.println(e.toString());

	}
}
