package bg.roboleague.desktop.robots.data;

import java.io.IOException;
import java.util.List;

import bg.roboleague.desktop.robots.Robot;

public abstract class RobotImporter {
	protected String importLocation;
	protected String encoding;
	
	public RobotImporter(String location) {
		importLocation = location;
		encoding = "UTF-8";
	}
	
	public RobotImporter(String location, String encoding) {
		importLocation = location;
		this.encoding = encoding;
	}
	
	public abstract List<Robot> importRobots() throws IOException;
}
