package de.hegmanns.adventofcode2018.day06;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolutionAppDay06Task01 {

	public static void main(String[] args) {
		String[] coords = Input06.getStrings();
		Grid grid = new Grid();
		List<Coordinate> coordinates = Stream.of(coords).map(SolutionAppDay06Task01::mapString).collect(Collectors.toList());
		coordinates.stream().forEach((c) -> grid.addPoint(c));
		System.out.println("largestArea= " + grid.calculateSizeOfLargestArea());
	}
	
	private static Coordinate mapString(String coordinate) {
		StringTokenizer tokenizer = new StringTokenizer(coordinate, ", ");
		return new Coordinate(Long.parseLong(tokenizer.nextToken()), Long.parseLong(tokenizer.nextToken()));
	}
	
	

}
