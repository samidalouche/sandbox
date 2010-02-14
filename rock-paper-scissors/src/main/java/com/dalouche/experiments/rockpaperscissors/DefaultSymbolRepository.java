package com.dalouche.experiments.rockpaperscissors;

import com.google.common.collect.ImmutableMap;

public class DefaultSymbolRepository implements SymbolRepository {

	private ImmutableMap<String, Symbol> symbols = symbolsByName();
	
	@Override
	public Symbol findSymbolByName(String name) throws SymbolNotFoundException {
		Symbol symbol = symbols.get(name);
		if(symbol == null) {
			throw new SymbolNotFoundException(name);
		}
		return symbol;
	}
	
	private ImmutableMap<String, Symbol> symbolsByName() {
		ImmutableMap.Builder<String, Symbol> builder = new ImmutableMap.Builder<String, Symbol>();
		for(Symbol s : Symbols.symbols()) {
			builder.put(s.name(), s);
		}
		return builder.build();
	}

}
