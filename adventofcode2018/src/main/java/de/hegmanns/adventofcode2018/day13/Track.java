package de.hegmanns.adventofcode2018.day13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Track {

	Map<MovementDirection, Track> movementDirections = new HashMap<>();
	private int x;
	private int y;
	private char sign;
	
	private Track(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public static Track create(int x, int y) {
		Track track = new Track(x, y);
		return track;
	}
	public Track withSign(char sign) {
		this.sign = sign;
		return this;
	}
	public Track withConnection(MovementDirection movementDirection, Track track) {
		if (movementDirections.put(movementDirection, track)!=null) {
			throw new IllegalArgumentException("MovementDirection " + movementDirection + " already defined");
		}
		return this;
	}
	public Map<MovementDirection, Track> getMovementDirections() {
		return movementDirections;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Position getPosition() {
		return new Position(x, y);
	}
	
	public boolean isIntersection() {
		return movementDirections.keySet().size() == 4;
	}
	
	public Set<MovementDirection> getPossibleMovementDirections(MovementDirection currentMovementDirection){
		Set<MovementDirection> directions = new HashSet<>(movementDirections.keySet());
		directions.remove(currentMovementDirection.getOpposite());
		return directions;
	}
	@Override
	public String toString() {
		return "Track [movementDirections=" + movementDirections.keySet() + ", x=" + x + ", y=" + y + "]";
	}
	
	public char getSign() {
		return sign;
	}
	
	public String getPrintout() {
		if (movementDirections.size() == 4) {
			return "+";
		}
		if (movementDirections.containsKey(MovementDirection.TOP) && movementDirections.containsKey(MovementDirection.DOWN)) {
			return "|";
		}
		if (movementDirections.containsKey(MovementDirection.LEFT) && movementDirections.containsKey(MovementDirection.RIGHT)) {
			return "-";
		}
		
		if (movementDirections.containsKey(MovementDirection.LEFT) && movementDirections.containsKey(MovementDirection.DOWN)) {
			return "\\";
		}
		if (movementDirections.containsKey(MovementDirection.DOWN) && movementDirections.containsKey(MovementDirection.RIGHT)) {
			return "/";
		}
		if (movementDirections.containsKey(MovementDirection.TOP) && movementDirections.containsKey(MovementDirection.RIGHT)) {
			return "\\";
		}
		if (movementDirections.containsKey(MovementDirection.TOP) && movementDirections.containsKey(MovementDirection.LEFT)) {
			return "/";
		}
		return "";
	}
	
}
