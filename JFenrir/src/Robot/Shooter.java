/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Runs the shooter
 * @author Neelay Junnarkar
 */
public class Shooter extends Subsystem{

    public final int state;
    
    public static final int IDLE = 0, PREPARING = 1, FIRING = 2, EJECTING = 3, FLUSHING = 4;

    private Victor shooterVic1;
    private Victor shooterVic2;
    private Victor shooterVic3;
    private Victor shooterVic4;
    private Victor loaderVic;
    
    Timer timer;
    
    
    public Shooter(){
        shooterVic1 = new Victor(PORT_SHOOTER_VIC_1);
        shooterVic2 = new Victor(PORT_SHOOTER_VIC_2);
        shooterVic3 = new Victor(PORT_SHOOTER_VIC_3);
        shooterVic4 = new Victor(PORT_SHOOTER_VIC_4);
        loaderVic = new Victor(PORT_LOADER_VIC);
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
                
                break;
            case FIRING:
                
                break;
            case EJECTING:
                
                break;
            case FLUSHING:
                
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
