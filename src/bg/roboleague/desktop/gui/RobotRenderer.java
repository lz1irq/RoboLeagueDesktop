package bg.roboleague.desktop.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import bg.roboleague.desktop.robots.Robot;

class RobotRenderer extends JLabel implements ListCellRenderer {
	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	public RobotRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Robot entry = (Robot) value;
		setText(entry.getName());
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}

}
