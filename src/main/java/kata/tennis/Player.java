package kata.tennis;

public class Player {

	private String name;
	private int scoreGame = 0;
	private int scoreGameToDislay = 0;
	private int scoreSet = 0;
	private int scoreTieBreak = 0;
	private int setsWon = 0;
	private int gamesWon = 0;

	private int scoreSet1 = 0;
	private int scoreSet2 = 0;
	private int scoreSet3 = 0;
	private int scoreSet4 = 0;
	private int scoreSet5 = 0;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScoreGame() {
		return scoreGame;
	}

	public void setScoreGame(int scoreGame) {
		this.scoreGame = scoreGame;
	}

	public int getScoreSet() {
		return scoreSet;
	}

	public void setScoreSet(int scoreSet) {
		this.scoreSet = scoreSet;
	}

	public int getScoreTieBreak() {
		return scoreTieBreak;
	}

	public void setScoreTieBreak(int scoreTieBreak) {
		this.scoreTieBreak = scoreTieBreak;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void winPoint(Player player2) {
		this.scoreGame = getScoreGame() + 1;
		scoreGameToDislay = scoreGame;
		player2.setScoreGameToDislay(player2.getScoreGame());
		if(hasWinGameAgainst(player2)){
			// reset score game
			scoreGame = 0;
			player2.setScoreGame(0);
			gamesWon ++;
			if(gamesWon == 6){
				gamesWon = 0;
				winSetGame(player2);
			}
		}
	}

	public void winTieBreakPoint(Player player2) {
		this.scoreTieBreak = getScoreTieBreak() + 1;
		if(hasWinSetAgainst(player2)){
			winSetGame(player2);
		}
	}

	public void loosePoint() {
		this.scoreGame = getScoreGame() - 1;
	}

	public void winSetGame(Player player2) {
		this.scoreSet = getScoreSet() + 1;
		if ((setsWon + player2.getSetsWon()) == 0) {
			scoreSet1 = scoreSet;
			player2.setScoreSet1(player2.getScoreSet());
		} else if ((setsWon + player2.getSetsWon()) == 1) {
			scoreSet2 = scoreSet;
			player2.setScoreSet2(player2.getScoreSet());
		} else if ((setsWon + player2.getSetsWon()) == 2) {
			scoreSet3 = scoreSet;
			player2.setScoreSet3(player2.getScoreSet());
		} else if ((setsWon + player2.getSetsWon()) == 3) {
			scoreSet4 = scoreSet;
			player2.setScoreSet4(player2.getScoreSet());
		} else if ((setsWon + player2.getSetsWon()) == 4) {
			scoreSet5 = scoreSet;
			player2.setScoreSet5(player2.getScoreSet());
		}
		if (hasWinSetAgainst(player2)) {
			// reset score set
			scoreSet = 0;
			player2.setScoreSet(0);
			setsWon++;
		}
	}

	public boolean hasWinGameAgainst(Player player2) {
		boolean winGame = this.scoreGame >= 4 && (this.scoreGame - player2.scoreGame) >= 2;
		return winGame;
	}

	public boolean hasAdvantageAgainst(Player player2) {
		return this.scoreGame == 4 && player2.scoreGame == 3;
	}

	public boolean isDeuceRuleActivated(Player player2) {
		return this.scoreGame == 3 && player2.getScoreGame() == this.scoreGame;
	}

	public boolean hasWinSetAgainst(Player player2) {
		boolean winSet = (this.scoreSet == 6 && player2.scoreSet <= 4) || (this.scoreTieBreak >= 6
				&& this.scoreTieBreak > player2.scoreTieBreak && (this.scoreTieBreak - player2.scoreTieBreak >= 2));
		return winSet;
	}

	public boolean isTeiBreakRuleActivated(Player player2) {
		return (this.scoreSet == 6 && player2.getScoreSet() == 6);
	}

	public int getSetsWon() {
		return setsWon;
	}

	public void setSetsWon(int setsWon) {
		this.setsWon = setsWon;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getScoreSet1() {
		return scoreSet1;
	}

	public void setScoreSet1(int scoreSet1) {
		this.scoreSet1 = scoreSet1;
	}

	public int getScoreSet2() {
		return scoreSet2;
	}

	public void setScoreSet2(int scoreSet2) {
		this.scoreSet2 = scoreSet2;
	}

	public int getScoreSet3() {
		return scoreSet3;
	}

	public void setScoreSet3(int scoreSet3) {
		this.scoreSet3 = scoreSet3;
	}

	public int getScoreSet4() {
		return scoreSet4;
	}

	public void setScoreSet4(int scoreSet4) {
		this.scoreSet4 = scoreSet4;
	}

	public int getScoreSet5() {
		return scoreSet5;
	}

	public void setScoreSet5(int scoreSet5) {
		this.scoreSet5 = scoreSet5;
	}

	public int getScoreGameToDislay() {
		return scoreGameToDislay;
	}

	public void setScoreGameToDislay(int scoreGameToDislay) {
		this.scoreGameToDislay = scoreGameToDislay;
	}

}
