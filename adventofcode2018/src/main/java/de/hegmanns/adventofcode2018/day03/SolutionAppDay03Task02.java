package de.hegmanns.adventofcode2018.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolutionAppDay03Task02 {

	public static void main(String[] args) {
		String[] strings = Input03.getStrings();
		List<Rectangle> rectangles = new ArrayList<>();
		Fabric fabric = new Fabric();
		for (String string : strings) {
			Rectangle rectangle = new Rectangle(string);
			
			rectangle.place(fabric);
			rectangles.add(rectangle);
		}
		
		Optional<Integer> rectangleNumber = Optional.empty();
		for (Rectangle rectangle : rectangles) {
			if (rectangle.isFullPlaced(fabric)) {
				rectangleNumber = Optional.ofNullable(rectangle.getNumber());
				System.out.println("Found: " + rectangleNumber.get());
			}
		}
		rectangleNumber.ifPresent((i) -> System.out.println("Rectangle = " + i));
	}

}
