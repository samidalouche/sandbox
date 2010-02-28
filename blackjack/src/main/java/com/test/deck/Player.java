package com.test.deck;

import java.util.List;

public interface Player {

	List<PlayingCard> getCards();
	void acceptCard(PlayingCard card);

}