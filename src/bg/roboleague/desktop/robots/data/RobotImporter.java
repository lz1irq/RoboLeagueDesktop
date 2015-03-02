package bg.roboleague.desktop.robots.data;

import java.io.IOException;
import java.util.List;

import bg.roboleague.desktop.robots.Robot;

public abstract class RobotImporter {
	
	protected final static String DEFAULT_ENCODING = "UTF-8";
	
	protected String encoding;
	protected String importLocation;
	
	public RobotImporter(String location) {
		this(location, DEFAULT_ENCODING);
	}
	
	public RobotImporter(String location, String encoding) {
		importLocation = location;
		this.encoding = encoding;
	}
	
	public String getImportLocation() {
		return importLocation;
	}

	public void setImportLocation(String importLocation) {
		this.importLocation = importLocation;
	}
	
	public abstract List<Robot> importRobots() throws IOException;
}
