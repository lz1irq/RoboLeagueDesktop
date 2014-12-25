package bg.roboleague.desktop.robots.timer;

import java.util.List;

public abstract class TimerInterface {
	protected int availiable;
	
	public abstract List<String> getPortNames();
	
	public TimerInterface() {
		availiable = 0;
	};
	
	public abstract void connect(String portName, int baudRate);
	
	public abstract void write(String message);
	public abstract int availiable();
	public abstract String read();
	
}
