package de.hegmanns.adventofcode2018.day12;

public interface PlantStrategy {

	public boolean doMatch(boolean secondLeft, boolean firstLeft, boolean from, boolean firstRight, boolean secondRight);
	public Boolean resultFrom(boolean secondLeft, boolean firstLeft, boolean from, boolean firstRight, boolean secondRight);
	
}
