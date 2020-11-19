/*
package com.epam.university.java.core.task064;

import static com.epam.university.java.core.task064.RankingEnum.FLUSH;
import static com.epam.university.java.core.task064.RankingEnum.FOUR_OF_A_KIND;
import static com.epam.university.java.core.task064.RankingEnum.FULL_HOUSE;
import static com.epam.university.java.core.task064.RankingEnum.HIGH_CARD;
import static com.epam.university.java.core.task064.RankingEnum.ONE_PAIR;
import static com.epam.university.java.core.task064.RankingEnum.ROYAL_FLUSH;
import static com.epam.university.java.core.task064.RankingEnum.STRAIGHT;
import static com.epam.university.java.core.task064.RankingEnum.STRAIGHT_FLUSH;
import static com.epam.university.java.core.task064.RankingEnum.THREE_OF_A_KIND;
import static com.epam.university.java.core.task064.RankingEnum.TWO_PAIR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epam.university.java.core.task064.*;

public class RankingUtil {

    private RankingUtil() {
    }

    public static Integer getRankingToInt(Player player) {
        return player.;
    }

    public static void checkRanking(Player player, List<Card> tableCards) {

        //HIGH_CARD
        Card highCard = getHighCard(player, tableCards);
        player.setHighCard(highCard);

        //ROYAL_FLUSH
        List<Card> rankingList = getRoyalFlush(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, ROYAL_FLUSH, rankingList);
            return;
        }
        //STRAIGHT_FLUSH
        rankingList = getStraightFlush(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, STRAIGHT_FLUSH,
                    rankingList);
            return;
        }
        //FOUR_OF_A_KIND
        rankingList = getFourOfAKind(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, FOUR_OF_A_KIND,
                    rankingList);
            return;
        }
        //FULL_HOUSE
        rankingList = getFullHouse(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, FULL_HOUSE, rankingList);
            return;
        }
        //FLUSH
        rankingList = getFlush(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, FLUSH, rankingList);
            return;
        }
        //STRAIGHT
        rankingList = getStraight(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, STRAIGHT, rankingList);
            return;
        }
        //THREE_OF_A_KIND
        rankingList = getThreeOfAKind(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, THREE_OF_A_KIND,
                    rankingList);
            return;
        }
        //TWO_PAIR
        rankingList = getTwoPair(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, TWO_PAIR, rankingList);
            return;
        }
        //ONE_PAIR
        rankingList = getOnePair(player, tableCards);
        if (rankingList != null) {
            setRankingEnumAndList(player, ONE_PAIR, rankingList);
            return;
        }
        //HIGH_CARD
        player.setRankingEnum(HIGH_CARD);
        List<Card> highCardRankingList = new ArrayList<Card>();
        highCardRankingList.add(highCard);
        player.setRankingList(highCardRankingList);
        return;
    }

    public static List<Card> getRoyalFlush(Player player, List<Card> tableCards) {
        if (!isSameSuit(player, tableCards)) {
            return null;
        }

        List<CardRankEnum> rankEnumList = toRankEnumList(player, tableCards);

        if (rankEnumList.contains(CARD_10)
                && rankEnumList.contains(JACK)
                && rankEnumList.contains(QUEEN)
                && rankEnumList.contains(KING)
                && rankEnumList.contains(ACE)) {

            return getMergedCardList(player, tableCards);
        }

        return null;
    }

    public static List<Card> getStraightFlush(Player player,
                                              List<Card> tableCards) {
        return getSequence(player, tableCards, 5, true);
    }

    public static List<Card> getFourOfAKind(Player player,
                                            List<Card> tableCards) {
        List<Card> mergedList = getMergedCardList(player, tableCards);
        return checkPair(mergedList, 4);
    }

    public static List<Card> getFullHouse(Player player, List<Card> tableCards) {
        List<Card> mergedList = getMergedCardList(player, tableCards);
        List<Card> threeList = checkPair(mergedList, 3);
        if (threeList != null) {
            mergedList.removeAll(threeList);
            List<Card> twoList = checkPair(mergedList, 2);
            if (twoList != null) {
                threeList.addAll(twoList);
                return threeList;
            }
        }
        return null;
    }

    public static List<Card> getFlush(Player player, List<Card> tableCards) {
        List<Card> mergedList = getMergedCardList(player, tableCards);
        List<Card> flushList = new ArrayList<Card>();

        for (Card card1 : mergedList) {
            for (Card card2 : mergedList) {
                if (card1.getSuit()
                         .equals(card2.getSuit())) {
                    if (!flushList.contains(card1)) {
                        flushList.add(card1);
                    }
                    if (!flushList.contains(card2)) {
                        flushList.add(card2);
                    }
                }
            }
            if (flushList.size() == 5) {
                return flushList;
            }
            flushList.clear();
        }
        return null;
    }

    //S‹o 5 cartas seguidas de naipes diferentes, caso empate ganha aquele com a maior sequ�ncia.
    public static List<Card> getStraight(Player player, List<Card> tableCards) {
        return getSequence(player, tableCards, 5, false);
    }

    public static List<Card> getThreeOfAKind(Player player,
                                             List<Card> tableCards) {
        List<Card> mergedList = getMergedCardList(player, tableCards);
        return checkPair(mergedList, 3);
    }

    public static List<Card> getTwoPair(Player player, List<Card> tableCards) {
        List<Card> mergedList = getMergedCardList(player, tableCards);
        List<Card> twoPair1 = checkPair(mergedList, 2);
        if (twoPair1 != null) {
            mergedList.removeAll(twoPair1);
            List<Card> twoPair2 = checkPair(mergedList, 2);
            if (twoPair2 != null) {
                twoPair1.addAll(twoPair2);
                return twoPair1;
            }
        }
        return null;
    }

    public static List<Card> getOnePair(Player player, List<Card> tableCards) {
        List<Card> mergedList = getMergedCardList(player, tableCards);
        return checkPair(mergedList, 2);
    }

    public static Card getHighCard(Player player, List<Card> tableCards) {
        List<Card> allCards = new ArrayList<Card>();
        allCards.addAll(tableCards);
        allCards.add(player.getPlayerHan);
        allCards.add(player.getCards()[1]);

        Card highCard = allCards.get(0);
        for (Card card : allCards) {
            if (card.getCardRank() > highCard.getRankToInt()) {
                highCard = card;
            }
        }
        return highCard;
    }

    private static List<Card> getSequence(Player player,
                                          List<Card> tableCards, Integer sequenceSize,
                                          Boolean compareSuit) {
        List<Card> orderedList = getOrderedCardList(player, tableCards);
        List<Card> sequenceList = new ArrayList<Card>();

        Card cardPrevious = null;
        for (Card card : orderedList) {
            if (cardPrevious != null) {
                if ((card.getRankToInt() - cardPrevious.getRankToInt()) == 1) {
                    if (!compareSuit
                            || cardPrevious.getSuit()
                                           .equals(card.getSuit())) {
                        if (sequenceList.size() == 0) {
                            sequenceList.add(cardPrevious);
                        }
                        sequenceList.add(card);
                    }
                } else {
                    if (sequenceList.size() == sequenceSize) {
                        return sequenceList;
                    }
                    sequenceList.clear();
                }
            }
            cardPrevious = card;
        }

        return (sequenceList.size() == sequenceSize) ? sequenceList : null;
    }

    private static List<Card> getMergedCardList(Player player,
                                                List<Card> tableCards) {
        List<Card> merged = new ArrayList<Card>();
        merged.addAll(tableCards);
        merged.add(player.getCards()[0]);
        merged.add(player.getCards()[1]);
        return merged;
    }

    private static List<Card> getOrderedCardList(Player player,
                                                 List<Card> tableCards) {
        List<Card> ordered = getMergedCardList(player, tableCards);
        Collections.sort(ordered, new Comparator<Card>() {

            public int compare(Card c1, Card c2) {
                return c1.getRankToInt() < c2.getRankToInt() ? -1 : 1;
            }

        });
        return ordered;
    }

    private static List<Card> checkPair(List<Card> mergedList, Integer pairSize) {
        List<Card> checkedPair = new ArrayList<Card>();
        for (Card card1 : mergedList) {
            checkedPair.add(card1);
            for (Card card2 : mergedList) {
                if (!card1.equals(card2)
                        && card1.getRank()
                                .equals(card2.getRank())) {
                    checkedPair.add(card2);
                }
            }
            if (checkedPair.size() == pairSize) {
                return checkedPair;
            }
            checkedPair.clear();
        }
        return null;
    }

    private static Boolean isSameSuit(Player player, List<Card> tableCards) {
        CardSuitEnum suit = player.getCards()[0].getSuit();

        if (!suit.equals(player.getCards()[1].getSuit())) {
            return false;
        }

        for (Card card : tableCards) {
            if (!card.getSuit()
                     .equals(suit)) {
                return false;
            }
        }

        return true;
    }

    private static List<CardRankEnum> toRankEnumList(Player player,
                                                     List<Card> tableCards) {
        List<CardRankEnum> rankEnumList = new ArrayList<CardRankEnum>();

        for (Card card : tableCards) {
            rankEnumList.add(card.getRank());
        }

        rankEnumList.add(player.getCards()[0].getRank());
        rankEnumList.add(player.getCards()[1].getRank());

        return rankEnumList;
    }

    private static void setRankingEnumAndList(Player player,
                                              RankingEnum rankingEnum, List<Card> rankingList) {
        player.setRankingEnum(rankingEnum);
        player.setRankingList(rankingList);
    }
}
*/
