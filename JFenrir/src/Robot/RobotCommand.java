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
	public final int SubsystemType;
	
	/**
	 * Numbers that represent each sumbsystem.
	 */
	public static final int ACCUMULATOR = 0;
	public static final int DRIVETRAIN = 1;
	public static final int RANGEFINDER = 2;
	public static final int SHOOTER = 3;
	
	/**
	 * Execute is a method that carries out a certain task that the command is supposed to do.
	 */
	abstract void execute(Object... objects);
}
