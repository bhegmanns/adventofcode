package de.hegmanns.adventofcode2018.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Grid {

	Map<Long, Coordinate> coordinates = new HashMap<>();
	long maxX = 0;
	long maxY = 0;

	public void addPoint(Coordinate coordinate) {
		coordinates.put((long) coordinates.size(), coordinate);
		calculateBoundaries(coordinate);
	}

	private void calculateBoundaries(Coordinate coordinate) {
		maxX = Math.max(maxX, coordinate.getX());
		maxY = Math.max(maxY, coordinate.getY());
	}
	
	public long calculateCountLocationsOfCoordinatesLessThan(long maxSum) {
		maxX = Math.max(maxY, maxX);
		maxY = maxX;
		long countLocationsLessMaxSum = 0;
		for (long x = 0; x <= maxX; x++) {
			for (long y = 0; y <= maxY; y++) {
				Long distanceSum = calculateDistanceSum(x, y);
				if (distanceSum<maxSum) {
					countLocationsLessMaxSum++;
				}
			}
		}
		return countLocationsLessMaxSum;
	}

	private Long calculateDistanceSum(long x, long y) {
		Result result = new Result();
		for (Entry<Long, Coordinate> entry : coordinates.entrySet()) {
			long distance = Math.abs(x - entry.getValue().getX()) + Math.abs(y - entry.getValue().getY());
			result.add(entry.getKey(), distance);
		}
		return result.calculateDistanceSum();
	}

	public long calculateSizeOfLargestArea() {
		maxX = Math.max(maxY, maxX);
		maxY = maxX;
		Map<Long, Long> manhattanDistanceAreas = new HashMap<>();
		List<Long> infinitiveAreas = new ArrayList<>();
		for (long x = 0; x <= maxX; x++) {
			for (long y = 0; y <= maxY; y++) {
				Long area = calculateManhattanDistanceArea(x, y);
				if (area != null) {
					Long coordinateNumber = manhattanDistanceAreas.get(area);
					if (coordinateNumber == null) {
						manhattanDistanceAreas.put(area, 1L);
					} else {
						coordinateNumber++;
						manhattanDistanceAreas.put(area, coordinateNumber);
					}
					if (x == 0 || y == 0 || x == maxY || y == maxY) {
						if (!infinitiveAreas.contains(area.longValue())) {
							infinitiveAreas.add(area.longValue());
						}
					}
				}
			}
		}

		long maxAreaSize = 0;
		for (Long l : infinitiveAreas) {
			manhattanDistanceAreas.remove(l);
		}
		for (Entry<Long, Long> entry : manhattanDistanceAreas.entrySet()) {
			if (maxAreaSize<entry.getValue()) {
				maxAreaSize = entry.getValue();
			}
		}
		return maxAreaSize;
	}

	private Long calculateManhattanDistanceArea(long x, long y) {
		Result result = new Result();
		for (Entry<Long, Coordinate> entry : coordinates.entrySet()) {
			long distance = Math.abs(x - entry.getValue().getX()) + Math.abs(y - entry.getValue().getY());
			result.add(entry.getKey(), distance);
		}
		return result.calculateAreaResult();
	}
}

class Result{
	
	Map<Long, Long> values = new HashMap<>();

	public void add(Long key, long distance) {
		values.put(key, distance);
	}
	
	public Long calculateDistanceSum() {
		long sum = 0;
		for (long l : values.values()) {
			sum += l;
		}
		return sum;
	}
	
	public Long calculateAreaResult() {
		Long minimum = values.values().stream().min(Long::compareTo).get();
		Long result = null;
		boolean doubleFound = false;
		for (Entry<Long, Long> entry : values.entrySet()) {
			if (entry.getValue() == minimum) {
				if (result == null) {
					result = entry.getKey();
				}else {
					doubleFound = true;
				}
			}
		}
		if (!doubleFound) {
			return result;
		}else {
			return null;
		}
	}
	
}
