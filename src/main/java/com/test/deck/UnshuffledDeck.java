package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UnshuffledDeck implements Deck {
	private static final int NUMBER_OF_JOKER_CARDS = 2;
	private boolean shouldIncludeJokers;
	private List<PlayingCard> cards = new ArrayList<PlayingCard>();

	public static UnshuffledDeck deckWithJokers() {
		return new UnshuffledDeck(true);
	}
	public static UnshuffledDeck deckWithoutJokers() {
		return new UnshuffledDeck(false);
	}
	
	public UnshuffledDeck() {
		this(true);
	}

	public UnshuffledDeck(boolean shouldIncludeJokers) {
		super();
		this.shouldIncludeJokers = shouldIncludeJokers;
		
		createStandardCards();
		createJokers();
	}

	public List<PlayingCard> getCards() {
		
		return Collections.unmodifiableList(cards);
	}

	private void createJokers() {
		if(shouldIncludeJokers) {
			for(int i = 0 ; i < NUMBER_OF_JOKER_CARDS ; i++) {
				cards.add(new JokerCard());
			}
		}
	}

	private void createStandardCards() {
		for(Suit suit : Suit.values()) {
			for(Rank rank : Rank.values()) {
				cards.add(new StandardCard(rank, suit));
			}	
		}
	}
	
}
