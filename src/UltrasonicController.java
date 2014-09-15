import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

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
 * Abstraction of an UltrasonicController, which elides the details of
 * how to turn left and right based on the error to the extending classes.
 *****************************************************************************/

public abstract class UltrasonicController {
	private static final int
		// Maximum number of gaps before turning
		FILTER_OUT = 20;
	
	/********
	 * Motor speed when going straight in °/s*/
	protected static final int MOTOR_STRAIGHT = 200;

	private final int 
		// Ideal Position
		BAND_CENTER,
		// Allowable threshold of error
		BAND_WIDTH;
	
	protected final NXTRegulatedMotor 
		leftMotor = Motor.A, 
		rightMotor = Motor.C; 

	private int distance, gapCount;
			
	/******
	 * Create a new UltrasonicController with given bandCenter and bandWidth.
	 * This values must be passed from the extending class.
	 * @param bandCenter The Ideal distance from the wall (cm)
	 * @param bandWidth The allowable threshold of error in distance from the wall (cm)
	 */
	public UltrasonicController(int bandCenter, int bandWidth){
		this.BAND_CENTER = bandCenter;
		this.BAND_WIDTH = bandWidth;
		gapCount = 0;
		distance = 0;
		straight();
	}
	/*****
	 * Method to processes and act based on current distance from wall
	 * @param distance Distance from the wall
	 */
	public void processUSData(int distance) {
		this.distance = distance;
		//difference between ideal distance and real distance (y - r)
		int error = (BAND_CENTER - this.distance);
		//Too far away from wall
		if (error < -BAND_WIDTH){
			// increment filterControl and if found turn, go left
			if (++gapCount > FILTER_OUT)
				turnLeft(Math.abs(error));}
		//Too Close to wall
		else if (error > BAND_WIDTH){
			gapCount = 0;
			turnRight(Math.abs(error));}
		// Within allowable threshold
		else {
			gapCount = 0;
			straight();
		}
	}
	
	/*****************************************************
	 * Sets the robot to go straight at speed MOTOR_STRAIGHT
	 * Called if robot is traveling between BANDCENTER +- BANDWITH
	 */
	public void straight(){
		leftMotor.setSpeed(MOTOR_STRAIGHT);		
		rightMotor.setSpeed(MOTOR_STRAIGHT); 
		leftMotor.forward();
		rightMotor.forward();
	}	
	
	protected abstract void turnLeft(int error);
	protected abstract void turnRight(int error);
	
	/***
	 * Get the current distance
	 * @return The current distance from the wall (cm)
	 */
	public int readUSDistance(){return this.distance;}
}
