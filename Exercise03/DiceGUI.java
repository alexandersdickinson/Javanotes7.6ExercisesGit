import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DiceGUI{
	
	static final int DIMENSIONS = 350;
	
	private static class DicePanel extends JPanel implements MouseListener{
		
		static final int DIE_SIZE = DIMENSIONS / 8 * 3;
		static PairOfDice dice;
		
		public DicePanel(){
			
			dice = new PairOfDice();
			addMouseListener(this);
			
		}
		
		public void paintComponent(Graphics g){
			
			int die1 = dice.getDie1();
			int die2 = dice.getDie2();
			int die1Coordinates = DIMENSIONS / 16;
			int die2Coordinates = (DIMENSIONS / 16) * 9;
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			
			drawDie(die1, die1Coordinates, g);
			drawDie(die2, die2Coordinates, g);
			
		}
		
		/**
			This draws a die with a certain number of dots at a certain point in a panel, with a certain graphics context. Location and size of the die is relative
			to the size of the panel.
			Precondition: A panel with member constants DIE_SIZE and DIMENSIONS, DIMENSIONS being the size of the panel as a whole. There must also be a value of the
			dice.
			Postcondition: A die will be painted on the graphics context, with dots depending on what the dice roll value is.
			@param diceValue The value of a die.
			@param dieCoordinates The coordinates of a die. Only one coordinate value is needed, as the top left corner of the die is drawn intersecting a diagonal
			line going through the panel, which is assumed to be square.
			@param g The graphics context.
		*/
		static void drawDie(int diceValue, int dieCoordinates, Graphics g){
			
			g.setColor(Color.WHITE);
			g.fillRoundRect(dieCoordinates, dieCoordinates, DIE_SIZE, DIE_SIZE, DIE_SIZE / 10, DIE_SIZE / 10);
			g.setColor(Color.BLACK);
			g.drawRoundRect(dieCoordinates, dieCoordinates, DIE_SIZE, DIE_SIZE, DIE_SIZE / 10, DIE_SIZE / 10);
			//if dice total is odd, draw dot in center
			if(diceValue % 2 == 1){
				g.fillOval((DIE_SIZE/7) * 3 + dieCoordinates, (DIE_SIZE/7) * 3 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
			}
			
			if(diceValue > 1){
				
				g.fillOval((DIE_SIZE/7) * 1 + dieCoordinates, (DIE_SIZE/7) * 1 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
				g.fillOval((DIE_SIZE/7) * 5 + dieCoordinates, (DIE_SIZE/7) * 5 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
								
				if(diceValue > 3){
					
					g.fillOval((DIE_SIZE/7) * 5 + dieCoordinates, (DIE_SIZE/7) * 1 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
					g.fillOval((DIE_SIZE/7) * 1 + dieCoordinates, (DIE_SIZE/7) * 5 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
					
					if(diceValue == 6){
						
						g.fillOval((DIE_SIZE/7) * 1 + dieCoordinates, (DIE_SIZE/7) * 3 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
						g.fillOval((DIE_SIZE/7) * 5 + dieCoordinates, (DIE_SIZE/7) * 3 + dieCoordinates, DIE_SIZE/7, DIE_SIZE/7);
						
					}
					
				}
				
			}
			
		}
		
		public void mousePressed(MouseEvent evt){
			
			dice.roll();
			repaint();
			
		}
		public void mouseClicked(MouseEvent evt){}
		public void mouseEntered(MouseEvent evt){}
		public void mouseExited(MouseEvent evt){}
		public void mouseReleased(MouseEvent evt){}
		
	}
	
	public static void main(String[] args){
		
		JFrame window = new JFrame();
		window.setVisible(true);
		DicePanel content = new DicePanel();
		Dimension d = new Dimension(DIMENSIONS, DIMENSIONS);
		content.setPreferredSize(d);
		window.setContentPane(content);
		window.pack();
				
	}
	
}