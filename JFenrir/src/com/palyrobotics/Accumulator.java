/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the accumulator subsystem
 * 
 * @author Neelay Junnarkar
 */
public class Accumulator extends Subsystem {

	private Victor accuVic;

	private int state;
	private double speed;

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	private static final int IDLE = 0, ACCUMULATING = 1, EJECTING = 2;

	public Accumulator() {
		accuVic = new Victor(Constants.PORT_ACCUMULATOR_VIC_7);
		speed = 0.0;
	}

	public void init() {
		state = IDLE;
	}

	public void disable() {
		state = IDLE;
	}

	public void update() {
		// System.out.println("accuVic: "+accuVic.get());
		System.out.println("Netbeans deployed " + state);
		switch (state) {
		case IDLE:
			accuVic.set(0.0);
			break;
		case ACCUMULATING:
			accuVic.set(0.0);
			break;
		case EJECTING:
			accuVic.set(1.0);
			break;
		}
	}

	public static class AccumulateCommand extends RobotCommand {
		private double arg;

		public AccumulateCommand(double speed) {
			subsystemType = RobotCommand.ACCUMULATOR;
			this.arg = speed;
		}

		public void execute(Subsystem accu) {
			((Accumulator) accu).speed = arg;
			((Accumulator) accu).setState(ACCUMULATING);
		}
	}

	public static class SetIdleCommand extends RobotCommand {

		public SetIdleCommand() {
			subsystemType = RobotCommand.ACCUMULATOR;
		}

		public void execute(Subsystem accu) {
			((Accumulator) accu).setState(IDLE);
		}
	}

	public static class EjectCommand extends RobotCommand {

		public EjectCommand() {
			subsystemType = RobotCommand.ACCUMULATOR;
		}

		public void execute(Subsystem accu) {
			((Accumulator) accu).setState(EJECTING);
		}
	}
	
	public static class FlushCommand extends RobotCommand {
		public FlushCommand() {
			subsystemType = RobotCommand.ACCUMULATOR;
		}

		public void execute(Subsystem accu) {
			((Accumulator) accu).setState(EJECTING);
		}
	}
}
