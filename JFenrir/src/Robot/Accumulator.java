/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the accumulator subsystem
 * 
 * @author Neelay Junnarkar
 */
public class Accumulator extends Subsystem {
	
	private Victor accuVic;
	
	private int state;
	private static final int IDLE = 0, ACCUMULATING = 1, PASSING = 2; 
	
	public Accumulator() {
		accuVic = new Victor(Constants.PORT_ACCUMULATOR_VIC_7);
	}
	
	public void init() {
		state = IDLE;
	}
	
	public void disable() {
		state = IDLE;
	}
	
	public void update() {
		switch(state) {
		case IDLE:
			setAllVics(0.0);
			break;
		case ACCUMULATING:
			accuVic.set(-1.0);
			break;
		case PASSING:
			accuVic.set(1.0);
			break;
		}
	}
    
	private void setAllVics(double spd) {
		accuVic.set(spd);
	}
	
	public static class AccumulateCommand extends RobotCommand {
		setSubsystemType(ACCUMULATOR);
		
		void execute(Accumulator accu){
			accu.state = ACCUMULATING;
		}
	}
    
	public static class PassCommand extends RobotCommand {
		setSubsystemType(ACCUMULATOR);
		
		void execute(Accumulator accu){
			accu.state = PASSING;
		}
    }
    
	public static class FlushCommand extends RobotCommand {
		setSubsystemType(ACCUMULATOR);
		
		void execute(Accumulator accu){
			accu.state = PASSING;
		}
	}
	
	public static class SetIdleCommand extends RobotCommand {
		setSubsystemType(ACCUMULATOR);
		
		void execute(Accumulator accu){
			accu.state = IDLE;
		}
	}
}
