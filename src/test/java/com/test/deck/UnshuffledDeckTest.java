package com.test.deck;

import static com.test.deck.UnshuffledDeck.deckWithJokers;
import static com.test.deck.UnshuffledDeck.deckWithoutJokers;
import static junit.framework.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UnshuffledDeckTest {

	private static final int NUMBER_OF_SUITS = 4;
	private static final int NUMBER_OF_RANKS_PER_SUIT = 13;

	@Test
	public void deckWithoutJokersShouldhave52Cards() {
		assertEquals(52, deckWithoutJokers().getCards().size());
	}
	
	@Test
	public void deckWithJokersShouldhave54Cards() {
		assertEquals(54, deckWithJokers().getCards().size());
	}
	
	@Test
	public void deckShouldInclude13RanksForEachSuit() {
		Map<Suit, Set<Rank>> ranksForEachSuit = extractAllSuitsAndRanks();
		assertEquals(numberOfSuits(), ranksForEachSuit.size());
		for(Set<Rank> ranks : ranksForEachSuit.values()) {
			assertEquals(numberOfRanksPerSuit(), ranks.size());
		}
	}

	private Object numberOfRanksPerSuit() {
		return NUMBER_OF_RANKS_PER_SUIT;
	}

	private int numberOfSuits() {
		return NUMBER_OF_SUITS;
	}

	private Map<Suit, Set<Rank>> extractAllSuitsAndRanks() {
		Map<Suit, Set<Rank>> ranksForEachSuit = new HashMap<Suit, Set<Rank>>();
		for(PlayingCard p : deckWithoutJokers().getCards()) {
			if(p.isStandardCard()) {
				StandardCard card = (StandardCard) p;
				multiMapAdd(ranksForEachSuit, card.getSuit(), card.getRank());
			} else {
				throw new RuntimeException("The deck is not supposed to return anything else than standard cards");
			}
		}
		return ranksForEachSuit;
	}

	/**
	 * FIMXE: we lack a concept of MultiMap. 
	 * @param ranksForEachSuit
	 * @param suit
	 * @param rank
	 */
	private void multiMapAdd(Map<Suit, Set<Rank>> ranksForEachSuit, Suit suit, Rank rank) {
		Set<Rank> ranks = ranksForEachSuit.get(suit);
		if(ranks == null) {
			ranks = new HashSet<Rank>();
		}
		ranks.add(rank);
		ranksForEachSuit.put(suit, ranks);
	}


}
