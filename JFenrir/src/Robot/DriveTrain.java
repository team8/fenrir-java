/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Runs the drivetrain
 * @author Paly Robotics
 */
public class DriveTrain {
    
    public static class SetSpeedCommand implements RobotCommand {
        private int speed;
        
        public SetSpeedCommand(int speed) {
            this.speed = speed;
        }
        
        public void execute() {
            // Set the speed of the victors to the variable speed
        }
    }
}
