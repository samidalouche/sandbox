package com.dalouche.experiments.rockpaperscissors.console;

import java.io.PrintStream;

import org.junit.Test;
import org.mockito.Mockito;

import com.dalouche.experiments.rockpaperscissors.players.Player;

public class PrintRoundOutcomeListenerTest {

	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithNullOutputStream() {
		new PrintRoundOutcomeListener(null, anyPlayer());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotCreateWithNullPlayer() {
		new PrintRoundOutcomeListener(anyOutputStream(), null);
	}

	/**
	 * It would make sense to test the rest of this class, but not using JUnit,
	 * it is far too low-level for this.
	 * 
	 * @see http://www.gnu.org/software/dejagnu/
	 */
	@Test
	public void theRestOfThisClassShouldNotBeUnitTested() {
		
	}
	
	private PrintStream anyOutputStream() {
		return System.out;
	}

	private Player anyPlayer() {
		return Mockito.mock(Player.class);
	}
}
