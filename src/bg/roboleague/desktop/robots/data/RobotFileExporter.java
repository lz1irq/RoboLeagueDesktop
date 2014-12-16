package bg.roboleague.desktop.robots.data;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import bg.roboleague.desktop.robots.Robot;

import com.cedarsoftware.util.io.JsonWriter;

public class RobotFileExporter extends RobotExporter {
	
	public RobotFileExporter(String location) {
		super(location);
	}
	
	public RobotFileExporter(String location, String encoding) {
		super(location, encoding);
	}

	public void exportRobots(List<Robot> robots) throws IOException {
		String robotsJson = JsonWriter.objectToJson(robots);
		OutputStreamWriter exportStream = new OutputStreamWriter(new FileOutputStream(exportLocation), encoding);
		BufferedWriter output = new BufferedWriter(exportStream);
		output.write(robotsJson);
		output.flush();
		output.close();
	}
}
