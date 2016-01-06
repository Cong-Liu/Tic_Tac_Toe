package FinalProject;

import java.text.DecimalFormat;

public class User {
	protected String name;
	protected int wins;
	protected int total;
	protected double rate;

	public User() {
		name = "";
		wins = 0;
		total = 0;
		rate = 0;
	}

	public User(String line) throws Exception {

		// set user data from FileReader
		// file formula: name,wins,total

		String[] a = line.split(",");
		if (a.length != 3)
			throw new Exception("Could not read input file.");
		name = a[0];
		total = Integer.parseInt(a[1]);
		wins = Integer.parseInt(a[2]);
		rate = (double)wins/total;
	}

	public void setUserName(String aStr) throws Exception {
		if (aStr.length() < 3 || !Character.isLetter(aStr.charAt(0)))
			throw new Exception("User name format not valid");
		name = aStr;
	}

	public void setWins(int a) {
		wins = a;
	}

	public void setTotal(int a) {
		total = a;
	}

	public String getUserName() {
		return name;
	}

	public int getWins() {
		return wins;
	}

	public double getRate() {
		return rate;
	}

	public int getTotal() {
		return total;
	}

	public String display() {
		DecimalFormat a = new DecimalFormat("0.00%");
		String frate = a.format(rate);
		return (name + "     " + "\t" + wins + "\t" + frate);
	}

	public String toString() {
		return (name + "," + total + "," + wins + ",");
	}

	public int compareTo(User a) {
		if (Math.abs(rate - a.getRate()) < 0.0001) {
			return 0;
		} else if (rate - a.getRate() > 0.0001)
			return 1;
		else
			return -1;
	}

	// equals method by author and title
	public boolean equals(User a) {
		if (name.equals(a.getUserName())) {
			return true;
		}
		return false;
	}

}
