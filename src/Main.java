import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
    	Deck deck = new Deck();
    	
    	Dealer dealer = new Dealer(deck);
    	Player player1 = new Player("Leandro", dealer, 1);
    	Player player2 = new Player("Franco", dealer, 2);
    	Player player3 = new Player("Antonino", dealer, 3);
    	
    	ArrayList<Player> players = new ArrayList<>();

    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	
    	for (Player p : players) {
    		p.addObserver(dealer);
    	}
    	
    	
    	while(!deck.IsEmpty()) {
        	for (Player p : players) {
        		(new Thread(p)).start();
        	}
    	}

    	Player winner = null;
    	int score = 0;
    	
    	for (Player p : players) {
    		if (p.getScore() > score) {
    			score = p.getScore();
    			winner = p;
    		}
    	}
    	
    	System.out.println(winner.getName() + " gana con " + winner.getScore() + " puntos");
    	
    	try {  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/card_game", "root", "mypass");  
    		Statement stmt = con.createStatement();  
    		stmt.executeQuery("INSERT INTO matches (winnerId, score, date) VALUES " + winner.getId() + ", " + winner.getScore() + ", " + LocalDate.now());
    		
    		con.close();  
		}
    	catch(Exception e){ 
    		System.out.println(e);
    	}
    }
}
