package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Nobody.nobody;
import static com.dalouche.experiments.rockpaperscissors.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.Scissors.scissors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public final class MoveAndGameOutcomeTest {

	private Player player1;
	private Player player2;
	
	@Before
	public void onSetUp() {
		player1 = mock(Player.class);
		player2 = mock(Player.class);
	}
	
	@Test
	public void move1ShouldDefeatMove2() {
		Move move1 = playerOnePlaysScissors();
		Move move2 = player2PlaysPaper();
		Assert.assertTrue(move1.defeats(move2));
		Assert.assertFalse(move2.defeats(move1));
	}
	
	@Test
	public void player1ShouldBeWinner() {
		Move move1 = playerOnePlaysScissors();
		Move move2 = player2PlaysPaper();
		assertThat(move1.against(move2).getWinner(), is(player1));
		assertThat(move1.against(move2).getLoser(), is(player2));
	}
	
	@Test
	public void nobodyShouldWin() {
		Move move1 = playerOnePlaysScissors();
		Move move2 = new Move(player2, scissors());
		assertThat(move1.against(move2).getWinner(), is((Player)nobody()));
	}
	
	private Move player2PlaysPaper() {
		return new Move(player2, paper());
	}

	private Move playerOnePlaysScissors() {
		return new Move(player1, scissors());
	}
}
