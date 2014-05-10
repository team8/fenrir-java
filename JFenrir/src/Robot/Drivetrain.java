/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the drivetrain
 * @author Paly Robotics
 */
public class Drivetrain {
	
	// Victors 
	private Victor leftFrontVic;
	private Victor leftBackVic;
	private Victor rightFrontVic;
	private Victor rightBackVic;

	//Gyro gyroscope;

	private Encoder leftEnc;
	private Encoder rightEnc;

	private PIDController leftFrontController;
	private PIDController rightFrontController;
	private PIDController leftBackController;
	private PIDController rightBackController;
	//PIDController angleController;

	//target speeds 
	private double targetSpeed;
	private double rotateSpeed;
	private double rotateAngle;
	private double targetDist;

	// For testing
	private double prevLeftDist;
	private double prevRightDist;
	
	//state 
	private int state;
	public final static int DRIVE_DIST;
	public final static int ROTATE_SPEED;
	public final static int TURN_ANGLE;
	public final static int STOP_VICTORS;
	
	public static abstract DrivetrainCommand extends RobotCommand {
		public DrivetrainCommand() {
			setSubsystemType(DRIVETRAIN);
		}
		
		abstract void execute(Drivetrain drivetrain);
	}
	
	public static class SetSpeedCommand extends DrivetrainCommand {
		private int speed;
		
		public SetSpeedCommand(int speed) {
			this.speed = speed;
		}
		
		public void execute(Drivetrain drivetrain) {
			// Set the speed of the victors to the variable speed
		}
	}
}
