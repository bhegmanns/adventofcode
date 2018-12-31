package de.hegmanns.adventofcode2018.day13;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import de.hegmanns.adventofcode2018.common.Solution;
import de.hegmanns.adventofcode2018.common.input.StringArrayInput;

/**
 * The important point is the sequence of the carts, from highest row and left position.
 * And the next important point is, that the hit can happens during one tick, for example after the third cart has done is tick.
 * So, the collision check have to be done after every movement of each car und not after all movements.
 * 
 * @author B. Hegmanns
 *
 */
public class SolutionDay13Task01 implements Solution<String>{
	
	private StringArrayInput input;
	public SolutionDay13Task01(StringArrayInput stringarrayInput) {
		this.input = stringarrayInput;
	}
	
	@Override
	public String solve() {
		InputDay13Provider provider = new InputDay13Provider(input);
		InputDay13 inputday13 = provider.getData();
		
		List<Cart> carts = inputday13.getCarts();
		Map<Position, Track> tracks = inputday13.getPositionToTrackMap();
		int positionsAfterMovement = 0;
		do {
			TreeSet<Cart> cartTreeSet = new TreeSet<>(new Comparator<Cart>() {

				@Override
				public int compare(Cart o1, Cart o2) {
					if (o1.getCurrentPosition().getY() != o2.getCurrentPosition().getY()) {
						return o1.getCurrentPosition().getY() - o2.getCurrentPosition().getY();
					}else {
						return o1.getCurrentPosition().getX() - o2.getCurrentPosition().getX();
					}
				}
			});
			cartTreeSet.addAll(carts);
			for (Cart cart : cartTreeSet) {
				cart.move(tracks);
				if (carts.stream().map(Cart::getCurrentPosition).collect(Collectors.toSet()).size() !=17) {
					break;
				}
			}
			positionsAfterMovement = carts.stream().map(Cart::getCurrentPosition).collect(Collectors.toSet()).size();
			System.out.println("Size = " + positionsAfterMovement);
		} while (positionsAfterMovement == carts.size());

		System.out.println("ExpectedSie = " + carts.size());
		
		boolean added = false;
		Position position = null;
		Set<Position> positions = new HashSet<>();
		Position notAddedPosition = null;
		for (Cart cart : carts) {
			position = cart.getCurrentPosition();
			added = positions.add(position);
			if (!added) {
				System.out.println("Position " + position + " not added!!!");
				notAddedPosition = position;;
			}
		}
		System.out.println("Position: " + notAddedPosition);
		System.out.println("RESULT: " + notAddedPosition.getX() + "," + notAddedPosition.getY());
		
		return "" + notAddedPosition.getX() + "," + notAddedPosition.getY();
	}

	
}
