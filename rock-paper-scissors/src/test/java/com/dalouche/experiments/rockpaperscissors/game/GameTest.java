package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dalouche.experiments.rockpaperscissors.players.Player;

public class GameTest {

	private Player player1;
	private Player player2;
	
	@Before
	public void onSetUp() {
		player1 = mock(Player.class);
		player2 = mock(Player.class);
		when(player1.nextGesture()).thenReturn(rock());
		when(player2.nextGesture()).thenReturn(scissors());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateGameWithNullRoundSynchronizer() {
		new Game(null, anyNumberOfRoundProvider(), anyRoundProgressListener(), anyGameProgressListener());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateGameWithNullNumberOfRoundsProvider() {
		new Game(anyRoundSynchronizer(), null, anyRoundProgressListener(), anyGameProgressListener());
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateGameWithNullRoundProgressListener() {
		new Game(anyRoundSynchronizer(), anyNumberOfRoundProvider(), null, anyGameProgressListener());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateGameWithNullGameProgressListener() {
		new Game(anyRoundSynchronizer(), anyNumberOfRoundProvider(), anyRoundProgressListener(), null);
	}
	
	@Test
	public void shouldAskNumberOfRounds() {
		NumberOfRoundsProvider roundProviderThatReturnsOne = roundProviderThatReturnsOne();
		Game game = new Game(realRoundSynchronizer(), roundProviderThatReturnsOne, anyRoundProgressListener(), anyGameProgressListener());
		game.play();
		Mockito.verify(roundProviderThatReturnsOne).getNumberOfRounds();
	}
	
	/**
	 * FIXME: we are supposed to match the arguments (notify) to make sure they make sense
	 */
	@Test
	public void shouldNotifyEndOfRoundForEachRound() {
		RoundProgressListener roundProgressListener = anyRoundProgressListener();
		Game game = new Game(realRoundSynchronizer(), roundProviderThatReturnsFive(), roundProgressListener, anyGameProgressListener());
		game.play();
		Mockito.verify(roundProgressListener, Mockito.times(5)).roundFinished((Round) Mockito.anyObject());
	}
	
	/**
	 * FIXME: we are supposed to match the arguments (notify) to make sure they make sense
	 */
	@Test
	public void shouldNotifyEndOfEndOfGamesOnce() {
		GameProgressListener gameProgressListener = anyGameProgressListener();
		
		Game game = new Game(realRoundSynchronizer(), roundProviderThatReturnsFive(), anyRoundProgressListener(), gameProgressListener);
		game.play();
		Mockito.verify(gameProgressListener, Mockito.times(1)).gameFinished((Game)Mockito.anyObject(), (GameOutcome)Mockito.anyObject());
	}

	private NumberOfRoundsProvider roundProviderThatReturnsFive() {
		NumberOfRoundsProvider roundProviderThatReturnsFive = mock(NumberOfRoundsProvider.class);
		when(roundProviderThatReturnsFive.getNumberOfRounds()).thenReturn(5);
		return roundProviderThatReturnsFive;
	}

	private BlockingRoundSynchronizer realRoundSynchronizer() {
		return new BlockingRoundSynchronizer(player1, player2);
	}
	
	
	private RoundSynchronizer anyRoundSynchronizer() {
		return Mockito.mock(RoundSynchronizer.class);
	}

	private GameProgressListener anyGameProgressListener() {
		return Mockito.mock(GameProgressListener.class);
	}

	private RoundProgressListener anyRoundProgressListener() {
		return mock(RoundProgressListener.class);
	}

	private NumberOfRoundsProvider anyNumberOfRoundProvider() {
		return roundProviderThatReturnsOne();
	}
	
	private NumberOfRoundsProvider roundProviderThatReturnsOne() {
		NumberOfRoundsProvider provider = mock(NumberOfRoundsProvider.class);
		Mockito.when(provider.getNumberOfRounds()).thenReturn(1);
		return provider;
	}
}
