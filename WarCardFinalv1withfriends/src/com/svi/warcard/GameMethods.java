package com.svi.warcard;

import java.util.*;

public class GameMethods {

	public static ArrayList<Card> faroShuffleDeck(int numOfShuffles, ArrayList<Card> deck) {
		ArrayList<Card> shuffledDeck = new ArrayList<>();
		ArrayList<Card> tempDeck = new ArrayList<>();

		for (int i = 0; i < numOfShuffles; i++) {
			if (shuffledDeck.isEmpty()) {
				tempDeck = deck;
			} else {
				tempDeck = shuffledDeck;
			}
			shuffledDeck = new ArrayList<Card>();
			for (int j = 0; j < deck.size() / 2; j++) {
				shuffledDeck.add(tempDeck.get(j));
				shuffledDeck.add(tempDeck.get(j + 26));
			}
		}
		return shuffledDeck;
	}

	public static void giveCards(int numberOfPlayers, List<Player> playerList, ArrayList<Card> deck) {
		int n = 0;
		do {
			if (n != numberOfPlayers) {
				for (n = 0; n < numberOfPlayers; n++) {
					playerList.get(n).getPlayerHands().add(deck.get(0));
					deck.remove(0);
				}
			} else if (n == numberOfPlayers) {
				n = 0;
			}
		} while (!deck.isEmpty());
		System.out.println("\nCards are now distributed to each players.");
		// uncomment the next lines if you want to see the starting hand of each player.
// 		System.out.println("\nStarting cards per player : ");
// 		for (Player player : playerList) {
// 			System.out.println(player.getPlayerName() + " : " + player.getPlayerHands());
// 		}
	}

	public static List<Card> getTopCards(List<Player> playerList) {
		List<Card> topCardList = new ArrayList<Card>();
		for (Player player : playerList) {
			topCardList.add(player.getPlayerHands().get(0));
			player.getPlayerHands().remove(0);
		}
		return topCardList;
	}

	public static void playGame(List<Player> playerList, int numberOfPlayers) {
		List<Card> topCardList = new ArrayList<Card>();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (Player player : playerList) {
			System.out.println(player.getPlayerName() + " : " + player.getPlayerHands());
		}
		topCardList = (ArrayList<Card>) GameMethods.getTopCards(playerList);
		System.out.println("Table's Cards: " + topCardList);
		Card topCard = Collections.max(topCardList, Comparator.comparing(s -> s.getCardValue()));	
		int tempindex = topCardList.indexOf(topCard);
		Collections.rotate(topCardList, topCardList.size() - topCardList.indexOf(topCard));
		playerList.get(tempindex).getPlayerHands().addAll(topCardList);
		System.out.println("The winning card is : " + topCard);
		topCardList.clear();
		playerList.removeIf(player -> player.getPlayerHands().isEmpty()); 
	}

}
