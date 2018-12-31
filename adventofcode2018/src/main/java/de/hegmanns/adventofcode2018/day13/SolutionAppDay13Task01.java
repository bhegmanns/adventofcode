package de.hegmanns.adventofcode2018.day13;

import de.hegmanns.adventofcode2018.common.input.DefaultStringArrayInput;
import de.hegmanns.adventofcode2018.common.streamprovider.PackageFileInputStreamProvider;

public class SolutionAppDay13Task01 {
	
	

	/**
	 * Fails
	 * 112,69
	 * @param args
	 */
	public static void main(String[] args) {
		SolutionDay13Task01 solution = new SolutionDay13Task01(new DefaultStringArrayInput(PackageFileInputStreamProvider.get(SolutionAppDay13Task01.class, "input13.dat")));
		System.out.println("Result = >" + solution.solve() + "<");
	}
}
