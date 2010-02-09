package com.test.deck;

import java.util.List;

public interface Player {

	/**
	 * FIXME: Not sure if the meaning of player cards has an importance 
	 * in blackjack, so leaving List in doubt
	 * @return
	 */
	List<PlayingCard> getCards();
	void acceptCard(PlayingCard card);

}