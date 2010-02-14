package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.commons.TestUtils.equalShouldBeReflexive;
import static com.dalouche.experiments.commons.TestUtils.shouldBeEqualAndHaveSameHashCode;
import static com.dalouche.experiments.commons.TestUtils.shouldNotBeEqualAndHaveDifferentHashCode;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualNull;
import static com.dalouche.experiments.commons.TestUtils.shouldNotEqualObjectOfDifferentType;
import static com.dalouche.experiments.rockpaperscissors.symbols.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;

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
		Move move1 = anyMove();
		Move move2 = player2PlaysPaper();
		Assert.assertTrue(move1.defeats(move2));
		Assert.assertFalse(move2.defeats(move1));
	}
	
	@Test
	public void equalsShouldBeSane() {
		equalShouldBeReflexive(anyMove());
		shouldNotEqualNull(anyMove());
		shouldNotEqualObjectOfDifferentType(anyMove());
	}

	
	@Test
	public void movesWithSamePlayerAndSymbolShouldBeEqual() {
		shouldBeEqualAndHaveSameHashCode(playerOnePlaysScissors(), playerOnePlaysScissors());
	}
	
	@Test
	public void movesWithDifferentPlayersShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(new Move(player1, paper()), new Move(player2, paper()));
	}
	
	@Test
	public void movesWithDifferentSymbolsShouldNotBeEqual() {
		shouldNotBeEqualAndHaveDifferentHashCode(new Move(player1, rock()), new Move(player1, paper()));
	}
	
	private Move anyMove() {
		return playerOnePlaysScissors();
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
