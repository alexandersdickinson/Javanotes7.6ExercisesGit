/**
This class creates a panel with a text area, and a button that the user can press, which gives them an analysis of text in the field.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextAnalysis extends JPanel{
	
	private static JTextArea textField;
	private static int charCount, wordCount, lineCount;
	private static String[] stringBase;
	JLabel lines, words, chars;
	
	public TextAnalysis(){
		
		setVisible(true);
		setLayout(new BorderLayout());
		
		textField = new JTextArea(5, 20);
		textField.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(textField);
		add(scroller, BorderLayout.CENTER);
		
		add(new Analysis(), BorderLayout.SOUTH);
		
	}
	
	private class Analysis extends JPanel implements ActionListener{
		
		public Analysis(){
			
			setLayout(new GridLayout(0,1));
			setBorder(BorderFactory.createEtchedBorder());
			
			JButton analysisButton = new JButton("Analyze Text");
			analysisButton.addActionListener(this);
			add(analysisButton);
		
			lines = new JLabel("Lines", JLabel.CENTER);
			lines.setBorder(BorderFactory.createEtchedBorder());
			add(lines);
		
			words = new JLabel("Words", JLabel.CENTER);
			words.setBorder(BorderFactory.createEtchedBorder());
			add(words);
		
			chars = new JLabel(Integer.toString(charCount), JLabel.CENTER);
			chars.setBorder(BorderFactory.createEtchedBorder());
			add(chars);
		
		}
		
		public void actionPerformed(ActionEvent evt){
		
			String text = textField.getText();
			charCount = text.length();
			chars.setText(Integer.toString(charCount));
			
			lineCount = textField.getLineCount() + 1;
			lines.setText(Integer.toString(lineCount));
			
			stringBase = text.split(" ");
			wordCount = stringBase.length;
			words.setText(Integer.toString(wordCount));
		
		}
		
	}
	
}