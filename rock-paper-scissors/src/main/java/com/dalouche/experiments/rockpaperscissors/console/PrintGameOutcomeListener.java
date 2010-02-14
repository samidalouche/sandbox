package com.dalouche.experiments.rockpaperscissors.console;

import static com.dalouche.experiments.rockpaperscissors.players.Nobody.nobody;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.rockpaperscissors.game.Game;
import com.dalouche.experiments.rockpaperscissors.game.GameOutcome;
import com.dalouche.experiments.rockpaperscissors.game.GameProgressListener;
import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * Prints the outcome of the game (how many wins for each player)
 * 
 * @author sdalouche
 *
 */
public final class PrintGameOutcomeListener implements GameProgressListener{

	private PrintStream outputStream;
	private Player playerInterestedByTheResults;
	private Player otherPlayer;

	public PrintGameOutcomeListener(PrintStream outputStream, Player playerInterestedByTheResults, Player otherPlayer) {
		super();
		Validate.notNull(outputStream);
		Validate.notNull(playerInterestedByTheResults);
		Validate.notNull(otherPlayer);
		this.outputStream = outputStream;
		this.playerInterestedByTheResults = playerInterestedByTheResults;
		this.otherPlayer = otherPlayer;
	}

	@Override
	public void gameFinished(Game game, GameOutcome gameOutcome) {
		outputStream.println();
		outputStream.println(" = Game results = ");
		
		for(Player p: ImmutableList.of(playerInterestedByTheResults, otherPlayer, nobody())) {
			outputStream.println(statusFor(p, gameOutcome.numberOfWinsPerPlayer()));
		}
	}
	
	private String statusFor(Player player, ImmutableMap<Player, Integer> numberOfWinsPerPlayer) {
		return String.format("%s : %s round(s)", playerDescription(player), numberOfWins(numberOfWinsPerPlayer, player));
	}

	// FIXME: there is a smell.. this is not a concern of the formatting/display layer
	private Integer numberOfWins(
			ImmutableMap<Player, Integer> numberOfWinsPerPlayer, Player p) {
		Integer wins =  numberOfWinsPerPlayer.get(p);
		if(wins == null) {
			wins = 0;
		}
		return wins;
	}
	
	private String playerDescription(Player player) {
		if(nobody().equals(player)) {
			return "Rounds tied";
		} else if(playerInterestedByTheResults.equals(player)) {
			return "You";
		} else {
			return "Computer";
		}
	}
}
