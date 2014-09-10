/********************************************************
 * Group 5
 * @author Scott Cooper	- 260503452
 * @author Liqing Ding - 260457392
 * 
 * @requirement Sensor must be positioned at a 45 degree angle
 * @requirement Robot must be on the right of the wall
 * 
 * <h2>Bang Bang Controller</h2>
 * 
 * BangBang constants: 	<br>
 * 	add +- 100<br>
 * 	multiply +- 1
 */
public class BangBangController extends UltrasonicController{
	
	private static final int 
		// Speed of left wheel when correcting
		LEFT_SPEED = 100,
		// Speed of right wheel when correcting
		RIGHT_SPEED = 300;
		
	/********************************************************
	 * Initialize a new BangBangController, and start it moving
	 * @param bandCenter Ideal distance
	 * @param bandWidth Allowable threshold (|r-y|)
	 */
	public BangBangController(int bandCenter, int bandWidth) {
		super(bandCenter, bandWidth);}

	/********************************************************
	 * Increases the speed of the leftMotor and reduces the speed of the rightMotor
	 * Method is called if the robot is traveling less than BANDCENTER - BANDWITH
	 * 
	 * The speed of the left motor is: 		200 rad/s
	 * The speed of the right motor is:		-200 rad/s
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
	 * The speed of the left motor is LEFT_SPEED (100)
	 * The speed of the right motor is RIGHT_SPEED (300)
	 */
	protected void turnLeft(int error) {
		leftMotor.setSpeed(LEFT_SPEED);
		rightMotor.setSpeed(RIGHT_SPEED);
		leftMotor.forward();
		rightMotor.forward();}
}
