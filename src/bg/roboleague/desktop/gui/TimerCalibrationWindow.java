package bg.roboleague.desktop.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import bg.roboleague.desktop.timer.RobotTimer;
import bg.roboleague.desktop.timer.TimerDataReceiver;

public class TimerCalibrationWindow extends JPanel implements TimerDataReceiver {

	private final static int WINDOW_WIDTH = 400;
	private final static int WINDOW_HEIGHT = 150;
	private final static String WINDOW_CAPTION = "Timer Calibration";

	JLabel sensorReadingValue;
	JLabel thresholdNearValue;
	JLabel thresholdDistantValue;

	private RobotTimer timer;

	public TimerCalibrationWindow(RobotTimer timer) {
		this.timer = timer;
		setupWindow();
		addGUIElements();

	}

	public static int display(RobotTimer timer) {
		JPanel calibrationWindow = new TimerCalibrationWindow(timer);
		timer.addReceiver((TimerDataReceiver) calibrationWindow);

		int userChoice = JOptionPane.showConfirmDialog(null, calibrationWindow, "WINDOW_CAPTION",
				JOptionPane.OK_CANCEL_OPTION);

		timer.removeReceiver((TimerDataReceiver) calibrationWindow);
		return userChoice;
	}

	private void addGUIElements() {

		String setButtonText = "Set to sensor";

		JLabel sensorReading = new JLabel("Sensor value:");
		sensorReadingValue = new JLabel(Integer.toString(0));
		JLabel thresholdNear = new JLabel("Threshold near:");
		JLabel thresholdDistant = new JLabel("Threshold distant:");

		thresholdNearValue = new JLabel(Integer.toString(timer.getParameter(timer.THRESHOLD_NEAR)));
		thresholdDistantValue = new JLabel(Integer.toString(timer.getParameter(timer.THRESHOLD_DISTANT)));

		JButton setNear = new JButton(setButtonText);
		setNear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.setParameter(RobotTimer.THRESHOLD_NEAR, timer.getParameter(RobotTimer.SENSOR_READING));
			}
		});

		JButton setDistant = new JButton(setButtonText);
		setDistant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.setParameter(RobotTimer.THRESHOLD_DISTANT, timer.getParameter(RobotTimer.SENSOR_READING));
			}
		});

		add(sensorReading);
		add(sensorReadingValue, "wrap");
		add(thresholdNear);
		add(thresholdNearValue);
		add(setNear, "wrap");
		add(thresholdDistant);
		add(thresholdDistantValue);
		add(setDistant, "wrap");
	}

	private void setupWindow() {
		setLayout(new MigLayout());
		UIManager.put("OptionPane.minimumSize", new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	@Override
	public void receive(String parameter, int value) {

		String valueText = Integer.toString(value);

		switch (parameter) {
		case RobotTimer.SENSOR_READING:
			sensorReadingValue.setText(valueText);
			break;
		case RobotTimer.THRESHOLD_NEAR:
			thresholdNearValue.setText(valueText);
			break;
		case RobotTimer.THRESHOLD_DISTANT:
			thresholdDistantValue.setText(valueText);
		}

	}
}
