package com.test.deck;

import com.test.commons.Validate;

/**
 * Make sure to keep Immutable
 * 
 * FIXME: find a better name...
 * @author sdalouche
 *
 */
public final class StandardCard implements PlayingCard {
	private Rank rank;
	private Suit suit;
	
	public StandardCard(Rank rank, Suit suit) {
		super();
		Validate.notNull(rank);
		Validate.notNull(suit);
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	/**
	 * FIXME: ugly, should use commons HashCodeBuilder to implement correctly
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	/**
	 * FIXME: ugly, should use commons HashCodeBuilder to implement correctly
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardCard other = (StandardCard) obj;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (suit == null) {
			if (other.suit != null)
				return false;
		} else if (!suit.equals(other.suit))
			return false;
		return true;
	}

	public boolean isStandardCard() {
		return true;
	}

	
	
}
