package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Paper.paper;
import static com.dalouche.experiments.rockpaperscissors.Rock.rock;
import static com.dalouche.experiments.rockpaperscissors.Scissors.scissors;

import com.google.common.collect.ImmutableSet;

public class Symbols {

	public static ImmutableSet<Symbol> symbols() {
		return ImmutableSet.of((Symbol)rock(), paper(), scissors());
	}
}
