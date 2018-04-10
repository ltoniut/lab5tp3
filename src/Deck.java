import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	private Stack<Card> cards;
	
	public Deck() {
		cards = new Stack<Card>();
		
		ArrayList<Card> shufflable = new ArrayList<Card>();

		int n;
		
		for(n = 1; n < 13; n++) {
			shufflable.add(new Card('E', n));
			shufflable.add(new Card('B', n));
			shufflable.add(new Card('O', n));
			shufflable.add(new Card('C', n));
		}
		
		int random;

		for (n = shufflable.size(); n > 1; n--) {
			random = ThreadLocalRandom.current().nextInt(0, n - 1);
			
			cards.push(shufflable.get(random));
			shufflable.remove(random);
		}
	}
	
	public boolean IsEmpty() {
		return cards.size() == 0;
	}
	
	public Card PopTop() {
		return cards.pop();
	}
}
