/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;

/**
 * 
 * @author Nihar Mitra
 *
 */

public class AutonomousController {
	
	private Timer time = new Timer();
	
	public AutonomousController(Robot robot) {
		time.stop();
		time.reset();
	}
	
	/**
	 * The path we want to take during autonomous
	 */
	private void path() {

	}
	
	public void init() {
		path();
	}
	
	public void update() {
		
	}
}
