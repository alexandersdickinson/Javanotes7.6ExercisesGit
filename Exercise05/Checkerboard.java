import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Checkerboard extends JPanel{
	
	static int selectedCol;
	static int selectedRow;
	
	public Checkerboard(){
		
		setVisible(true);
		setPreferredSize(new Dimension(160, 160));//size size
		addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent evt){
				
				selectedCol = evt.getX() / 20;
				selectedRow = evt.getY() / 20;
				repaint();
				
			}
			
		});
		
	}
	
	public void paintComponent(Graphics g){
		
		for(int cols = 0; cols < 8; cols++){
			
			for(int rows = 0; rows < 8; rows++){
				
				if(rows % 2 == cols % 2)
					g.setColor(Color.RED);
				else
					g.setColor(Color.BLACK);
				
				g.fillRect(cols * 20, rows * 20, 20, 20);
				
			}
			
		}
		
		g.setColor(Color.CYAN);
		g.drawRect(selectedCol * 20, selectedRow * 20, 19, 19);
		
	}
	
}