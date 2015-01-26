import javax.swing.JFrame;

public class PolygonGUI{
	
	public static void main(String[] args){
		
		JFrame window = new JFrame("Draw Polygons!");
		window.setResizable(false);
		window.setSize(500, 500);
		PolygonDraw content = new PolygonDraw();
		window.setContentPane(content);
		window.setVisible(true);
		
	}
	
}