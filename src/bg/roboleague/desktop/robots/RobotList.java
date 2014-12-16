package bg.roboleague.desktop.robots;

import bg.roboleague.desktop.robots.data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class RobotList extends AbstractListModel<Robot> {

	private List<Robot> robots;
	private List<RobotExporter> exporters;

	public RobotList() {
		super();
		robots = new ArrayList<Robot>();
		exporters = new ArrayList<RobotExporter>();
	}

	public Robot add(String name) {
		System.out.println("Adding new robot with name " + name);
		robots.add(new Robot(name));
		fireIntervalAdded(this, robots.size()-1, robots.size()-1);
		return this.getLast();
	}

	public Robot add(Robot robot) {
		robots.add(robot);
		fireIntervalAdded(this, robots.size()-1, robots.size()-1);
		return this.getLast();
	}
	
	public void remove(Robot robot) {
		int removedIndex = robots.indexOf(robot);
		robots.remove(robot);
		fireIntervalRemoved(this, removedIndex+1, removedIndex+1);
	}
	
	public void remove(String name) {
		remove(findByName(name));
	}
	
	public Robot getLast() {
		return robots.get(robots.size() - 1);
	}

	public Robot findByName(String name) {
		Robot result = null;
		for (Robot robot : robots) {
			if (robot.getName().equals(name))
				result = robot;
		}
		return result;
	}

	@Override
	public Robot getElementAt(int index) {
		return robots.get(index);
	}

	@Override
	public int getSize() {
		return robots.size();
	}
	
	public void addExporter(RobotExporter exporter) {
		exporters.add(exporter);
	}

	public void export() throws IOException {
		for (RobotExporter exporter : exporters) {
			exporter.exportRobots(robots);
		}
	}

	
}
