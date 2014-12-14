package bg.roboleague.desktop.robots;

import java.util.ArrayList;

public class RobotList extends ArrayList<Robot> {

	public RobotList() {
		super();
	}
	
	public void add(String name) {
		this.add(new Robot(name));
	}
	
	public Robot findByName(String name) {
		Robot result = null;
		for(Robot robot: this) {
			if(robot.getName().equals(name)) result = robot;
		}
		return result;
	}
}
