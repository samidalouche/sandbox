package com.dalouche.experiments.rockpaperscissors;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Lists;

public final class Game {
	private Player player1;
	private Player player2;
	private int numberOfRounds;
	private RoundSynchronizer roundSynchronizer;
	private List<Round> rounds = Lists.newArrayList();
	
	public Game(Player player1, Player player2, int numberOfRounds) {
		super();
		Validate.notNull(player1);
		Validate.notNull(player2);
		Validate.isTrue(numberOfRounds>= 1);
		this.player1 = player1;
		this.player2 = player2;
		this.numberOfRounds = numberOfRounds;
		this.roundSynchronizer = new BlockingRoundSynchronizer(player1, player2);
	}

	public GameOutcome play() {
		for(int i = 0 ; i < numberOfRounds ; i++) {
			rounds.add(roundSynchronizer.nextRound());
		}
		return new GameOutcome(rounds);
	}
	

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	
}
