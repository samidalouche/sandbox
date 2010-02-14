package com.dalouche.experiments.rockpaperscissors.symbols;

import static com.dalouche.experiments.rockpaperscissors.symbols.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;

import com.google.common.collect.ImmutableSet;

public class Symbols {

	public static ImmutableSet<Symbol> symbols() {
		return ImmutableSet.of((Symbol)rock(), paper(), scissors());
	}
}
