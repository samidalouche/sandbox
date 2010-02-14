package com.dalouche.experiments.rockpaperscissors.game;

public interface GameProgressListener {
	void gameFinished(Game game, GameOutcome gameOutcome);
}
