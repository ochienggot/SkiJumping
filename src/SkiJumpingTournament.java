/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
//import java.util.stream.IntStream;
/**
 *
 * @author ngot
 */
public class SkiJumpingTournament {
	private Map<Jumper, ArrayList<Integer>> jumpersMap; // for jump lengths
	private Map<Jumper, ArrayList<Integer>> jumpDecision; // for decisons
	private ArrayList<Jumper> jumpersList; // for sorting according to points
	private Scanner reader;
	private int roundNumber;
	private Random rand;

	public SkiJumpingTournament(Scanner reader) {
		this.jumpersMap = new HashMap<Jumper, ArrayList<Integer>>();
		this.jumpDecision = new HashMap<Jumper, ArrayList<Integer>>();
		this.reader = reader;
		this.jumpersList = new ArrayList<Jumper>();
		this.roundNumber = 0;
		this.rand = new Random();
	}

	public int getRoundNumber() { return this.roundNumber; }

	public void points() {
		for (Jumper jumper : jumpDecision.keySet()) {
			int sum = 0;
			int decisionPoints = 0;
			for (int i : jumpDecision.get(jumper)) {
				decisionPoints += i;
			}
			// remove largest and smallest
			decisionPoints = decisionPoints - Collections.max(jumpDecision.get(jumper)); 
			decisionPoints = decisionPoints - Collections.min(jumpDecision.get(jumper)); 
			sum += jumpersMap.get(jumper).get(getRoundNumber()-1) + decisionPoints;

			// Why is equals not working???
			for (Jumper jmp : jumpersList) {
				if (jmp.name().equals(jumper.name())) {
					// Object references, must be the same
					jmp.addPoints(sum);
				}
			}

		}
	}

	public void jump() {

		for (Jumper jumper : jumpersMap.keySet()) {
			int jumpLength;
			ArrayList<Integer> decision = new ArrayList<Integer>();
			jumpLength = 60 + rand.nextInt(120 - 60 + 1);

			for (int i = 0; i < 5; i++) {
				decision.add(10 + rand.nextInt(20 - 10 + 1));
			}
			// store in decisions map
			jumpDecision.put(jumper, decision);
			// Store jump in map
			jumpersMap.get(jumper).add(jumpLength);

		}

		System.out.println("Results of round " + this.roundNumber);
		for (Jumper jumper : jumpersMap.keySet()	) {
			System.out.println("  " + jumper.name());

			int index = jumpersMap.get(jumper).size();
			System.out.println("    length: " + jumpersMap.get(jumper).get(index-1));

			System.out.print("    judge votes: ");
			System.out.println((jumpDecision.get(jumper)));
		}
	}

	public void printResults() {
		System.out.println("Tournament results:");
		Collections.sort(jumpersList);
		Collections.reverse(jumpersList);
		System.out.print("Position    ");
		System.out.println("Name");

		for (int i = 0; i < jumpersList.size(); i++) {
			System.out.println((i+1) + "           " + jumpersList.get(i).name()
			+ " (" + jumpersList.get(i).points() + " points)");
			System.out.print("            " + "jump lengths: ");

			for (Jumper jumper : jumpersMap.keySet()) {
				if (jumper.name().equals(jumpersList.get(i).name())) {
					ArrayList<Integer> temp = jumpersMap.get(jumper);
					int j;

					for (j = 0; j < temp.size() - 1; j++) {
						System.out.print(temp.get(j) + " m, ");
					}
					System.out.println(temp.get(j) + " m");
				}
			}
		}
	}

	public void start() {
		System.out.println("Kumpula ski jumping week");
		System.out.println("");
		System.out.println("Write the names of the participants one at a time; an empty"
		    + " string brings you to the jumping phase.");
		System.out.print("  Participant name: ");
		String name = this.reader.nextLine();

		while (!name.isEmpty()) {
			ArrayList<Integer> jumps = new ArrayList<Integer>();
			jumpersMap.put(new Jumper(name), jumps);
			jumpersList.add(new Jumper(name));

			System.out.print("  Participant name: ");
			name = this.reader.nextLine();
		}
		System.out.println("");
		System.out.println("The tournament begins!");
		System.out.println("");
		// reuse reader here
		System.out.print("Write \"jump\" to jump; otherwise you quit: ");
		String command = reader.nextLine();

		while (command.equals("jump")) {
			this.roundNumber++;
			System.out.println("Round " + getRoundNumber());
			System.out.println("");

			System.out.println("Jumping order: ");
			Collections.sort(jumpersList);
			//Collections.reverse(jumpersList);
			for (int i = 0; i < jumpersList.size(); i++) {
				System.out.print("  " + (i+1) + ". ");
				System.out.println(jumpersList.get(i));
			}

			// jump!
			jump();

			// put the points in the bag
			points();

			// read next input
			System.out.print("Write \"jump\" to jump; otherwise you quit: ");
			command = reader.nextLine();
		}

		// Ok, so you do wanna play any more?
		System.out.println("");
		System.out.println("Thanks!");
		System.out.println("");

		printResults();

	}
}
