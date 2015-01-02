package bg.roboleague.desktop.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import bg.roboleague.desktop.robots.Robot;
import bg.roboleague.desktop.robots.RobotList;

public class LapTimesTableModel extends AbstractTableModel {

	private static String columnNames[] = { "Lap", "Time" };
	private Robot selectedRobot;
	private final static DateFormat timeFormat = new SimpleDateFormat("mm:ss:SSS");

	public LapTimesTableModel() {
		super();
		selectedRobot = null;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return Robot.LAPS;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String result = null;
		if (columnIndex == 0) {
			result = Integer.toString(rowIndex);
		} else if (columnIndex == 1) {
			if (selectedRobot != null)
				if (selectedRobot.getLapTime(rowIndex) != null) {
					result = timeFormat.format(selectedRobot.getLapTime(rowIndex));
				}
				else result = timeFormat.format(new Date(0));
		}

		return result;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean editable = false;
		if (columnIndex == 1) {
			editable = true;
		}
		return editable;
	}

	public void setSelectedRobot(Robot robot) {
		fireTableDataChanged();
		selectedRobot = robot;
	}

	public Robot getSelectedRobot() {
		return selectedRobot;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
			if (aValue instanceof String) {
				if(aValue.equals("0")) selectedRobot.setLapTime(rowIndex, 0);
				else
					try {
						selectedRobot.setLapTime(rowIndex, timeFormat.parse((String)aValue));
					} catch (ParseException e) {
						e.printStackTrace();
					}

			} else {
				selectedRobot.setLapTime(rowIndex, (int) aValue);
			}
			fireTableDataChanged();
		}
	}
}
