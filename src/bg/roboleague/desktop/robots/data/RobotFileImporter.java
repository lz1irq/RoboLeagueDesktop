package bg.roboleague.desktop.robots.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bg.roboleague.desktop.robots.Robot;

import com.cedarsoftware.util.io.JsonReader;

public class RobotFileImporter extends RobotImporter {

	public RobotFileImporter(String location) {
		super(location);
	}
	
	public RobotFileImporter(String location, String encoding) {
		super(location, encoding);
	}

	@Override
	public List<Robot> importRobots() throws IOException {
		List<Robot> robots = new ArrayList<Robot>();
		
		File robotFile = new File(importLocation);
		FileInputStream input = new FileInputStream(robotFile);
		byte[] robotsJSON = new byte[(int)robotFile.length()];
		input.read(robotsJSON);
		robots = (List<Robot>) JsonReader.jsonToJava(new String(robotsJSON, encoding));
		return robots;
	}

}
