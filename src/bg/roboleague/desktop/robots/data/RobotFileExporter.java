package bg.roboleague.desktop.robots.data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import bg.roboleague.desktop.robots.Robot;

import com.cedarsoftware.util.io.JsonWriter;

public class RobotFileExporter extends RobotExporter {

	public RobotFileExporter(String location) {
		this(location, DEFAULT_ENCODING);
	}

	public RobotFileExporter(String location, String encoding) {
		super(location, encoding);
	}

	public void exportRobots(List<Robot> robots) {

		OutputStreamWriter exportStream;
		BufferedWriter output = null;
		try {
			exportStream = new OutputStreamWriter(new FileOutputStream(
					exportLocation), encoding);
			output = new BufferedWriter(exportStream);
			String robotsJson = JsonWriter.objectToJson(robots);
			output.write(robotsJson);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
