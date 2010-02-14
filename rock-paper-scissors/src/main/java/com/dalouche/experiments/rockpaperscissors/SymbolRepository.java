package com.dalouche.experiments.rockpaperscissors;

import com.google.common.collect.ImmutableSet;

public interface SymbolRepository {
	Symbol findSymbolByName(String name) throws SymbolNotFoundException;
	ImmutableSet<Symbol> findAllSymbols();
}
