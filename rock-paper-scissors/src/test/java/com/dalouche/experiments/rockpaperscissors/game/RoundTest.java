package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.rockpaperscissors.players.Nobody.nobody;
import static com.dalouche.experiments.rockpaperscissors.symbols.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.dalouche.experiments.rockpaperscissors.players.Player;

public final class RoundTest {

	private Player player1;
	private Player player2;
	
	@Before
	public void onSetUp() {
		player1 = mock(Player.class);
		player2 = mock(Player.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateMoveWithoutMove1() {
		new Round(null, anyMove());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateMoveWithoutMove2() {
		new Round(anyMove(), null);
	}
	
	

	@Test
	public void player1ShouldBeWinner() {
		Move move1 = playerOnePlaysScissors();
		Move move2 = playerTwoPlaysPaper();
		assertThat(move1.against(move2).getRoundWinner(), is(player1));
		assertThat(move1.against(move2).getRoundLoser(), is(player2));
	}
	
	@Test
	public void nobodyShouldWin() {
		Move move1 = playerOnePlaysScissors();
		Move move2 = new Move(player2, scissors());
		assertThat(move1.against(move2).getRoundWinner(), is((Player)nobody()));
	}
	
	private Move playerTwoPlaysPaper() {
		return new Move(player2, paper());
	}

	private Move playerOnePlaysScissors() {
		return new Move(player1, scissors());
	}
	
	private Move anyMove() {
		return playerOnePlaysScissors();
	}
}
