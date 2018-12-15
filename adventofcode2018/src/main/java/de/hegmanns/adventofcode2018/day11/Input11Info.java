package de.hegmanns.adventofcode2018.day11;

public class Input11Info {

	private int minSquareResult;
	private int maxSquareResult;
	private int square;
	private int gridSerialNumber;
	private String expectedCoordinate;
	
	
	public Input11Info(int minSquareResult, int maxSquareResult, int square, int gridSerialNumber) {
		super();
		this.minSquareResult = minSquareResult;
		this.maxSquareResult = maxSquareResult;
		this.square = square;
		this.gridSerialNumber = gridSerialNumber;
	}
	public Input11Info(int minSquareResult, int maxSquareResult, int square, int gridSerialNumber,
			String expectedCoordinate) {
		super();
		this.minSquareResult = minSquareResult;
		this.maxSquareResult = maxSquareResult;
		this.square = square;
		this.gridSerialNumber = gridSerialNumber;
		this.expectedCoordinate = expectedCoordinate;
	}
	public Input11Info(int square, int gridSerialNumber, String expectedCoordinate) {
		super();
		this.gridSerialNumber = gridSerialNumber;
		this.expectedCoordinate = expectedCoordinate;
	}
	public Input11Info(int square, int gridSerialNumber) {
		super();
		this.gridSerialNumber = gridSerialNumber;
	}
	public int getGridSerialNumber() {
		return gridSerialNumber;
	}
	public String getExpectedCoordinate() {
		return expectedCoordinate;
	}
	public int getSquare() {
		return square;
	}
	public int getMinSquareResult() {
		return minSquareResult;
	}
	public int getMaxSquareResult() {
		return maxSquareResult;
	}
	
	
}
