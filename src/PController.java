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
 * <h2>P Type Controller</h2><p>
 * The P Type controller extending the UltrasonicController. The way to turn left and
 * right based on error is defined below, with error = |this.distance - BANDCENTER|.
 * This value it to be passed via a call from the super class.</p>
 * 
 * error = |this.distance - BANDCENTER|
 * <h3>Left:</h3></p>
 * The speed of the left motor is max(200 - 10 * error, 50) °/s<br>
 * The speed of the right motor is min(200 + 10 * error, 350) °/s</p>
 * 
 * <h3>Right:</h3><p>
 * The speed of the left motor is: 	200 + error °/s <br>
 * The speed of the right motor is:	200 - error °/s </p>
 *****************************************************************************/

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
	 * @param bandCenter The Ideal distance from the wall (cm)
	 * @param bandWidth The allowable threshold of error in distance from the wall (cm)
	 */
	public PController(int bandCenter, int bandWidth) {
		super(bandCenter, bandWidth);}

	/***************************************************** 
	 * Increases the speed of the rightMotor and reduces speed of the Left Motor
	 * Method is called if robot is traveling further than BANDCENTER + BANDWITH
	 * 
	 * @param error : the absolute value of (BANDCENTER - this.distance)
	 * 
	 * The speed of the left motor is 200 - 10 * error down to 50 °/s
	 * The speed of the right motor is 200 + 10 * error up to 350 °/s
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
	 * The speed of the left motor is: 	200 + error °/s
	 * The speed of the right motor is:	-(200 - error) °/s
	 */
	@Override
	protected void turnRight(int error){	
		leftMotor.setSpeed(MOTOR_STRAIGHT + error);
		rightMotor.setSpeed(MOTOR_STRAIGHT - error);
		leftMotor.forward();
		rightMotor.backward();
	}
}
