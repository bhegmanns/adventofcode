package de.hegmanns.adventofcode2018.day07;

public class Graph {
	
	private Point from;
	private Point to;

	private Graph() {};
	
	
	
	public Point getFrom() {
		return from;
	}



	public Point getTo() {
		return to;
	}



	public static Graph from(Point a) {
		Graph graph = new Graph();
		graph.from = a;
		return graph;
	}
	
	public Graph to(Point b) {
		this.to = b;
		return this;
	}
}
