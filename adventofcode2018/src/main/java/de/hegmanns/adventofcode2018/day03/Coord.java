package de.hegmanns.adventofcode2018.day03;

public class Coord {
	
	public Coord(int x, int y) {
		this((long)x, y);
	}
	public Coord(long x, long y) {
		this.x = x;
		this.y = y;
	}
	public Coord(String x, String y) {
		this(Long.parseLong(x), Long.parseLong(y));
	}
	private long x;
	private long y;
	
	
	public long getX() {
		return x;
	}
	public long getY() {
		return y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 32));
		result = prime * result + (int) (y ^ (y >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}
	
	
}
