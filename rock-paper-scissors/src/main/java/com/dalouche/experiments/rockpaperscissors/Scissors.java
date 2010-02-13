package com.dalouche.experiments.rockpaperscissors;

import static com.dalouche.experiments.rockpaperscissors.Paper.paper;


public final class Scissors extends AbstractSymbol {

	public static Scissors scissors() {
		return new Scissors();
	}
	private Scissors() {}
	
	@Override
	public Symbol defeats() {
		return paper();
	}

	@Override
	public String actionName() {
		return "cuts";
	}

	@Override
	public String name() {
		return "scissors";
	}
}
