import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlackjackGUI extends JPanel implements ActionListener{
	
	private Deck deck;
	private BlackjackHand userHand = new BlackjackHand();
	private BlackjackHand dealerHand = new BlackjackHand();
	private String message = "Play Blackjack!";
	private JButton hit, stand;
	private JTextField betField;
	private double pot = 100.0;
	private boolean warning = false;//warns player if they bet more money than they have.
	
	public static void main(String[] args){
		
		JFrame window = new JFrame("Blackjack");
		window.setSize(600, 450);
		window.setResizable(false);
		BlackjackGUI content = new BlackjackGUI();
		window.setContentPane(content);
		window.setVisible(true);
		
	}
	
	public BlackjackGUI(){//constructs button panel and panel with cards.
		
		setLayout(new BorderLayout());
		
		JPanel cardTable = new JPanel();
		setOpaque(true);
		setBackground(new Color(164, 0, 45));
		cardTable.setOpaque(false);
		add(cardTable, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		
		hit = new JButton("Hit");
		hit.addActionListener(this);
		hit.setEnabled(false);
		buttonPanel.add(hit);
		
		stand = new JButton("Stand");
		stand.addActionListener(this);
		stand.setEnabled(false);
		buttonPanel.add(stand);
		
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(this);
		buttonPanel.add(newGame);
		
		JLabel betLabel = new JLabel("Bet:");
		buttonPanel.add(betLabel);
		
		betField = new JTextField("0", 5);
		buttonPanel.add(betField);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
		
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		
		int i;//iterator
		
		for(i = 0; i < dealerHand.getCardCount(); i ++){
		
			if(i == 1)
				drawCard(g, dealerHand.getCard(i), 10 + (100 * i), 60, true);
			else
				drawCard(g, dealerHand.getCard(i), 10 + (100 * i), 60, false);
		
		}
		for(i = 0; i < userHand.getCardCount(); i ++){
			drawCard(g, userHand.getCard(i), 10 + (100 * i), 220, true);
		}
		
		g.setColor(Color.BLUE);
		g.drawString(message, 10, 370);
		g.drawString("Dealer's Cards:", 10, 50);
		g.drawString("Your Cards:", 10, 210);
		g.drawString("You have " + pot + " dollars.", 300, 210);
		if(warning)
			g.drawString("You don't have enough money to make that bet. >:(", 10, 196);
				
	}
		
	public void actionPerformed(ActionEvent evt){
		
		String button = evt.getActionCommand();
		if(button.equals("New Game")){
			
			deck = new Deck(false);
			userHand = new BlackjackHand();
			dealerHand = new BlackjackHand();
			deck.shuffle();
			userHand.addCard(deck.dealCard());
			userHand.addCard(deck.dealCard());
			dealerHand.addCard(deck.dealCard());
			dealerHand.addCard(deck.dealCard());
			
			handAssessment(userHand.getBlackjackValue(), dealerHand.getBlackjackValue());
			
			repaint();
			
		}
		else if(button.equals("Hit")){
			
			userHand.addCard(deck.dealCard());
			handAssessment(userHand.getBlackjackValue(), dealerHand.getBlackjackValue());
			repaint();
			
		}
		else if(button.equals("Stand")){
			if(dealerHand.getBlackjackValue() < 17)
				dealerHand.addCard(deck.dealCard());
			
			double bet = Double.parseDouble(betField.getText());
			if(bet > pot)
				warning = true;
			else
				warning = false;
			
			if(userHand.getBlackjackValue() > dealerHand.getBlackjackValue()){
				message = "You win!";
				if(!warning)
					pot += Double.parseDouble(betField.getText());
				betField.selectAll();
			}
			else{
				message = "You lose.";
				if(!warning)
					pot -= Double.parseDouble(betField.getText());
				betField.selectAll();
			}
				
			hit.setEnabled(false);
			stand.setEnabled(false);
			repaint();
		}
				
	}
	
	/**
     * Draws a card as a 80 by 100 rectangle with upper left corner at (x,y).
     * The card is drawn in the graphics context g.  If card is null, then
     * a face-down card is drawn.  (The cards are rather primitive!)
     */
    void drawCard(Graphics g, Card card, int x, int y, boolean faceUp) {
        if (!faceUp) {  
            // Draw a face-down card
            g.setColor(Color.BLUE);
            g.fillRect(x,y,80,100);
            g.setColor(Color.WHITE);
            g.drawRect(x+3,y+3,73,93);
            g.drawRect(x+4,y+4,71,91);
        }
        else {
            g.setColor(Color.WHITE);
            g.fillRect(x,y,80,100);
            g.setColor(Color.GRAY);
            g.drawRect(x,y,79,99);
            g.drawRect(x+1,y+1,77,97);
            if (card.getSuit() == Card.DIAMONDS || card.getSuit() == Card.HEARTS)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);
            g.drawString(card.getValueAsString(), x + 10, y + 30);
            g.drawString("of", x+ 10, y + 50);
            g.drawString(card.getSuitAsString(), x + 10, y + 70);
        }
    } // end drawCard()
	
	/**
		Assesses whether the game is over or not based on the values of each player's hand. Determines functionality of buttons accordingly.
		@param userVal The blackjack value of the player's hand.
		@param dealerVal The blackjack value of the dealer's hand.
	*/
	void handAssessment(int userVal, int dealerVal){
		
		double bet = Double.parseDouble(betField.getText());
		if(bet > pot)
			warning = true;
		else
			warning = false;
		//dealer has 21
		if(dealerVal == 21){
		
			message = "You lose. Dealer has 21.";
			if(!warning)
				pot -= Double.parseDouble(betField.getText());
			betField.selectAll();
			hit.setEnabled(false);
			stand.setEnabled(false);
		
		}
		//player has 21
		else if(userVal == 21){
		
			message = "You win with 21!";
			if(!warning)
				pot += Double.parseDouble(betField.getText());
			betField.selectAll();
			hit.setEnabled(false);
			stand.setEnabled(false);
		
		}
		//player is over 21
		else if(userVal > 21){
		
			message = "You lose. You went over 21.";
			if(!warning)
				pot -= Double.parseDouble(betField.getText());
			betField.selectAll();
			hit.setEnabled(false);
			stand.setEnabled(false);
		
		}
		//dealer is over 21
		else if(dealerVal > 21){
		
			message = "You win! Dealer is over 21!";
			if(!warning)	
				pot += Double.parseDouble(betField.getText());
			betField.selectAll();
			hit.setEnabled(false);
			stand.setEnabled(false);
		
		}
		//player has more than dealer
		else{
			
			message = "Hit or Stand?";
			hit.setEnabled(true);
			stand.setEnabled(true);
		
		}
		
	}
	
}