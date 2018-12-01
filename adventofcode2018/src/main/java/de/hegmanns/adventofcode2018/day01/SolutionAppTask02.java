package de.hegmanns.adventofcode2018.day01;

import java.util.ArrayList;
import java.util.List;

public class SolutionAppTask02 {

	public static void main(String[] args) {
		List<Long> results = new ArrayList<>(140 * 1300);

		long sum = 0;
		boolean found = false;
		long repitition = 1;
		long start = System.currentTimeMillis();
		while (!found) {
			for (Long number : Input.NUMBERS) {
				sum += number;
				if (results.contains(sum)) {
					System.out.println("FOUND: " + sum);
					found = true;
				} else {
					results.add(sum);
				}
				if (found) {
					break;
				}

			}
//			System.out.println("Sequence : " + sequence + "  sum = " + sum);
//			System.out.println("Found: " + found);
			repitition++;
		}
		long timeInMillis = System.currentTimeMillis() - start;
		long timeInSeconds = timeInMillis / 1000;
		System.out.println("Millis: " + timeInMillis + "; Zeit in Sekunden: " + timeInSeconds);
		System.out.println("" + repitition + " repititions");
	}
}
