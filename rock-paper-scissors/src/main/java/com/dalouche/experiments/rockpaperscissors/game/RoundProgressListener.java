package com.dalouche.experiments.rockpaperscissors.game;

/**
 * To implement to be notified of Round Events
 * 
 * @author sdalouche
 *
 */
public interface RoundProgressListener {
	void roundFinished(Round round);
}
