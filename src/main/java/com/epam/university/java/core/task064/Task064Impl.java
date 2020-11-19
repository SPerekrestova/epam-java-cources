/*
package com.epam.university.java.core.task064;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Task064Impl implements Task064 {

    int playerId = 1;
    final int STRAIGHT_FLUSH = 8000000;
    final int FOUR_OF_A_KIND = 7000000;
    final int FULL_HOUSE = 6000000;
    final int FLUSH = 5000000;
    final int STRAIGHT = 4000000;
    final int SET = 3000000;
    final int TWO_PAIRS = 2000000;
    final int ONE_PAIR = 1000000;

    @Override
    public Player determineWinner(String firstHand, String secondHand, String board) {
        if (isNotValidInput(firstHand, secondHand, board)) {
            throw new IllegalArgumentException();
        }
        List<Player> players = new LinkedList<>();
        Player player1 = fillInCards(firstHand);
        Player player2 = fillInCards(secondHand);
        players.add(player1);
        players.add(player2);

        List<Card> boardsCards = parseBoard(board);

        List<Player> winners = getWinner(players, boardsCards);

        return null;
    }

    public List<Player> getWinner(List<Player> players, List<Card> boardsCards) {
        checkPlayersRanking(players, boardsCards);
        List<Player> winnerList = new ArrayList<Player>();
        Player winner = players.get(0);
        Integer winnerRank = RankingUtil.getRankingToInt(winner);
        winnerList.add(winner);
        for (int i = 1; i < players.size(); i++) {
            Player player = players.get(i);
            Integer playerRank = RankingUtil.getRankingToInt(player);
            //Draw game
            if (winnerRank == playerRank) {
                Player highHandPlayer = checkHighSequence(winner, player);
                //Draw checkHighSequence
                if (highHandPlayer == null) {
                    highHandPlayer = checkHighCardWinner(winner, player);
                }
                //Not draw in checkHighSequence or checkHighCardWinner
                if (highHandPlayer != null && !winner.equals(highHandPlayer)) {
                    winner = highHandPlayer;
                    winnerList.clear();
                    winnerList.add(winner);
                } else if (highHandPlayer == null) {
                    //Draw in checkHighSequence and checkHighCardWinner
                    winnerList.add(winner);
                }
            } else if (winnerRank < playerRank) {
                winner = player;
                winnerList.clear();
                winnerList.add(winner);
            }
            winnerRank = RankingUtil.getRankingToInt(winner);
        }

        return winnerList;
    }

    private void checkPlayersRanking(List<Player> players, List<Card> boardsCards) {
        for (Player player : players) {
            RankingUtil.checkRanking(player, boardsCards);
        }
    }

    private List<Card> parseBoard(String board) {
        String[] boardArr = board.split(",");
        List<Card> cards = new LinkedList<>();
        iterate(boardArr, cards);
        return cards;
    }

    private Player fillInCards(String hand) {
        String[] handArr = hand.split(",");
        Collection<Card> cards = new LinkedList<>();
        Player player = new PlayerImpl();
        iterate(handArr, cards);
        player.setId(playerId);
        playerId++;
        player.setPlayerHand(cards);
        return player;
    }

    private void iterate(String[] handArr, Collection<Card> cards) {
        for (String card : handArr) {
            Card cardBuf = new CardImpl();
            String rank = card.substring(0, card.length() - 1);
            setRank(cardBuf, rank);
            String cardSuit = card.substring(card.length() - 1);
            setSuit(cardBuf, cardSuit);
            cards.add(cardBuf);
        }
    }

    private void setSuit(Card cardBuf, String cardSuit) {
        switch (cardSuit) {
            case "c":
                cardBuf.setCardSuit(Suit.CLUB);
                break;
            case "d":
                cardBuf.setCardSuit(Suit.DIAMOND);
                break;
            case "h":
                cardBuf.setCardSuit(Suit.HEART);
                break;
            case "s":
                cardBuf.setCardSuit(Suit.SPADE);
                break;
            default:
                break;
        }
    }

    private void setRank(Card cardBuf, String rank) {
        if (Character.isDigit(rank.charAt(0))) {
            cardBuf.setCardRank(Integer.parseInt(rank));
        } else {
            switch (rank) {
                case "A":
                    cardBuf.setCardRank(1);
                    break;
                case "J":
                    cardBuf.setCardRank(11);
                    break;
                case "Q":
                    cardBuf.setCardRank(12);
                    break;
                case "K":
                    cardBuf.setCardRank(13);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isNotValidInput(String firstHand, String secondHand, String board) {
        if (firstHand == null || secondHand == null || board == null) {
            return true;
        } else if (!firstHand.contains(",") || !secondHand.contains(",") || !board.contains(",")) {
            return true;
        } else if (firstHand.split(",").length != 2 || secondHand.split(",").length != 2) {
            return true;
        } else if (board.split(",").length != 5) {
            return true;
        }
        String[] second = secondHand.split(",");
        String[] first = firstHand.split(",");
        for (int i = 0; i < first.length; i++) {
            int finalI = i;
            if (Arrays.stream(second)
                      .anyMatch(e -> first[finalI].equals(e))) {
                return true;
            }
        }
        String[] boardArr = board.split(",");
        for (int i = 0; i < boardArr.length; i++) {
            int finalI = i;
            long buf = Arrays.stream(boardArr)
                             .filter(e -> boardArr[finalI].equals(e))
                             .count();
            if (buf > 1) {
                return true;
            }
        }
        for (int i = 0; i < second.length; i++) {
            int finalI = i;
            if (Arrays.stream(boardArr)
                      .anyMatch(e -> second[finalI].equals(e))) {
                return true;
            }
        }
        for (int i = 0; i < first.length; i++) {
            int finalI = i;
            if (Arrays.stream(boardArr)
                      .anyMatch(e -> first[finalI].equals(e))) {
                return true;
            }
        }
        return false;
    }
}
*/
