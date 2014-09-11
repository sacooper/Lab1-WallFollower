/*****   
 * Group 5
 * @author Scott Cooper	- 260503452
 * @author Liqing Ding - 260457392
 * 
 * @requirement Sensor must be positioned at a 45 degree angle
 * @requirement Robot must be on the right of the wall
 * 
 * <h2>P Type Controller</h2><p>
 * The P Type controller extending the UltrasonicController. The way to turn left and
 * right based on error is defined below, with error = |this.distance - BANDCENTER|.
 * This value it to be passed via a call from the super class.</p>
 * 
 * error = |this.distance - BANDCENTER|
 * <h3>Left:</h3></p>
 * The speed of the left motor is max(200 - 10 * error, 50) rad/s<br>
 * The speed of the right motor is min(200 + 10 * error, 350) rad/s</p>
 * 
 * <h3>Right:</h3><p>
 * The speed of the left motor is: 	200 + error <br>
 * The speed of the right motor is:	200 - error</p>
 */

public class PController extends UltrasonicController {
	private static final int
		//Minimum speed a wheel will rotate at
		MIN_SPEED = 150,
		// Maximum speed a wheel will rotate at
		MAX_SPEED = 350,
		// Factor to multiply error by to determine correct speed
		SCALING_FACTOR = 10;
	
	
	/*****************************************************
	 * Instantiate a new PController, and start it moving
	 * @param bandCenter Ideal distance
	 * @param bandWidth Allowable threshold (|r-y|)
	 */
	public PController(int bandCenter, int bandWidth) {
		super(bandCenter, bandWidth);}

	/***************************************************** 
	 * Increases the speed of the rightMotor and reduces speed of the Left Motor
	 * Method is called if robot is traveling further than BANDCENTER + BANDWITH
	 * 
	 * @param error : the absolute value of (BANDCENTER - this.distance)
	 * 
	 * The speed of the left motor is 200 - 10 * error down to 50 rad/s
	 * The speed of the right motor is 200 + 10 * error up to 350 rad/s
	 */
	@Override
	protected void turnLeft(int error){
		leftMotor.setSpeed(Math.max((MOTOR_STRAIGHT - SCALING_FACTOR * error) , MIN_SPEED));
		rightMotor.setSpeed(Math.min((MOTOR_STRAIGHT + SCALING_FACTOR * error), MAX_SPEED));
		leftMotor.forward();
		rightMotor.forward();}
	
	/*****************************************************
	 * Increases the speed of the leftMotor and reduces the speed of the rightMotor
	 * Method is called if the robot is traveling less than BANDCENTER - BANDWITH
	 * 
	 * @param error : the absolute value of (BANDCENTER - this.distance)
	 * 
	 * The speed of the left motor is: 	200 + error
	 * The speed of the right motor is:	200 - error
	 */
	@Override
	protected void turnRight(int error){	
		leftMotor.setSpeed(MOTOR_STRAIGHT + error);
		rightMotor.setSpeed(MOTOR_STRAIGHT - error);
		leftMotor.forward();
		rightMotor.backward();
	}
}
