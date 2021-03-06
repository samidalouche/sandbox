package com.dalouche.experiments.rockpaperscissors.game;

import static com.dalouche.experiments.rockpaperscissors.players.Nobody.nobody;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.rockpaperscissors.players.Player;

/**
 * Represents the move of two players
 * 
 * <p> FIXME: there are still way too many if's, but it is not clear whether it
 * is worth creating more abstractions to represent tied rounds.
 * </p>
 * @author sdalouche
 *
 */
public final class Round {
	private static final class Outcome {
		Move winner;
		Move loser;
		Outcome(Move winner, Move loser) {
			super();
			this.winner = winner;
			this.loser = loser;
		}
	}
	private Move move1;
	private Move move2;
	
	public Round(Move move1, Move move2) {
		super();
		Validate.notNull(move1);
		Validate.notNull(move2);
		this.move1 = move1;
		this.move2 = move2;
	}
	
	/**
	 * 
	 * @return nobody if it is a tie
	 */
	public Player getRoundWinner() {
		Move winnerMove = getWinnerMove();
		return winnerMove != null ? winnerMove.getPlayer() : nobody();
	}
	
	public Player getRoundLoser() {
		Move loserMove = getLoserMove();
		return loserMove != null ? loserMove.getPlayer() : nobody();
	}
	
	/**
	 * 
	 * @return null if it is a tie
	 */
	public Move getWinnerMove() {
		Outcome outcome = getRoundOutcome();
		return outcome != null ? outcome.winner : null;
	}
	
	public Move getLoserMove() {
		Outcome outcome = getRoundOutcome();
		return outcome != null ? outcome.loser : null;
	}
	
	
	private Outcome getRoundOutcome() {
		if(move1.defeats(move2)) {
			return new Outcome(move1, move2);
		} else if(move2.defeats(move1)) {
			return new Outcome(move2, move1);
		} else {
			return null;	
		}
	}

	public Move getMove1() {
		return move1;
	}

	public Move getMove2() {
		return move2;
	}
	
}
