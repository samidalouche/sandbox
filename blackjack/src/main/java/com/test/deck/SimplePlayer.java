package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SimplePlayer implements Player {

	private List<PlayingCard> cards = new ArrayList<PlayingCard>();
	
	/* (non-Javadoc)
	 * @see com.test.deck.Player#acceptCard(com.test.deck.PlayingCard)
	 */
	public void acceptCard(PlayingCard card) {
		cards.add(card);
	}

	public List<PlayingCard> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
