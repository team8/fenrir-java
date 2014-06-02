
/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

/**
 * Has the constants
 * @author Paly Robotics
 */
public class Constants {
    //ports for HumanController
    public final static int PORT_SPEED = 1;
    public final static int PORT_TURN = 2;
    public final static int PORT_OPERATOR = 3;

    //Helps refine sensitivity of joysticks
    public final static double SPEED_SENSITIVITY = 0.5;
//ports for drive train victors
    public final static int PORT_DRIVE_VIC_LEFT_FRONT = 5;
    public final static int PORT_DRIVE_VIC_LEFT_BACK = 6;
    public final static int PORT_DRIVE_VIC_RIGHT_FRONT = 1;
    public final static int PORT_DRIVE_VIC_RIGHT_BACK = 2;

    //ports for drive train encoders
    public final static int PORT_ENCODER_RIGHT_A = 1;
    public final static int PORT_ENCODER_RIGHT_B = 2;
    public final static int PORT_ENCODER_LEFT_A = 5;
    public final static int PORT_ENCODER_LEFT_B = 6;
    public final static double DISTANCE_PER_ROT = 6*Math.PI; //6 pi for prototype

    //default speeds for DriveTrain
    public final static double DEFAULT_AUTO_ROTATE = 0.5;




    //ports for shooter victors
    public final static int PORT_SHOOTER_VIC_1 = 3;
    public final static int PORT_SHOOTER_VIC_2 = 4;
    public final static int PORT_SHOOTER_VIC_3 = 8;
    public final static int PORT_SHOOTER_VIC_4 = 7;
    public final static int PORT_LOADER_VIC = 9;

    //default speed for loaders
    public final static int LOAD_SPEED = -1;

    //ports for accumulator victors
    //public final int PORT_ACCUMULATOR_VIC_5 = 0;
    //public final int PORT_ACCUMULATOR_VIC_6 = 0;
    public final static int PORT_ACCUMULATOR_VIC_7 = 10;

//default distances for accumulator deploy and retract

    public final static int DEFAULT_DEPLOY_DIST = 1;
    public final static int DEFAULT_RETRACT_DIST = -1;

    //Constants for the Vics for shooting
    public final static int SHOOTER_VICS_SPEED = 1;

    //button number for operator stick trigger
    public final static int ACCUMULATOR_BUTTON_PORT = 10;
    public final static int FLUSH_TRIGGER = 2;

    // Ultrasonic ports
    public final static int ULTRASONIC_CHANNEL_LEFT = 1;
    public final static int ULTRASONIC_RX_LEFT = 13;
    public final static int ULTRASONIC_CHANNEL_RIGHT = 2;
    public final static int ULTRASONIC_RX_RIGHT = 14;
    public final static int HIGH = 1;
    public final static int LOW = 0;
   // public final static int ULTRA_GAP = 1; // Distance between the two ultrasonic sensors in inches// needs to be measured and put in


    public final static int SHOOT_DISTANCE = 0; //The set distance we want it to shoot at
}
