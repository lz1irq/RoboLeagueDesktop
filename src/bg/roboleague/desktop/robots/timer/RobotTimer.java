package bg.roboleague.desktop.robots.timer;

public class RobotTimer implements TimerDataReceiver {

	private SerialInterface timer;
	
	public RobotTimer(SerialInterface serial) {
		timer = serial;
		timer.addReceiver(this);
	}
	
	@Override
	public void receive(String command) {
		System.out.println(command);
	}

}
