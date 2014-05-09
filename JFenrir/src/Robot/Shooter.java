/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

import edu.wpi.first.wpilibj.*;
/**
 * Runs the shooter
 * @author Neelay Junnarkar
 */
public class Shooter extends Subsystem{

    private int state;
    
    //states, replacement for enum
    private static final int IDLE = 0, PREPARING = 1, FIRING = 2, EJECTING = 3, FLUSHING = 4, MANUAL_LOAD = 5, MANUAL_FIRE = 6;

    private Victor shooterVic1;
    private Victor shooterVic2;
    private Victor shooterVic3;
    private Victor shooterVic4;
    private Victor loaderVic;
    
    private Timer shootTimer;
    
    
    public Shooter(){
        
        shooterVic1 = new Victor(Constants.PORT_SHOOTER_VIC_1);
        shooterVic2 = new Victor(Constants.PORT_SHOOTER_VIC_2);
        shooterVic3 = new Victor(Constants.PORT_SHOOTER_VIC_3);
        shooterVic4 = new Victor(Constants.PORT_SHOOTER_VIC_4);
        loaderVic = new Victor(Constants.PORT_LOADER_VIC);
        
        shootTimer = new Timer;
        
        init();
    }
    
    @Override
    public void init(){
        state = IDLE;
    }
    
    @Override
    public void disable(){
        state = IDLE;
    }
    
    @Override
    public void update(){
        switch (state){
            case IDLE:
                setAllVics(0.0);
                break;
            case PREPARING:
                if (!shootTimer.hasPassedPeriod(3.0)){
                    setShooterVics(3.0 );
                }
                else{
                    state = FIRING;
                    shootTimer.reset();
                }
                break;
            case FIRING:
                if (!shootTimer.hasPeriodPassed(3.0)){
			loaderVic.Set(Constants.LOAD_SPEED);
		}
		else{
    		    state = IDLE;
    		}   
                break;
            case EJECTING:
                loaderVic.set(1);
                break;
            case FLUSHING:
                setAllVics(-0.3);
                break;
            case MANUAL_LOAD:
            	loaderVic.set(Constants.LOAD_SPEED);
            	break;
            case MANUAL_FIRE:
            	setAllVics(1.0);
            	break;
        }
    }
    
    @Override
    public void runCommand(RobotCommand command){
        
    }
    
    private void setAllVics(double spd){
        shooterVic1.set(-spd);
        shooterVic2.set(-spd);
        shooterVic3.set(spd);
        shooterVic4.set(spd);
        loaderVic.set(-spd);
    }
    
    private void setShooterVics(double spd){
        shooterVic1.set(-spd);
        shooterVic2.set(-spd);
        shooterVic3.set(spd);
        shooterVic4.set(spd);
    }
}
