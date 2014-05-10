package Robot;

/**
 * The framework for a command that can be executed.
 * 
 * @author Tyler Packard
 * @author Joey Kellison-Linn
 */
public abstract class RobotCommand {
	
	/**
	 * The subsystem the robot class should send the command to.
	 */
	public int subsystemType;
	
	/**
	 * Numbers that represent each subsystem.
	 */
	public static final int ACCUMULATOR = 0;
	public static final int DRIVETRAIN = 1;
	public static final int RANGEFINDER = 2;
	public static final int SHOOTER = 3;
	
	/**
	 * Execute is a method that carries out a certain task that the command is supposed to do.
	 * Default method only runs when wrong subsystem is used, and throws IllegalArgumentException.
	 * 
	 * @param subsystem The subsystem that will run the command
	 */
	public void execute(Subsystem subsystem)
	{
		throw new IllegalArgumentException("The wrong subsystem type was used.");
	}
}
