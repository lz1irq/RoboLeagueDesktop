package bg.roboleague.desktop.robots.data;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

import com.cedarsoftware.*;
import com.cedarsoftware.util.io.JsonWriter;

import bg.roboleague.desktop.robots.Robot;

public class RobotFileExporter extends RobotExporter {
	
	public RobotFileExporter(String location) {
		super(location);
	}
	
	public RobotFileExporter(String location, String encoding) {
		super(location, encoding);
	}

	public void export(List<Robot> robots) throws IOException {
		String robotsJson = JsonWriter.objectToJson(robots);
		OutputStreamWriter exportStream = new OutputStreamWriter(new FileOutputStream(exportLocation), encoding);
		BufferedWriter output = new BufferedWriter(exportStream);
		output.write(robotsJson);
		output.flush();
		output.close();
	}
}
