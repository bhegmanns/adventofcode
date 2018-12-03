package de.hegmanns.adventofcode2018.day02;

import java.util.HashMap;
import java.util.Map;

public class SolutionAppTask01 {

	public static void main(String[] args) {
		int countDouble = 0;
		int countTrio   = 0;
		for (String string : Input02.STRINGS) {
			Map<String, Integer> letterCounts = new HashMap<>();
			countDoubles(string, letterCounts);
			if (letterCounts.values().contains(2)) {
				countDouble ++;
			}
			letterCounts = new HashMap<>();
			countTrios(string, letterCounts);
			if (letterCounts.values().contains(3)) {
				countTrio++;
			}
		}
		System.out.println("Doubles : " + countDouble);
		System.out.println("Trios   : " + countTrio);
		System.out.println(">>> Product : " + (countDouble * countTrio));
	}

	private static void countTrios(String string, Map<String, Integer> letterCounts) {
		for (char c : string.toCharArray()) {
			addToMap("" + c, letterCounts);
		}
	}

	private static void countDoubles(String string, Map<String, Integer> letterCounts) {
		for (char c : string.toCharArray()) {
			addToMap("" + c, letterCounts);
		}
	}
	
	private static void addToMap(String c, Map<String, Integer> letterCounts) {
		Integer count = letterCounts.get(c);
		if (count != null) {
			count++;
			letterCounts.put(c, count);
		}else {
			letterCounts.put(c, 1);
		}
	}
}
