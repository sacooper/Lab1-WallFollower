import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/******************************************************************************
 * Group 5
 * @author Scott Cooper	- 260503452
 * @author Liqing Ding - 260457392
 * 
 * @requirement Sensor must be positioned at a 45° angle
 * @requirement Robot must be on the right of the wall
 * @requirement Ultrasonic Sensor is plugged into port S1
 * @requirement The left and right motors are plugged into A and C respectively
 * 
 * Entry point - determine type of controlled based on LEFT or RIGHT button
 *****************************************************************************/

public class Lab1 {
	private static final int 
		BAND_CENTER = 32, 
		BAND_WIDTH = 3;
	
	//Assign better names to the buttons for use later on
	public static final int
		BANG_BANG=Button.ID_LEFT,
		PTYPE=Button.ID_RIGHT;	
	
	public static void main(String [] args) {
		// Print the main menu
		int option = 0;
		Printer.printMainMenu();
		
		// Wait for button press
		while (option == 0) option = Button.waitForAnyPress();		
		
		// Define ultrasonic poller
		UltrasonicPoller usPoller = null;
		
		// Instantiate the UltrasonicPoller based on button pressed
		switch(option) {
			case BANG_BANG:
				BangBangController bangbang = new BangBangController(BAND_CENTER, BAND_WIDTH);
				usPoller = new UltrasonicPoller(new UltrasonicSensor(SensorPort.S1), bangbang, new Printer(option));
				break;
			case PTYPE:
				PController p = new PController(BAND_CENTER, BAND_WIDTH);
				usPoller = new UltrasonicPoller(new UltrasonicSensor(SensorPort.S1), p, new Printer(option));
				break;
			default:
				LCD.drawString("Error - Invalid Button", 0, 1);
				System.exit(-1);
				break;}
		
		// Start poller (also starts printer)
		usPoller.start();
		
		//Wait for exit button press and exit
		while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		System.exit(0);
		
	}
}
