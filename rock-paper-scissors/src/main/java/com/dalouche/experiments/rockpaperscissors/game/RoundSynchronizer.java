package com.dalouche.experiments.rockpaperscissors.game;

/**
 * synchronizes players for the next round
 * 
 * @author sdalouche
 *
 */
public interface RoundSynchronizer {

	public abstract Round playNextRound();

}