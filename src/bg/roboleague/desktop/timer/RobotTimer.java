package bg.roboleague.desktop.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RobotTimer implements SerialReceiver {

	//state control commands
	public final static String ENABLE_MEASURING = "EMS";
	public final static String DISABLE_MEASURING = "DMS";
	public final static String BEGIN_CALIBRATION = "BCL";
	public final static String END_CALIBRATION = "ECL";

	//callibration commands
	public final static String THRESHOLD_NEAR = "NER";
	public final static String THRESHOLD_DISTANT = "DST";
	public final static String TOLERANCE = "TOL";
	public final static String AMOUNT = "TMS";
	public final static String DELAY = "DLY";
	public final static String MINIMAL_TIME = "MTI";

	//reporting back events/values
	public final static String ROBOT_STARTED = "RST";
	public final static String ROBOT_FINISHED = "RFN";
	public final static String SENSOR_READING = "SRD";


	public final static int COMMAND_LENGTH = 3;
	public final static int PARAMETER_LENGTH = 4;
	
	private SerialInterface serial;

	private HashMap<String, Integer> parameters;

	private List<TimerDataReceiver> receivers;

	private boolean measuring = false;

	public RobotTimer(SerialInterface sint) {
		serial = sint;
		serial.addReceiver(this);

		receivers = new ArrayList<TimerDataReceiver>();
		initializeParameters();
	}

	public boolean isMeasuring() {
		return measuring;
	}

	public void addReceiver(TimerDataReceiver receiver) {
		receivers.add(receiver);
	}

	public void removeReceiver(TimerDataReceiver receiver) {
		receivers.remove(receiver);
	}

	public void notifyReceivers(String parameter, int value) {
		for (TimerDataReceiver receiver : receivers) {
			receiver.receive(parameter, value);
		}
	}

	public void enterCalibrationMode() {
		serial.write(BEGIN_CALIBRATION);
	}

	public void exitCalibrationMode() {
		serial.write(END_CALIBRATION);
	}
	
	public int getSensorReading() {
		return parameters.get(SENSOR_READING);
	}

	public int getParameter(String parameter) {
		return parameters.get(parameter);
	}

	public void setParameter(String parameter, int value) {
		if (parameters.containsKey(parameter)) {
			serial.write(parameter + value);
		}
	}

	public void startMeasuring() {
		measuring = true;
		serial.write(ENABLE_MEASURING);
	}

	public void stopMeasuring() {
		measuring = false;
		serial.write(DISABLE_MEASURING);
	}

	@Override
	public void receive(String command) {
		String parameter = command.substring(0, COMMAND_LENGTH);
		int value = Integer.parseInt(command.substring(COMMAND_LENGTH));
		notifyReceivers(parameter, value);

		if (parameters.containsKey(parameter)) {
			parameters.put(parameter, value);

		} else {
			System.out.println("Error: invalid parameter " + parameter + " received from timer!");
		}
	}

	private void set(String parameter, int value) {

		parameters.put(parameter, value);
	}

	private void initializeParameters() {
		parameters = new HashMap<String, Integer>();

		parameters.put(THRESHOLD_NEAR, 0);
		parameters.put(THRESHOLD_DISTANT, 0);
		parameters.put(TOLERANCE, 0);
		parameters.put(MINIMAL_TIME, 0);
		parameters.put(AMOUNT, 0);
		parameters.put(DELAY, 0);
		parameters.put(SENSOR_READING, 0);
		parameters.put(ROBOT_FINISHED, 0);
	}

}
