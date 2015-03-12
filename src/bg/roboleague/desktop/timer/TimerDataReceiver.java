package bg.roboleague.desktop.timer;

public interface TimerDataReceiver {
	
	public void receiveSensorValue(int value);
	public void receiveThresholdNear(int thresholdNear);
	public void receiveThresholdDistant(int thresholdDistant);
	public void receiveTolerance(int tolerance);
	public void receiveMeasurementTimes(int times);
	public void receiveDelay(int delay);
	public void receiveMinimalTime(int time);
	
	public void eventRobotStarted();
	public void eventRobotFinished(int time);
}
