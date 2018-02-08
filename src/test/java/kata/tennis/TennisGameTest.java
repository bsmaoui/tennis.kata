package kata.tennis;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tennis Game Unit Test Class
 */
@RunWith(JUnitParamsRunner.class)
public class TennisGameTest {

    private Game tennisGame;
    private Player player1;
    private Player player2;
    @Before
    public void setUp(){
    	player1 = new Player("Federer");
    	player2 = new Player("Nadal");
        tennisGame = new Game(player1, player2);

    }

    @Test
    @Parameters({
           "1,0, Player 1 : Federer\nPlayer 2 : Nadal\nScore : (0-0)(0-0)(0-0)\nCurrent game status : 15-0\nMatch Status : In progress",
           "100,330, Player 1 : Federer\nPlayer 2 : Nadal\nScore : (4-6)(0-6)(0-1)\nCurrent game status : 0-30\nMatch Status : In progress",
            
    })
    
    public void should_display_current_game_score_when_players_win_points(int pointsToWinForP1, int pointsToWinForP2,  String gameScore){
        // When
        simulateGameFor(player1,player2, pointsToWinForP1);
        simulateGameFor(player2,player1, pointsToWinForP2);
//        System.out.println(tennisGame.getScore());
        // Then
        assertThat(tennisGame.getScore()).isEqualTo(gameScore);
    }

    private void simulateGameFor(Player player1, Player player2, int pointsToWin){
	  for(int i=0; i<pointsToWin; i++){
	      player1.winPoint(player2);
	  }
	}

    @Test
    public void should_activate_deuce_rule_when_the_2_players_reach_40_score(){
        // When
        simulateGameFor(player1,player2, 3);
        simulateGameFor(player2,player1, 3);
//        System.out.println(tennisGame.getScore());
        // Then
        assertThat(tennisGame.getScore()).isEqualTo("Player 1 : Federer\nPlayer 2 : Nadal\nScore : (0-0)(0-0)(0-0)\nCurrent game status : Deuce\nMatch Status : In progress");
    }

    @Test
    public void should_display_current_game_and_set_score_when_a_player_win_a_game(){
        // When Federer win game
        simulateGameFor(player1,player2, 4);
        // Then
        assertThat(tennisGame.getScore()).isEqualTo("Player 1 : Federer\nPlayer 2 : Nadal\nScore : (0-0)(0-0)(0-0)\nCurrent game status : Federer wins the game\nMatch Status : In progress");
        // when Federer win all next game
        for(int k=1; k<6; k++){
            simulateGameFor(player1,player2, 4);
        }
//        System.out.println(tennisGame.getScore());
        assertThat(tennisGame.getScore()).isEqualTo("Player 1 : Federer\nPlayer 2 : Nadal\nScore : (1-0)(0-0)(0-0)\nCurrent game status : Federer wins the game\nMatch Status : In progress");
    }



    @Test
    @Parameters({
            "5,7,Player 1 : Federer\nPlayer 2 : Nadal\nScore : (6-7)(0-0)(0-0)\nCurrent game status : 0-0\nMatch Status : In progress",
    })
    public void should_display_tie_break_score_when_player_win_point(int pointsToWinForP1, int pointsToWinForP2, String score){
        // when
        player1.setScoreSet(6);
        player2.setScoreSet(6);
        simulateTieBreakFor(player1,player2, pointsToWinForP1);
        simulateTieBreakFor(player2,player1, pointsToWinForP2);
        //
        assertThat(tennisGame.getScore()).isEqualTo(score);
    }


    private void simulateTieBreakFor(Player player1,Player player2, int pointsToWin){
        for(int i=0; i<pointsToWin; i++){
            player1.winTieBreakPoint(player2);
        }
    }

}
