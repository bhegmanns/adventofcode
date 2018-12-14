package de.hegmanns.adventofcode2018.day10;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * My idea of the solution is, moving all the stars. I hope, that the place needed for all stars will become smaller
 * and gets a minimum and gets greater.
 * For that minimum the text is readable.
 * So I move the stars to that minimum and read the text.
 * 
 * @author Hegmanns
 *
 */
public class SolutionAppDay10Task01AndTask02 {

	public static void main(String[] args) {
		List<Star> stars = Input10.getStars();
		int expectedHeight = 61;
		int calculatedHeight = 200160;
		int seconds = 0;
		do {
			seconds++;
			stars.stream().forEach(Star::move);
//			System.out.println(stars.stream().map(Star::getX).collect(Collectors.toList()));
			int maxY = stars.stream().map(Star::getX).max(Integer::compareTo).get();
			int minY = stars.stream().map(Star::getX).min(Integer::compareTo).get();
			int currentCalculatedHeight = Math.abs(maxY - minY);
//			System.out.println("CurrentHeight: " + currentCalculatedHeight);
			
			if (currentCalculatedHeight > calculatedHeight) {
				printOut(stars);
				break;
			}
			calculatedHeight = currentCalculatedHeight;
			
		} while (calculatedHeight != expectedHeight);

		printOut(stars);
		System.out.println("Needed " + seconds + " seconds");
		
	}

	private static void printOut(List<Star> stars) {
		Set<Star> starset = new TreeSet<>(new Comparator<Star>() {

			@Override
			public int compare(Star o1, Star o2) {
				return (o1.getY() - o2.getY()) * 1000 + (o1.getX() - o2.getX());
			}
		});

		starset.addAll(stars);
		int minX = starset.stream().map(Star::getX).min(Integer::compareTo).get();
		int maxX = starset.stream().map(Star::getX).max(Integer::compareTo).get();
		int width = Math.abs(minX - maxX) + 1;

		Iterator<Star> starsetIterator = starset.iterator();
		StringBuilder line = new StringBuilder(StringUtils.repeat('.', width));
		Integer formerLine = null;
		while (starsetIterator.hasNext()) {
			Star s = starsetIterator.next();
			if (formerLine == null) {
				formerLine = s.getY();
			}
			if (formerLine != s.getY()) {
				System.out.println(line);
				line = new StringBuilder(StringUtils.repeat('.', width));
				int diffLines = Math.abs(s.getY() - formerLine);
				for (int i = 0 ; i < diffLines-1 ; i++) {
					System.out.println(line);
				}
				formerLine = s.getY();
			}
			line.setCharAt(s.getX()-minX, '#');
		}
		System.out.println(line);

		
	}

}
