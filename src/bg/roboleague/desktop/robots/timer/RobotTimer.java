package bg.roboleague.desktop.robots.timer;

import java.util.HashMap;

import javax.swing.Timer;

public class RobotTimer implements TimerDataReceiver {

	public final static String THRESHOLD_NEAR = "NER";
	public final static String THRESHOLD_DISTANT = "DST";
	public final static String TOLERANCE = "TOL";
	public final static String TIME_MINIMUM = "TMN";

	public final static String TIMES_MEASURE = "TMS";
	public final static String DELAY_MEASURE = "DLY";

	public final static String BEGIN_MEASURING = "BMS";
	public final static String STOP_MEASURING = "SMS";
	public final static String CALIBRATE = "CAL";
	public final static String STANDBY = "SBY";

	public final static String MEASURED_VALUE = "RAW";
	public final static String LAP_STARTED = "SLP";
	public final static String LAP_FINISHED = "FLP";

	private SerialInterface serial;

	private HashMap<String, Integer> parameters;

	private HashMap<String, Boolean> parameterRequests;

	public RobotTimer(SerialInterface sint) {
		serial = sint;
		serial.addReceiver(this);
		initializeParameters();
	}

	public void enterCalibrationMode() {
		serial.write(CALIBRATE);
	}
	
	public void exitCalibrationMode() {
		serial.write(STANDBY);
	}
	
	public int getThresholdNear() {
		return request(THRESHOLD_NEAR);
	}
	
	public int getThresholdDistant() {
		return request(THRESHOLD_DISTANT);
	}
	
	public void startMeasuring() {
		serial.write(BEGIN_MEASURING);
	}

	public void stopMeasuring() {
		serial.write(STOP_MEASURING);
	}

	@Override
	public void receive(String command) {
		String parameter = command.substring(0, 3);
		int value = Integer.parseInt(command.substring(3));

		if (parameters.containsKey(parameter)) {
			parameters.put(parameter, value);
			if (parameter.equals(LAP_FINISHED)) {
				// TODO - communicate that a robot has finished
			}
		} else {
			System.out.println("Error: invalid parameter " + parameter + " received from timer!");
		}
	}

	private int request(String parameter) {
		return parameters.get(parameter);
	}

	private void set(String parameter, int value) {
		serial.write(parameter + Integer.toString(value));
		parameters.put(parameter, value);
	}
	
	private void initializeParameters() {
		parameters = new HashMap<String, Integer>();
		parameterRequests = new HashMap<String, Boolean>();

		parameters.put(THRESHOLD_NEAR, 0);
		parameters.put(THRESHOLD_DISTANT, 0);
		parameters.put(TOLERANCE, 0);
		parameters.put(TIME_MINIMUM, 0);
		parameters.put(TIMES_MEASURE, 0);
		parameters.put(DELAY_MEASURE, 0);
		parameters.put(MEASURED_VALUE, 0);
		parameters.put(LAP_FINISHED, 0);
	}

}
