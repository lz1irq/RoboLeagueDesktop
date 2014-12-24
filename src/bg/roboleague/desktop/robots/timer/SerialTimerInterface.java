package bg.roboleague.desktop.robots.timer;

// RXTX binary builds provided as a courtesy of Mfizz Inc. (http://mfizz.com/).
// Please see http://mfizz.com/oss/rxtx-for-java for more information.

import gnu.io.*;

public class SerialTimerInterface extends TimerInterface {

	public SerialTimerInterface(String port) {
		super(port);
	}

	@Override
	public String[] getPortNames() {
		
		return null;
	}

	@Override
	public void write(String message) {
		
	}

	@Override
	public int availiable() {
		
		return 0;
	}

	@Override
	public String read() {
		
		return null;
	}

}
