package com.dalouche.experiments.rockpaperscissors;

/**
 * synchronizes players for the next round
 * 
 * @author sdalouche
 *
 */
public interface RoundSynchronizer {

	public abstract Round playNextRound();

}