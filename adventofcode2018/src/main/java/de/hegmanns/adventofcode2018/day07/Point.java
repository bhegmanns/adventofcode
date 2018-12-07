package de.hegmanns.adventofcode2018.day07;

public class Point implements Comparable<Point>{

	public String letter;
	
	public Point(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((letter == null) ? 0 : letter.hashCode());
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
		Point other = (Point) obj;
		if (letter == null) {
			if (other.letter != null)
				return false;
		} else if (!letter.equals(other.letter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [letter=" + letter + "]";
	}

	@Override
	public int compareTo(Point o) {
		return this.letter.compareTo(o.letter);
	}

	
}
