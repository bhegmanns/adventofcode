package de.hegmanns.adventofcode2018.day13;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.hegmanns.adventofcode2018.common.input.DefaultStringArrayInput;
import de.hegmanns.adventofcode2018.common.streamprovider.PackageFileInputStreamProvider;

@RunWith(JUnitPlatform.class)
public class CompleteSolutionTest {

	@Test
	public void solutionForTest() {
		SolutionDay13Task01 solution = new SolutionDay13Task01(new DefaultStringArrayInput(PackageFileInputStreamProvider.get(SolutionDay13Task01.class, "input13.dat")));
		assertThat(solution.solve(), notNullValue());
	}
}
