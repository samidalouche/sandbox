package com.dalouche.experiments.rockpaperscissors.symbols;

import static com.dalouche.experiments.rockpaperscissors.symbols.Scissors.scissors;

public final class Rock  extends AbstractSymbol {

	public static Rock rock() {
		return new Rock();
	}
	private Rock() {}
	
	@Override
	public Symbol defeats() {
		return scissors();
	}

	@Override
	public String actionName() {
		return "blunts";
	}

	@Override
	public String name() {
		return "rock";
	}
}
