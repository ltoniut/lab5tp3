
public class Card {
	private char suit;
	private int number;
	
	public Card(char s, int n) {
		this.suit = s;
		this.number = n;
	}

	public char getSuit() {
		return suit;
	}

	public int getNumber() {
		return number;
	}
}
