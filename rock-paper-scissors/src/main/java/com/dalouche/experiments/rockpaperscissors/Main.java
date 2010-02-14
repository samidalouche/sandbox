package com.dalouche.experiments.rockpaperscissors;

import com.dalouche.experiments.rockpaperscissors.game.Game;

public class Main {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		Game game = Games.computerVersusHuman();
		game.play();
	}

	
}
