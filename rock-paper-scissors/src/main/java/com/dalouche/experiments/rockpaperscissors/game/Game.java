package com.dalouche.experiments.rockpaperscissors.game;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * A game comprises several {@link Round}s
 * 
 * <p> Delegates most of the logic to other collaborators :
 * 	<ul>
 * 		<li>The retrieval of the number of rounds to play is delegated to {@link NumberOfRoundsProvider}</li>
 * 		<li>The execution of rounds (synchronization of players ) is delegated to {@link RoundSynchronizer} </li>
 * 		<li>The display is delegated to {@link RoundProgressListener} and {@link GameProgressListener}</li>
 * 	</ul>
 * </p>
 * @author sdalouche
 *
 */
public final class Game {
	private RoundSynchronizer roundSynchronizer;
	private NumberOfRoundsProvider numberOfRoundsProvider;
	private RoundProgressListener roundProgressListener;
	private GameProgressListener gameProgressListener;

	public Game(RoundSynchronizer roundSynchronizer, NumberOfRoundsProvider numberOfRoundsProvider, 
			RoundProgressListener roundProgressListener, GameProgressListener gameProgressListener) {
		super();
		Validate.notNull(roundSynchronizer);
		Validate.notNull(numberOfRoundsProvider);
		Validate.notNull(roundProgressListener);
		Validate.notNull(gameProgressListener);
		this.roundSynchronizer = roundSynchronizer;
		this.numberOfRoundsProvider = numberOfRoundsProvider;
		this.roundProgressListener = roundProgressListener;
		this.gameProgressListener = gameProgressListener;
	}

	public void play() {
		int numberOfRounds = numberOfRoundsProvider.getNumberOfRounds();
		List<Round> rounds = Lists.newArrayList();
		for(int i = 0 ; i < numberOfRounds ; i++) {
			Round round = roundSynchronizer.playNextRound();
			rounds.add(round);
			notifyRoundFinished(round);
		}
		notifyGameFinished(new GameOutcome(ImmutableList.copyOf(rounds)));
	}
	
	private void notifyRoundFinished(Round round) {
		roundProgressListener.roundFinished(round);
	}
	
	private void notifyGameFinished(GameOutcome gameOutcome) {
		gameProgressListener.gameFinished(this, gameOutcome);
	}
	
}
