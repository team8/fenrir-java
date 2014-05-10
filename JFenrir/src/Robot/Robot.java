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

    private Drivetrain drivetrain;
    private Shooter shooter;
    private Accumulator accumulator;
    private Rangefinder rangefinder;

    public Robot(){
        driveTrain = new Drivetrain();
        shooter = new Shooter();
        accumulator = new Accumulator();
        rangefinder = new Rangefinder();
    }

    public void update(){
        accumulator.update();
        drivetrain.update();
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
                command.execute(accumulator);
                break;
            case RobotCommand.DRIVETRAIN:
                command.execute(drivetrain);
                break;
            case RobotCommand.RANGEFINDER:
                command.execute(rangefinder);
                break;
            case RobotCommand.SHOOTER:
                command.execute(shooter);
                break;
        }
    }
}
