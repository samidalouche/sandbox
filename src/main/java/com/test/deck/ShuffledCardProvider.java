package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffledCardProvider implements CardProvider {

	private List<PlayingCard> cards;
	
	public static CardProvider shuffledCardProvider(CardProvider deckToShuffle) {
		return new ShuffledCardProvider(deckToShuffle);
	}
	
	public ShuffledCardProvider(CardProvider deckToShuffle) {
		super();
		cards = new ArrayList<PlayingCard>(deckToShuffle.getCards());
		Collections.shuffle(cards);
	}

	public List<PlayingCard> getCards() {
		return Collections.unmodifiableList(cards);
	}

}
