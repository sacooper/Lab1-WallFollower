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
 * <h2>Bang Bang Controller</h2>
 * Turning right:
 * 	Turn in place with left motor going at 200 °/s and the right 
 * 	motor going backwards at 200°/s
 * 
 * Turning left:
 * 	Left wheel turns at 100 °/s
 * 	Right wheel turns at 250 °/s
 *****************************************************************************/
public class BangBangController extends UltrasonicController{
	
	private static final int 
		// Speed of left wheel when correcting
		LEFT_SPEED = 100,
		// Speed of right wheel when correcting
		RIGHT_SPEED = 250;
		
	/********************************************************
	 * Initialize a new BangBangController, and start it moving
	 * @param bandCenter The Ideal distance from the wall (cm)
	 * @param bandWidth The allowable threshold of error in distance from the wall (cm)
	 */
	public BangBangController(int bandCenter, int bandWidth) {
		super(bandCenter, bandWidth);}

	/********************************************************
	 * Increases the speed of the leftMotor and reduces the speed of the rightMotor
	 * Method is called if the robot is traveling less than BANDCENTER - BANDWITH
	 * 
	 * The speed of the left motor is: 		200 °/s
	 * The speed of the right motor is:		-200 °/s
	 */
	protected void turnRight(int error) {
		leftMotor.setSpeed(MOTOR_STRAIGHT);
		rightMotor.setSpeed(MOTOR_STRAIGHT);
		leftMotor.forward();
		rightMotor.backward();}
	
	/********************************************************
	 * Increases the speed of the rightMotor and reduces speed of the Left Motor
	 * Method is called if robot is traveling further than BANDCENTER + BANDWITH
	 * 
	 * The speed of the left motor is LEFT_SPEED (100 °/s)
	 * The speed of the right motor is RIGHT_SPEED (200 °/s)
	 */
	protected void turnLeft(int error) {
		leftMotor.setSpeed(LEFT_SPEED);
		rightMotor.setSpeed(RIGHT_SPEED);
		leftMotor.forward();
		rightMotor.forward();}
}
