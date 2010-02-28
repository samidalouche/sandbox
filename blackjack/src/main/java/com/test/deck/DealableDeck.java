package com.test.deck;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public final class DealableDeck implements Deck {

	private Stack<PlayingCard> cards;
	
	public DealableDeck(CardProvider cardProvider) {
		super();
		cards = new Stack<PlayingCard>();
		for(int i = cardProvider.getCards().size()-1 ; i >=0 ; i--) {
			cards.add(cardProvider.getCards().get(i));
		}
	}

	public List<PlayingCard> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public PlayingCard draw() {
		if(cards.isEmpty()) {
			throw new NoMoreCardsException();
		}
		return cards.pop();
	}

}
