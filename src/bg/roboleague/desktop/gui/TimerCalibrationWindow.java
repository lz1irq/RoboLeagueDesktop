package bg.roboleague.desktop.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import bg.roboleague.desktop.robots.timer.RobotTimer;

public class TimerCalibrationWindow extends JPanel {
	
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 500;
	
	private RobotTimer timer;
	
	public TimerCalibrationWindow(RobotTimer timer) {
		this.timer = timer;
		setupWindow();
		addGUIElements();
	}
	
	private void addGUIElements() {
		JLabel current = new JLabel("Current values:");
		
		JLabel sensorReading = new JLabel("Sensor value:");
		JLabel sensorReadingValue = new JLabel(Integer.toString(0));
		JLabel thresholdNear = new JLabel("Threshold near:");
		JLabel thresholdDistant = new JLabel("Threshold distant:");
		
		JLabel thresholdNearValue = new JLabel(Integer.toString(timer.getThresholdNear()));
		JLabel thresholdDistantValue = new JLabel(Integer.toString(timer.getThresholdDistant()));

		add(current, "wrap");
		add(sensorReading);
		add(sensorReadingValue, "wrap");
		add(thresholdNear);
		add(thresholdNearValue, "wrap");
		add(thresholdDistant);
		add(thresholdDistantValue, "wrap");
	}

	private void setupWindow() {
		setLayout(new MigLayout());
		UIManager.put("OptionPane.minimumSize",new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT)); 
	}
}
