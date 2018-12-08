package de.hegmanns.adventofcode2018.day08;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionAppDay08Task02 {

	public static void main(String[] args) {
		String input = Input08.getString();
		StringTokenizer tokenizer = new StringTokenizer(input, " ");
		List<Long> header = new ArrayList<>(tokenizer.countTokens());
		while (tokenizer.hasMoreTokens()) {
			header.add(Long.parseLong(tokenizer.nextToken()));
		}
		Iterator<Long> headerIterator = header.iterator();
		
		Node node = Node.init(headerIterator);
		
		System.out.println("Sum of metadatas : " + node.getNodeValueSum());
	}
}
