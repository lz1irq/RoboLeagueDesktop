package bg.roboleague.desktop.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import bg.roboleague.desktop.robots.Robot;
import bg.roboleague.desktop.robots.RobotList;
import bg.roboleague.desktop.robots.data.RobotFileExporter;

public class MainWindow extends JFrame {

	private static RobotList robots = new RobotList();

	private final static String WINDOW_TITLE = "RoboLeague";
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 600;

	public MainWindow() {

		setLayout(new GridLayout(2, 1));
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				robots.add("random");
			}
		});

		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					robots.export();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		robots.add("dsa");
		robots.add("dsa");
		robots.add("dsa");

		final JList robotList = new JList(robots);
		robotList.setCellRenderer(new RobotRenderer());
		robotList.setVisibleRowCount(4);
		final JScrollPane robotView = new JScrollPane(robotList);

		add(robotView);
		add(exportButton);
		add(addButton);

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String robotName = robotList.getSelectedValue().toString();
				if (robotName != null) {
					System.out.println("Deleting robot with name " + robotName);
					robots.remove(robotName);
				}
			}
		});

		add(removeButton);

		pack();

	}

	public static void main(String[] args) {

		robots.addExporter(new RobotFileExporter("robots.txt"));

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow ex = new MainWindow();
				ex.setVisible(true);
			}
		});
	}
}
