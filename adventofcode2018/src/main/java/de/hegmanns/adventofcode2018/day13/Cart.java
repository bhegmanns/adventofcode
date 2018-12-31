package de.hegmanns.adventofcode2018.day13;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author B. Hegmanns
 */
public class Cart {

	private Position currentPosition;
	private MovementOnIntersection lastMovementOnIntersection = MovementOnIntersection.NOTHING;
	private MovementDirection currentMovementDirection;
	
	public Cart(Position currentPosition, MovementDirection movementDirection) {
		this.currentPosition = currentPosition;
		this.currentMovementDirection = movementDirection;
	}
	
	public void move(Map<Position, Track> tracks) {
		currentPosition.move(currentMovementDirection);
		
		// now set the new movement direction
		Track newPositionTrack = tracks.get(currentPosition);

		if (newPositionTrack == null) {
			throw new RuntimeException("there is not track at new position " + currentPosition);
		}

		Set<MovementDirection> possibleMovementDirections = newPositionTrack.getPossibleMovementDirections(currentMovementDirection);
		if (possibleMovementDirections.isEmpty()) {
			throw new RuntimeException("there are no possible movementDirections at new Position " + currentPosition);
		}
		if (possibleMovementDirections.size() == 1) {
			this.currentMovementDirection = possibleMovementDirections.iterator().next();
		}else {
			if (possibleMovementDirections.size() != 3) {
				throw new RuntimeException("there are not enouph possible movementDirections at intersection " + currentPosition + ", possible: " + possibleMovementDirections);
			}
			lastMovementOnIntersection = lastMovementOnIntersection.getNext();
			currentMovementDirection = currentMovementDirection.getNewDirection(lastMovementOnIntersection);
		}
	}
	
	public Position getCurrentPosition() {
		return currentPosition;
	}

	public MovementDirection getCurrentMovementDirection() {
		return currentMovementDirection;
	}
	
	
}

enum MovementOnIntersection{
	NOTHING, LEFT, STRAIGHT, RIGHT;
	
	private MovementOnIntersection() {
		
	}
	
	public MovementOnIntersection getNext() {
		switch(this) {
		case NOTHING  : return LEFT;
		case LEFT     : return STRAIGHT;
		case STRAIGHT : return RIGHT;
		case RIGHT    : return LEFT;
		default: throw new RuntimeException("current movement not defined: " + this);
		}
	}
}

