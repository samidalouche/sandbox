package com.dalouche.experiments.rockpaperscissors.game;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Lists;

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
		notifyGameFinished(new GameOutcome(rounds));
	}
	
	private void notifyRoundFinished(Round round) {
		roundProgressListener.roundFinished(round);
	}
	
	private void notifyGameFinished(GameOutcome gameOutcome) {
		gameProgressListener.gameFinished(this, gameOutcome);
	}
	
}