package de.hegmanns.adventofcode2018.day13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputDay13 {

	private List<Cart> carts;
	private List<Track> tracks;
	private Map<Position, Track> positionToTrackMap;
	
	private List<Cart> originalCarts;
	private List<Track> originalTracks;
	private Map<Position, Track> originalPositionToTrackMap;
	
	public InputDay13(List<Cart> carts, List<Track> tracks) {
		this.originalCarts = carts;
		this.originalTracks = tracks;
		setTransferValues();
	}
	
	private void setTransferValues() {
		this.carts = this.originalCarts;
		this.tracks = this.originalTracks;
		
		originalPositionToTrackMap = new HashMap<>();
		positionToTrackMap = new HashMap<>();
		
		for (Track track : this.tracks) {
			originalPositionToTrackMap.put(track.getPosition(), track);
			positionToTrackMap.put(track.getPosition(), track);
		}
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public Map<Position, Track> getPositionToTrackMap() {
		return positionToTrackMap;
	}
	
	public void addCart(Cart cart) {
		this.originalCarts.add(cart);
		setTransferValues();
	}
	
	public void addTrack(Track track) {
		this.originalTracks.add(track);
		setTransferValues();
	}
}
