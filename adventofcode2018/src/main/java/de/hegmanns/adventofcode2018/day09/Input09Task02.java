package de.hegmanns.adventofcode2018.day09;

public class Input09Task02 {

	public static Input09Info getInput() {
		Input09Info info = Input09Task01.getInput();
		
		return new Input09Info(info.getCountPlayer(), info.getLastMarbleWorth() * 100);
	}
}
