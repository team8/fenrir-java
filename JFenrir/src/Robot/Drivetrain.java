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
public class Drivetrain extends Subsystem {
	
	// Victors 
	private Victor leftFrontVic;
	private Victor leftBackVic;
	private Victor rightFrontVic;
	private Victor rightBackVic;

	private Encoder leftEnc;
	private Encoder rightEnc;

	private PIDController leftFrontController;
	private PIDController rightFrontController;
	private PIDController leftBackController;
	private PIDController rightBackController;


	//target speeds 
	private double targetSpeed;
	private double rotateSpeed;
	private double targetDist;

	// For testing
	private double prevLeftDist;
	private double prevRightDist;
	
	//state 
	private int state;
	public final static int IDLE = 0;
	public final static int AUTO_DRIVING = 1;
	public final static int TELE_DRIVING = 2;
	
	public void init() {
		rightEnc.reset();
		leftEnc.reset();
		rightEnc.start();
		leftEnc.start();
		//circumference = 19 inches
		rightFrontController.setOutputRange(-1, 1);
		leftFrontController.setOutputRange(-1, 1);
		rightBackController.setOutputRange(-1, 1);
		leftBackController.setOutputRange(-1, 1);
		rightEnc.setDistancePerPulse(.0782);//(.07734);
		leftEnc.setDistancePerPulse(.0813);//(.07849);
		rightEnc.setPIDSourceParameter(PIDSource.kDistance);
		leftEnc.setPIDSourceParameter(PIDSource.kDistance);
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
				if(rightEnc.getDistance() < Math.abs(targetDist)){
					System.out.println("getting called right");
					rightFrontVic.set(0.3);
					rightBackVic.set(0.3);
				}
				if(leftEnc.getDistance() < Math.abs(targetDist)){
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
	
	public void disable(){
		state = IDLE;
	}
	
	public void setSpeed(double spd) {
		targetSpeed = spd;
		state = TELE_DRIVING;
	}
	
	public void rotateS(double speed) {
		rotateSpeed = speed;
		state = TELE_DRIVING;
	}

	private void setAllVics(double spd){
		leftFrontVic.set(-spd);
		leftBackVic.set(-spd);
		rightFrontVic.set(spd);
		rightBackVic.set(spd);
	}
	
	public static abstract class DrivetrainCommand extends RobotCommand {
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
