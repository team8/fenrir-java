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
	public final static int STOPPED = 0;
	public final static int DRIVING = 1;
	public final static int ROTATING = 2;
	public final static int TURNING = 3;
	
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
		rightEnc.setPIDSourceParameter(PIDSource::kDistance);
		leftEnc.setPIDSourceParameter(PIDSource::kDistance);
		state = STOPPED;
	}

	
	public void update() {
	
		//std::printf("Left Enc: %f\n",leftEnc.GetDistance()); 
		//std::printf("Right Enc: %f\n",rightEnc.GetDistance());
	
		switch (state) {
	
		case ROTATING:
			double leftSpeed = Math.min(Math.max(-(targetSpeed + rotateSpeed), -1), 1);
			double rightSpeed = Math.min(Math.max(targetSpeed - rotateSpeed, -1), 1);
			leftFrontVic.set(leftSpeed);
			leftBackVic.set(leftSpeed);
			rightFrontVic.set(rightSpeed);
			rightBackVic.set(rightSpeed);
			break;
	
		case DRIVING:
			double average = (rightEnc.GetDistance()+leftEnc.GetDistance())/2;
			if(rightEnc.GetDistance() < abs(targetDist)){
				System.out.println("getting called right");
				rightFrontVic.set(0.3);
				rightBackVic.set(0.3);
			}
			if(leftEnc.GetDistance() < abs(targetDist)){
				System.out.println("getting called left");
				leftFrontVic.set(-0.3);
				leftBackVic.set(-0.3);
			}
	
	
			break;
	
		case TURNING:
			//		leftFrontVic.Set(-(angleController.Get()));
			//		leftBackVic.Set(-(angleController.Get()));
			//		rightFrontVic.Set(angleController.Get());
			//		rightBackVic.Set(angleController.Get());
			break;
	
		case STOPPED:
			leftFrontVic.Set(0);
			leftBackVic.Set(0);
			rightFrontVic.Set(0);
			rightBackVic.Set(0);
			break;
		}
	}
	
	public void setSpeed(double spd) {
		targetSpeed = spd;
		state = ROTATING;
	}

	public void rotateA(double angle) {
		//	gyroscope.Reset();
		//	angleController.SetSetpoint(angle);
		//	angleController.Enable();
		//	rotateAngle = angle;
		state = TURNING;
	}

	
	public void rotateS(double speed) {
		rotateSpeed = speed;
		state = ROTATING;
	}


	public void stopVictors() {
		state = STOPPED;
	}


	public double getRightEnc() {
		return rightEnc.getDistance();
	}


	public double getLeftEnc() {
		return leftEnc.getDistance();
	}

	
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
