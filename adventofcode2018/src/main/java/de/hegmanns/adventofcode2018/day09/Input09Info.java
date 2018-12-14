package de.hegmanns.adventofcode2018.day09;

public class Input09Info {

	private int countPlayer;
	private int lastMarbleWorth;
	private Integer expectedResult;
	
	public Input09Info(int countPlayer, int lastMarbleWorth) {
		super();
		this.countPlayer = countPlayer;
		this.lastMarbleWorth = lastMarbleWorth;
	}
	
	public Input09Info(int countPlayer, int lastMarbleWorth, int expectedResult) {
		this.countPlayer = countPlayer;
		this.lastMarbleWorth = lastMarbleWorth;
		this.expectedResult = expectedResult;
	}

	public int getCountPlayer() {
		return countPlayer;
	}

	public int getLastMarbleWorth() {
		return lastMarbleWorth;
	}
	
	public Integer getExpectedResult() {
		return expectedResult;
	}
}
