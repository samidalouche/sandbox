package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.rockpaperscissors.symbols.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static org.mockito.Mockito.mock;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.google.common.collect.ImmutableList;

public class GameOutcomeTest {
	private Player player1;
	private Player player2;
	
	@Before
	public void onSetUp() {
		player1 = mock(Player.class);
		player2 = mock(Player.class);
	}
	
	@Test
	public void player1ShouldHaveOneWin() {
		GameOutcome outcome = new GameOutcome(ImmutableList.of(playerTwoPlaysPaper().against(playerOnePlaysScissors())));
		Assert.assertThat(outcome.numberOfWinsPerPlayer().get(player1), CoreMatchers.is(1));
	}
	
	private Move playerTwoPlaysPaper() {
		return new Move(player2, paper());
	}

	private Move playerOnePlaysScissors() {
		return new Move(player1, scissors());
	}
}
