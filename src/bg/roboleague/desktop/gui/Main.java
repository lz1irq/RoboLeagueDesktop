package bg.roboleague.desktop.gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bg.roboleague.desktop.robots.RobotList;
import bg.roboleague.desktop.robots.data.*;
import bg.roboleague.desktop.robots.timer.*;

public class Main {

	public static void main(String[] args) throws IOException {
		final String robotFile = "robots.txt";
		final RobotList robots = new RobotList();

		final boolean timerEnabled;
		boolean enabled = true;

		robots.setImporter(new RobotFileImporter(robotFile));
		robots.addExporter(new RobotFileExporter(robotFile));
		robots.importRobots();

		final SerialInterface serial = new SerialInterface();

		SerialSetupWindow setup = new SerialSetupWindow();
		final int result = JOptionPane.showConfirmDialog(null, setup, "Timer Setup", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			serial.connect(setup.getPortName(), setup.getBaudRate());
		} else {
			timerEnabled = false;
			JOptionPane.showMessageDialog(null, "You will only be able to look at/edit current scores",
					"Timer not set up properly", JOptionPane.WARNING_MESSAGE);
		}

		final RobotTimer timer = new RobotTimer(serial);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame ex = new MainWindow(robots, timer, result == JOptionPane.OK_OPTION);
				ex.setVisible(true);
			}
		});

	}

}
