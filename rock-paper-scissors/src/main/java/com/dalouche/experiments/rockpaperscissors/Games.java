package com.dalouche.experiments.rockpaperscissors;

import com.dalouche.experiments.rockpaperscissors.console.ConsoleNumberOfRoundsProvider;
import com.dalouche.experiments.rockpaperscissors.console.ConsolePlayer;
import com.dalouche.experiments.rockpaperscissors.console.PrintGameOutcomeListener;
import com.dalouche.experiments.rockpaperscissors.console.PrintRoundOutcomeListener;
import com.dalouche.experiments.rockpaperscissors.game.BlockingRoundSynchronizer;
import com.dalouche.experiments.rockpaperscissors.game.Game;
import com.dalouche.experiments.rockpaperscissors.game.NumberOfRoundsProvider;
import com.dalouche.experiments.rockpaperscissors.game.RoundSynchronizer;
import com.dalouche.experiments.rockpaperscissors.players.ComputerPlayer;
import com.dalouche.experiments.rockpaperscissors.players.Player;

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
		
		PrintRoundOutcomeListener roundProgressListener = 
			new PrintRoundOutcomeListener(System.out, consolePlayer);
		PrintGameOutcomeListener printGameOutcomeListener = 
			new PrintGameOutcomeListener(System.out, consolePlayer, computerPlayer);
		
		return new Game(roundSynchronizer, numberOfRoundsProvider, roundProgressListener, printGameOutcomeListener);
	}
}
