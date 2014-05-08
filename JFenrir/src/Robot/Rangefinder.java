/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the rangefinder
 * @author Tyler Packard
 */
public class Rangefinder extends Subsystem {
    private AnalogChannel ultraLeft = new AnalogChannel(Constants.ULTRASONIC_CHANNEL_LEFT);
    private DigitalOutput rxLeft = new DigitalOutput(Constants.ULTRASONIC_RX_LEFT);
    private AnalogChannel ultraRight = new AnalogChannel(Constants.ULTRASONIC_CHANNEL_RIGHT);
    private DigitalOutput rxRight = new DigitalOutput(Constants.ULTRASONIC_RX_RIGHT);
    
    private static final int IDLE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int FINISHED = 3;
    private int state = IDLE;
    
    private double distInch = 0;
    private double leftAvg = 0;
	private double rightAvg = 0;
	private int leftTotal = 0;
	private int rightTotal = 0;
	
	
	public double measureAngle() {
	    // Probably obsolete
	}
	
	public void rotateToWall() {
	    // Probably obsolete
	}
	
	public float wallDist() {
	    // Probably obsolete
	}
	
	public double getDist() {
	    return distInch
	}
	
	public void setDistToWall(float dist) {
	    // Send a command to drivetrain
	}
	
    @Override
    public void runCommand(RobotCommand command) {
        command.execute();
    }
    
    @Override
	public void update() {
	    
	}
}
