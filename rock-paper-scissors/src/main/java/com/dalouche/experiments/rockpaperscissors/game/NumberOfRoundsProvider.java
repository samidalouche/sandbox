package com.dalouche.experiments.rockpaperscissors.game;

/**
 * Provides a {@link Game} with a mean to ask the user 
 * how many rounds he wants to play
 * 
 * @author sdalouche
 *
 */
public interface NumberOfRoundsProvider {
	/**
	 * 
	 * @return greater or equal to one
	 */
	int getNumberOfRounds();
}
