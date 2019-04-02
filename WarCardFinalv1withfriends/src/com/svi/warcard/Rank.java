package com.svi.warcard;

public enum Rank {
	ACE(14, "A"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "T"),
	JACK(11, "J"),
	QUEEN(12, "Q"),
	KING(13, "K");
	
	private String rankCode;
	private int rankValue;

	private Rank(int rankValue, String rankName) {
		this.rankValue = rankValue;
		this.rankCode = rankName;
	}

	public String getRankName() {
		return rankCode;
	}

	public int getRankValue() {
		return rankValue;
	}

}