package de.hegmanns.adventofcode2018.day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

import de.hegmanns.adventofcode2018.common.Solution;
import de.hegmanns.adventofcode2018.common.input.StringArrayInput;

public class SolutionDay13Task02 implements Solution<String> {

	private StringArrayInput input;
	public SolutionDay13Task02(StringArrayInput stringarrayInput) {
		this.input = stringarrayInput;
	}
	
	@Override
	public String solve() {
		InputDay13Provider provider = new InputDay13Provider(input);
		InputDay13 inputday13 = provider.getData();
		
		List<Cart> carts = inputday13.getCarts();
		List<Cart> deletedCarts = new ArrayList<>();
		Map<Position, Track> tracks = inputday13.getPositionToTrackMap();
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
				if (deletedCarts.contains(cart)){
					continue;
				}
				cart.move(tracks);
				
				
				List<Cart> otherCarts = new ArrayList<>(cartTreeSet);
				otherCarts.remove(cart);
				otherCarts.removeAll(deletedCarts);
				Optional<Cart> hitCart = otherCarts.stream().filter((c) -> c.getCurrentPosition().equals(cart.getCurrentPosition())).findFirst();
				if (hitCart.isPresent()) {
					deletedCarts.add(hitCart.get());
					deletedCarts.add(cart);
				}
//				if (cartTreeSet.size() - deletedCarts.size() == 1) {
//					break; // only one cart left
//				}
			}
			
			carts.removeAll(deletedCarts);
			deletedCarts = new ArrayList<>();
		} while (carts.size() != 1);

		Cart onlyLeftCart = carts.get(0);
		System.out.println("Position: " + onlyLeftCart.getCurrentPosition());
		
		return "" + onlyLeftCart.getCurrentPosition().getX() + "," + onlyLeftCart.getCurrentPosition().getY();
	}

}
