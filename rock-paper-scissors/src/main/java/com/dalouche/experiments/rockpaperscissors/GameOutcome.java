package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Nobody.nobody;

import org.apache.commons.lang.Validate;

public class GameOutcome {
	private static final class WinnerLooser {
		Move winner;
		Move loser;
		WinnerLooser(Move winner, Move loser) {
			super();
			this.winner = winner;
			this.loser = loser;
		}
	}
	private Move move1;
	private Move move2;
	
	public GameOutcome(Move move1, Move move2) {
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
	public Player getWinner() {
		Move winnerMove = getWinnerMove();
		return winnerMove != null ? winnerMove.getPlayer() : nobody();
	}
	
	public Player getLoser() {
		Move loserMove = getLoserMove();
		return loserMove != null ? loserMove.getPlayer() : nobody();
	}
	
	/**
	 * 
	 * @return null if it is a tie
	 */
	private Move getWinnerMove() {
		WinnerLooser outcome = getGameOutcome();
		return outcome != null ? outcome.winner : null;
	}
	
	private Move getLoserMove() {
		WinnerLooser outcome = getGameOutcome();
		return outcome != null ? outcome.loser : null;
	}
	
	
	private WinnerLooser getGameOutcome() {
		if(move1.defeats(move2)) {
			return new WinnerLooser(move1, move2);
		} else if(move2.defeats(move1)) {
			return new WinnerLooser(move2, move1);
		} else {
			return null;	
		}
	}
	
}
