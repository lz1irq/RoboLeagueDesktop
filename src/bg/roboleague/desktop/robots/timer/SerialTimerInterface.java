package bg.roboleague.desktop.robots.timer;

// RXTX binary builds provided as a courtesy of Mfizz Inc. (http://mfizz.com/).
// Please see http://mfizz.com/oss/rxtx-for-java for more information.

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class SerialTimerInterface extends TimerInterface {

	private static int DEFAULT_DATABITS = SerialPort.DATABITS_8;
	private static int DEFAULT_STOPBITS = SerialPort.STOPBITS_1;
	private static int DEFAULT_PARITY = SerialPort.PARITY_NONE;

	private SerialPort serialPort;
	private HashMap<String, CommPortIdentifier> ports;

	private BufferedReader input;
	private PrintWriter output;

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

	@Override
	public void connect(String portName, int baudRate) {
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier
					.getPortIdentifier(portName);
			CommPort port = portIdentifier.open("RoboLeague Desktop", 2000);
			serialPort = (SerialPort) port;

			serialPort.setSerialPortParams(baudRate, DEFAULT_DATABITS,
					DEFAULT_STOPBITS, DEFAULT_PARITY);

			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			output = new PrintWriter(serialPort.getOutputStream());

		} catch (NoSuchPortException e) {
			e.printStackTrace();
			System.out.println("Error: port " + portName + " not found!");
		} catch (PortInUseException e) {
			e.printStackTrace();
			System.out.println("Error: Port " + portName + " already in use!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			System.out.println("Error: invalid serial port parameters!");
			e.printStackTrace();
		}

	}

}
