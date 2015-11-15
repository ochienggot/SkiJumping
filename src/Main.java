import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner reader = new Scanner(System.in);
	    
	    SkiJumpingTournament tourna = new SkiJumpingTournament(reader);

	    tourna.start();
    }
}
