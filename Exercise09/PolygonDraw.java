/**This class creates a panel that draws a polygon with as many vertices as the user desires. It will have a button that tells the component to close the 
polygon. Vertices are chosen with clicks of the mouse on the drawing surface.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class PolygonDraw extends JPanel implements ActionListener{
	
	private static DrawPanel drawingSurface;
	private static ArrayList<Coordinate> vertices = new ArrayList<Coordinate>(1);
	private static boolean close = false;
	int indices = 1;
	
	public PolygonDraw(){
		
		//create painting panel.
		setLayout(new BorderLayout());
		drawingSurface = new DrawPanel();
		add(drawingSurface, BorderLayout.CENTER);
		
		JButton closePoly = new JButton("Close the Polygon");
		closePoly.addActionListener(this);
				
		JButton clear = new JButton("Clear");
		clear.addActionListener(this);
				
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,1));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonPanel.add(clear);
		buttonPanel.add(closePoly);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	private class DrawPanel extends JPanel{
		
		public DrawPanel(){
			
			addMouseListener(new MouseAdapter(){
				
				/**
					This creates an array containing coordinates for every mouse press. Logic in this allows for the creation of array that get larger and larger as more
					vertices are created by the user.
				*/
				public void mousePressed(MouseEvent evt){//compile list of vertices
		
					if(close){
						
						vertices.clear();
						close = false;
						
					}
					
					vertices.add(new Coordinate(evt.getX(), evt.getY()));
					repaint();
					
				}
				
			});
			
		}
	
		public void paintComponent(Graphics g){//draw lines between vertices, finish polygon, and fill polygon in with a color.
			
			int[] xCoords, yCoords;
		
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
			
			for(int i = 0; i < vertices.size(); i++){
				
				if(i > 0){
					
					g.setColor(Color.RED);
					g.drawLine(vertices.get(i - 1).getX(), vertices.get(i - 1).getY(), vertices.get(i).getX(), vertices.get(i).getY());
					
				}
				
				g.setColor(Color.BLACK);
				g.fillOval(vertices.get(i).getX() - 2, vertices.get(i).getY() - 2, 4, 4);
				
			}
			
			if(close){
				
				xCoords = new int[vertices.size()];
				yCoords = new int[vertices.size()];
				
				for(int i = 0; i < vertices.size(); i++){
					
					xCoords[i] = vertices.get(i).getX();
					yCoords[i] = vertices.get(i).getY();
					
				}
				
				Coordinate element1 = vertices.get(0);
				Coordinate elementN = vertices.get(vertices.size() - 1);
				g.setColor(Color.BLUE);
				g.drawLine(element1.getX(), element1.getY(), elementN.getX(), elementN.getY());
				g.fillPolygon(xCoords, yCoords, vertices.size());
				
			}
		
		}
		
	}
	
	/**
		This class represents a pair of coordinates.
	*/
	private class Coordinate{
		
		private int x;
		private int y;
		
		public Coordinate(){
			
			x = 0;
			y = 0;
			
		}
		
		public Coordinate(int inputX, int inputY){
			
			x = inputX;
			y = inputY;
			
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public void setX(int inputX){
			x = inputX;
		}
		
		public void setY(int inputY){
			y = inputY;
		}
		
	}
	
	public void actionPerformed(ActionEvent evt){//Clear drawing area, or close vertices to make polygon.
	
		String button = evt.getActionCommand();
		if(button.equals("Close the Polygon") && vertices.size() > 2){
			close = true;
			repaint();
		}
		
		if(button.equals("Clear")){
			vertices.clear();
			repaint();
		}
		
	}
	
	
}
