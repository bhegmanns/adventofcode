package de.hegmanns.adventofcode2018.day13;

enum MovementDirection{
	LEFT, RIGHT, TOP, DOWN;
	
	public MovementDirection getOpposite() {
		switch(this) {
		case LEFT: return RIGHT;
		case RIGHT: return LEFT;
		case TOP: return DOWN;
		case DOWN: return TOP;
		}
		throw new RuntimeException(" ... ");
	}
	
	public MovementDirection getNewDirection(MovementOnIntersection movementOnIntersection) {
		if (movementOnIntersection==MovementOnIntersection.STRAIGHT) {
			return this;
		}
		
		switch(this) {
		case LEFT:
			if (movementOnIntersection==MovementOnIntersection.LEFT) {
				return DOWN;
			}else {
				return TOP;
			}
		case RIGHT:
			if (movementOnIntersection==MovementOnIntersection.LEFT) {
				return TOP;
			}else {
				return DOWN;
			}
		case TOP:
			if (movementOnIntersection==MovementOnIntersection.LEFT) {
				return LEFT;
			}else {
				return RIGHT;
			}
		case DOWN:
			if (movementOnIntersection==MovementOnIntersection.LEFT) {
				return RIGHT;
			}else {
				return LEFT;
			}
		}
		throw new RuntimeException("");
	}
	
	
}