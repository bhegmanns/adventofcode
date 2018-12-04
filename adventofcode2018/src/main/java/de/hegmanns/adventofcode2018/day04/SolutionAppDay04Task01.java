package de.hegmanns.adventofcode2018.day04;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SolutionAppDay04Task01 {

	public static void main(String[] args) {
		String[] records = Input04.getStrings();
		SortedSet<Record> recordSet = new TreeSet<>();
		for (String record : records) {
			recordSet.add(new Record(record));
		}
		
		Long currentGuardId = null;
		for (Record record : recordSet) {
			if (currentGuardId == null) {
				currentGuardId = record.getGuardId();
			}
			if (record.getGuardId() != null && currentGuardId.longValue() != record.getGuardId()) {
				currentGuardId = record.getGuardId();
			}
			
			record.placeGuardIdIfNotSet(currentGuardId);
			
			if (record.getGuardId() == null) {
				System.out.println("PROBLEM: " + record.getOriginalInfoString());
			}
		}
		
		System.out.println(recordSet);
		
		Map<Long, Map<LocalDate, TimeTable>> times = new HashMap<>();
		boolean beforeZero = false;
		Record lastRecord = null;
		for (Record record : recordSet) {
			Map<LocalDate, TimeTable> guardTimeMap = times.get(record.getGuardId());
			if (guardTimeMap == null) {
				guardTimeMap = new HashMap<>();
				times.put(record.getGuardId(), guardTimeMap);
			}
			
			TimeTable timeTable = guardTimeMap.get(record.getTime().toLocalDate());
			if (timeTable == null) {
				timeTable = new TimeTable();
				guardTimeMap.put(record.getTime().toLocalDate(), timeTable);
			}
			
			if (beforeZero && lastRecord.getTime().toLocalDate().compareTo(record.getTime().toLocalDate()) != 0) {
				timeTable.setUp(lastRecord.getTime().toLocalTime(), Record.Happing.ASLEEP == lastRecord.getHappening(), lastRecord);
			}
			
			beforeZero = timeTable.setUp(record.getTime().toLocalTime(), Record.Happing.ASLEEP == record.getHappening(), record);
			if (beforeZero) {
				lastRecord = record;
			}
		}
		
		Set<Long> guardIds = times.keySet();
		Map<Long, Long> timeForGuard = new HashMap<>();
		
		long bestSleeperGuard = -1;
		long bestSleeperTime = -1;
		for (long guardId : guardIds) {
			Map<LocalDate, TimeTable> timeTables = times.get(guardId);
			long sleepingMinutes = 0;
			for (TimeTable timeTable : timeTables.values()) {
				sleepingMinutes += timeTable.getSleepingMinutes();
			}
			timeForGuard.put(guardId, sleepingMinutes);
			if (sleepingMinutes > bestSleeperTime) {
				bestSleeperGuard = guardId;
				bestSleeperTime = sleepingMinutes;
			}
		}
		
		System.out.println("Found most sleeping guard: id= " + bestSleeperGuard + " with " + bestSleeperTime + " min");
		System.out.println("Most sleeping minute: " + getMostSleepingMinute(times.get(bestSleeperGuard)));
		System.out.println("Product: " + (bestSleeperGuard * getMostSleepingMinute(times.get(bestSleeperGuard))));
	}
	
	public static int getMostSleepingMinute(Map<LocalDate, TimeTable> timeTables) {
		int mostSleepingMinute = -1;
		int mostCounts = -1;
		Collection<TimeTable> times = timeTables.values();
		for (int i = 0 ; i < 60 ; i++) {
			int count = 0;
			for (TimeTable tt : times) {
				count += tt.isSleeping(i) ? 1 : 0;
			}
			if (count > mostCounts) {
				mostCounts = count;
				mostSleepingMinute = i;
			}
		}
		
		return mostSleepingMinute;
	}
}
