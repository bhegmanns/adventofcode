package de.hegmanns.adventofcode2018.day11;

import java.util.ArrayList;
import java.util.List;

public class SolutionAppDay11Task02 {

	public static void main(String[] args) {
		Input11Info input11Info = Input11Task02.getInt();

		int gridSerialNumber = input11Info.getGridSerialNumber();
		List<List<Integer>> matrix = createPowerLevelMatrix(input11Info.getSquare(), gridSerialNumber);

		int maxTotalPower = 0;
		int xForMaxTotalPower = 0;
		int yForMaxTotalPower = 0;
		int squareForMaxTotalPower = 0;
		for (int x = 0; x < 300; x++) {
			for (int y = 0; y < 300; y++) {
				for (int square = input11Info.getMinSquareResult(); square <= input11Info
						.getMaxSquareResult(); square++) {
					Integer calculatedTotalPower = calculateTotalPower(x, y, square, matrix);
					if (calculatedTotalPower != null && calculatedTotalPower > maxTotalPower) {
						maxTotalPower = calculatedTotalPower;
						xForMaxTotalPower = x;
						yForMaxTotalPower = y;
						squareForMaxTotalPower = square;
					}
				}
			}
			System.out.println("Line " + (x+1) + " calculated");
		}
		xForMaxTotalPower++;
		yForMaxTotalPower++;
		System.out.println("Max TotalPower = " + maxTotalPower);
		System.out.println(" its x = " + xForMaxTotalPower);
		System.out.println(" its y = " + yForMaxTotalPower);
		System.out.println(" its square = " + squareForMaxTotalPower);
		System.out.println(" ResultString: " + xForMaxTotalPower + "," + yForMaxTotalPower + ","+ squareForMaxTotalPower);
		System.out.println("Correct?"
				+ (("" + xForMaxTotalPower + "," + yForMaxTotalPower + "," + squareForMaxTotalPower).equals(input11Info.getExpectedCoordinate())
						? "YES"
						: "NO, expected: " + input11Info.getExpectedCoordinate()));
	}

	protected static List<List<Integer>> createPowerLevelMatrix(int square, int gridSerialNumber) {
		List<List<Integer>> matrix = new ArrayList<>();
		for (int x = 0; x < square; x++) {
			List<Integer> row = new ArrayList<>();
			for (int y = 0; y < square; y++) {
				row.add(calculatePowerlevel(x + 1, y + 1, gridSerialNumber));
			}
			matrix.add(row);
		}
		return matrix;
	}

	protected static Integer calculateTotalPower(int xLeft, int yTop, int square, List<List<Integer>> matrix) {
		Integer result = 0;
		try {
			List<List<Integer>> lines = new ArrayList<>();
			for (int i = 0; i < square; i++) {
				lines.add(matrix.get(xLeft + i));
			}

			for (int i = 0; i < square; i++) {
				for (int line = 0; line < square; line++) {
					result += lines.get(line).get(yTop + i);
				}
			}

		} catch (Exception e) {
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

		return Integer.parseInt("" + ("" + value).charAt(("" + value).length() - 3));
	}
}

class Day11Result{
	Integer maxTotalPower = 0;
	int xForMaxTotalPower = 0;
	int yForMaxTotalPower = 0;
	int squareForMaxTotalPower = 0;
	
	public Day11Result() {}
	
	public void compareTo(Day11Result currentResult) {
		if (currentResult.maxTotalPower != null && currentResult.maxTotalPower > maxTotalPower) {
			maxTotalPower = currentResult.maxTotalPower;
			xForMaxTotalPower = currentResult.xForMaxTotalPower;
			yForMaxTotalPower = currentResult.yForMaxTotalPower;
			squareForMaxTotalPower = currentResult.squareForMaxTotalPower;
		}
	}
}
