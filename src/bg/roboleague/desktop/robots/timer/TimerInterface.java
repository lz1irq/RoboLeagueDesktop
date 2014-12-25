package bg.roboleague.desktop.robots.timer;

import java.util.ArrayList;
import java.util.List;

public abstract class TimerInterface {
	
	protected List<TimerDataReceiver> receivers;
	
	public TimerInterface() {
		receivers = new ArrayList<TimerDataReceiver>();
	}
	
	public TimerInterface(TimerDataReceiver receiver) {
		this();
		receivers.add(receiver);
	}
	
	public abstract List<String> getPortNames();
	public abstract void connect(String portName, int baudRate);
	public abstract void write(String message);
	
	public void addReceiver(TimerDataReceiver receiver) {
		receivers.add(receiver);
	}
	public void removeReceiver(TimerDataReceiver receiver) {
		receivers.remove(receiver);
	}
	protected void notifyReceivers(String command) {
		for(TimerDataReceiver receiver : receivers) {
			receiver.receive(command);
		}
	}
}
