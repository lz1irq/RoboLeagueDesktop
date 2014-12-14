package bg.roboleague.desktop.robots;

public class Robot {
	public final static int LAPS = 3;
	private String name;
	private int lapTimes[];
	
	public Robot(String rName) {
		name = rName;
		lapTimes =  new int[LAPS];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getLapTime(int lap) {
		return lapTimes[lap];
	}
	
	public void setLapTime(int lap, int time) {
		lapTimes[lap] = time;
	}
	
	public String toString() {
		return name;
	}
}
