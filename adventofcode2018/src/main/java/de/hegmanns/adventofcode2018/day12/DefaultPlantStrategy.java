package de.hegmanns.adventofcode2018.day12;

public class DefaultPlantStrategy implements PlantStrategy{

	private boolean from;
	private boolean firstLeft;
	private boolean secondLeft;
	private boolean firstRight;
	private boolean secondRight;
	private boolean results;
	
	public DefaultPlantStrategy(boolean secondLeft, boolean firstLeft, boolean from, boolean firstRight, boolean secondRight, boolean results) {
		this.secondLeft = secondLeft;
		this.firstLeft = firstLeft;
		this.firstRight = firstRight;
		this.secondRight = secondRight;
		this.from = from;
		this.results = results;
	}

	@Override
	public boolean doMatch(boolean secondLeft, boolean firstLeft, boolean from, boolean firstRight,
			boolean secondRight) {
		return secondLeft==this.secondLeft && firstLeft==this.firstLeft && from==this.from && firstRight==this.firstRight && secondRight==this.secondRight;
	}

	@Override
	public Boolean resultFrom(boolean secondLeft, boolean firstLeft, boolean from, boolean firstRight,
			boolean secondRight) {
		if (!doMatch(secondLeft, firstLeft, from, firstRight, secondRight)) {
			return null;
		}else {
			return results;
		}
	}
	
	
}
