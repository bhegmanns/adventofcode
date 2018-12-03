package de.hegmanns.adventofcode2018.day03;

import java.util.StringTokenizer;

public class Rectangle {
	public Rectangle(String specification) {
		StringTokenizer tokenizer = new StringTokenizer(specification, "#@:x, ");
		number = Integer.parseInt(tokenizer.nextToken());
		topLeft = new Coord(tokenizer.nextToken(), tokenizer.nextToken());
		topRight = new Coord(topLeft.getX() + Long.parseLong(tokenizer.nextToken()) - 1, topLeft.getY());
		bottomLeft = new Coord(topLeft.getX(), Long.parseLong(tokenizer.nextToken()) + topLeft.getY() - 1);
		bottomRight = new Coord(topRight.getX(), bottomLeft.getY());
	}
	
	private int number;
	private Coord topLeft;
	private Coord topRight;
	private Coord bottomLeft;
	private Coord bottomRight;
	
	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void place(Fabric fabric) {
		long left = topLeft.getX();
		long right= topRight.getX();
		long top  = topLeft.getY();
		long bottom = bottomLeft.getY();
		
		for (long y = top ; y <= bottom ; y++) {
			for (long x = left ; x <= right ; x++) {
				fabric.placeOnSquare(number, x, y);
			}
		}
	}
	
	public boolean isFullPlaced(Fabric fabric) {
		long left = topLeft.getX();
		long right= topRight.getX();
		long top  = topLeft.getY();
		long bottom = bottomLeft.getY();
		boolean result = true;
		for (long y = top ; y <= bottom ; y++) {
			for (long x = left ; x <= right ; x++) {
				result = result && fabric.isOccupiedBy(1, x, y);
				if (!result) {
					break;
				}
				if (!result) {
					break;
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Rectangle [number=" + number + ", topLeft=" + topLeft + ", topRight=" + topRight + ", bottomLeft="
				+ bottomLeft + ", bottomRight=" + bottomRight + "]";
	}
	
	
	
}
