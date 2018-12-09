package de.hegmanns.adventofcode2018.day03;

public class SolutionAppDay03Task01 {

	public static void main(String[] args) {
		String[] strings = Input03.getStrings();
		Fabric fabric = new Fabric();
		for (String string : strings) {
			Rectangle rectangle = new Rectangle(string);
			
			rectangle.place(fabric);
		}
		System.out.println("Solution: " + fabric.calculateMultiOccupancy(2L));
	}
	
	
}
