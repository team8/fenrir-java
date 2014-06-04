
/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

import edu.wpi.first.wpilibj.*;
/**
 * Runs the shooter
 * @author Neelay Junnarkar
 */
public class Shooter extends Subsystem {

	private int state;

	public void setState(int state){ this.state = state; }
	public int getState(){ return state; }

	//states, replacement for enum
	private static final int IDLE = 0, PREPARING = 1, FIRING = 2, EJECTING = 3, FLUSHING = 4, MANUAL_FIRING = 5, MANUAL_PREPARING = 6;

	private Victor shooterVic1;
	private Victor shooterVic2;
	private Victor shooterVic3;
	private Victor shooterVic4;
	private Victor loaderVic;

	public Timer shootTimer;

	public Shooter() {

		shooterVic1 = new Victor(Constants.PORT_SHOOTER_VIC_1);
		shooterVic2 = new Victor(Constants.PORT_SHOOTER_VIC_2);
		shooterVic3 = new Victor(Constants.PORT_SHOOTER_VIC_3);
		shooterVic4 = new Victor(Constants.PORT_SHOOTER_VIC_4);
		loaderVic = new Victor(Constants.PORT_LOADER_VIC);

		shootTimer = new Timer();

	}

	public void init() {
		state = IDLE;
	}

	public void disable() {
		state = IDLE;
	}

	public void update() {
		switch (state) {
		case IDLE:
			setAllVics(0.0);
			break;
		case PREPARING:
			if (shootTimer.get() < 3.0) {
				setShooterVics(1.0);
			}
			else {
				state = FIRING;
			}
			break;
		case FIRING:
			if (shootTimer.get() >= 3.0 && shootTimer.get() < 5.0){
				loaderVic.set(Constants.LOAD_SPEED);
			}
			else if (shootTimer.get() >= 5.0){ 
				state = IDLE;
			}   
			break;
		case EJECTING:
			loaderVic.set(1);
			break;
		case FLUSHING:
			setAllVics(-0.3);
			break;
		case MANUAL_FIRING:
			loaderVic.set(Constants.LOAD_SPEED);
			break;
		case MANUAL_PREPARING:
			setShooterVics(1.0);
			break;
		}
	}

	private void setAllVics(double spd) {
		shooterVic1.set(-spd);
		shooterVic2.set(-spd);
		shooterVic3.set(spd);
		shooterVic4.set(spd);
		loaderVic.set(-spd);
	}

	private void setShooterVics(double spd) {
		shooterVic1.set(-spd);
		shooterVic2.set(-spd);
		shooterVic3.set(spd);
		shooterVic4.set(spd);
	}

	public static class EjectCommand extends RobotCommand {
		public EjectCommand(){
			subsystemType = RobotCommand.SHOOTER;
		}

		public void execute(Subsystem shooter){
			((Shooter)shooter).setState(EJECTING);
		}
	}

	public static class SetIdleCommand extends RobotCommand {
		public SetIdleCommand(){
			subsystemType = RobotCommand.SHOOTER;
		}

		public void execute(Subsystem shooter){
			((Shooter)shooter).setState(IDLE);
		}
	}

	public static class FlushCommand extends RobotCommand {
		public FlushCommand(){
			subsystemType = RobotCommand.SHOOTER;
		}

		public void execute(Subsystem shooter){
			((Shooter)shooter).setState(FLUSHING);
		}
	}

	public static class FireCommand extends RobotCommand {
		public FireCommand(){
			subsystemType = RobotCommand.SHOOTER;
		}

		public void execute(Subsystem shooter){
			((Shooter)shooter).shootTimer.reset();
			((Shooter)shooter).setState(PREPARING);
		}
	}

	public static class ManualPrepareCommand extends RobotCommand {
		public ManualPrepareCommand(){
			subsystemType = RobotCommand.SHOOTER;
		}

		public void execute(Subsystem shooter){
			((Shooter)shooter).setState(MANUAL_PREPARING);
		}
	}

	public static class ManualFireCommand extends RobotCommand {
		public ManualFireCommand(){
			subsystemType = RobotCommand.SHOOTER;
		}

		public void execute(Subsystem shooter) {
			((Shooter)shooter).setState(MANUAL_FIRING);
		}
	}
}
