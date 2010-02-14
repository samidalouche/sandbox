package com.dalouche.experiments.rockpaperscissors;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

public class ComputerPlayerTest {

	private Player player;
	
	@Before
	public void onSetUp() {
		this.player = new ComputerPlayer();
	}
	
	/**
	 * FIXME: a better test would be to calculate statistics on the symbols played
	 * and check that each symbol roughly accounts for 1/3 of the total number of symbols
	 */
	@Test(timeout=10000)
	public void shouldEndUpPlayingAllSymbols() {
		Set<Symbol> symbolsPlayed = Sets.newHashSet();
		while(! Symbols.symbols().equals(symbolsPlayed)) {
			symbolsPlayed.add(player.nextSymbol());
		}
	}
}
