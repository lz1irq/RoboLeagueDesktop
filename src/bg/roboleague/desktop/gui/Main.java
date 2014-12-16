package bg.roboleague.desktop.gui;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import bg.roboleague.desktop.robots.RobotList;
import bg.roboleague.desktop.robots.data.*;

public class Main {

	public static void main(String[] args) throws IOException {
		final String robotFile = "robots.txt";
		final RobotList robots = new RobotList();
		robots.setImporter(new RobotFileImporter(robotFile));
		robots.addExporter(new RobotFileExporter(robotFile));
		robots.importRobots();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame ex = new MainWindow(robots);
				ex.setVisible(true);
			}
		});
	}

}
