/**
This program draws a small window with a red and a blue square. Users can drag the squares around the window.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SquareMove {
	
	private static class SquareMovePanel extends JPanel implements MouseListener, MouseMotionListener{
		
		private static final int SQUARE_SIZE = 100;
		private static int redX = 100;
		private static int redY = 100;
		private static int blueX = 100 + SQUARE_SIZE;
		private static int blueY = 100 + SQUARE_SIZE;
		private static boolean draggingRed;
		private static boolean draggingBlue;
		private static int redXOffset, redYOffset, blueXOffset, blueYOffset; //difference between square coordinates and mouse event coordinates.
		
		public SquareMovePanel(){
			
			addMouseListener(this);
			addMouseMotionListener(this);
			
		}
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			//paint squares somewhere.
			g.setColor(Color.RED);
			g.fillRect(redX, redY, SQUARE_SIZE, SQUARE_SIZE);
			g.setColor(Color.BLUE);
			g.fillRect(blueX, blueY, SQUARE_SIZE, SQUARE_SIZE);
			
		}
		
		public void mouseClicked(MouseEvent evt){}
		public void mouseEntered(MouseEvent evt){}
		public void mouseExited(MouseEvent evt){}
		public void mousePressed(MouseEvent evt){
			
			if((evt.getX() >= redX && evt.getX() <= (redX + SQUARE_SIZE)) && (evt.getY() >= redY && evt.getY() <= (redY + SQUARE_SIZE))){
			//must be in the bounds of a square.
			
				draggingRed = true;
				redXOffset = evt.getX() - redX;
				redYOffset = evt.getY() - redY;
				redX = evt.getX() - redXOffset;
				redY = evt.getY() - redYOffset;
				repaint();
			
			}
			
			if((evt.getX() >= blueX && evt.getX() <= (blueX + SQUARE_SIZE)) && (evt.getY() >= blueY && evt.getY() <= (blueY + SQUARE_SIZE))){
			//must in the bounds of a square.
			
				draggingBlue = true;
				blueXOffset = evt.getX() - blueX;
				blueYOffset = evt.getY() - blueY;
				blueX = evt.getX() - blueXOffset;
				blueY = evt.getY() - blueYOffset;
				repaint();
			
			}
			
		}
		
		public void mouseReleased(MouseEvent evt){
			
			draggingRed = false;
			draggingBlue = false;
			
		}
			
		public void mouseMoved(MouseEvent evt){}
		public void mouseDragged(MouseEvent evt){
						
			if(draggingRed){
				
				redX = evt.getX() - redXOffset;
				redY = evt.getY() - redYOffset;
				repaint();
				
			}
			
			if(draggingBlue){
				
				blueX = evt.getX() - blueXOffset;
				blueY = evt.getY() - blueYOffset;
				repaint();
				
			}
			
		}
		
	}
	
	public static void main(String[] args){
		
		JFrame window = new JFrame();//create JPanel object
		window.setVisible(true);
		window.setSize(400,400);
		SquareMovePanel content = new SquareMovePanel();
		window.setContentPane(content);
		
		
	}
	
}