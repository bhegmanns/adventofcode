package de.hegmanns.adventofcode2018.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SolutionAppDay07Task01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strings = Input07.getStrings();

		List<Graph> graphs = new ArrayList<>();
		Map<Point, List<Graph>> fromPoints = new HashMap<>();
		Map<Point, List<Graph>> toPoints = new HashMap<>();

		for (String string : strings) {
			Graph graph = createGraph(string, fromPoints, toPoints);
			graphs.add(graph);
		}

		SortedSet<Point> onlyOutgoingPoints = new TreeSet<>();
		SortedSet<Point> onlyIncomingPoints = new TreeSet<>();

		for (Entry<Point, List<Graph>> entry : fromPoints.entrySet()) {
			if (entry.getValue().size() == 0) {
				onlyIncomingPoints.add(entry.getKey());
			}
		}
		for (Entry<Point, List<Graph>> entry : toPoints.entrySet()) {
			if (entry.getValue().size() == 0) {
				onlyOutgoingPoints.add(entry.getKey());
			}
		}

		System.out.println("Count onlyOutgogingPoints: " + onlyOutgoingPoints.size() + " : " + onlyOutgoingPoints);
		System.out.println("Count onlyIncomingPoint  : " + onlyIncomingPoints.size() + " : " + onlyIncomingPoints);
		Set<Point> usedPoints = new HashSet<>();
		String row = "";
		while (true) {
			Iterator<Point> pointIterator = onlyOutgoingPoints.iterator();
			Point startPoint = null;
			while (pointIterator.hasNext()) {
				Point possibleStartpoint = pointIterator.next();
				List<Point> allNeededPassedPoints = graphs.stream().filter((g) -> g.getTo().equals(possibleStartpoint)).map(Graph::getFrom).collect(Collectors.toList());
				if (allNeededPassedPoints.isEmpty() || usedPoints.containsAll(allNeededPassedPoints)) {
					startPoint = possibleStartpoint;
					break;
				}
			}
			if (startPoint == null) {
				break;
			}
			
			usedPoints.add(startPoint);
			row += startPoint.getLetter();
			pointIterator.remove();
			onlyOutgoingPoints
					.addAll(fromPoints.get(startPoint).stream().map(Graph::getTo).collect(Collectors.toList()));
			onlyOutgoingPoints.removeAll(usedPoints);
			System.out.println("Row: " + row);
			System.out.println("outgoingPoint: " + onlyOutgoingPoints);
		}
		if (fromPoints.size() == row.length()) {
			System.out.println("FOUND: '" + row + "'");
		}
	}

	private static Graph createGraph(String string, Map<Point, List<Graph>> fromPoints,
			Map<Point, List<Graph>> toPoints) {
		String from = "" + string.charAt(5);
		String to = "" + string.charAt(36);
		Point fromPoint = new Point(from);
		Point toPoint = new Point(to);
		Graph graph = Graph.from(fromPoint).to(toPoint);
		List<Graph> graphsFrom = fetchGraphs(fromPoint, fromPoints);
		graphsFrom.add(graph);
		fetchGraphs(toPoint, fromPoints); // only for registration, so that all points are located in map
		fetchGraphs(toPoint, toPoints).add(graph);
		fetchGraphs(fromPoint, toPoints);
		return graph;
	}

	private static List<Graph> fetchGraphs(Point p, Map<Point, List<Graph>> points) {
		List<Graph> graphs = points.get(p);
		if (graphs == null) {
			graphs = new ArrayList<>();
			points.put(p, graphs);
		}
		return graphs;
	}
}
