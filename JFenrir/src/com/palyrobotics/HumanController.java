package com.palyrobotics;

import edu.wpi.first.wpilibj.*;

public class HumanController {

	private Robot robot;
	private Joystick speedStick;
	private Joystick turnStick;
	private Joystick operatorStick;

	//booleans used to make sure we don't reissue same command
	private boolean shootButtonPrev;
	private boolean lastFlushTrigger;
	private boolean prevRangeButton;
	private boolean prevStop;
	private boolean prevZ;
	private boolean prevManualButton;
	private boolean manualState;

	public HumanController(Robot robot) {
		speedStick = new Joystick(Constants.PORT_SPEED);
		turnStick = new Joystick(Constants.PORT_TURN);
		operatorStick = new Joystick(Constants.PORT_OPERATOR);
		prevStop = false;
		prevZ = false;
		manualState = false;
		lastFlushTrigger = false;
		this.robot = robot;
	}

	public void update() {

		/*SLIGHT MOVEMENT*/
		if(getAbsTurnStick()<=.1 && getAbsSpeedStick()<=.1) {
			robot.relayCommand(new Drivetrain.SetSpeedCommand(0.0));
			robot.relayCommand(new Drivetrain.SetRotateCommand(0.0));
		}

		/*FULL MOVEMENT*/
		if(getAbsSpeedStick()>0.1) {
			robot.relayCommand(new Drivetrain.SetSpeedCommand(speedStick.getY()));
		}
		if(getAbsTurnStick()>0.1) {
			robot.relayCommand(new Drivetrain.SetRotateCommand(turnStick.getX()));
		}

		/*ACCUMULATOR*/
		if(getAccumulator()<-0.2) {
			//System.out.print("accumulating\n");
			robot.relayCommand(new Accumulator.AccumulateCommand());
			prevStop = false;
		}
		else if(getAccumulator()>0.2) {
			robot.relayCommand(new Shooter.EjectCommand());
			robot.relayCommand(new Accumulator.EjectCommand());
			prevStop = false;
		}
		else {
			robot.relayCommand(new Accumulator.SetIdleCommand());
			if(!prevStop) {
				robot.relayCommand(new Shooter.SetIdleCommand());
			}
			prevStop = true;
		}

		/*FLUSHING*/
		if (getFlushTrigger()) {
			robot.relayCommand(new Accumulator.FlushCommand());
			robot.relayCommand(new Shooter.FlushCommand());
		}
		else if (!getFlushTrigger() && lastFlushTrigger) {
			robot.relayCommand(new Accumulator.SetIdleCommand());
			robot.relayCommand(new Shooter.SetIdleCommand());
		}
		
		/*SHOOTER*/
		if (getManualButton() && !prevManualButton) {
			toggleManualState();
		}
		if (manualState == false) {
			if (shootButtonPrev == false && getShootButton()) {
				robot.relayCommand(new Shooter.FireCommand());
			}
			else if (!prevZ) {
				robot.relayCommand(new Shooter.SetIdleCommand());
			}
			prevZ = true;
		}
		else if (manualState == true) {
			System.out.println("is in manual\n");
			robot.relayCommand(new Shooter.ManualPrepareCommand());
			
			if (getShootButton()) {
				robot.relayCommand(new Shooter.ManualFireCommand());
			}
			prevZ = false;
		}
		
		/*RANGEFINDER*/
		if (!prevRangeButton && getRangeButton()){
			robot.relayCommand(new Rangefinder.FindDistCommand());
		}	
		
		shootButtonPrev = getShootButton();
		lastFlushTrigger = getFlushTrigger();
		prevRangeButton = getRangeButton();
		prevManualButton = getManualButton();
	}

	private double getAbsSpeedStick(){
		double speed = Math.abs(speedStick.getY());
		return speed;
	}

	private double getAbsTurnStick() {
		double turn = Math.abs(turnStick.getX());
		return turn;
	}

	private double getAccumulatorStick() {
		return operatorStick.getY(); // For adjusting the accumulator with Operator stick
	}
	
	private double getAccumulator() {
		//return operatorStick.GetRawButton((uint32_t)ACCUMULATOR_BUTTON_PORT); // Get button to start accumulator from Operator stick
		return operatorStick.getY(); // For testing purposes
	}
	
	private boolean getShootButton() {
		// Get trigger button to shoot from Operator stick
		// return false;
		return operatorStick.getRawButton(3);
	}
	
	private boolean getFlushTrigger() {
		//flush out the ball
		return operatorStick.getRawButton(Constants.FLUSH_TRIGGER);
	}
	private boolean getManualButton() {
		return operatorStick.getRawButton(5);
	}
	private boolean getRangeButton() {
		return operatorStick.getRawButton(4);
	}	
	private void toggleManualState() {
		manualState = !manualState;
	}
}
