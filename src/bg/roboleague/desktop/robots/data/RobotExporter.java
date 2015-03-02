package bg.roboleague.desktop.robots.data;

import java.io.IOException;
import java.util.List;

import bg.roboleague.desktop.robots.Robot;

public abstract class RobotExporter {
	
	protected final static String DEFAULT_ENCODING = "UTF-8";
	
	protected String encoding;
	protected String exportLocation;
	
	public RobotExporter(String location) {
		this(location, DEFAULT_ENCODING);
	}
	
	public RobotExporter(String location, String encoding) {
		this.exportLocation = location;
		this.encoding = encoding;
	}

	public String getExportLocation() {
		return exportLocation;
	}

	public void setExportLocation(String exportLocation) {
		this.exportLocation = exportLocation;
	}

	public abstract void exportRobots(List<Robot> robots);
	
}