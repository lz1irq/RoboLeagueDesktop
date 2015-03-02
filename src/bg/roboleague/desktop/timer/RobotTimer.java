package bg.roboleague.desktop.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RobotTimer implements SerialReceiver {

	//state control commands
	private final static String ENABLE_MEASURING = "EMS";
	private final static String DISABLE_MEASURING = "DMS";
	private final static String BEGIN_CALIBRATION = "BCL";
	private final static String END_CALIBRATION = "ECL";

	//callibration commands
	private final static String THRESHOLD_NEAR = "NER";
	private final static String THRESHOLD_DISTANT = "DST";
	private final static String TOLERANCE = "TOL";
	private final static String AMOUNT = "TMS";
	private final static String DELAY = "DLY";
	private final static String MINIMAL_TIME = "MTI";

	//reporting back events/values
	private final static String ROBOT_STARTED = "RST";
	private final static String ROBOT_FINISHED = "RFN";
	private final static String SENSOR_READING = "SRD";


	private final static int COMMAND_LENGTH = 3;
	private final static int PARAMETER_LENGTH = 4;
	
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
		switch(parameter) {
		case SENSOR_READING:
			for (TimerDataReceiver receiver : receivers) {
				receiver.receiveSensorValue(value);
			}
			break;
			
		case THRESHOLD_NEAR:
			for (TimerDataReceiver receiver : receivers) {
				receiver.receiveThresholdNear(value);
			}
			break;
		case THRESHOLD_DISTANT:
			for (TimerDataReceiver receiver : receivers) {
				receiver.receiveThresholdDistant(value);
			}
			break;
		case ROBOT_STARTED:
			for (TimerDataReceiver receiver : receivers) {
				receiver.eventRobotStarted();
			}
			break;
		case ROBOT_FINISHED:
			for (TimerDataReceiver receiver : receivers) {
				receiver.eventRobotFinished(value);
			}
			break;
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
	
	public int getThresholdNear() {
		return parameters.get(THRESHOLD_NEAR);
	}
	
	public void setThresholdNear(int threshold) {
		setParameter(THRESHOLD_NEAR, threshold);
	}
	
	public int getThresholdDistant() {
		return parameters.get(THRESHOLD_DISTANT);
	}
	
	public void setThresholdDistant(int threshold) {
		setParameter(THRESHOLD_DISTANT, threshold);
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
