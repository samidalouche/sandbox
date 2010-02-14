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
		List<Round> rounds = Lists.newArrayList();
		for(int i = 0 ; i < numberOfRoundsProvider.getNumberOfRounds() ; i++) {
			rounds.add(roundSynchronizer.nextRound());
		}
		return new GameOutcome(rounds);
	}
	
}
