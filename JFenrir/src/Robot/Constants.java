/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Has the constants
 * @author Paly Robotics
 */
public class Constants {
    //ports for HumanController
    public final int PORT_SPEED = 1;
    public final int PORT_TURN = 2;
    public final int PORT_OPERATOR = 3;

    //Helps refine sensitivity of joystiks
    public final double SPEED_SENSITIVITY = 0.5;
    public final int ROTATE_SENSITIVITY;     //No value?
//ports for drive train victors
    public final int PORT_DRIVE_VIC_LEFT_FRONT = 5;
    public final int PORT_DRIVE_VIC_LEFT_BACK = 6;
    public final int PORT_DRIVE_VIC_RIGHT_FRONT = 1;
    public final int PORT_DRIVE_VIC_RIGHT_BACK = 2;

    //ports for drive train encoders
    public final int PORT_ENCODER_RIGHT_A = 1;
    public final int PORT_ENCODER_RIGHT_B = 2;
    public final int PORT_ENCODER_LEFT_A = 5;
    public final int PORT_ENCODER_LEFT_B = 6;
    public final double DISTANCE_PER_ROT = 6*Math.atan(1)*4; //6 pi for prototype
    //ports for drive train gyroscopes
    public final int PORT_GYRO = 0;

    //default speeds for DriveTrain
    public final double DEFAULT_AUTO_ROTATE = 0.5;

//ports for vision


    //ports for shooter encoders
    public final int PORT_SHOOTER_ENCODER_1 = 0;
    public final int PORT_SHOOTER_ENCODER_2 = 0;
    public final int PORT_SHOOTER_ENCODER_3 = 0;
    public final int PORT_SHOOTER_ENCODER_4 = 0;

    //ports for shooter victors
    public final int PORT_SHOOTER_VIC_1 = 3;
    public final int PORT_SHOOTER_VIC_2 = 4;
    public final int PORT_SHOOTER_VIC_3 = 8;
    public final int PORT_SHOOTER_VIC_4 = 7;
    public final int PORT_LOADER_VIC = 9;

    //default speed for loaders
    public final int LOAD_SPEED = -1;

    //ports for accumulator victors
    //public final int PORT_ACCUMULATOR_VIC_5 = 0;
    //public final int PORT_ACCUMULATOR_VIC_6 = 0;
    public final int PORT_ACCUMULATOR_VIC_7 = 10;

    //ports for accumulator encoders
    public final int PORT_ACCUMULATOR_ENC_1A = 0;
    public final int PORT_ACCUMULATOR_ENC_1B = 0;
    public final int PORT_ACCUMULATOR_ENC_2A = 0;
    public final int PORT_ACCUMULATOR_ENC_2B = 0;

//default distances for accumulator deploy and retract

    public final int DEFAULT_DEPLOY_DIST = 1;
    public final int DEFAULT_RETRACT_DIST = -1;

    //Constants for the Vics for shooting
    public final int SHOOTER_VICS_SPEED = 1;

    //Encoder Ports
    public final int PORT_SHOOTER_ENCODER_1A = 0;//is this obselete with the other shooter encoders?
    public final int PORT_SHOOTER_ENCODER_1B = 0;
    public final int PORT_SHOOTER_ENCODER_2A = 0;
    public final int PORT_SHOOTER_ENCODER_2B = 0;
    public final int PORT_SHOOTER_ENCODER_3A = 0;
    public final int PORT_SHOOTER_ENCODER_3B = 0;
    public final int PORT_SHOOTER_ENCODER_4A = 0;
    public final int PORT_SHOOTER_ENCODER_4B = 0;

    //button number for operator stick trigger
    public final int ACCUMULATOR_BUTTON_PORT = 10;
    public final int FLUSH_TRIGGER = 2;

    // Ultrasonic ports
    public final int ULTRASONIC_CHANNEL_LEFT = 1;
    public final int ULTRASONIC_RX_LEFT = 13;
    public final int ULTRASONIC_CHANNEL_RIGHT = 2;
    public final int ULTRASONIC_RX_RIGHT = 14;
    public final int HIGH = 1;
    public final int LOW = 0;
    public final int ULTRA_GAP = 1; // Distance between the two ultrasonic sensors in inches


    public final int SHOOT_DISTANCE = 0; //The set distance we want it to shoot at
}
