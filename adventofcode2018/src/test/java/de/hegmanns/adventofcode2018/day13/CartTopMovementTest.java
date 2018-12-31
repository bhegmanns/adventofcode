package de.hegmanns.adventofcode2018.day13;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class CartTopMovementTest {
	static Track track, trackAbove, trackBelow;
	private static List<Track> prepareTracks(){
		track = Track.create(5, 5);
		trackAbove = Track.create(5, 4);
		trackBelow = Track.create(5, 6);
		
		track.getMovementDirections().put(MovementDirection.TOP, trackAbove);
		track.getMovementDirections().put(MovementDirection.DOWN, trackBelow);
		return Arrays.asList(track, trackAbove, trackBelow);
	}
	 
	
	private static Cart prepareCart(MovementDirection movementDirection) {
		return new Cart(new Position(5, 5), movementDirection);
	}
	
	private static void connect(Track from, Track to, MovementDirection movementDirection) {
		from.getMovementDirections().put(movementDirection, to);
	}
	
	private static void setupPossibleDirection(Track track, MovementDirection movementDirection) {
		track.getMovementDirections().put(movementDirection, null);
	}
	
	/**
	 * Situation:
	 *  |
	 *  ^
	 *  |
	 *  
	 *  Expected Situation:
	 *  ^
	 *  |
	 *  |
	 *  
	 */
	@Test
	public void topForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.TOP);
	    cart.move(trackMap);

	    assertThat(cart.getCurrentPosition(), equalTo(new Position(5, 4)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.TOP));
	}

	/**
	 * Situation:
	 *  /
	 *  ^
	 *  |
	 *  
	 *  Expected Situation:
	 *  >
	 *  |
	 *  |
	 *  
	 */
	@Test
	public void rightForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.RIGHT);
	    
	    cart.move(trackMap);

	    assertThat(cart.getCurrentPosition(), equalTo(new Position(5, 4)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.RIGHT));
	}
	
	/**
	 * Situation:
	 *  \
	 *  ^
	 *  |
	 *  
	 *  Expected Situation:
	 *  <
	 *  |
	 *  |
	 *  
	 */
	@Test
	public void leftForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.LEFT);
	    
	    cart.move(trackMap);

	    assertThat(cart.getCurrentPosition(), equalTo(new Position(5, 4)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.LEFT));
	}
	
	
	/**
	 * Situation:
	 *  +
	 *  ^
	 *  |
	 *  
	 *  Expected Situation:
	 *  <
	 *  |
	 *  |
	 *  
	 */
	@Test
	public void firstIntersectionLeftForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.LEFT);
		setupPossibleDirection(trackAbove, MovementDirection.TOP);
		setupPossibleDirection(trackAbove, MovementDirection.RIGHT);
	    
	    cart.move(trackMap);

	    assertThat(cart.getCurrentPosition(), equalTo(new Position(5, 4)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.LEFT));
	}
	
	/**
	 * Situation:
	 *  ++
	 *   ^
	 *   |
	 *  Intermediate situation:
	 *  +<
	 *   |
	 *   |
	 *   
	 *  Expected Situation:
	 *  <+
	 *   |
	 *   |
	 *  
	 */
	@Test
	public void secondIntersectionTopForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.LEFT);
		setupPossibleDirection(trackAbove, MovementDirection.TOP);
		setupPossibleDirection(trackAbove, MovementDirection.RIGHT);
	    
	    cart.move(trackMap);
	    Track newTrack = Track.create(4, 4);
	    connect(track, newTrack, MovementDirection.LEFT);
	    setupPossibleDirection(newTrack, MovementDirection.DOWN);
	    setupPossibleDirection(newTrack, MovementDirection.TOP);
	    setupPossibleDirection(newTrack, MovementDirection.LEFT);
	    trackMap = convertToMap(Arrays.asList(newTrack, track, trackAbove, trackBelow));
	    cart.move(trackMap);
	    
	    assertThat(cart.getCurrentPosition(), equalTo(new Position(4, 4)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.LEFT));
	}
	
	/**
	 * Situation:
	 *  +++
	 *    ^
	 *    |
	 *  Intermediate situation:
	 *  ++<
	 *    |
	 *    |
	 *   
	 *  Intermediate Situation:
	 *  +<+
	 *    |
	 *    |
	 *  
	 *  Expected Situation:
	 *  ^++
	 *    |
	 *    |
	 */
	@Test
	public void thirdIntersectionTopForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.LEFT);
		setupPossibleDirection(trackAbove, MovementDirection.TOP);
		setupPossibleDirection(trackAbove, MovementDirection.RIGHT);
	    
	    cart.move(trackMap);
	    Track firstNewTrack = Track.create(4, 4);
	    connect(track, firstNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(firstNewTrack, MovementDirection.DOWN);
	    setupPossibleDirection(firstNewTrack, MovementDirection.TOP);
	    setupPossibleDirection(firstNewTrack, MovementDirection.LEFT);
	    trackMap = convertToMap(Arrays.asList(firstNewTrack, track, trackAbove, trackBelow));
	    
	    cart.move(trackMap);
	    Track secondNewTrack = Track.create(3, 4);
	    connect(firstNewTrack, secondNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(secondNewTrack, MovementDirection.DOWN);
	    setupPossibleDirection(secondNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(secondNewTrack, MovementDirection.TOP);
	    trackMap = convertToMap(Arrays.asList(firstNewTrack, secondNewTrack, track, trackAbove, trackBelow));
	    cart.move(trackMap);

	    assertThat(cart.getCurrentPosition(), equalTo(new Position(3, 4)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.TOP));
	}
	
	/**
	 * Situation:
	 *  +
	 *  +++
	 *    ^
	 *    |
	 *    
	 *  Intermediate situation:
	 *  +
	 *  ++<
	 *    |
	 *    |
	 *   
	 *  Intermediate Situation:
	 *  +
	 *  +<+
	 *    |
	 *    |
	 *  
	 *  Intermediate Situation:
	 *  +
	 *  ^++
	 *    |
	 *    |
	 *    
	 *  Intermediate Situation:
	 *  <
	 *  +++
	 *    |
	 *    |
	 */
	@Test
	public void fouthIntersectionTopForward() {
		List<Track> tracks = prepareTracks();
		Map<Position, Track> trackMap = convertToMap(tracks);
		Cart cart = prepareCart(MovementDirection.TOP);
		connect(trackBelow, track, MovementDirection.DOWN);
		setupPossibleDirection(trackAbove, MovementDirection.LEFT);
		setupPossibleDirection(trackAbove, MovementDirection.TOP);
		setupPossibleDirection(trackAbove, MovementDirection.RIGHT);
	    
	    cart.move(trackMap);
	    Track firstNewTrack = Track.create(4, 4);
	    connect(track, firstNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(firstNewTrack, MovementDirection.DOWN);
	    setupPossibleDirection(firstNewTrack, MovementDirection.TOP);
	    setupPossibleDirection(firstNewTrack, MovementDirection.LEFT);
	    trackMap = convertToMap(Arrays.asList(firstNewTrack, track, trackAbove, trackBelow));
	    
	    cart.move(trackMap);
	    Track secondNewTrack = Track.create(3, 4);
	    connect(firstNewTrack, secondNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(secondNewTrack, MovementDirection.DOWN);
	    setupPossibleDirection(secondNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(secondNewTrack, MovementDirection.TOP);
	    trackMap = convertToMap(Arrays.asList(firstNewTrack, secondNewTrack, track, trackAbove, trackBelow));
	    
	    cart.move(trackMap);
	    Track thirdNewTrack = Track.create(3, 3);
	    connect(secondNewTrack, thirdNewTrack, MovementDirection.TOP);
	    setupPossibleDirection(thirdNewTrack, MovementDirection.LEFT);
	    setupPossibleDirection(thirdNewTrack, MovementDirection.RIGHT);
	    setupPossibleDirection(thirdNewTrack, MovementDirection.TOP);
	    trackMap = convertToMap(Arrays.asList(firstNewTrack, secondNewTrack, thirdNewTrack, track, trackAbove, trackBelow));
	    
	    cart.move(trackMap);
	    
	    assertThat(cart.getCurrentPosition(), equalTo(new Position(3, 3)));
	    assertThat(cart.getCurrentMovementDirection(), is(MovementDirection.LEFT));
	}

	private Map<Position, Track> convertToMap(Collection<Track> tracks){
		Map<Position, Track> trackmap = new HashMap<>();
		
		for (Track track : tracks) {
			trackmap.put(track.getPosition(), track);
		}
		
		return trackmap;
	}
	
	
}
