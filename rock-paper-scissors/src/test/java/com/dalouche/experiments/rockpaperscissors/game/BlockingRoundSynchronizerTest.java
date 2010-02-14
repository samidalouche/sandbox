package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dalouche.experiments.rockpaperscissors.players.Player;

public class BlockingRoundSynchronizerTest {

	private Player player1;
	private Player player2;
	
	@Before
	public void onSetUp() {
		player1 = mock(Player.class);
		player2 = mock(Player.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithNullPlayer1() {
		new BlockingRoundSynchronizer(null, player2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithNullPlayer2() {
		new BlockingRoundSynchronizer(player1, null);
	}
	
	@Test
	public void shouldCreateRoundUsingPlayersGestures() {
		when(player1.nextGesture()).thenReturn(rock());
		when(player2.nextGesture()).thenReturn(scissors());
		
		Round round = new BlockingRoundSynchronizer(player1, player2).playNextRound();
		assertThat(round.getMove1(), CoreMatchers.is(new Move(player1, rock())));
		assertThat(round.getMove2(), CoreMatchers.is(new Move(player2, scissors())));
		
		Mockito.verify(player1).nextGesture();
		Mockito.verify(player2).nextGesture();
	}
}
