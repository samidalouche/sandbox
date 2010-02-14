package com.dalouche.experiments.rockpaperscissors;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Lists;

public final class Game {
	private RoundSynchronizer roundSynchronizer;
	private NumberOfRoundsProvider numberOfRoundsProvider;
	
	public Game(RoundSynchronizer roundSynchronizer, NumberOfRoundsProvider numberOfRoundsProvider) {
		super();
		Validate.notNull(roundSynchronizer);
		Validate.notNull(numberOfRoundsProvider);
		this.roundSynchronizer = roundSynchronizer;
		this.numberOfRoundsProvider = numberOfRoundsProvider;
	}

	public GameOutcome play() {
		int numberOfRounds = numberOfRoundsProvider.getNumberOfRounds();
		List<Round> rounds = Lists.newArrayList();
		for(int i = 0 ; i < numberOfRounds ; i++) {
			rounds.add(roundSynchronizer.playNextRound());
		}
		return new GameOutcome(rounds);
	}
	
}
