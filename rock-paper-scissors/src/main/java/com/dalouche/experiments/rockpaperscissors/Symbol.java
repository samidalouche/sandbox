package com.dalouche.experiments.rockpaperscissors;

public interface Symbol {
	/**
	 * 
	 * @return The symbol that this symbol defeats
	 */
	Symbol defeats();
	/**
	 * 
	 * @return the verb (wraps, blunts, cuts) that describes 
	 * the action of this symbol on the one it defeats
	 */
	String actionName();
	
	/**
	 * @return the name of this symbol
	 */
	String name();
}
