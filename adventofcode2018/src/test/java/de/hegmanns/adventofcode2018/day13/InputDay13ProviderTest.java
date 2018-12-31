package de.hegmanns.adventofcode2018.day13;

import static de.hegmanns.adventofcode2018.day13.PositionToTrackMapMatcher.hasIntersection;
import static de.hegmanns.adventofcode2018.day13.PositionToTrackMapMatcher.hasOnlyLeftRight;
import static de.hegmanns.adventofcode2018.day13.PositionToTrackMapMatcher.hasOnlyTopDown;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.hegmanns.adventofcode2018.common.input.DefaultStringArrayInput;
import de.hegmanns.adventofcode2018.common.streamprovider.InputStreamProvider;

@RunWith(JUnitPlatform.class)
public class InputDay13ProviderTest {
	
	private InputDay13Provider createWith(String oneLine) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(oneLine.getBytes());
		InputDay13Provider provider = new InputDay13Provider(new DefaultStringArrayInput(InputStreamProvider.convert(inputStream)));
		
		return provider;
	}
	private InputDay13Provider createWith(String ...lines) {
		String completeInput = Stream.of(lines).collect(Collectors.joining("\r\n"));
		return createWith(completeInput);
	}

	@Test
	public void emptyFile() {
		InputDay13Provider provider = createWith("");
		
		assertThat(provider.getData(), notNullValue());
	}
	
	@Test
	public void emptyFileResultsInEmptyCartsAndTracks() {
		InputDay13Provider provider = createWith("");
		
		Assertions.assertAll(
				() -> assertThat(provider.getData().getCarts(), notNullValue())
				, () -> assertThat(provider.getData().getTracks(), notNullValue())
				, () -> assertThat(provider.getData().getPositionToTrackMap(), notNullValue())
				, () -> assertThat(provider.getData().getCarts(), hasSize(0))
				, () -> assertThat(provider.getData().getTracks(), hasSize(0))
				, () -> assertThat(provider.getData().getPositionToTrackMap().size(), is(0))
				);
	}
	
	@Test
	public void oneVertical() {
		InputDay13Provider provider = createWith("|");
		InputDay13 data = provider.getData();
		Assertions.assertAll(
				() -> assertThat("data", data, notNullValue())
				,() -> assertThat("carts", data.getCarts(), notNullValue())
				, () -> assertThat("tracks", data.getTracks(), notNullValue())
				, () -> assertThat("map", data.getPositionToTrackMap(), notNullValue())
				, () -> assertThat("carts", data.getCarts(), hasSize(0))
				, () -> assertThat("tracks", data.getTracks(), hasSize(1))
				, () -> assertThat("size of map", data.getPositionToTrackMap().size(), is(1))
				);
	}
	
	@Test
	public void oneCart() {
		InputDay13Provider provider = createWith("-<-");
		InputDay13 data = provider.getData();
		
		Assertions.assertAll(
				() -> assertThat("data", data, notNullValue())
				,() -> assertThat("carts", data.getCarts(), notNullValue())
				, () -> assertThat("tracks", data.getTracks(), notNullValue())
				, () -> assertThat("map", data.getPositionToTrackMap(), notNullValue())
				, () -> assertThat("carts", data.getCarts(), hasSize(1))
				, () -> assertThat("tracks", data.getTracks(), hasSize(3))
				, () -> assertThat("size of map", data.getPositionToTrackMap().size(), is(3))
				);
	}
	
	@Test
	public void oneHorizontal() {
		InputDay13Provider provider = createWith("-");
		InputDay13 data = provider.getData();
		
		Assertions.assertAll(
				() -> assertThat("data", data, notNullValue())
				,() -> assertThat("carts", data.getCarts(), notNullValue())
				, () -> assertThat("tracks", data.getTracks(), notNullValue())
				, () -> assertThat("map", data.getPositionToTrackMap(), notNullValue())
				, () -> assertThat("carts", data.getCarts(), hasSize(0))
				, () -> assertThat("tracks", data.getTracks(), hasSize(1))
				, () -> assertThat("size of map", data.getPositionToTrackMap().size(), is(1))
				);
	}
	
	/**
	 * Situation:
	 *  |
	 * -+-
	 *  |
	 */
	@Test
	public void withIntersection() {
		InputDay13Provider provider = createWith(" |", "-+-", " |");
		InputDay13 data = provider.getData();
		
		Assertions.assertAll(
				() -> assertThat("data", data, notNullValue())
				,() -> assertThat("carts", data.getCarts(), notNullValue())
				, () -> assertThat("tracks", data.getTracks(), notNullValue())
				, () -> assertThat("map", data.getPositionToTrackMap(), notNullValue())
				, () -> assertThat("carts", data.getCarts(), hasSize(0))
				, () -> assertThat("tracks", data.getTracks(), hasSize(5))
				, () -> assertThat("size of map", data.getPositionToTrackMap().size(), is(5))
				, () -> assertThat("insersection localized", data.getPositionToTrackMap().get(new Position(1,1)).isIntersection(), is(true))
				);
	}
	
	/**
	 * Situation:
	 *  |
	 * -<-
	 *  |
	 */
	@Test
	public void withCartToLeftInIntersection() {
		InputDay13Provider provider = createWith(" |", "-<-", " |");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		assertThat(positionToTrackMap,  PositionToTrackMapMatcher.hasIntersection(1, 1));
	}
	
	/**
	 * Situation:
	 *  |
	 * ->-
	 *  |
	 */
	@Test
	public void withCartToRightInIntersection() {
		InputDay13Provider provider = createWith(" |", "->-", " |");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		assertThat(positionToTrackMap,  PositionToTrackMapMatcher.hasIntersection(1, 1));
	}
	
	/**
	 * Situation:
	 *  |
	 * -^-
	 *  |
	 */
	@Test
	public void withCartToUpInIntersection() {
		InputDay13Provider provider = createWith(" |", "-^-", " |");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		assertThat(positionToTrackMap,  PositionToTrackMapMatcher.hasIntersection(1, 1));
	}
	
	/**
	 * Situation:
	 *  |
	 * -v-
	 *  |
	 */
	@Test
	public void withCartToBottomInIntersection() {
		InputDay13Provider provider = createWith(" |", "-v-", " |");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		assertThat(positionToTrackMap,  PositionToTrackMapMatcher.hasIntersection(1, 1));
	}
	
	/**
	 * situation:
	 * 
	 * /--
	 * |
	 * |
	 */
	@Test
	public void fromButtomToRight() {
		InputDay13Provider provider = createWith("/--", "|", "|");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 0))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 0, MovementDirection.RIGHT, MovementDirection.DOWN))
		);
	}
	
	/**
	 * situation:
	 * 
	 * /--
	 * ^
	 * |
	 */
	@Test
	public void fromButtomToRightWithCartToTop() {
		InputDay13Provider provider = createWith("/--", "^", "|");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 0))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 0, MovementDirection.RIGHT, MovementDirection.DOWN))
		);
	}
	
	/**
	 * situation:
	 * 
	 * /--
	 * ^
	 * |
	 */
	@Test
	public void fromButtomToRightWithCartToRight() {
		InputDay13Provider provider = createWith("/--", "^", "|");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 0))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 0, MovementDirection.RIGHT, MovementDirection.DOWN))
		);
	}
	
	/**
	 * situation:
	 * 
	 * |
	 * |
	 * \--
	 */
	@Test
	public void fromTopToRight() {
		InputDay13Provider provider = createWith("|", "|", "\\--");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 2))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 2, MovementDirection.TOP, MovementDirection.RIGHT))
		);
	}
	
	/**
	 * situation:
	 * 
	 * |
	 * v
	 * \--
	 */
	@Test
	public void fromTopToRightWithCartDown() {
		InputDay13Provider provider = createWith("|", "v", "\\--");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 2))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 2, MovementDirection.TOP, MovementDirection.RIGHT))
		);
	}
	
	/**
	 * situation:
	 * 
	 * ^
	 * |
	 * \--
	 */
	@Test
	public void fromTopToRightWithCartUp() {
		InputDay13Provider provider = createWith("^", "|", "\\--");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 2))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 2, MovementDirection.TOP, MovementDirection.RIGHT))
		);
	}
	
	/**
	 * situation:
	 * 
	 * |
	 * |
	 * \<-
	 */
	@Test
	public void fromTopToRightWithCartLeft() {
		InputDay13Provider provider = createWith("|", "|", "\\<-");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 2))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 2, MovementDirection.TOP, MovementDirection.RIGHT))
		);
	}
	
	/**
	 * situation:
	 * 
	 * |
	 * |
	 * \->
	 */
	@Test
	public void fromTopToRightWithCartRight() {
		InputDay13Provider provider = createWith("|", "|", "\\->");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		Assertions.assertAll(
		  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 1))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyLeftRight(1, 2))
		, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(0, 2, MovementDirection.TOP, MovementDirection.RIGHT))
		);
	}
	
	/**
	 *  0123
	 *0 -+-\
	 *1 +<-+-
	 *2 ||/+-
	 *3 ||||
	 *4 ||||
	 */
	@Test
	public void scenarioWithIntersectionsAndCart() {
		InputDay13Provider provider = createWith("-+-\\", "+<-+-", "||/+-", "||||", "||||");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		
		Assertions.assertAll(
				  () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 2))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(0, 3))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(1, 2))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(1, 3))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(2, 3))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasOnlyTopDown(3, 3))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(2, 2, MovementDirection.RIGHT, MovementDirection.DOWN))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasMovement(3, 0, MovementDirection.LEFT, MovementDirection.DOWN))
				, () -> assertThat("intersection with cart to left", positionToTrackMap, PositionToTrackMapMatcher.hasIntersection(1, 1))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasIntersection(3, 2))
				, () -> assertThat(positionToTrackMap, PositionToTrackMapMatcher.hasIntersection(3, 2))
				);
	}

	
	
	/**
	 *            1111
	 *  01234567890123
	 *0 /->-\        
     *1 |   |  /----\
     *2 | /-+--+-\  |
     *3 | | |  | v  |
     *4 \-+-/  \-+--/
     *5   \------/  
	 */
	@Test
	public void describedExampleScenarioInTask() {
		InputDay13Provider provider = createWith("/->-\\", "|   |  /----\\", "| /-+--+-\\  |", "| | |  | v  |", "\\-+-/  \\-+--/", "  \\------/");
		InputDay13 data = provider.getData();
		
		Map<Position, Track> positionToTrackMap = data.getPositionToTrackMap();
		
		Assertions.assertAll(
				() -> assertThat(data.getCarts(), hasSize(2))
				,() -> assertThat(positionToTrackMap, hasIntersection(4, 2))
				,() -> assertThat(positionToTrackMap, hasIntersection(7, 2))
				,() -> assertThat(positionToTrackMap, hasIntersection(2, 4))
				,() -> assertThat(positionToTrackMap, hasIntersection(9, 4))
				
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(1, 0))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(2, 0))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(3, 0))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(8, 1))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(9, 1))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(10, 1))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(11, 1))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(3, 2))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(6, 2))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(5, 2))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(8, 2))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(1, 4))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(3, 4))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(8, 4))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(10, 4))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(11, 4))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(3, 5))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(4, 5))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(5, 5))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(6, 5))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(7, 5))
				,() -> assertThat(positionToTrackMap, hasOnlyLeftRight(8, 5))
				
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(0, 1))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(0, 2))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(0, 3))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(2, 3))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(4, 1))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(7, 3))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(9, 3))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(12, 2))
				,() -> assertThat(positionToTrackMap, hasOnlyTopDown(12, 3))
		);
	}
}
