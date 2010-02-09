package com.test.deck;

import static com.test.deck.UnshuffledCardProvider.cardsWithJokers;
import static com.test.deck.UnshuffledCardProvider.cardsWithoutJokers;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UnshuffledCardProviderTest {

	private static final int EXPECTED_NUMBER_OF_SUITS = 4;
	private static final int EXPECTED_NUMBER_OF_RANKS_PER_SUIT = 13;

	@Test
	public void shouldProvide52CardsWhenNotAskingForJokers() {
		assertEquals(52, cardsWithoutJokers().getCards().size());
	}
	
	@Test
	public void shouldProvide54CardsWhenAskingForJokers() {
		assertEquals(54, cardsWithJokers().getCards().size());
	}
	
	@Test
	public void shouldIncludeAllRanksForEachSuit() {
		Map<Suit, Set<Rank>> ranksForEachSuit = extractAllSuitsAndRanks();
		for(Suit s : Suit.values()) {
			assertEquals(AllRanks(), ranksForEachSuit.get(s));	
		}
		
	}

	private HashSet<Rank> AllRanks() {
		return new HashSet<Rank>(Arrays.asList(Rank.values()));
	}
	
	
	@Test
	public void shouldInclude13RanksForEachSuit() {
		Map<Suit, Set<Rank>> ranksForEachSuit = extractAllSuitsAndRanks();
		assertEquals(numberOfSuits(), ranksForEachSuit.keySet().size());
		for(Set<Rank> ranks : ranksForEachSuit.values()) {
			assertEquals(numberOfRanksPerSuit(), ranks.size());
		}
	}

	private Object numberOfRanksPerSuit() {
		return EXPECTED_NUMBER_OF_RANKS_PER_SUIT;
	}

	private int numberOfSuits() {
		return EXPECTED_NUMBER_OF_SUITS;
	}

	private Map<Suit, Set<Rank>> extractAllSuitsAndRanks() {
		Map<Suit, Set<Rank>> ranksForEachSuit = new HashMap<Suit, Set<Rank>>();
		for(PlayingCard p : cardsWithoutJokers().getCards()) {
			if(p.isStandardCard()) { // FIXME: smell that something is not right...
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
