import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your main program here. Implementing your own classes will be very useful.
	    Scanner reader = new Scanner(System.in);
	    // Create new tournament
	    SkiJumpingTournament tourna = new SkiJumpingTournament(reader);

	    tourna.start();
    }
}
