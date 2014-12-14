package bg.roboleague.desktop.robots.data;

import bg.roboleague.desktop.robots.Robot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

public abstract class RobotExporter {
	
	protected String encoding;
	protected String exportLocation;
	
	public RobotExporter(String location) {
		this.exportLocation = location;
		this.encoding = "UTF-8";
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

	public abstract void export(List<Robot> robots) throws IOException;
	
}