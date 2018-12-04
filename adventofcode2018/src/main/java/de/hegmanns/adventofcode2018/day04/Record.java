package de.hegmanns.adventofcode2018.day04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Record implements Comparable<Record>{
	private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static enum Happing {
		BEGINS_SHIFT, ASLEEP, WAKES_UP;
	}

	/**
	 * [1518-11-01 00:00] Guard #10 begins shift [1518-11-01 00:05] falls asleep
	 * [1518-11-01 00:25] wakes up [1518-11-01 00:30] falls asleep [1518-11-01
	 * 00:55] wakes up [1518-11-01 23:58] Guard #99 begins shift [1518-11-02 00:40]
	 * falls asleep [1518-11-02 00:50] wakes up [1518-11-03 00:05] Guard #10 begins
	 * shift [1518-11-03 00:24] falls asleep [1518-11-03 00:29] wakes up [1518-11-04
	 * 00:02] Guard #99 begins shift [1518-11-04 00:36] falls asleep [1518-11-04
	 * 00:46] wakes up [1518-11-05 00:03] Guard #99 begins shift [1518-11-05 00:45]
	 * falls asleep [1518-11-05 00:55] wakes up
	 * 
	 * @param info
	 */
	public Record(String info) {
		originalInfoString = info;
		int indexClosedBracked = info.indexOf(']');
		int indexGuard = -1;
		time = LocalDateTime.parse(info.substring(1, indexClosedBracked), FORMATTER);
		if ((indexGuard = info.indexOf("Guard")) >= 0) {
			// guard-id is contained
			int indexSharp = info.indexOf('#');
			guardId = Long.parseLong(new StringTokenizer(info.substring(indexSharp + 1), " ").nextToken());

			info = info.substring(indexSharp + ("" + guardId).length() + 1);
		}else {
			info = info.substring(indexClosedBracked + 2);
		}

		switch (info.trim()) {
		case "begins shift":
			happening = Happing.BEGINS_SHIFT; break;
		case "falls asleep":
			happening = Happing.ASLEEP; break;
		case "wakes up":
			happening = Happing.WAKES_UP;break;
		}

	}

	private String originalInfoString;
	private LocalDateTime time;
	private Long guardId;
	private Happing happening;
	
	

	public String getOriginalInfoString() {
		return originalInfoString;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public Long getGuardId() {
		return guardId;
	}

	public Happing getHappening() {
		return happening;
	}
	
	public void placeGuardIdIfNotSet(long guardId) {
		if (this.guardId == null) {
			this.guardId = guardId; 
		}
	}

	public static void main(String[] args) {
		LocalDateTime time = LocalDateTime.now().withYear(1518).withMonth(11).withDayOfMonth(01).withHour(1)
				.withMinute(5).withSecond(0).withNano(0);
		System.out.println(time);
	}
	
	

	@Override
	public int compareTo(Record o) {
		return time.compareTo(o.time);
	}

	@Override
	public String toString() {
		return "Record [originalInfoString=" + originalInfoString + ", time=" + time + ", guardId=" + guardId
				+ ", happening=" + happening + "]";
	}
	
	
}
