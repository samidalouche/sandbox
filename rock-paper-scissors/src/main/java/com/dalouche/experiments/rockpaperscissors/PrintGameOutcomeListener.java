package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Nobody.nobody;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

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
