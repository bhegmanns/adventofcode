package de.hegmanns.adventofcode2018.day13;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Set of commong hamcrest matcher for {@link Map<Position,Track>>}.
 * 
 * @author B. Hegmanns
 *
 */
public class PositionToTrackMapMatcher {

	public static Matcher<Map<Position, Track>> hasMovement(int xCoord, int yCoord, MovementDirection... directions) {
		return new TypeSafeDiagnosingMatcher<Map<Position, Track>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("Track at position " + xCoord + "," + yCoord
						+ " should have movement directions " + Arrays.asList(directions));
			}

			@Override
			protected boolean matchesSafely(Map<Position, Track> item, Description mismatchDescription) {
				Track track = item.get(new Position(xCoord, yCoord));

				if (track == null) {
					mismatchDescription.appendText("no track at this position exists");
					return false;
				}

				Set<MovementDirection> movementDirectios = track.getMovementDirections().keySet();

				boolean containsAll = movementDirectios.containsAll(Arrays.asList(directions));

				if (!containsAll) {
					mismatchDescription.appendText("but the following movement directions are missed: [");
					mismatchDescription
							.appendText(Arrays.asList(directions).stream().filter(movementDirectios::contains)
									.map(Object::toString).collect(Collectors.joining(",")));
					mismatchDescription.appendText("]");
				}

				return containsAll;
			}
		};
	}

	public static Matcher<Map<Position, Track>> hasIntersection(int xCoord, int yCoord) {
		return new TypeSafeDiagnosingMatcher<Map<Position, Track>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("Track at position " + xCoord + "," + yCoord + " should be an intersection");
			}

			@Override
			protected boolean matchesSafely(Map<Position, Track> item, Description mismatchDescription) {
				Track track = item.get(new Position(xCoord, yCoord));

				if (track == null) {
					mismatchDescription.appendText("no track at this position exists");
					return false;
				}

				Set<MovementDirection> movementDirectios = track.getMovementDirections().keySet();

				boolean containsAll = movementDirectios.containsAll(Arrays.asList(MovementDirection.LEFT,
						MovementDirection.RIGHT, MovementDirection.DOWN, MovementDirection.TOP));

				if (!containsAll) {
					mismatchDescription.appendText("but the following movement directions are missed: [");
					mismatchDescription.appendText(
							Arrays.asList(MovementDirection.values()).stream().filter(movementDirectios::contains)
									.map(Object::toString).collect(Collectors.joining(",")));
					mismatchDescription.appendText("]");
				}

				return containsAll;
			}
		};
	}

	public static Matcher<Map<Position, Track>> hasOnlyLeftRight(int xCoord, int yCoord) {
		return new TypeSafeDiagnosingMatcher<Map<Position, Track>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("Track at position " + xCoord + "," + yCoord
						+ " should only contains LEFT and RIGHT movement direction");
			}

			@Override
			protected boolean matchesSafely(Map<Position, Track> item, Description mismatchDescription) {
				Map<Position, Track> map = (Map<Position, Track>) item;
				Track track = map.get(new Position(xCoord, yCoord));
				if (track == null) {
					mismatchDescription.appendText("no track at this position exists");
					return false;
				}
				Map<MovementDirection, Track> movementDirections = track.getMovementDirections();

				Set<MovementDirection> keySet = movementDirections.keySet();
				if (movementDirections.size() != 2) {
					mismatchDescription.appendText("not 2 movementDirections but " + keySet);
					return false;
				}
				boolean containsAll = keySet
						.containsAll(Arrays.asList(MovementDirection.LEFT, MovementDirection.RIGHT));
				if (!containsAll) {
					mismatchDescription.appendText("but the following movement directions are missed: [");
					mismatchDescription.appendText(Arrays.asList(MovementDirection.LEFT, MovementDirection.RIGHT)
							.stream().filter(keySet::contains).map(Object::toString).collect(Collectors.joining(",")));
					mismatchDescription.appendText("]");
				}

				return containsAll;
			}
		};
	}

	public static Matcher<Map<Position, Track>> noTrack(int xCoord, int yCoord) {
		return new TypeSafeDiagnosingMatcher<Map<Position, Track>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("no Track at position " + xCoord + "," + yCoord);
			}

			@Override
			protected boolean matchesSafely(Map<Position, Track> item, Description mismatchDescription) {
				Map<Position, Track> map = (Map<Position, Track>) item;
				Track track = map.get(new Position(xCoord, yCoord));
				if (track == null) {
					return true;
				}

				mismatchDescription.appendText("but the following track is at that position: " + track);

				return false;
			}
		};
	}

	public static Matcher<Map<Position, Track>> hasOnlyTopDown(int xCoord, int yCoord) {
		return new TypeSafeDiagnosingMatcher<Map<Position, Track>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("Track at position " + xCoord + "," + yCoord
						+ " should only contains LEFT and RIGHT movement direction");
			}

			@Override
			protected boolean matchesSafely(Map<Position, Track> item, Description mismatchDescription) {
				Map<Position, Track> map = (Map<Position, Track>) item;
				Track track = map.get(new Position(xCoord, yCoord));
				if (track == null) {
					mismatchDescription.appendText("no track at this position exists");
					return false;
				}
				Map<MovementDirection, Track> movementDirections = track.getMovementDirections();

				Set<MovementDirection> keySet = movementDirections.keySet();
				if (movementDirections.size() != 2) {
					mismatchDescription.appendText("not 2 movementDirections but " + keySet);
					return false;
				}
				boolean containsAll = keySet
						.containsAll(Arrays.asList(MovementDirection.TOP, MovementDirection.DOWN));
				if (!containsAll) {
					mismatchDescription.appendText("but the following movement directions are missed: [");
					mismatchDescription.appendText(Arrays.asList(MovementDirection.TOP, MovementDirection.DOWN)
							.stream().filter(keySet::contains).map(Object::toString).collect(Collectors.joining(",")));
					mismatchDescription.appendText("]");
				}

				return containsAll;
			}
		};
	}
}
