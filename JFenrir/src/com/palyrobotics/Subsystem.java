package com.palyrobotics;

/**
 * Subsystems represent a subsytem of the robot such as the shooter or drivetrain.
 * 
 * @author Tyler Packard
 */
public abstract class Subsystem {

	/**
	 * Initialize the subsystem.
	 */
	public void init() {}
	
	/**
	 * Disable the subsystem.
	 */
	public void disable() {}
	
	/**
	 * Update the subsystem.
	 */
	public void update() {}
}
