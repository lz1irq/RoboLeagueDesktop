package bg.roboleague.desktop.robots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractListModel;

import bg.roboleague.desktop.robots.data.RobotExporter;
import bg.roboleague.desktop.robots.data.RobotImporter;

public class RobotList extends AbstractListModel<Robot> {

	private List<Robot> robots;
	private List<RobotExporter> exporters;
	private RobotImporter importer;

	public RobotList() {
		super();
		robots = new ArrayList<Robot>();
		exporters = new ArrayList<RobotExporter>();
	}

	public Robot add(String name) {
		robots.add(new Robot(name));
		fireIntervalAdded(this, robots.size() - 1, robots.size() - 1);
		return this.getLast();
	}

	public Robot add(Robot robot) {
		robots.add(robot);
		fireIntervalAdded(this, robots.size() - 1, robots.size() - 1);
		return this.getLast();
	}

	public void remove(Robot robot) {
		int removedIndex = robots.indexOf(robot);
		robots.remove(robot);
		fireIntervalRemoved(this, removedIndex + 1, removedIndex + 1);
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

	public void sortByBestLap() {
		Collections.sort(robots, new Comparator<Robot>() {
			@Override
			public int compare(Robot robot1, Robot robot2) {
				if (robot1.getBestLap() == null && robot2.getBestLap() == null) {
	                return 0;
	            }
	            if (robot1.getBestLap() == null) {
	                return 1;
	            }
	            if (robot2.getBestLap() == null) {
	                return -1;
	            }
	            return robot1.getBestLap().compareTo(robot2.getBestLap());
			}
		});
		fireContentsChanged(this, 0, robots.size());
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

	public RobotImporter getImporter() {
		return importer;
	}

	public void setImporter(RobotImporter importer) {
		this.importer = importer;
	}

	public void importRobots() throws IOException {
		robots = importer.importRobots();
	}

}
