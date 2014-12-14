package bg.roboleague.desktop.robots;

import bg.roboleague.desktop.robots.data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RobotList extends ArrayList<Robot> {

	private List<RobotExporter> exporters;
	
	public RobotList() {
		super();
		exporters = new ArrayList<RobotExporter>();
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
	
	public void addExporter(RobotExporter exporter) {
		exporters.add(exporter);
	}
	
	public void export() throws IOException {
		for(RobotExporter exporter: exporters) {
			exporter.export(this);
		}
	}
}
