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

    private Drivetrain drivetrain = new Drivetrain();
    private Shooter shooter = new Shooter();
    private Accumulator accumulator = new Accumulator();
    private Rangefinder rangefinder = new Rangefinder();
    

    public void update(){
        accumulator.update();
        drivetrain.update();
        shooter.update();
        rangefinder.update();
    }

    public void init() {
        drivetrain.init();
        shooter.init();
        accumulator.init();
        rangefinder.init();
    }

    public void disable(){
        drivetrain.disable();
        shooter.disable();
        accumulator.disable();
        rangefinder.disable();
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
