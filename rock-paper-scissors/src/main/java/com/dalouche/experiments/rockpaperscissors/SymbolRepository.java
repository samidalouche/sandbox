package com.dalouche.experiments.rockpaperscissors;

public interface SymbolRepository {
	Symbol findSymbolByName(String name) throws SymbolNotFoundException;
}
