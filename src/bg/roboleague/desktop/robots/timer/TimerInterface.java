package bg.roboleague.desktop.robots.timer;

public abstract class TimerInterface {
	private String portName;
	private int availiable;
	
	public abstract String[] getPortNames();
	
	public TimerInterface(String port) {
		portName = port;
		availiable = 0;
	};
	
	public abstract void write(String message);
	public abstract int availiable();
	public abstract String read();
	
}
