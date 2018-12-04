package de.hegmanns.adventofcode2018.day04;

import java.time.LocalTime;

public class TimeTable {

	private int hour;
	private boolean[] isSleepingMinute = new boolean[60]; 
	
	public boolean setUp(LocalTime time, boolean sleeping, Record record) {
		this.hour = time.getHour();
		boolean beforeZero = hour == 23;
		for (int i = time.getMinute() ; i < isSleepingMinute.length ; i++) {
			isSleepingMinute[i] = sleeping;
		}
		if (hour == 23 || hour == 0) {
//			beforeZero = true;
		}else {
			throw new RuntimeException("unexpected situation, hour isn't 0 or 23: " + hour + "; Record:" + record);
		}
		return beforeZero;
	}
	
	public boolean isSleeping(int minute) {
		if (hour == 23) {
			return false;
		}
		return isSleepingMinute[minute];
	}
	public long getSleepingMinutes() {
		if (hour == 23) {
			return 0;
		}
		int count = 0;
		
		for (boolean isSleeping : isSleepingMinute) {
			if (isSleeping) {
				count++;
			}
		}
		
		return count;
	}
}
