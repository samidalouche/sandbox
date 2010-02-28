package com.test.deck;

import static com.test.deck.ShuffledCardProvider.shuffled;
import static com.test.deck.UnshuffledCardProvider.cardsWithoutJokers;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IntegrationTest {

	@Test
	public void shouldGiveTwoCardsToEachPlayerWhileDealingOpeningHand() {
		Player player1 = new SimplePlayer();
		Player player2 = new SimplePlayer();
		Deck deck = new DealableDeck(shuffled(cardsWithoutJokers()));
		BlackjackDealer dealer = new BlackjackDealer(deck, Arrays.asList(player1, player2));
		
		dealer.dealOpeninghand();
		
		eachPlayerShouldHaveExactlyTwoCards(player1, player2, dealer);
		
	}

	private void eachPlayerShouldHaveExactlyTwoCards(
			Player player1, Player player2, BlackjackDealer dealer) {
		for(List<PlayingCard> cards : Arrays.asList(player1.getCards(), player2.getCards(), dealer.getCards())) {
			assertEquals(2, cards.size());	
		}
	}
}
