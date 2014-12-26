package bg.roboleague.desktop.robots.timer;

public class RobotTimer implements TimerDataReceiver {

	private TimerInterface timer;
	
	public RobotTimer(TimerInterface ti) {
		timer = ti;
		timer.addReceiver(this);
	}
	
	@Override
	public void receive(String command) {
		System.out.println(command);
	}

}
