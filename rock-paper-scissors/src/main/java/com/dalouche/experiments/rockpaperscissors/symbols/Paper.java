package com.dalouche.experiments.rockpaperscissors.symbols;

import static com.dalouche.experiments.rockpaperscissors.symbols.Rock.rock;

public final class Paper  extends AbstractSymbol {

	public static Paper paper() {
		return new Paper();
	}
	private Paper() {}
	
	@Override
	public Symbol defeats() {
		return rock();
	}

	@Override
	public String actionName() {
		return "wraps";
	}

	@Override
	public String name() {
		return "paper";
	}

}
