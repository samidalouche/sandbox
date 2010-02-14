package com.dalouche.experiments.rockpaperscissors;

import org.apache.commons.lang.Validate;

/**
 * A round synchronizer that blocks while asking each player its next move
 * 
 * <p> This would not be realistic if all players were humans, but in our example, it
 * will be good enough for the job. We will not spawn threads just for the pleasure of using java5's 
 * awesome util.concurrent ;-)
 * </p>
 * @author sdalouche
 *
 */
public final class BlockingRoundSynchronizer implements RoundSynchronizer {
	private Player player1;
	private Player player2;
	
	public BlockingRoundSynchronizer(Player player1, Player player2) {
		super();
		Validate.notNull(player1);
		Validate.notNull(player2);
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public Round playNextRound() {
		return new Move(player1, player1.nextSymbol())
			.against(new Move(player2, player2.nextSymbol()));
	}
	

}
