package bg.roboleague.desktop.robots.timer;

// RXTX binary builds provided as a courtesy of Mfizz Inc. (http://mfizz.com/).
// Please see http://mfizz.com/oss/rxtx-for-java for more information.

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class SerialTimerInterface extends TimerInterface {

	private SerialPort serial;
	private HashMap<String, CommPortIdentifier> ports;

	@Override
	public List<String> getPortNames() {

		List<String> portNames = new ArrayList<String>();
		Enumeration portList = CommPortIdentifier.getPortIdentifiers();

		while (portList.hasMoreElements()) {
			CommPortIdentifier port = (CommPortIdentifier) portList
					.nextElement();
			if (port.getPortType() == port.PORT_SERIAL) {
				String portName = port.getName();
				ports.put(portName, port);
				portNames.add(portName);
			}
		}
		return portNames;
	}

	@Override
	public void write(String message) {

	}

	@Override
	public int availiable() {
		return availiable;
	}

	@Override
	public String read() {
		return null;
	}

	public void setPort(String portName) {

	}
}
