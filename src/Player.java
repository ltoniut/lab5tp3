import java.util.ArrayList;
import java.util.Observable;

public class Player extends Observable implements Runnable {
	private String name;

	private int id;
	
	private boolean hasCards;
	
	private ArrayList<Card> cards;
	private Card lastCard;
	
	private Dealer dealer;
	
	
	
	public Player(String name, Dealer dealer, int id) {
		this.name = name;
		this.hasCards = false;
		this.dealer = dealer;
		this.id = id;
		
		this.cards = new ArrayList<Card>();
	}
	
	@Override
    public void run() {

		while (!dealer.EmptyDeck()) {
		    lastCard = dealer.Deal();
		    cards.add(lastCard);
		    hasCards = true;
		    
		    this.setChanged();
		    this.notifyObservers();
	
		    try {
		    	Thread.sleep(250);
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
		}
    }
	
	public int getScore() {
		int score = 0;
		
		for (Card c : cards) {
			score += c.getNumber();
		}
		
		return score;
	}
	
	public boolean getHasCards() {
		return hasCards;
	}
	

	public int getId() {
		return id;
	}

	public String lastCardInfo() {
		return lastCard.getNumber() + "" + lastCard.getSuit();
	}

	public String getName() {
		return name;
	}
}
