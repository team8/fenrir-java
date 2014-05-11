/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the drivetrain
 * @author Neelay Junnarkar
 * @author Benjamin Cohen-Wang
 * @author Jonathan Zwiebel
 */
public class Drivetrain extends Subsystem {
	
	// Victors
	private Victor leftFrontVic;
	private Victor leftBackVic;
	private Victor rightFrontVic;
	private Victor rightBackVic;

	//Encoders and PID Controllers
	private Encoder leftEnc;
	private Encoder rightEnc;

	private PIDController leftFrontController;
	private PIDController rightFrontController;
	private PIDController leftBackController;
	private PIDController rightBackController;

	//Variables for the commands
	private double targetSpeed;
	private double rotateSpeed;
	private double targetDist;

	// For testing what?
	private double prevLeftDist;
	private double prevRightDist;
	
	//Enum for the states
	private int state;
	public final static int IDLE = 0;
	public final static int AUTO_DRIVING = 1;
	public final static int TELE_DRIVING = 2;
	
	public void init() {
		//Reset Encoders
		rightEnc.reset();
		leftEnc.reset();
		rightEnc.start();
		leftEnc.start();
		/*circumference of wheel= 19 inches*/
		//Calibrate encoders
		rightFrontController.setOutputRange(-1, 1);
		leftFrontController.setOutputRange(-1, 1);
		rightBackController.setOutputRange(-1, 1);
		leftBackController.setOutputRange(-1, 1);
		rightEnc.setDistancePerPulse(.0782);
		leftEnc.setDistancePerPulse(.0813);
		rightEnc.setPIDSourceParameter(PIDSource.kDistance);
		leftEnc.setPIDSourceParameter(PIDSource.kDistance);
		
		state = IDLE;
	}

	public void disable(){
		state = IDLE;
	}
	
	public void update() {
	
		switch (state) {
			case TELE_DRIVING:
				double leftSpeed = Math.min(Math.max(-(targetSpeed + rotateSpeed), -1), 1);
				double rightSpeed = Math.min(Math.max(targetSpeed - rotateSpeed, -1), 1);
				leftFrontVic.set(leftSpeed);
				leftBackVic.set(leftSpeed);
				rightFrontVic.set(rightSpeed);
				rightBackVic.set(rightSpeed);
				break;
			case AUTO_DRIVING:
				double average = (rightEnc.getDistance()+leftEnc.getDistance())/2;
				if(rightEnc.getDistance() < Math.abs(targetDist)) {
					System.out.println("getting called right");
					rightFrontVic.set(0.3);
					rightBackVic.set(0.3);
				}
				if(leftEnc.getDistance() < Math.abs(targetDist)) {
					System.out.println("getting called left");
					leftFrontVic.set(-0.3);
					leftBackVic.set(-0.3);
				}
				break;
			case IDLE:
				setAllVics(0.0);
				break;
		}
	}

	private void setSpeed(double spd) {
		targetSpeed = spd;
		state = TELE_DRIVING;
	}
	
	private void rotateS(double speed) {
		rotateSpeed = speed;
		state = TELE_DRIVING;
	}

	private void setAllVics(double spd) {
		leftFrontVic.set(-spd);
		leftBackVic.set(-spd);
		rightFrontVic.set(spd);
		rightBackVic.set(spd);
	}
	
	public static abstract class DrivetrainCommand extends RobotCommand {
		protected final double speed;
		
		public DrivetrainCommand() {
			setSubsystemType(DRIVETRAIN);
		}
		
		abstract void execute(Drivetrain drivetrain);
	}
	
	public static class SetSpeedCommand extends DrivetrainCommand {
		
		public SetSpeedCommand(double speed) {
			this.speed = speed;
		}
		
		public void execute(Drivetrain drivetrain) {
			// Set the speed of the victors to the variable speed
			drivetrain.setSpeed(speed);
		}
	}
	
	public static class SetRotateCommand extends DrivetrainCommand {
		
		public SetRotateCommand(double speed){
			this.speed = speed;
		}
		
		public void execute(Drivetrain drivetrain){
			drivetrain.rotateS(speed);
		}
	}
}
