/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Runs the robot
 * @author Paly Robotics
 */
public class Robot {
    
    private DriveTrain driveTrain;
    private Shooter shooter;
    private Accumulator accumulator;
    private Rangefinder rangefinder;
    
    public Robot()
    {
    	driveTrain = new DriveTrain();
    	shooter = new Shooter();
	accumulator = new Accumulator(); 
	rangefinder = new Rangefinder();
    }
    
}
