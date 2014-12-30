package bg.roboleague.desktop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.miginfocom.swing.MigLayout;
import bg.roboleague.desktop.robots.Robot;
import bg.roboleague.desktop.robots.RobotList;
import bg.roboleague.desktop.robots.timer.RobotTimer;

public class MainWindow extends JFrame {

	private RobotList robots;

	private final static String WINDOW_TITLE = "RoboLeague";
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 600;

	private Robot selectedRobot = null;
	private RobotTimer timer;
	private boolean timerEnabled;

	public MainWindow(RobotList robolist, RobotTimer timer, boolean enabled) {
		robots = robolist;
		timerEnabled = enabled;
		this.timer = timer;
		setUpWindow();
		addMenuBar();
		addGUIElements();
	}

	private void setUpWindow() {
		setLayout(new MigLayout());
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					robots.export();
				} catch (IOException e1) {
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

		final LapTimesTableModel tmod = new LapTimesTableModel();
		tmod.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent event) {
				if (event.getType() == event.UPDATE) {
					try {
						robots.export();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		JTable jt = new JTable(tmod);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane lapTimes = new JScrollPane(jt);

		final JList robotList = new JList(robots);
		robotList.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel model = (ListSelectionModel) e
								.getSource();
						if (model.isSelectionEmpty() == false) {
							selectedRobot = robots.getElementAt(model
									.getMinSelectionIndex());
							tmod.setSelectedRobot(selectedRobot);
						}
					}
				});

		robotList.setCellRenderer(new RobotRenderer());
		robotList.setVisibleRowCount(4);
		final JScrollPane robotView = new JScrollPane(robotList);

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String robotName = robotList.getSelectedValue().toString();
				if (robotName != null) {
					robots.remove(robotName);
				}
			}
		});

		JButton lapToggleButton = new JButton("Start");
		lapToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton btn = (JButton) event.getSource();
				if (btn.getText().equals("Start")) {
					btn.setText("Stop");
				} else {
					btn.setText("Start");
				}
			}
		});
		
		if(timerEnabled == false) {
			lapToggleButton.setEnabled(false);
		}

		add(robotView, "span 2, height :550:, width :208:");
		add(lapTimes, "wrap");
		add(addButton, "width :100:");
		add(removeButton, "width :100:");
		add(lapToggleButton);
	}

	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exit);

		JMenu settingsMenu = new JMenu("Settings");
		settingsMenu.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem calibration = new JMenuItem("Timer calibration");
		calibration.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel calibrationWindow = new TimerCalibrationWindow(timer);
				JOptionPane.showConfirmDialog(null, calibrationWindow, "Timer Calibration",
						JOptionPane.OK_CANCEL_OPTION);
				
			}
		});
		settingsMenu.add(calibration);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		menuBar.add(fileMenu);
		menuBar.add(settingsMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);
	}
}
