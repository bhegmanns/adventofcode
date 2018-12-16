package de.hegmanns.adventofcode2018.day12;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * My solution:
 * Expand the first solution to the big count of generation 50000000000 will take too much time ... :( .
 * 
 * The difference between two generations count of plants will go to a limit. After arrive this limit, the only task is to
 * multiplicate this constant difference with the pending count of generations.
 * 
 * @author B. Hegmanns
 *
 */
public class SolutionAppDay12Task02 {
	private static final long EXPECTED_COUNT_GENERATIONS = 50000000000L;

	public static void main(String[] args) {
		String[] input = Input12.getStrings();
		
		List<String> lines = new ArrayList<>();
		List<PlantStrategy> strategies = new ArrayList<>();
		int firstValue = 0;
		String formerLine = getInitialStateline(input[0]);
		System.out.println(formerLine);
		System.out.println();
		int firstSharp = formerLine.indexOf("#");
		int lastSharp  = formerLine.lastIndexOf("#");
		if (lastSharp > formerLine.length() - 4) {
			formerLine = formerLine + StringUtils.repeat(".", 4);
		}
		if (firstSharp<4) {
			formerLine = StringUtils.repeat(".", 4-firstSharp) + formerLine;
			firstValue = firstSharp-4;
		}
		System.out.println(formerLine);
		lines.add(formerLine);
		
		for (int line = 2 ; line < input.length ; line++) {
			strategies.add(getStrategy(input[line]));
		}
		
		
		int countPlants = countPlants(formerLine);
		long lastPlantLineValue = getPlantLineValue(formerLine, firstValue);
		long lastDiffPlantLineValue = 0;
		int countEqualPlantLineValue = 0;
		long generation = 0;
//		lastPlantLineValue = 3750000001113L;
		System.out.println("count plants >>> " + countPlants);
		
		while(countEqualPlantLineValue<=100){
			generation++;
			String newLine = "";
			byte[] bytes = formerLine.getBytes();
			
			for (int i = 0 ; i < formerLine.length() ; i++) {
				
				PlantSpecification specification = createSpecifiationFrom(bytes, i);
				Boolean result = null;
				for (PlantStrategy strategy : strategies) {
					result = strategy.resultFrom(specification.isSecondLeft(), specification.isFirstLeft(), specification.isFrom(), specification.isFirstRight(), specification.isSecondRight());
					if (result != null) {
						break;
					}
				}
				if (result == null) {
					System.out.println("No Strategy found for " + specification + " take false");
					result = false;
				}
				newLine += result?"#":"."; 
			}
			
			firstSharp = newLine.indexOf("#");
			lastSharp  = formerLine.lastIndexOf("#");
			if (lastSharp > newLine.length() - 4) {
				newLine = newLine + StringUtils.repeat(".", 4);
			}
			if (firstSharp<4) {
				newLine = StringUtils.repeat(".", 4-firstSharp) + newLine;
				firstValue += firstSharp-4;
			}
			
			long currentPlantLineValue = getPlantLineValue(newLine, firstValue);
			formerLine = newLine;
			long currentDiffPlantLineValue = lastPlantLineValue - currentPlantLineValue;
			System.out.println("run " + generation + " : current value last line = " + currentPlantLineValue + ", diff = " + currentDiffPlantLineValue);
			if (currentDiffPlantLineValue != lastDiffPlantLineValue) {
				lastDiffPlantLineValue = currentDiffPlantLineValue;
				lastPlantLineValue = currentPlantLineValue;
				countEqualPlantLineValue = 0;
			}else {
				lastPlantLineValue = currentPlantLineValue;
				countEqualPlantLineValue++;
			}
		};
		System.out.println();
		long pendingGenerations = EXPECTED_COUNT_GENERATIONS - generation;
		lastPlantLineValue += pendingGenerations * Math.abs(lastDiffPlantLineValue);
		System.out.println("estimated value lastLine " + lastPlantLineValue);
		
	}
	
	private static int countPlants(String line) {
		return line.replace(".", "").length();
	}
	
	private static long getPlantLineValue(String line, int startValue) {
		long result = 0L;
		for (int i = 0 ; i < line.length() ; i++) {
			result += line.charAt(i)=='.'?0:(startValue+i);
		}
		return result;
	}
	
	private static PlantSpecification createSpecifiationFrom(byte[] line, int index) {
		return new PlantSpecification(gatherValueFromIndex(line, index - 2), 
				  gatherValueFromIndex(line, index - 1),
				  gatherValueFromIndex(line, index),
				  gatherValueFromIndex(line, index + 1),
				  gatherValueFromIndex(line, index + 2));
	}
	
	private static boolean gatherValueFromIndex(byte[] line, int index) {
		if (index >=0 && index < line.length) {
			return line[index] == '#';
		}else {
			return false;
		}
	}
	private static PlantSpecification createSpecifiationFrom(String line, int index) {
		return new PlantSpecification(gatherValueFromIndex(line, index - 2), 
									  gatherValueFromIndex(line, index - 1),
									  gatherValueFromIndex(line, index),
									  gatherValueFromIndex(line, index + 1),
									  gatherValueFromIndex(line, index + 2));
	}
	
	private static boolean gatherValueFromIndex(String line, int index) {
		if (index >=0 && index < line.length()) {
			return line.charAt(index) == '#';
		}else {
			return false;
		}
	}
	
	private static String getInitialStateline(String line) {
		int indexOfSharp = line.indexOf('#');
		int lastIndexOfSharp = line.lastIndexOf('#');
		return line.substring(indexOfSharp, lastIndexOfSharp + 1);
	}
	
	private static PlantStrategy getStrategy(String line) {
		String specificationString = line.substring(0, 5);
		String resultString        = line.substring(9);
		boolean result = resultString.equals(".") ? false:true;
		boolean secondLeft = specificationString.charAt(0)=='#' ? true:false;
		boolean firstLeft = specificationString.charAt(1)=='#' ? true:false;
		boolean from = specificationString.charAt(2)=='#' ? true:false;
		boolean firstRight = specificationString.charAt(3)=='#' ? true:false;
		boolean secondRight = specificationString.charAt(4)=='#' ? true:false;
		
		return new DefaultPlantStrategy(secondLeft, firstLeft, from, firstRight, secondRight, result);
	}
}
