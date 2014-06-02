package com.palyrobotics;

import edu.wpi.first.wpilibj.*;

/**
 * Main method?
 * 
 * @author Neelay Junnarkar
 */
public class JFenrir extends IterativeRobot {

	/**
	 * Robot object
	 */
	private Robot robot;
	
	/**
	 * HumanController object
	 */
	private HumanController humanController;
	
	/**
	 * AutonomousController object
	 */
	private AutonomousController autoController;
	
	/**
	 * Timer object
	 */
	private Timer timer;

	/**
	 * JFenrir object
	 */
	public JFenrir() {
		robot = new Robot();
		humanController = new HumanController(robot);
		autoController = new AutonomousController(robot);
		timer = new Timer();
	}

	/**
	 * Initializes robot
	 * @see Robot#init()
	 */
	public void robotInit() {
		robot.init();
	}

	/**
	 * Initializes robot for autonomous
	 * @see Robot#init() 
	 * @see AutonomousController#init()
	 */
	public void autonomousInit() {
		timer.start();
		robot.init();
		autoController.init();
	}

	/**
	 * Updates the autoController and robot
	 * @see AutonomousController#update()
	 * @see Robot#update() 
	 */
	public void autonomousPeriodic() {
		autoController.update();
		robot.update();
	}

	public void autonomousDisabled() {

	}

	/**
	 * Disables the robot
	 * @see Robot#disable() 
	 */
	public void disabledInit() {
		robot.disable();
	}

	/**
	 * Disables the robot for periodic
	 * @see Robot#disable() 
	 * @see Robot#update()
	 */
	public void disabledPeriodic() {
		robot.disable();
		robot.update();
	}

	/**
	 * Initializes the robot for teleop
	 * @see Robot#init()
	 */
	public void teleopInit() {
		robot.init();
	}

	/**
	 * Updates for teleop
	 * @see HumanController#update()
	 * @see Robot#update()
	 */
	public void teleopPeriodic() {
		humanController.update();
		robot.update();
	}

	/**
	 * Unused
	 */
	public void teleopDisabled() {

	}

	/**
	 * Unused
	 */
	public void testInit() {

	}

	public void testPeriodic() {

	}
}
