package bg.roboleague.desktop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import bg.roboleague.desktop.robots.RobotList;

public class MainWindow extends JFrame {

	private RobotList robots;

	private final static String WINDOW_TITLE = "RoboLeague";
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 600;

	public MainWindow(RobotList robolist) {
		robots = robolist;
		setUpWindow();
		addGUIElements();
	}

	private void setUpWindow() {
		setLayout(new MigLayout());
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					robots.export();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void addGUIElements() {
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String robotName = (String) JOptionPane
						.showInputDialog("New robot name");
				if (robotName != null && robotName.length() > 0) {
					robots.add(robotName);
				}
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

		final JList robotList = new JList(robots);
		robotList.setCellRenderer(new RobotRenderer());
		robotList.setVisibleRowCount(4);
		final JScrollPane robotView = new JScrollPane(robotList);

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

		add(robotView, "span 2, height :500:, width :208: ,wrap");
		add(addButton, "width :100:");
		add(removeButton, "width :100:");
	}
}
