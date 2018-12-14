package de.hegmanns.adventofcode2018.day10;

public class Star {

	private int x;
	private int y;
	
	private int deltaX;
	private int deltaY;
	public Star(int x, int y, int deltaX, int deltaY) {
		super();
		this.x = x;
		this.y = y;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	public void move() {
		this.x += this.deltaX;
		this.y += this.deltaY;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "Star [x=" + x + ", y=" + y + "]";
	}
	
	
}
