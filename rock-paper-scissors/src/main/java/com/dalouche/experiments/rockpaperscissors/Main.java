package com.dalouche.experiments.rockpaperscissors;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = Games.computerVersusHuman();
		GameOutcome result = game.play();
	}

	
}
