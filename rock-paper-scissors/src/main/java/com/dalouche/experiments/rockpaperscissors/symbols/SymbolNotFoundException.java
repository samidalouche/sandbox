package com.dalouche.experiments.rockpaperscissors.symbols;

public class SymbolNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String name;

	public SymbolNotFoundException(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
