package de.hegmanns.adventofcode2018.day05;

public class SolutionAppDay05Task01 {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		int countIterations = 0;
		int totalIterations = 0;
		String s = Input05.getString();
		StringBuilder text = new StringBuilder(s);
		boolean reacted = true;
		while (reacted) {
			reacted = false;
			countIterations ++;
			for (int i = 0; i < text.length() - 1; i++) {
				totalIterations++;
				if (isCapitalization(text.charAt(i), text.charAt(i + 1))) {
					text = text.delete(i, i+2);
					i--;
					reacted = true;
				}
			}
		}
		
		System.out.println("rest length : " + text.length());
		System.out.println("" + (System.currentTimeMillis() - time));
		System.out.println("count iterations = " + countIterations);
		System.out.println("internal iterations = " + totalIterations);
	}

	private static boolean isCapitalization(char a, char b) {
		int diff = a-b;
		return diff == 32 || diff ==-32;
	}

}
