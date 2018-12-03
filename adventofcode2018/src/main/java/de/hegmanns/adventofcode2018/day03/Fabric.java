package de.hegmanns.adventofcode2018.day03;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Fabric {

	Map<Long, Map<Long, Long>> fabricCoordinate = new HashMap<>();

	public long calculateMultiOccupancy(long l) {
		long count = 0;
		Collection<Map<Long, Long>> values = fabricCoordinate.values();
		for (Map<Long, Long> value : values) {
			for (Long cc : value.values()) {
				if (cc>= l) {
					count++;
				}
			}
		}
		return count;
	}
	
	public void placeOnSquare(Coord coord) {
		
	}
	
	public boolean isOccupiedBy(long expectedCount, long x, long y) {
		Map<Long, Long> line = fabricCoordinate.get(y);
		if (line == null) {
			return expectedCount == 0;
		}else {
			Long squareCount = line.get(x);
			return squareCount == expectedCount;
		}
	}
	
	public void placeOnSquare(int rectangleNumber, long x, long y) {
		Map<Long, Long> line = fabricCoordinate.get(y);
		if (line == null) {
			line = new HashMap<>();
			fabricCoordinate.put(y, line);
		}
		
		Long square = line.get(x);
		if (square == null) {
			line.put(x, 1L);
		}else {
			line.put(x, square + 1);
		}
	}
}
