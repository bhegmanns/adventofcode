package de.hegmanns.adventofcode2018.day11;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class PowerLevelTest {

	public static Stream<PowerLevelInfo> getTestCases(){
		return Stream.of(
				PowerLevelInfo.create(122, 79, 57, -5),
				PowerLevelInfo.create(217, 196, 39, 0),
				PowerLevelInfo.create(101, 153, 71, 4)
				);
	}
	
	@ParameterizedTest
	@MethodSource("getTestCases")
	public void testCase(PowerLevelInfo powerLevelInfo) {
		int calculatedPowerLevel = SolutionAppDay11Task02.calculatePowerlevel(powerLevelInfo.x, powerLevelInfo.y, powerLevelInfo.serialNumber);
		assertThat(calculatedPowerLevel, is(powerLevelInfo.expectedPowerLevel));
	}

	@Test
	public void meinTest() {
		
	}
}

class PowerLevelInfo{
	int x;
	int y;
	int serialNumber;
	int expectedPowerLevel;
	
	private PowerLevelInfo(int x, int y, int serialNumber, int expectedPowerLevel) {
		super();
		this.x = x;
		this.y = y;
		this.serialNumber = serialNumber;
		this.expectedPowerLevel = expectedPowerLevel;
	}

	public static PowerLevelInfo create(int x, int y, int serialNumber, int expectedPowerLevel) {
		return new PowerLevelInfo(x, y, serialNumber, expectedPowerLevel);
	}
}
