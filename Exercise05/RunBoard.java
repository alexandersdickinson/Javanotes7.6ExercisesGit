import javax.swing.JFrame;

public class RunBoard{
	
	public static void main(String[] args){
		
		JFrame window = new JFrame("Checkerboard");
		window.setVisible(true);
		Checkerboard contents = new Checkerboard();
		window.setContentPane(contents);
		window.pack();
		
	}
	
}