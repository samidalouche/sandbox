package com.dalouche.experiments.rockpaperscissors;

public interface GameProgressListener {
	void gameFinished(Game game, GameOutcome gameOutcome);
}
