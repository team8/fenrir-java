/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;

/**
 * Human controller
 * @author Paly Robotics
 */
public class HumanController {
    
    Joystick speedStick;
    Joystick turnStick;
    Joystick operatorStick;
    boolean accuButtonPrev;
    boolean shootButtonPrev;
    boolean flushTriggerPrev;
    boolean accumPrev;
    boolean manualPrev;
    boolean prevZ;
    Robot bot;
    
    public HumanController(Robot robot) {

         //fill constructors of Joystick 
        speedStick = new Joystick();
        turnStick = new Joystick();
        operatorStick = new Joystick();
        accumPrev = false;
        manualPrev = false;
        prevZ = true;
        bot = robot;
    }
    public void update() {
        /*SLIGHT MOVEMENT WITHOUT TURNING*/
        if(Math.abs(getTurnStick()) <= 0.1 && Math.abs(getSpeedStick()) <= 0.1) {
            //command for speedstick y to drive
            
            //command rot speed 0
        }
        /*FULL MOVEMENT*/
        if(Math.abs(getSpeedStick()) > 0.1) {
            // command for speedstick y to drive
        }
        if(Math.abs(getTurnStick()) > 0.1) {
            // command for turnstick x to drive
        }
        /*ACCUMULATOR*/
        //fix logic
        if(getAccumY() < -0.2) {
            //accumulate AND SET LOADER WHEELS DEAD ONCE
            accumPrev = false;
        }
        else if(getAccumY() > 0.2) {
            // command to eject loader wheels
            
            //command to pass accum
            accumPrev = false;
        }
        else {
            //stop accum
            if(!accumPrev) {
                //shooter idle
            }
        }
        /*FLUSHING*/
        if(getFlushTrigger()) {
            //command to flush accum
            
            //command to flush shooter
        }
        else if(!getFlushTrigger() && flushTriggerPrev) {
            //command accum stop
            
            //command to shooter stop
        }
        /*SHOOTER*/
        if(getOperatorZ() < 0.5) {
            if(shootButtonPrev != getShootButton() && getShootButton()) {
                //command to shoot
            }
            else if(!prevZ) {
                //command to idle shooter
                prevZ = true;
            }
        }
        else if(getOperatorZ() > 0.5) {
            //command to manual speed up
            if(getShootButton()) {
                //command to manual load
            }
            prevZ = false;
        }
    }
    //check official names of getters
    private double getSpeedStick() {
        double speed = speedStick.getY();
        return speed;
    }
    private double getTurnStick() {
        double turn = turnStick.getX();
        return turn;
    }
    private double getAccumY() {
        double accum = operatorStick.getY();
        return accum;
    }
    private boolean getShootButton() {
        boolean button = operatorStick.getTrigger();
        return button;
    }
    private boolean getFlushTrigger() {
        //add button ports here
        boolean button = operatorStick.getRawButton();
        return button;
    }
    private double getOperatorZ() {
        double zVal = operatorStick.getZ();
        return zVal;
        
    }
    
}
