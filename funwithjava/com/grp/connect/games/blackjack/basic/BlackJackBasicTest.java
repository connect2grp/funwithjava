package com.grp.connect.games.blackjack.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlackJackBasicTest {

	private ArrayList<String> deck;
	private ArrayList<String> playerHand;
	private ArrayList<String> dealerHand;

	@BeforeEach
	void setup() {
		deck = new ArrayList<>(Arrays.asList("2 of Hearts", "3 of Hearts", "4 of Hearts", "5 of Hearts", "6 of Hearts",
				"7 of Hearts", "8 of Hearts", "9 of Hearts", "10 of Hearts", "Jack of Hearts", "Queen of Hearts",
				"King of Hearts", "Ace of Hearts", "2 of Diamonds", "3 of Diamonds", "4 of Diamonds", "5 of Diamonds",
				"6 of Diamonds", "7 of Diamonds", "8 of Diamonds", "9 of Diamonds", "10 of Diamonds",
				"Jack of Diamonds", "Queen of Diamonds", "King of Diamonds", "Ace of Diamonds", "2 of Clubs",
				"3 of Clubs", "4 of Clubs", "5 of Clubs", "6 of Clubs", "7 of Clubs", "8 of Clubs", "9 of Clubs",
				"10 of Clubs", "Jack of Clubs", "Queen of Clubs", "King of Clubs", "Ace of Clubs", "2 of Spades",
				"3 of Spades", "4 of Spades", "5 of Spades", "6 of Spades", "7 of Spades", "8 of Spades", "9 of Spades",
				"10 of Spades", "Jack of Spades", "Queen of Spades", "King of Spades", "Ace of Spades"));
		playerHand = new ArrayList<>(Arrays.asList("2 of Hearts", "3 of Hearts"));
		dealerHand = new ArrayList<>(Arrays.asList("4 of Hearts", "5 of Hearts"));
	}

	@Test
	void createDeck_ShouldCreateADeckWith52Cards() {
		ArrayList<String> deck = BlackJackBasic.createDeck();
		Assertions.assertEquals(52, deck.size());
	}

	@Test
	void calculateHandTotal_ShouldReturnCorrectTotalForHandWithNoAces() {
		int total = BlackJackBasic.calculateHandTotal(Arrays.asList("2 of Hearts", "3 of Hearts"));
		Assertions.assertEquals(5, total);
	}

	@Test
	void calculateHandTotal_ShouldReturnCorrectTotalForHandWithOneAce() {
		int total = BlackJackBasic.calculateHandTotal(Arrays.asList("Ace of Hearts", "4 of Hearts"));
		Assertions.assertEquals(15, total);
	}

	@Test
	void calculateHandTotal_ShouldReturnCorrectTotalForHandWithMultipleAces() {
		int total = BlackJackBasic.calculateHandTotal(Arrays.asList("Ace of Hearts", "Ace of Diamonds", "7 of Spades"));
		Assertions.assertEquals(19, total);
	}

	@Test
	void calculateHandTotal_ShouldReturnCorrectTotalForHandWithBust() {
		int total = BlackJackBasic
				.calculateHandTotal(Arrays.asList("10 of Hearts", "Jack of Hearts", "Queen of Hearts"));
		Assertions.assertEquals(30, total);
	}

	@Test
	public void testCreateDeck() {
		ArrayList<String> expectedDeck = new ArrayList<String>();
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		for (String suit : suits) {
			for (String rank : ranks) {
				expectedDeck.add(rank + " of " + suit);
			}
		}
		ArrayList<String> actualDeck = BlackJackBasic.createDeck();
		assertEquals(expectedDeck, actualDeck);
	}

	@Test
	public void testCalculateHandTotal() {
		ArrayList<String> hand1 = new ArrayList<String>(Arrays.asList("2 of Hearts", "3 of Diamonds"));
		ArrayList<String> hand2 = new ArrayList<String>(Arrays.asList("Ace of Clubs", "10 of Spades"));
		ArrayList<String> hand3 = new ArrayList<String>(Arrays.asList("King of Hearts", "Queen of Diamonds"));
		ArrayList<String> hand4 = new ArrayList<String>(Arrays.asList("Ace of Clubs", "Ace of Spades"));
		ArrayList<String> hand5 = new ArrayList<String>(
				Arrays.asList("Ace of Hearts", "Ace of Diamonds", "Ace of Clubs"));
		assertEquals(5, BlackJackBasic.calculateHandTotal(hand1));
		assertEquals(21, BlackJackBasic.calculateHandTotal(hand2));
		assertEquals(20, BlackJackBasic.calculateHandTotal(hand3));
		assertEquals(12, BlackJackBasic.calculateHandTotal(hand4));
		assertEquals(13, BlackJackBasic.calculateHandTotal(hand5));
	}

}
