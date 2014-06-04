/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the drivetrain
 *
 * @author Nihar Mitra
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
	public final static int DRIVE_DIST = 3;

	public Drivetrain(){
		leftEnc = new Encoder(Constants.PORT_ENCODER_LEFT_A, Constants.PORT_ENCODER_LEFT_B);
		rightEnc = new Encoder(Constants.PORT_ENCODER_RIGHT_A, Constants.PORT_ENCODER_RIGHT_B);
		leftFrontVic = new Victor(Constants.PORT_DRIVE_VIC_LEFT_FRONT);
		leftBackVic = new Victor(Constants.PORT_DRIVE_VIC_LEFT_BACK);
		rightFrontVic = new Victor(Constants.PORT_DRIVE_VIC_RIGHT_FRONT);
		rightBackVic = new Victor(Constants.PORT_DRIVE_VIC_RIGHT_BACK);
		leftFrontController = new PIDController(0.1,0.001,0.1,leftEnc,leftFrontVic);
		leftBackController = new PIDController(0.1,0.001,0.1,leftEnc,leftBackVic);
		rightBackController = new PIDController(0.1,0.001,0.1,rightEnc,rightBackVic);
		rightFrontController = new PIDController(0.1,0.001,0.1,rightEnc,rightFrontVic);
		targetSpeed = 0;
		rotateSpeed = 0;
		targetDist = 0;
	}

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
		rightEnc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
		leftEnc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);

		state = IDLE;
	}

	public void disable() {
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
		case DRIVE_DIST:
			rightFrontVic.set(-rightFrontController.get());
			rightBackVic.set(-rightBackController.get());
			leftFrontVic.set(leftFrontController.get());
			leftBackVic.set(leftBackController.get());
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

	private void driveDist(double dist) {
		leftEnc.reset();
		rightEnc.reset();
		leftFrontController.reset();
		leftBackController.reset();
		rightFrontController.reset();
		rightBackController.reset();
		leftFrontController.setSetpoint(dist);
		rightFrontController.setSetpoint(dist);
		leftBackController.setSetpoint(dist);
		rightBackController.setSetpoint(dist);
		leftFrontController.enable();
		rightFrontController.enable();
		leftBackController.enable();
		rightBackController.enable();

		state = DRIVE_DIST;
	}

	private void setAllVics(double spd) {
		leftFrontVic.set(-spd);
		leftBackVic.set(-spd);
		rightFrontVic.set(spd);
		rightBackVic.set(spd);
	}

	public static abstract class DrivetrainCommand extends RobotCommand {
		protected final double arg;

		public DrivetrainCommand(double speed) {
			this.arg = speed;
			subsystemType = RobotCommand.DRIVETRAIN;
		}

	}

	public static class SetSpeedCommand extends DrivetrainCommand {

		public SetSpeedCommand(double speed) {
			super(speed);
		}

		public void execute(Subsystem drivetrain) {
			// Set the speed of the victors to the variable speed
			((Drivetrain)drivetrain).setSpeed(arg);
		}
	}

	public static class SetRotateCommand extends DrivetrainCommand {

		public SetRotateCommand(double speed) {
			super(speed);
		}

		public void execute(Subsystem drivetrain) {
			((Drivetrain)drivetrain).rotateS(arg);
		}
	}
	public static class DriveDistCommand extends DrivetrainCommand {

		public DriveDistCommand(double dist) {
			super(dist);
		}

		public void execute(Subsystem drivetrain) {
			((Drivetrain)drivetrain).driveDist(arg);
		}
	}
}
