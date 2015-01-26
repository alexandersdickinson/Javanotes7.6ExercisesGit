import javax.swing.JFrame;

public class TextAnalysisGUI{
	
	public static void main(String[] args){
		
		JFrame window = new JFrame("Text Analyzer");
		TextAnalysis content = new TextAnalysis();
		window.setSize(300, 350);
		window.setContentPane(content);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		
	}
	
}