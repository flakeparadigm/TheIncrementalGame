package model;

public class Player {
	private String username;
	private int clickScore;
	private int autoScore;
	private int scoreFactor;
	
	// constructor
	public Player(String username) {
		this.username = username;
		
		clickScore = 0;
		autoScore = 0;
		scoreFactor = 1;
	}
	
	// get info
	public String getUsername() {
		return username;
	}
	public int getScore() {
		return clickScore + autoScore;
	}
	public int getScoreFactor() {
		return scoreFactor;
	}
	
	// score manipulation
	// returns score after manipulation
	public int incrementScore() {
		return autoScore + (clickScore += scoreFactor);
	}
	public int decrementScore() {
		return autoScore + (clickScore--);
	}
	public int changeScoreBy(int change) {
		return autoScore + (clickScore += change * scoreFactor);
	}
	public int autoScore() {
		return clickScore + (autoScore += scoreFactor);
	}
	
	// scoreFactor upgrades
	// returns scoreFactor after changes
	public int incrementScoreFacotor() {
		return (scoreFactor++);
	}
	public int decrementScoreFacotor() {
		return (scoreFactor--);
	}
	public int changeScoreFacotorBy(int factor) {
		return (scoreFactor *= factor);
	}
	
}
