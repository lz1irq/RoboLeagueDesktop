package bg.roboleague.desktop.timer;

public interface TimerDataReceiver {
	
	public void receiveSensorValue(int value);
	public void receiveThresholdNear(int thresholdNear);
	public void receiveThresholdDistant(int thresholdDistant);
	
	public void eventRobotStarted();
	public void eventRobotFinished(int time);
}
