package de.hegmanns.adventofcode2018.day07;

public class Worker {

	private String name;
	private Point workingPoint;
	private long untilTime;
	
	public Worker(String name) {
		this.name = name;
		workingPoint = null;
		untilTime = -1;
	}
	public Point getWorkingPoint() {
		return workingPoint;
	}
	public long getUntilTime() {
		return untilTime;
	}
	
	public String getName() {
		return name;
	}
	public void makeDone() {
		workingPoint = null;
		untilTime = -1;
	}
	
	public void startWork(Point point, long until) {
		this.workingPoint = point;
		this.untilTime = until;
	}
	@Override
	public String toString() {
		return "Worker [name=" + name + ", workingPoint=" + workingPoint + ", untilTime=" + untilTime + "]";
	}
	
	
	
}
