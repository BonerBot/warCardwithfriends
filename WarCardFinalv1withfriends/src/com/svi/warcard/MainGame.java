package com.svi.warcard;

import java.util.*;

public class MainGame {

	public static void main(String[] args) {

		int shuffleCount = 0, playerCount = 0;
		boolean wrongInput = true;
		System.out.println("War Card Game - SVI Edition");

		// Initializes scanner
		Scanner scanInput = new Scanner(System.in);

		do {
			try {
				System.out.println("Input player count: ");
				playerCount = scanInput.nextInt();

				if (playerCount == 2 || playerCount == 4) {
					wrongInput = false;
				} else {
					System.out.println("Only 2 and 4 players can play.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid number.");
				scanInput.nextLine();
			}
		} while (wrongInput);

		// Input number of shuffles
		wrongInput = true;
		do {
			try {
				System.out.println("Input number of times for shuffling the deck: ");
				shuffleCount = scanInput.nextInt();
				if (shuffleCount < 1) {
					System.out.println("Please shuffle the deck atleast once.");
				} else {
					wrongInput = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid number.");
				scanInput.nextLine();
			}
		} while (wrongInput);

		// Closing the scanner since every input is done
		scanInput.close();
		

		// Creating list for Deck
		ArrayList<Card> deck = new ArrayList<>();
		// Adding cards to deck
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(rank.getRankName(), rank.getRankValue(), suit.getSuitName(), suit.getSuitvalue());
				deck.add(card);
			}
		}
		// Showing the initial deck
		System.out.println("Initial Deck: ");
		System.out.println(deck + "\n");

		// Shuffle the deck
		ArrayList<Card> shuffledDeck = GameMethods.faroShuffleDeck(shuffleCount, deck);
		System.out.println("Shuffle Deck: ");
		System.out.println(shuffledDeck);

		// Create players then deal cards to players
		List<Player> playerList = new ArrayList<>();
		for (int i = 0; i < playerCount; i++) {
			playerList.add(new Player("Player " + (i + 1)));
		}

		// Deal cards
		GameMethods.giveCards(playerCount, playerList, shuffledDeck);

		// Start Game
		int round = 1;
		while (playerList.size() > 1) {
			System.out.println("\nROUND: " + round);
			GameMethods.playGame(playerList, playerCount);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			round++;
		}
		// If player list size = 1 , then Finish the game
		System.out.println("GAME OVER");
		System.out.println("\nThe winner is " + playerList.get(0).getPlayerName() + ". Total Round : " + (round - 1));
		for (Player player : playerList) {
			System.out.println("Winning Deck : " + player.getPlayerHands());
		}
	}

}
