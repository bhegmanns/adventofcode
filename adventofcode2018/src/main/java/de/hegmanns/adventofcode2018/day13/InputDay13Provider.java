package de.hegmanns.adventofcode2018.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hegmanns.adventofcode2018.common.data.DataFromStringArrayProvider;
import de.hegmanns.adventofcode2018.common.input.StringArrayInput;

public class InputDay13Provider extends DataFromStringArrayProvider<InputDay13> {

	public InputDay13Provider(StringArrayInput stringArrayInput) {
		super(stringArrayInput);
	}

	@Override
	public InputDay13 getData() {
		String[] lines = getInput();

		List<Cart> carts = getCarts(lines);
		return new InputDay13(carts, new ArrayList<>(getTracks(lines, carts).values()));
	}

	/**
	 * > < ^ v
	 * 
	 * @return
	 */
	private List<Cart> getCarts(String[] lines) {
		List<Cart> carts = new ArrayList<>();
		int yCoord = -1;
		for (String line : lines) {
			yCoord++;
			for (int xCoord = 0; xCoord < line.length(); xCoord++) {
				char sign = line.charAt(xCoord);
				switch (sign) {
				case '<':
					carts.add(new Cart(new Position(xCoord, yCoord), MovementDirection.LEFT));
					break;
				case '>':
					carts.add(new Cart(new Position(xCoord, yCoord), MovementDirection.RIGHT));
					break;
				case '^':
					carts.add(new Cart(new Position(xCoord, yCoord), MovementDirection.TOP));
					break;
				case 'v':
					carts.add(new Cart(new Position(xCoord, yCoord), MovementDirection.DOWN));
					break;
				}
			}
		}

		return carts;
	}

	/**
	 * | - / \ +
	 * 
	 * @param carts
	 * 
	 * @return
	 */
	public Map<Position, Track> getTracks(String[] lines, List<Cart> carts) {
		String possibleValues = "|-\\/+<>v^";
		List<Track> tracks = new ArrayList<>();
		Map<Position, Track> trackInPosition = new HashMap<>();

		int yCoord = -1;
		for (String line : lines) {
			yCoord++;
			for (int xCoord = 0; xCoord < line.length(); xCoord++) {
				char sign = line.charAt(xCoord);
				if (possibleValues.indexOf(sign) != -1) {
					Track createdTrack = Track.create(xCoord, yCoord).withSign(sign);
					tracks.add(createdTrack);
					trackInPosition.put(new Position(xCoord, yCoord), createdTrack);
				}
			}
		}

		String explicitValues = "|-\\/+";
		String notExplicitValues = "<>v^";
		yCoord = -1;
		for (String line : lines) {
			yCoord++;
			for (int xCoord = 0; xCoord < line.length(); xCoord++) {
				char sign = line.charAt(xCoord);
				if (possibleValues.indexOf(sign) != -1) {
					Track createdTrack = trackInPosition.get(new Position(xCoord, yCoord));
					if (explicitValues.indexOf(sign) != -1) {
						switch (sign) {
						case '|': {
							Track above = trackInPosition.get(new Position(xCoord, yCoord - 1));
							Track behind = trackInPosition.get(new Position(xCoord, yCoord + 1));
							connect(createdTrack, above, MovementDirection.TOP);
							connect(createdTrack, behind, MovementDirection.DOWN);
							
						}
							break;
						case '-': {
							Track left = trackInPosition.get(new Position(xCoord - 1, yCoord));
							Track right = trackInPosition.get(new Position(xCoord + 1, yCoord));
							connect(createdTrack, left, MovementDirection.LEFT);
							connect(createdTrack, right, MovementDirection.RIGHT);
						}
							break;
						case '/': {
							/*
							 * should be:
							 * (1)
							 *  |
							 * -/
							 * 
							 * (2)
							 *  /-
							 *  |
							 */
							if (xCoord==0 || "-<>+".indexOf(line.charAt(xCoord-1))==-1) {
								//(2)
								Track right = trackInPosition.get(new Position(xCoord + 1, yCoord));
								Track behind = trackInPosition.get(new Position(xCoord, yCoord + 1));
								connect(createdTrack, right, MovementDirection.RIGHT);
								connect(createdTrack, behind, MovementDirection.DOWN);
								
							}else {
								//(1)
								Track left = trackInPosition.get(new Position(xCoord - 1, yCoord));
								Track above = trackInPosition.get(new Position(xCoord, yCoord - 1));
								connect(createdTrack, left, MovementDirection.LEFT);
								connect(createdTrack, above, MovementDirection.TOP);
							}
						}
							break;
						case '\\': {
							/*
							 * should be:
							 * (1)
							 * -\
							 *  |
							 * 
							 * (2)
							 *  |
							 *  \-
							 */
							if (xCoord==0 || "-<>+".indexOf(line.charAt(xCoord-1))== -1) {
								//(1)
								Track above = trackInPosition.get(new Position(xCoord, yCoord-1));
								Track right = trackInPosition.get(new Position(xCoord+1, yCoord));
								connect(createdTrack, above, MovementDirection.TOP);
								connect(createdTrack, right, MovementDirection.RIGHT);
							}else {
								//(2)
								Track left = trackInPosition.get(new Position(xCoord-1, yCoord));
								Track behind = trackInPosition.get(new Position(xCoord, yCoord+1));
								connect(createdTrack, left, MovementDirection.LEFT);
								connect(createdTrack, behind, MovementDirection.DOWN);
								
								
							}
						}
							break;
						case '+':{
							Track above = trackInPosition.get(new Position(xCoord, yCoord - 1));
							Track behind = trackInPosition.get(new Position(xCoord, yCoord + 1));
							Track left = trackInPosition.get(new Position(xCoord - 1, yCoord));
							Track right = trackInPosition.get(new Position(xCoord + 1, yCoord));
							
							connect(createdTrack, above, MovementDirection.TOP);
							connect(createdTrack, behind, MovementDirection.DOWN);
							connect(createdTrack, left, MovementDirection.LEFT);
							connect(createdTrack, right, MovementDirection.RIGHT);
							
						}
						break;
						}
					}
					if (notExplicitValues.indexOf(sign) != -1) {
						Track left = trackInPosition.get(new Position(xCoord-1, yCoord));
						Track right = trackInPosition.get(new Position(xCoord+1, yCoord));
						Track above = trackInPosition.get(new Position(xCoord, yCoord-1));
						Track behind = trackInPosition.get(new Position(xCoord, yCoord+1));
						
						if (left != null && "\\-+".indexOf("" + left.getSign()) != -1) {
							connect(createdTrack, left, MovementDirection.LEFT);
						}
						
						if (right != null && "/-+".indexOf("" + right.getSign()) != -1) {
							connect(createdTrack, right, MovementDirection.RIGHT);
						}
						
						if (above != null && "|+/\\".indexOf("" + above.getSign()) != -1) {
							connect(createdTrack, above, MovementDirection.TOP);
						}
						
						if (behind != null && "|+/\\".indexOf("" + behind.getSign()) != -1) {
							connect(createdTrack, behind, MovementDirection.DOWN);
						}
						
					}
				}
			}
		}

		return trackInPosition;
	}
	
	private static void connect(Track from, Track to, MovementDirection from2To) {
		if (from != null && to != null) {
			from.getMovementDirections().put(from2To, to);
			to.getMovementDirections().put(from2To.getOpposite(), from);
		}
	}
	
}
