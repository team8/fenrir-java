/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Runs commands to the robot
 * @author Paly Robotics
 */
public class RobotCommand {
    // Arguments used for Drivetrain methods
//    class DriveArgs {
//    }
//
//    class DriveDist extends DriveArgs {
//        double driveDist;
//    }
//
//    class RotAngle extends DriveArgs {
//        double rotAngle;
//    }
//
//    class RotSpeed extends DriveArgs {
//        double rotSpeed;
//    }
//
//    class DriveSpeed extends DriveArgs {
//        double driveSpeed;
//    }
//
//    class SpeedValue extends DriveArgs {
//        double speedValue;
//    }
    class DriveArgs {
        double driveDist;
        double rotAngle;
        double rotSpeed;
        double driveSpeed;
        double speedValue;
    }

    class ShooterArgs {
        boolean trigger;
    }

    class RangefinderArgs {
        float target;
    }

    public enum SubsystemType {
        DRIVE,
        SHOOTER,
        ACCUMULATOR,
        RANGEFINDER,
        ROBOT;
    }

	/*SUBSYSTEM METHODS*/
    public enum DriveMethod {
        SETSPEED,
        DRIVEDIST,
        ROTATEANGLE,
        ROTATESPEED,
        STOPVICTORS;
    }

    public enum AccumulatorMethod {
        ACCUMULATE,
        STOP,
        PASS;
    }

    public enum ExtensionMethod {
        FOWARD,
        BACKWARD,
        HALT;
    }

    public enum ShooterMethod {
        FIRE,
        EJECT,
        IDLE,
        FLUSH,
        MANUAL_FIRE,
        MANUAL_LOAD;
    }

    public enum RangefinderMethod {
        ANGLE,
        SET_DIST,
        LEFT_ON,
        RIGHT_ON,
        WALL_DIST;
    }

    public enum RobotMethod {
        ALIGN_THEN_SHOOT;
    }

    Object arg;
    private SubsystemType subsystem;
    private Method method;

    public RobotCommand(SubsystemType subsystemType, Method methodType, Object args) {
        subsystem = subsystemType;
        method = methodType;
        arg = args;
    }

    public SubsystemType getSubsystem() {
        return subsystem;
    }

    public Method getMethod() {
        return method;
    }
}

class Method {
    RobotCommand.DriveMethod driveMethod;
    RobotCommand.ShooterMethod shooterMethod;
    RobotCommand.AccumulatorMethod accumulatorMethod;
}