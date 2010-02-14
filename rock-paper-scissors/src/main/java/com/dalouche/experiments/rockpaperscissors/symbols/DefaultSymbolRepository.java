package com.dalouche.experiments.rockpaperscissors.symbols;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Default implementation of {@link SymbolRepository} that provides the three gestures
 * @author sdalouche
 *
 */
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

	@Override
	public ImmutableSet<Symbol> findAllSymbols() {
		return Symbols.symbols();
	}

}
