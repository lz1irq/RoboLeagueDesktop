package bg.roboleague.desktop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import bg.roboleague.desktop.robots.timer.SerialInterface;

public class SerialSetupWindow extends JPanel {
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 300;

	private final static int BAUDRATES[] = { 300, 600, 1200, 2400, 4800, 9600,
			14400, 19200, 28800, 31250, 38400, 57600, 115200 };
	
	private final static int DEFAULT_BAUDRATE = 9600;

	private JComboBox portsList;
	private JComboBox baudRateList;

	public SerialSetupWindow() {
		setupWindow();
		addGUIElements();
	}

	public int getBaudRate() {
		return (int) baudRateList.getSelectedItem();
	}

	public String getPortName() {
		return (String) portsList.getSelectedItem();
	}

	private void setupWindow() {
		setLayout(new MigLayout());
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void addGUIElements() {
		
		JLabel portLabel = new JLabel("Port");
		
		portsList = new JComboBox();
		List<String> ports = SerialInterface.getPortNames();
		for (String port : ports) {
			portsList.addItem(port);
		}

		JLabel baudRateLabel = new JLabel("Baud rate");
		baudRateList = new JComboBox();
		for (int i = 0; i < BAUDRATES.length; i++) {
			baudRateList.addItem(new Integer(BAUDRATES[i]));
			if(BAUDRATES[i] == DEFAULT_BAUDRATE) {
				baudRateList.setSelectedIndex(i);
			}
		}
		
		add(portLabel);
		add(baudRateLabel, "wrap");
		add(portsList);
		add(baudRateList);

	}

}
