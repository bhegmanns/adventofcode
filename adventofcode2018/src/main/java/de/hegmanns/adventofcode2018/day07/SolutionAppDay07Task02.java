package de.hegmanns.adventofcode2018.day07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class SolutionAppDay07Task02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strings = Input07.getStrings();
		
		long stepTime = 60;
		long letterTime = 1; // 64
		int maxCountWorker = 5;

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
		List<Worker> workers = new ArrayList<>();
		String row = "";
		long time = -1;
		while (true) {
			Iterator<Point> pointIterator = onlyOutgoingPoints.iterator();
			Point startPoint = null;
			while (pointIterator.hasNext()) {
				if (workers.size() <maxCountWorker) {
				Point possibleStartpoint = pointIterator.next();
				List<Point> allNeededPassedPoints = graphs.stream().filter((g) -> g.getTo().equals(possibleStartpoint)).map(Graph::getFrom).collect(Collectors.toList());
				if (allNeededPassedPoints.isEmpty() || usedPoints.containsAll(allNeededPassedPoints)) {
					startPoint = possibleStartpoint;
					Worker worker = new Worker(startPoint.getLetter());
					worker.startWork(startPoint, time + stepTime + (startPoint.getLetter().getBytes()[0] - 64) * letterTime);
					workers.add(worker);
					pointIterator.remove();
				}
				}else {
					break;
				}
			}
			if (workers.isEmpty()) { // no workers found ...
				break;
			}

			Optional<Worker> firstEndWorker = workers.stream().sorted((w1, w2) -> (int)(w1.getUntilTime() - w2.getUntilTime())).findFirst();
			if (!firstEndWorker.isPresent()) {
				System.out.println("Problem, no best worker found ... :(");
				throw new RuntimeException("No best worker found ...");
			}
			time = firstEndWorker.get().getUntilTime();
			row += firstEndWorker.get().getWorkingPoint().getLetter();
			usedPoints.add(firstEndWorker.get().getWorkingPoint());
			onlyOutgoingPoints
					.addAll(fromPoints.get(firstEndWorker.get().getWorkingPoint()).stream().map(Graph::getTo).collect(Collectors.toList()));
			onlyOutgoingPoints.removeAll(usedPoints);
			workers.remove(firstEndWorker.get());
			System.out.println("Row: " + row);
			System.out.println("outgoingPoint: " + onlyOutgoingPoints);
		}
		if (fromPoints.size() == row.length()) {
			System.out.println("FOUND: '" + row + "'");
			System.out.println("Time : " + (time+1));
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
