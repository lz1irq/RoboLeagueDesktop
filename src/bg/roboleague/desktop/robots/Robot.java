package bg.roboleague.desktop.robots;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Robot {
	public final static int LAPS = 3;
	private String name;
	private Date lapTimes[];
	
	public Robot(String rName) {
		name = rName;
		lapTimes =  new Date[LAPS];
		for(int i=0;i<LAPS;++i) {
			lapTimes[i] = new Date(0);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getLapTime(int lap) {
		return lapTimes[lap];
	}
	
	public void setLapTime(int lap, Date time) {
		lapTimes[lap] = time;
	}
	
	public void setLapTime(int lap, int time) {
		lapTimes[lap] = new Date(time);	
	}
	
	public Date getBestLap() {
		Date sortedLaps[] = lapTimes.clone();
		Arrays.sort(sortedLaps, new Comparator<Date>() {
	        @Override
	        public int compare(Date date1, Date date2) {
	            if (date1 == null && date2 == null) {
	                return 0;
	            }
	            if (date1 == null) {
	                return 1;
	            }
	            if (date2 == null) {
	                return -1;
	            }
	            return date1.compareTo(date2);
	        }});
		return sortedLaps[0];
	}
	
	public String toString() {
		return name;
	}
}
