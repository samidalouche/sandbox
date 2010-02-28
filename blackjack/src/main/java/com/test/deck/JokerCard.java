package com.test.deck;

public final class JokerCard implements PlayingCard {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return (getClass() == obj.getClass());
	}

	@Override
	public int hashCode() {
		return JokerCard.class.hashCode();
	}

	public boolean isStandardCard() {
		return false;
	}

	
}
