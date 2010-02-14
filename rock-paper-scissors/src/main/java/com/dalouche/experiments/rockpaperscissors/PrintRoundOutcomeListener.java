package com.dalouche.experiments.rockpaperscissors;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;

public final class PrintRoundOutcomeListener implements RoundProgressListener{

	private PrintStream outputStream;
	private Player playerInterestedByTheResults;

	public PrintRoundOutcomeListener(PrintStream outputStream, Player playerInterestedByTheResults) {
		super();
		Validate.notNull(outputStream);
		Validate.notNull(playerInterestedByTheResults);
		this.outputStream = outputStream;
		this.playerInterestedByTheResults = playerInterestedByTheResults;
	}

	@Override
	public void roundFinished(Round round) {
		if(Nobody.nobody().equals(round.getRoundWinner())) {
			outputStream.println("The round is tied. Nobody wins.");
		} else if(playerInterestedByTheResults.equals(round.getRoundWinner())) {
			outputStream.println(String.format("You win. %s", additionalSymbolInformation(round)));
		} else {
			outputStream.println(String.format("You lose. %s", additionalSymbolInformation(round)));
		}
		outputStream.println();
	}

	private String additionalSymbolInformation(Round round) {
		return String.format("%s %s %s", // rock blunts scissors 
			round.getWinnerMove().getSymbol().name(),
			round.getWinnerMove().getSymbol().actionName(),
			round.getLoserMove().getSymbol().name());
	}
}
