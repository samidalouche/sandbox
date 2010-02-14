package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.Scissors.scissors;
import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveTest {
	private Player player1;
	private Player player2;
	
	@Before
	public void onSetUp() {
		player1 = mock(Player.class);
		player2 = mock(Player.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutSymbol() {
		new Move(anyPlayer(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithoutPlayer() {
		new Move(null, anySymbol());
	}

	@Test
	public void move1ShouldDefeatMove2() {
		Move move1 = playerOnePlaysScissors();
		Move move2 = player2PlaysPaper();
		Assert.assertTrue(move1.defeats(move2));
		Assert.assertFalse(move2.defeats(move1));
	}
	
	private Move player2PlaysPaper() {
		return new Move(player2, paper());
	}

	private Move playerOnePlaysScissors() {
		return new Move(player1, scissors());
	}
	private Player anyPlayer() {
		return player1;
	}
	
	private Symbol anySymbol() {
		return rock();
	}
}
