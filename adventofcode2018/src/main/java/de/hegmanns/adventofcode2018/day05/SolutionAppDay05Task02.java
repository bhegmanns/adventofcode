package de.hegmanns.adventofcode2018.day05;

import java.util.stream.IntStream;

public class SolutionAppDay05Task02 {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		String s = Input05.getString();
		long minimalLength = s.length();
		int[] charArray = IntStream.rangeClosed('a', 'z').toArray();

		for (int j = 0; j < charArray.length; j++) {
			StringBuffer text = new StringBuffer(getDeletetOf(s, (char)charArray[j]));
			boolean reacted = true;
			while (reacted) {
				reacted = false;
				for (int i = 0; i < text.length() - 1; i++) {
					char a = text.charAt(i);
					char b = text.charAt(i + 1);
					if (isCapitalization(a, b)) {
						text.deleteCharAt(i);
						text.deleteCharAt(i);
						reacted = true;
						break;
					}

				}
			}
			int length = text.length();
			if (length < minimalLength) {
				minimalLength = length;
			}
		}
		
		System.out.println("MinimalLength = " + minimalLength);
		System.out.println("" + (System.currentTimeMillis() - time));
	}

	private static String getDeletetOf(String string, char a) {
		return string.replace("" + a, "").replace("" + Character.toUpperCase(a), "");
	}

	private static boolean isCapitalization(char a, char b) {
		boolean isLowerCaseA = Character.isLowerCase(a);
		if (isLowerCaseA) {
			return Character.toUpperCase(a) == b;
		} else {
			return Character.toLowerCase(a) == b;
		}
	}
}
