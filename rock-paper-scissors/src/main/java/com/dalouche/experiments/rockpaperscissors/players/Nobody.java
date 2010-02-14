package com.dalouche.experiments.rockpaperscissors.players;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;

public final class Nobody implements Player {
	
	public static Nobody nobody() {
		return new Nobody();
	}
	private Nobody() {}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getClass())
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return (getClass() == obj.getClass());
		
	}
	@Override
	public Symbol nextSymbol() {
		throw new RuntimeException("I am not allowed to play as I am not a real player");
	}
	
	
	
}
