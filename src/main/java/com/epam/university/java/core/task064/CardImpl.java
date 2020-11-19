package com.epam.university.java.core.task064;

import java.util.Objects;

public class CardImpl implements Card {

    private int rank;
    private Suit suit;

    @Override
    public int getCardRank() {
        return rank;
    }

    @Override
    public void setCardRank(int rank) {
        this.rank = rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public void setCardSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardImpl card = (CardImpl) o;
        return rank == card.rank &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
