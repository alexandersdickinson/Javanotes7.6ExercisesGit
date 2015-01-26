/**
	This class runs the StatCalcPanel as a program.
*/

import javax.swing.JFrame;

public class StatCalcGUI{
	
	public static void main(String[] args){
		
		JFrame window = new JFrame();
		window.setVisible(true);
		StatCalcPanel content = new StatCalcPanel();
		window.setContentPane(content);
		window.pack();
		
	}
	
}