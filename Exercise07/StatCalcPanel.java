/**
	This class creates a panel that calculates statistics. It uses a StatCalc object, which is used to compile data. It allows users to enter data into a dataset
	and clear the dataset, which allows users to start over.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StatCalcPanel extends JPanel{
	
	StatCalc dataset;
	JLabel numLabel, sumLabel, meanLabel, stdDevLabel;
	
	public StatCalcPanel(){
		
		dataset = new StatCalc();
		setLayout(new GridLayout(0, 1));
		//add message panel
		JLabel message = new JLabel("Enter a number, and press return:", JLabel.CENTER);
		message.setBorder(BorderFactory.createEtchedBorder());
		//message.setPreferredSize(new Dimension(250, 400));
		add(message);
		//add button panel, this will need an inner class
		add(new ButtonPanel());
		//add number
		numLabel = new JLabel("Number: " + dataset.getCount(), JLabel.CENTER);
		numLabel.setBorder(BorderFactory.createEtchedBorder());
		add(numLabel);
		//add sum
		sumLabel = new JLabel("Sum: " + dataset.getSum(), JLabel.CENTER);
		sumLabel.setBorder(BorderFactory.createEtchedBorder());
		add(sumLabel);
		//add mean
		meanLabel = new JLabel("Mean: " + dataset.getMean(), JLabel.CENTER);
		meanLabel.setBorder(BorderFactory.createEtchedBorder());
		add(meanLabel);
		//add standard deviation
		stdDevLabel = new JLabel("Standard Deviation: " + dataset.getStandardDeviation(), JLabel.CENTER);
		stdDevLabel.setBorder(BorderFactory.createEtchedBorder());
		add(stdDevLabel);
		
	}
	
	/**
		This forms the panel with the text entry field, enter, and clear buttons.
	*/
	private class ButtonPanel extends JPanel implements ActionListener{
		
		JTextField dataEntry;
		
		public ButtonPanel(){
			
			dataEntry = new JTextField(null, 5);
			dataEntry.addActionListener(this);
			add(dataEntry);
			
			JButton enterButton = new JButton("Enter");
			enterButton.addActionListener(this);
			add(enterButton);
			
			JButton clear = new JButton("Clear");
			clear.addActionListener(this);
			add(clear);
			setBorder(BorderFactory.createEtchedBorder());
		}
		
		public void actionPerformed(ActionEvent evt){
			
			String command = evt.getActionCommand();
			
			if(command.equals("Clear")){
				dataset = new StatCalc();
				dataEntry.requestFocus();
			}
			else
				dataset.enter(Double.parseDouble(dataEntry.getText()));
				
				numLabel.setText("Number: " + dataset.getCount());
				sumLabel.setText("Sum: " + dataset.getSum());
				meanLabel.setText("Mean: " + dataset.getMean());
				stdDevLabel.setText("Standard Deviation: " + dataset.getStandardDeviation());
				dataEntry.selectAll();
			
		}
		
	}
	
}