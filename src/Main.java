import java.sql.*;
import java.time.LocalDate;

public class Main {
    public static void main(String args[]) {
    	Deck deck = new Deck();
    	
    	Dealer dealer = new Dealer(deck);
    	Player player1 = new Player("Leandro", dealer, 1);
    	Player player2 = new Player("Franco", dealer, 2);
    	Player player3 = new Player("Antonino", dealer, 3);

    	player1.addObserver(dealer);
    	player2.addObserver(dealer);
    	player3.addObserver(dealer);
    	
    	Player winner = null;
    	
    	while(!deck.IsEmpty()) {
	    	(new Thread(player1)).start();
	    	(new Thread(player2)).start();
	    	(new Thread(player3)).start();
    	}
    	
    	int score = 0;
    	
    	if (player1.getScore() > score) {
    		winner = player1;
    		score = player1.getScore();
    	}
    	
    	if (player2.getScore() > score) {
    		winner = player2;
    		score = player2.getScore();
    	}
    	
    	if (player3.getScore() > score) {
    		winner = player3;
    		score = player3.getScore();
    	}
    	
    	System.out.println(winner.getName() + " gana con " + winner.getScore() + " puntos");
    	
    	try {  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/card_game", "user", "mypass");  
    		Statement stmt = con.createStatement();  
    		stmt.executeQuery("INSERT INTO matches (winnerId, score, date) VALUES " + winner.getId() + ", " + winner.getScore() + ", " + LocalDate.now());
    		
    		con.close();  
		}
    	
    	catch(Exception e){ 
    		System.out.println(e);
    	}
    }
}
