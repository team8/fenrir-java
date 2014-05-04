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

    public void update()
    {
        accumulator.update();
        driveTrain.update();
        shooter.update();
        rangefinder.update();
    }

    public void init()
    {
        driveTrain.init();
    }

    public void disable()
    {
        driveTrain.setSpeed(0);
        shooter.setAllVics(0);
        accumulator.disable();
    }

    public void setCommand(RobotCommand command)
    {
        switch (command.getSubsystem()) {
            case DRIVE:
                driveTrain.runCommand(command);
                break;
            case SHOOTER:
                shooter.runCommand(command);
                break;
            case ACCUMULATOR:
                accumulator.runCommand(command);
                break;
            case RANGEFINDER:
                rangefinder.runCommand(command);
                break;
            case ROBOT:
                runCommand(command);
                break;
        }
    }

    public double getRightEnc()
    {
        return driveTrain.getRightEnc();
    }

    public double getLeftEnc()
    {
        return driveTrain.getLeftEnc();
    }
}
