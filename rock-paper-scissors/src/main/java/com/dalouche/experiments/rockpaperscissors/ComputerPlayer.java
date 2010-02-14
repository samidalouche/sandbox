package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Symbols.symbols;

import java.util.Random;

import com.google.common.collect.ImmutableList;

/**
 * A computer player that plays symbols randomly
 * 
 * @author sdalouche
 *
 */
public final class ComputerPlayer implements Player {
	private final static ImmutableList<Symbol> SYMBOLS = ImmutableList.copyOf(symbols());
	
	@Override
	public Symbol nextSymbol() {
		return SYMBOLS.get(randomIndex());
	}

	private int randomIndex() {
		return new Random().nextInt(SYMBOLS.size());
	}

}
