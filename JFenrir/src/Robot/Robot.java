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

    public Robot(){
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        accumulator = new Accumulator();
        rangefinder = new Rangefinder();
    }

    public void update(){
        accumulator.update();
        driveTrain.update();
        shooter.update();
        rangefinder.update();
    }

    public void init(){
        driveTrain.init();
        shooter.init();
    }

    public void disable(){
        driveTrain.setSpeed(0);
        shooter.disable();
        accumulator.disable();
    }
    
    public void relayCommand(RobotCommand command){
        switch(command.subsystemType) {
            case RobotCommand.ACCUMULATOR:
                accumulator.runCommand(command);
                break;
            case RobotCommand.DRIVETRAIN:
                driveTrain.runCommand(command);
                break;
            case RobotCommand.RANGEFINDER:
                rangefinder.runCommand(command);
                break;
            case RobotCommand.SHOOTER:
                shooter.runCommand(command);
                break;
        }
    }
}
