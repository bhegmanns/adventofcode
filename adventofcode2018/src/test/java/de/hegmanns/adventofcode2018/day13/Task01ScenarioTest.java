package de.hegmanns.adventofcode2018.day13;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.hegmanns.adventofcode2018.common.input.DefaultStringArrayInput;
import de.hegmanns.adventofcode2018.common.input.StringArrayInput;
import de.hegmanns.adventofcode2018.common.streamprovider.PackageFileInputStreamProvider;

public class Task01ScenarioTest {
	StringArrayInput stringArrayInput = null;
	InputDay13Provider provider = null;
	InputDay13 data = null;
	
	@BeforeEach
	public void beforeAnyTest() {
		stringArrayInput = new DefaultStringArrayInput(PackageFileInputStreamProvider.get(Task01ScenarioTest.class, "input13.dat"));
		provider = new InputDay13Provider(stringArrayInput);
		data = provider.getData();
	}
	
	public static Stream<Position> allCartPositions(){
		return Stream.of(
				 position(125,0)
				,position(77,4)
				,position(95,5)
				,position(128,11)
				,position(80,13)
				,position(70, 25)
				,position(42, 27)
				,position(81, 29)
				,position(48, 30)
				,position(84, 30)
				,position(17, 65)
				,position(87, 65)
				,position(20, 97)
				,position(78, 104)
				,position(27, 107)
				,position(97, 124)
				,position(142, 138)
		);
	}
	
	@Test
	public void solvingTask01() {
		SolutionDay13Task01 task01 = new SolutionDay13Task01(stringArrayInput);
		assertThat(task01.solve(), is("118,112"));
	}
	
	@ParameterizedTest
	@MethodSource("allCartPositions")
	public void allCartsRead(Position position) {
		Assertions.assertTrue(data.getCarts().stream().filter((c) -> c.getCurrentPosition().equals(position)).findFirst().isPresent(), "Cart at position " + position);
	}
	
	private static Position position(int xCoord, int yCoord) {
		return new Position(xCoord, yCoord);
	}
	
}
