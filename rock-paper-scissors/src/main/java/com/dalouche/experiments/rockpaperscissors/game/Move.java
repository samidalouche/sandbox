package com.dalouche.experiments.rockpaperscissors.game;

import org.apache.commons.lang.Validate;

import com.dalouche.experiments.rockpaperscissors.players.Player;
import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;

/**
 * Represents a symbol thrown by a player. 
 * 
 * <p> When two players make their move, they can compete and we 
 * have a {@link Round}
 * </p>
 * 
 * @author sdalouche
 *
 */
public final class Move {
	private Player player;
	private Symbol symbol;
	
	public Move(Player player, Symbol symbol) {
		super();
		Validate.notNull(player);
		Validate.notNull(symbol);
		this.player = player;
		this.symbol = symbol;
	}

	public Round against(Move move) {
		return new Round(this, move);
	}
	
	public boolean defeats(Move other) {
		return symbol.defeats().equals(other.getSymbol());
	}
	
	public Player getPlayer() {
		return player;
	}

	public Symbol getSymbol() {
		return symbol;
	}
	
}
