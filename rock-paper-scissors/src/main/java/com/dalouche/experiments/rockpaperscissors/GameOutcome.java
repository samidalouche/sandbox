package com.dalouche.experiments.rockpaperscissors;

import com.google.common.collect.ImmutableList;

public final class GameOutcome {
	private ImmutableList<Round> rounds;

	public GameOutcome(Iterable<Round> rounds) {
		super();
		this.rounds = ImmutableList.copyOf(rounds);
	}
}
