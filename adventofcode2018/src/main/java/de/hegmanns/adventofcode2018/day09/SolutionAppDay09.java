package de.hegmanns.adventofcode2018.day09;

import java.util.Arrays;

/**
 * The difference between the two tasks of this day is only the input (100 times larger).
 * 
 * @author B. Hegmanns
 */
public class SolutionAppDay09 {

	public static void main(String[] args) {
		Input09Info info = Input09Task02.getInput();
		long start = System.currentTimeMillis();
		
		CircleElement circleElement = CircleElement.createWithoutAncestorAndSuccessor(0);
		long[] playerScores = new long[info.getCountPlayer()];
		
		for (int i = 1 ; i <= info.getLastMarbleWorth() ; i++) {
			if (i%23 == 0) {
				circleElement = circleElement.rotateLeft(7);
				playerScores[i%info.getCountPlayer()] += i + circleElement.getCurrent();
			}else {
				circleElement = circleElement.insert(i);
			}
		}
		long winnerScore = Arrays.stream(playerScores).max().getAsLong();
		System.out.println("Winnerscore = " + winnerScore);
//		System.out.println("Players: ");
//		for (int i = 0 ; i < playerScores.length ; i++) {
//			System.out.println("" + (i+1) + " : " + playerScores[i]);
//		}
		if (info.getExpectedResult() != null) {
			System.out.println("Richtiges Ergebnis: " + (winnerScore==info.getExpectedResult() ? "JA" : "NEIN!, erwartet: " + info.getExpectedResult() + " erhalten: " + winnerScore));
		}
		System.out.println("Dauer: " + (System.currentTimeMillis() - start) + " Millisekunden");
	}
	

}
