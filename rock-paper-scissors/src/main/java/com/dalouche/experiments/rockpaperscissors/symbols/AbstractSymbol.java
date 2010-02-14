package com.dalouche.experiments.rockpaperscissors.symbols;

import org.apache.commons.lang.builder.HashCodeBuilder;


public abstract class AbstractSymbol implements Symbol {

	public AbstractSymbol() {
		super();
	}

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
	public String toString() {
		return name();
	}
}