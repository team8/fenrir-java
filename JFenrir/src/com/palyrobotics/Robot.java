/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

import edu.wpi.first.wpilibj.*;
/**
 * Runs the robot
 * @author Paly Robotics
 */
public class Robot {

	private Drivetrain drivetrain = new Drivetrain();
	private Shooter shooter = new Shooter();
	private Accumulator accumulator = new Accumulator();
	private Rangefinder rangefinder = new Rangefinder();


	public void update(){
		accumulator.update();
		drivetrain.update();
		shooter.update();
		rangefinder.update();
	}

	public void init() {
		drivetrain.init();
		shooter.init();
		accumulator.init();
		rangefinder.init();
	}

	public void disable(){
		drivetrain.disable();
		shooter.disable();
		accumulator.disable();
		rangefinder.disable();
	}

	/**
	 * Lets you run commands on subsystem
	 * Format:
	 * robot.relayCommand(new Subsytem.DoStuffCommand(params));
	 * 
	 * @param command Subsystem's command to run
	 */ 
	public void relayCommand(RobotCommand command){
		switch(command.getSubsystemType()) {
		case RobotCommand.ACCUMULATOR:
			command.execute(accumulator);
			break;
		case RobotCommand.DRIVETRAIN:
			command.execute(drivetrain);
			break;
		case RobotCommand.RANGEFINDER:
			command.execute(rangefinder);
			break;
		case RobotCommand.SHOOTER:
			command.execute(shooter);
			break;
		}
	}
}
