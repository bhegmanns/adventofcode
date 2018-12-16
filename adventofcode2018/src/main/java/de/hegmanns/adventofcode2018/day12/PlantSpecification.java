package de.hegmanns.adventofcode2018.day12;

public class PlantSpecification {

	private boolean from;
	private boolean firstLeft;
	private boolean secondLeft;
	private boolean firstRight;
	private boolean secondRight;
	
	public PlantSpecification(boolean secondLeft, boolean firstLeft, boolean from, boolean firstRight, boolean secondRight) {
		this.secondLeft = secondLeft;
		this.firstLeft = firstLeft;
		this.firstRight = firstRight;
		this.secondRight = secondRight;
		this.from = from;
	}

	public boolean isFrom() {
		return from;
	}

	public boolean isFirstLeft() {
		return firstLeft;
	}

	public boolean isSecondLeft() {
		return secondLeft;
	}

	public boolean isFirstRight() {
		return firstRight;
	}

	public boolean isSecondRight() {
		return secondRight;
	}

	@Override
	public String toString() {
		return "[" + (secondLeft?"#":".") + (firstLeft?"#":".") + (from?"#":".") + (firstRight?"#":".") + (secondRight?"#":".") + "]";
	}
	
	
}
