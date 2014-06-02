/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

import edu.wpi.first.wpilibj.*;
//import Robot.Drivetrain; // Need to use Drivetrain's commands
/**
 * Autonomous control
 * 
 * @author Nihar Mitra
 * @author Tyler Packard
 */

public class AutonomousController {
	
	private Robot robot;
	private boolean targetReached;
	// Distances in inches
	private double shootDist = 120; // Distance to shoot from
	private double spawnDist = 218; // Distance from wall when we start
	
	private Timer time = new Timer();
	
	public AutonomousController(Robot robot) {
		this.robot = robot;
		time.stop();
		time.reset();
	}
	
	/**
	 * The path we want to take during autonomous
	 */
	private void path() {
		robot.relayCommand(new Drivetrain.DriveDistCommand(spawnDist - shootDist));
	}
	
	public void init() {
		path();
	}
	
	public void update() {
		
	}
}
