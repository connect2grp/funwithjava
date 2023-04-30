package com.grp.connect.games.blackjack.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJackBasic {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");

		// Create a deck of cards
		ArrayList<String> deck = createDeck();
		Collections.shuffle(deck);

		// Initialize player and dealer hands
		ArrayList<String> playerHand = new ArrayList<>();
		ArrayList<String> dealerHand = new ArrayList<>();

		// Deal two cards to each player
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));
		playerHand.add(deck.remove(0));
		dealerHand.add(deck.remove(0));

		// Display hands and prompt player to hit or stand
		System.out.println("Your hand: " + playerHand);
		System.out.println("Dealer's hand: [" + dealerHand.get(0) + ", **]");

		boolean playerBust = false;
		boolean dealerBust = false;

		while (true) {
			System.out.print("Hit or stand? ");
			String choice = sc.nextLine();

			if (choice.equalsIgnoreCase("hit")) {
				// Give the player another card
				playerHand.add(deck.remove(0));
				System.out.println("Your hand: " + playerHand);

				// Check for bust
				int playerTotal = calculateHandTotal(playerHand);
				if (playerTotal > 21) {
					System.out.println("Bust! You lose.");
					playerBust = true;
					break;
				}
			} else if (choice.equalsIgnoreCase("stand")) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter 'hit' or 'stand'.");
			}
		}

		// Dealer's turn
		if (!playerBust) {
			System.out.println("Dealer's hand: " + dealerHand);

			while (true) {
				int dealerTotal = calculateHandTotal(dealerHand);

				if (dealerTotal < 17) {
					// Dealer hits
					System.out.println("Dealer hits.");
					dealerHand.add(deck.remove(0));
					System.out.println("Dealer's hand: " + dealerHand);
				} else if (dealerTotal > 21) {
					// Dealer busts
					System.out.println("Dealer busts! You win.");
					dealerBust = true;
					break;
				} else {
					// Dealer stands
					System.out.println("Dealer stands.");
					break;
				}
			}
		}

		// Determine the winner
		if (!playerBust && !dealerBust) {
			int playerTotal = calculateHandTotal(playerHand);
			int dealerTotal = calculateHandTotal(dealerHand);

			if (playerTotal > dealerTotal) {
				System.out.println("You win!");
			} else if (dealerTotal > playerTotal) {
				System.out.println("Dealer wins.");
			} else {
				System.out.println("It's a tie!");
			}
		}

		sc.close();
	}

	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

		for (String suit : suits) {
			for (String rank : ranks) {
				deck.add(rank + " of " + suit);
			}
		}

		return deck;
	}

	public static int calculateHandTotal(List<String> hand) {
		int total = 0;
		int numAces = 0;

		for (String card : hand) {
			String rank = card.substring(0, card.indexOf(" "));
			if (rank.equals("Ace")) {
				numAces++;
				total += 11;
			} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
				total += 10;
			} else {
				total += Integer.parseInt(rank);
			}
		}

		while (total > 21 && numAces > 0) {
			total -= 10;
			numAces--;
		}

		return total;
	}
}