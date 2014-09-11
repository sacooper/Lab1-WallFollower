import lejos.nxt.UltrasonicSensor;

/*****   
 * Group 5
 * @author Scott Cooper	- 260503452
 * @author Liqiang Ding	- 260457392
 * 
 * @requirement Sensor must be positioned at a 45 degree angle
 * @requirement Robot must be on the right of the wall
 * 
 * Ultrasonic Poller that controls the rate at which the NXT Unit
 * readjusts. This must be instantiated with the UltrasonicSensor to
 * use and the UltrasonicController to act upon the change in distance.
 */

public class UltrasonicPoller extends Thread {
	private UltrasonicSensor us;
	private UltrasonicController controller;
	private Printer printer;
	private static final int DELAY = 10;
	
	/*******
	 * Create a new ultrasonic controller
	 * @param us Ultrasonic Sensor to use for polling
	 * @param cont UltrasonicController to use (determines how it follows the wall)
	 */
	public UltrasonicPoller(UltrasonicSensor us, UltrasonicController cont, Printer printer) {
		this.us = us;
		this.controller = cont;
		this.printer = printer;
	}
	
	public void run() {
		while (true) {
			//process collected data
			int distance = us.getDistance();
			controller.processUSData(distance);
			printer.updateDisplay(distance);

			
			try { Thread.sleep(DELAY); } catch(Exception e){}
		}
	}

}
