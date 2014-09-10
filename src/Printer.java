import lejos.nxt.LCD;
import lejos.nxt.Motor;

/*****   
 * Group 5
 * @author Scott Cooper	- 260503452
 * @author Liqing Ding - 260457392
 * 
 * <br><br>
 * Printer to display information to NXT LCD screen
 */
public class Printer{
	private final int option;
	/*****
	 * Instantiate a new Printer with 
	 * @param option
	 */
	public Printer(int option) {
		this.option = option;}
	
	/**
	 * Print the controller type and current distance
	 * @param distance The current distance from the wall.
	 */
	public void updateDisplay(final int distance) {
		new Thread(new Runnable(){
			public void run(){
				// Display current controller type
				LCD.clear();
				LCD.drawString("Controller Type is: ", 0, 0);
				switch (option){
					case Lab1.BANG_BANG: LCD.drawString("BangBang", 0, 1); break;
					case Lab1.PTYPE: LCD.drawString("P type", 0, 1); break;}
				
				// Display current distance from wall
				LCD.drawString("US Distance: " + distance, 0, 2 );
				
				// Display motor speeds
				LCD.drawInt(Motor.B.getSpeed(), 0, 3);
				LCD.drawInt(Motor.C.getSpeed(), 4, 3);
		}}).start();
	}
	
	/***
	 * Print the main menu
	 */
	public static void printMainMenu() {
		LCD.clear();
		LCD.drawString("Left: BangBang",  0, 0);
		LCD.drawString("Right: P Type", 0, 1);
	}
}
