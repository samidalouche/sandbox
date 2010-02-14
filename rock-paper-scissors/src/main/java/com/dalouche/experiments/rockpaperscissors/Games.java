package com.dalouche.experiments.rockpaperscissors;

public class Games {
	
	/**
	 * Creates a new Game
	 * 
	 * <p> This factory method wires all the components together
	 * </p>
	 * @return
	 */
	public static Game computerVersusHuman() {
		Player consolePlayer = new ConsolePlayer(System.in, System.out);
		Player computerPlayer = new ComputerPlayer();
		
		RoundSynchronizer roundSynchronizer = 
			new BlockingRoundSynchronizer(consolePlayer, computerPlayer);
		NumberOfRoundsProvider numberOfRoundsProvider = 
			new ConsoleNumberOfRoundsProvider(System.in, System.out);
		
		PrintRoundOutcome roundProgressListener = 
			new PrintRoundOutcome(System.out, consolePlayer);
		PrintGameOutcome printGameOutcome = 
			new PrintGameOutcome(System.out, consolePlayer, computerPlayer);
		
		return new Game(roundSynchronizer, numberOfRoundsProvider, roundProgressListener, printGameOutcome);
	}
}
