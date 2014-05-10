/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Runs the drivetrain
 * @author Paly Robotics
 */
public class Drivetrain {
    
    public static class SetSpeedCommand extends RobotCommand {
    	private int speed;
        
		public SetSpeedCommand(int speed) {
        	this.speed = speed;
        	subsystemType = DRIVETRAIN;
        }
        
        public void execute(Drivetrain drivetrain) {
            // Set the speed of the victors to the variable speed
        }
    }
}
