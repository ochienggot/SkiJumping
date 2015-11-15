/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ngot
 */
public class Jumper implements Comparable<Jumper> {
	private String name;
	private int points;

	public Jumper(String name) {
		this(name, 0);
	}
	public Jumper(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public int points() { return this.points; }

	public String name() { return this.name; }

	public void addPoints(int points) {
		this.points += points;
	}

	@Override
	public int compareTo(Jumper jumper) {
		return this.points - jumper.points();
	}

	@Override
	public String toString() {
		return this.name + " (" + this.points + " points)";
	}

	@Override
	public boolean equals(Object ob) {
		if (ob instanceof Jumper) {
			Jumper other = (Jumper) ob;
			return this.name.equals(other.name());
		}
		// nope
		return false;
	}

	@Override
	public int hashCode() {
		int number = 7;

		number = 37 * number + this.name.hashCode();
		//number = 37 * number + this.points;

		return number;
	}
	
}
