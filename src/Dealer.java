import java.util.Observable;
import java.util.Observer;

public class Dealer implements Observer {
	private Deck deck;
	
	public Dealer (Deck d) {
		deck = d;
	}
	
	public boolean EmptyDeck() {
		return deck.IsEmpty();
	}
	
	public Card Deal() {
		return deck.PopTop();
	}

	@Override
	public void update(Observable newP, Object oldPlayer) {
		try {
		    Thread.sleep(250);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		Player newPlayer = (Player) newP;
	
		if (newPlayer.getHasCards() != false) {
		    System.out.println(newPlayer.getName() + " recibió " + newPlayer.lastCardInfo());
	
		} else {
		    System.out.println("error");
		    // System.exit(0);
		}
    }
}
