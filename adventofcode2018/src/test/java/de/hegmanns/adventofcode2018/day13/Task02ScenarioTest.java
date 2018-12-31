package de.hegmanns.adventofcode2018.day13;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import de.hegmanns.adventofcode2018.common.input.DefaultStringArrayInput;
import de.hegmanns.adventofcode2018.common.input.StringArrayInput;
import de.hegmanns.adventofcode2018.common.streamprovider.PackageFileInputStreamProvider;

public class Task02ScenarioTest {

	StringArrayInput stringArrayInput = null;
	
	@Test
	public void solvingExample() {
		stringArrayInput = new DefaultStringArrayInput(PackageFileInputStreamProvider.get(Task01ScenarioTest.class, "input13testtask02.dat"));
		
		SolutionDay13Task02 task02 = new SolutionDay13Task02(stringArrayInput);
		
		assertThat(task02.solve(), is("6,4"));
	}
	
	@Test
	public void solvingTask() {
		stringArrayInput = new DefaultStringArrayInput(PackageFileInputStreamProvider.get(Task01ScenarioTest.class, "input13.dat"));
		
		SolutionDay13Task02 task02 = new SolutionDay13Task02(stringArrayInput);
		
		assertThat(task02.solve(), is("50,21"));
	}
}
