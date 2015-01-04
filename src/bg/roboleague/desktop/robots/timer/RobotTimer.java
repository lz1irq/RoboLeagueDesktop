package bg.roboleague.desktop.robots.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RobotTimer implements SerialReceiver {

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
		serial.write(CALIBRATE);
	}

	public void exitCalibrationMode() {
		serial.write(STANDBY);
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
		serial.write(BEGIN_MEASURING);
	}

	public void stopMeasuring() {
		measuring = false;
		serial.write(STOP_MEASURING);
	}

	@Override
	public void receive(String command) {
		String parameter = command.substring(0, 3);
		int value = Integer.parseInt(command.substring(3));
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
		parameters.put(TIME_MINIMUM, 0);
		parameters.put(TIMES_MEASURE, 0);
		parameters.put(DELAY_MEASURE, 0);
		parameters.put(MEASURED_VALUE, 0);
		parameters.put(LAP_FINISHED, 0);
	}

}
