package kata.tennis;

import java.util.HashMap;
import java.util.Map;

public class Game {

	public Player player1;
	public Player player2;
	private Map<Integer, String> scoreMap = new HashMap<Integer, String>(5);

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		initScoreMap();
	}

	public String getScore() {
		StringBuilder score = new StringBuilder();
		score.append("Player 1 : " + player1.getName());
		score.append("\n");
		score.append("Player 2 : " + player2.getName());
		score.append("\n");
		score.append(getScoreSet());
		score.append("\n");
		score.append("Current game status : " + getCurrentGameStatus());
		score.append("\n");
		score.append("Match Status : " + getMatchStatus());
		return score.toString();
	}

	public String getCurrentGameStatus() {
		if (player1.getScoreGameToDislay() == player2.getScoreGameToDislay()) {
			switch (player1.getScoreGame()) {
			case 0:
				return "0-0";
			case 1:
				return "15-15";
			case 2:
				return "30-30";
			default:
				return "Deuce";
			}
		} else if (player1.getScoreGameToDislay() >= 4 || player2.getScoreGameToDislay() >= 4) {
			int pointsDiff = player1.getScoreGameToDislay() - player2.getScoreGameToDislay();
			if (pointsDiff == 1) {
				return "Advantage for " + player1.getName();
			} else if (pointsDiff == -1) {
				return "Advantage for " + player2.getName();
			} else if (pointsDiff >= 2) {
				return player1.getName() + " wins the game";
			} else {
				return player2.getName() + " wins the game";
			}
		} else {
			return scoreMap.get(player1.getScoreGameToDislay()) + "-" + scoreMap.get(player2.getScoreGameToDislay());
		}
	}

	public String getScoreSet() {
		StringBuilder scoreSet = new StringBuilder();
		scoreSet.append("Score : (");
		scoreSet.append(player1.getScoreSet1() + "-" + player2.getScoreSet1());
		scoreSet.append(")");
		scoreSet.append("(");
		scoreSet.append(player1.getScoreSet2() + "-" + player2.getScoreSet2());
		scoreSet.append(")");
		scoreSet.append("(");
		scoreSet.append(player1.getScoreSet3() + "-" + player2.getScoreSet3());
		scoreSet.append(")");
		if (player1.getScoreSet4() != 0 || player2.getScoreSet4() != 0) {
			scoreSet.append("(");
			scoreSet.append(player1.getScoreSet4() + "-" + player2.getScoreSet4());
			scoreSet.append(")");
		}
		if (player1.getScoreSet5() != 0 || player2.getScoreSet5() != 0) {
			scoreSet.append("(");
			scoreSet.append(player1.getScoreSet5() + "-" + player2.getScoreSet5());
			scoreSet.append(")");
		}
		return scoreSet.toString();
	}

	public String getMatchStatus() {
		if (player1.getSetsWon() >= 3) {
			return player1.getName() + " wins";
		} else if (player2.getSetsWon() >= 3) {
			return player2.getName() + " wins";
		}
		return "In progress";
	}

	public static void main(String[] args) {
		Player p1 = new Player("P1");
		Player p2 = new Player("P2");
		for (int i = 0; i < 50; i++) {
			p2.winPoint(p1);
		}
		for (int i = 0; i < 400; i++) {

			p1.winPoint(p2);
		}
		Game g = new Game(p1, p2);
		System.out.println(g.getScore());
	}

	private void initScoreMap() {
		scoreMap.put(0, "0");
		scoreMap.put(1, "15");
		scoreMap.put(2, "30");
		scoreMap.put(3, "40");
	}
}
