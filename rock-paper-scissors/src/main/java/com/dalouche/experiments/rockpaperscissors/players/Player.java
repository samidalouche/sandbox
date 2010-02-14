package com.dalouche.experiments.rockpaperscissors.players;

import com.dalouche.experiments.rockpaperscissors.symbols.Symbol;

/**
 * A player that is asked to throw gestures during a game
 * 
 * @author sdalouche
 *
 */
public interface Player {
	Symbol nextGesture();
}
