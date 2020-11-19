package com.epam.university.java.core.task064;

import java.util.Collection;
import java.util.Objects;

public class PlayerImpl implements Player {

    private int id;
    private Collection<Card> hand;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<Card> getPlayerHand() {
        return hand;
    }

    @Override
    public void setPlayerHand(Collection<Card> hand) {
        this.hand = hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerImpl player = (PlayerImpl) o;
        return id == player.id &&
                Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hand);
    }
}
