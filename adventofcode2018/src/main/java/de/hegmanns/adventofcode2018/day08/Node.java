package de.hegmanns.adventofcode2018.day08;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {
	private static long number = 0;
	private long nodeNumber;
	private long countNodes;
	private long countMetadatas;
	private List<Node> nodes = new ArrayList<>();
	private List<Long> metadatas = new ArrayList<>();
	
	public static Node init(Iterator<Long> headerIterator) {
		number++;
		
		Node node = new Node();
		node.countNodes = headerIterator.next();
		node.countMetadatas = headerIterator.next();
		node.nodeNumber = number;
		//
		for (int i = 0 ; i < node.countNodes ; i++) {
			node.nodes.add(Node.init(headerIterator));
		}
		
		
		// after initialize all child nodes, initialize his metadatas
		node.readMetadatas(headerIterator);
		
		return node;
	}
	
	private void readMetadatas(Iterator<Long> headerIterator) {
		for (int i = 0 ; i < countMetadatas ; i++) {
			Long next = headerIterator.next();
			metadatas.add(next);
		}
		System.out.println("fill metadatas");
	}
	
	public long getMetadataSum() {
		long thisMetadataSum = 0;
		for (Long l : metadatas) {
			thisMetadataSum += l;
		}
		
		for (Node node : nodes) {
			thisMetadataSum += node.getMetadataSum();
		}
		
		return thisMetadataSum;
	}
	
	public long getNodeValueSum() {
		long nodeValueSum = 0;
		if (countNodes == 0) {
			for (Long l : metadatas) {
				nodeValueSum += l;
			}
		}else {
			// there are nodes
			for (long l : metadatas) {
				if (l <= countNodes) {
					nodeValueSum += nodes.get((int)(l-1)).getNodeValueSum();
				}
			}
		}
		return nodeValueSum;
	}
	
}
