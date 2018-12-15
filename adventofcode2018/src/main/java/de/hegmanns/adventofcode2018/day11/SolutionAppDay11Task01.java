package de.hegmanns.adventofcode2018.day11;

import java.util.ArrayList;
import java.util.List;

public class SolutionAppDay11Task01 {

	public static void main(String[] args) {
		Input11Info input11Info = Input11Task01.getInt();
		
		int gridSerialNumber = input11Info.getGridSerialNumber();
		List<List<Integer>> matrix = createPowerLevelMatrix(gridSerialNumber);
		
		int maxTotalPower = 0;
		int xForMaxTotalPower = 0;
		int yForMaxTotalPower = 0;
		
		for (int x = 0 ; x < 300 ; x++) {
			for (int y = 0 ; y < 300 ; y++) {
				Integer calculatedTotalPower = calculateTotalPower(x, y, matrix);
				if (calculatedTotalPower != null && calculatedTotalPower > maxTotalPower) {
					maxTotalPower = calculatedTotalPower;
					xForMaxTotalPower = x;
					yForMaxTotalPower = y;
				}
			}
		}
		xForMaxTotalPower++;
		yForMaxTotalPower++;
		System.out.println("Max TotalPower = " + maxTotalPower);
		System.out.println(" its x = " + xForMaxTotalPower);
		System.out.println(" its y = " + yForMaxTotalPower);
		System.out.println(" ResultString: " +xForMaxTotalPower + "," + yForMaxTotalPower);
		System.out.println("Correct?" + (("" + xForMaxTotalPower + "," + yForMaxTotalPower).equals(input11Info.getExpectedCoordinate()) ? "YES": "NO, expected: " + input11Info.getExpectedCoordinate()));
	}
	
	protected static List<List<Integer>> createPowerLevelMatrix(int gridSerialNumber){
		List<List<Integer>> matrix = new ArrayList<>();
		for (int x = 0 ; x < 300 ; x++) {
			List<Integer> row = new ArrayList<>();
			for (int y = 0 ; y < 300 ; y++) {
				row.add(calculatePowerlevel(x+1, y+1, gridSerialNumber));
			}
			matrix.add(row);
		}
		return matrix;
	}
	
	protected static Integer calculateTotalPower(int xLeft, int yTop, List<List<Integer>> matrix) {
		Integer result = 0;
		try {
			List<Integer> line1 = matrix.get(xLeft);
			List<Integer> line2 = matrix.get(xLeft + 1);
			List<Integer> line3 = matrix.get(xLeft + 2);
			
			for (int i = 0 ; i < 3 ; i++) {
				result += line1.get(yTop + i) + line2.get(yTop + i) + line3.get(yTop+i);
			}
			
		}catch(Exception e) {
			return null;
		}
		return result;
	}
	
	protected static int calculatePowerlevel(int x, int y, int gridSerialNumber) {
		long rackId = x + 10L;
		long startingPowerlevel = rackId * y;
		long increasingPowerLevel = startingPowerlevel + gridSerialNumber;
		long product = rackId * increasingPowerLevel;
		int hundredsDigids = gatherHundredsDigit(product);
		
		return hundredsDigids - 5;
	}
	
	private static int gatherHundredsDigit(long value) {
		if (value < 100) {
			return 0;
		}
		
		return Integer.parseInt("" + ("" + value).charAt((""+value).length() - 3));
	}
}
