package bg.roboleague.desktop.gui;

import javax.swing.table.AbstractTableModel;

import bg.roboleague.desktop.robots.Robot;

public class LapTimesTableModel extends AbstractTableModel {

	private static String columnNames[] = { "Lap", "Time" };
	private Robot selectedRobot;

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
		int result = 0;
		if (columnIndex == 0) {
			result = rowIndex + 1; // most people count from 1, not from 0
		} else if (columnIndex == 1) {
			if (selectedRobot != null)
				result = selectedRobot.getLapTime(rowIndex);
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
		if(columnIndex == 1) {
			selectedRobot.setLapTime(rowIndex, Integer.parseInt((String)aValue));
			fireTableDataChanged();
		}
	}

}
