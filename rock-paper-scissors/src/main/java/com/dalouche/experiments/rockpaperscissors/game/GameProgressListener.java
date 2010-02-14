package com.dalouche.experiments.rockpaperscissors.game;

/**
 * Generic interface to implement to be notified of Game Events
 * 
 * @author sdalouche
 *
 */
public interface GameProgressListener {
	void gameFinished(Game game, GameOutcome gameOutcome);
}
