package com.test.deck;

import java.util.List;

public final class SimplePlayer implements Player {

	private List<PlayingCard> cards;
	
	/* (non-Javadoc)
	 * @see com.test.deck.Player#acceptCard(com.test.deck.PlayingCard)
	 */
	public void acceptCard(PlayingCard card) {
		cards.add(card);
	}
}
