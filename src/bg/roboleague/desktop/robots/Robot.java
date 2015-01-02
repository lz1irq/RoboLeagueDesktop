package bg.roboleague.desktop.robots;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Robot {
	public final static int LAPS = 3;
	private String name;
	private Date lapTimes[];
	
	public Robot(String rName) {
		name = rName;
		lapTimes =  new Date[LAPS];
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
	
	public String toString() {
		return name;
	}
}
