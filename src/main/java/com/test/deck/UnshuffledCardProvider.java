package com.test.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UnshuffledCardProvider implements CardProvider {
	private static final int NUMBER_OF_JOKER_CARDS = 2;
	private boolean shouldIncludeJokers;
	private List<PlayingCard> cards = new ArrayList<PlayingCard>();

	public static UnshuffledCardProvider deckWithJokers() {
		return new UnshuffledCardProvider(true);
	}
	public static UnshuffledCardProvider deckWithoutJokers() {
		return new UnshuffledCardProvider(false);
	}
	
	public UnshuffledCardProvider() {
		this(false);
	}

	public UnshuffledCardProvider(boolean shouldIncludeJokers) {
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
