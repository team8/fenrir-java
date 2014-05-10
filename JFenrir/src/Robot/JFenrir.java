/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package Robot;

/**
 * Main method?
 * @author Neelay Junnarkar
 */
public class JFenrir extends IterativeRobot {
    
    private Robot robot;
    private HumanController humanController;
    private AutonomousController autoController;
    private Timer timer;
    
    public JFenrir() {
        robot = new Robot();
        humanController = new HumanController(robot);
        autoController = new AutonomousControler(robot);
        timer = new Timer();
    }
    
    public void robotInit() {
        robot.init();
    }
    
    public void autonomousInit() {
        timer.start();
	    robot.init();
	    autoController.init();
    }
    
    public void autonomousPeriodic() {
        autoController.update();
        robot.update();
    }
    
    public void autonomousDisabled() {
        
    }
    
    public void disabledInit() {
        robot.disable();   
    }
    
    public void disabledPeriodic() {
        robot.disable();
        robot.update();
    }
    
    public void teleopInit() {
        robot.init();
    }
    
    public void teleopPeriodic() {
        humanController.update();
        robot.update();
    }
    
    public void teleopDisabled() {
        
    }
    
    public void testInit() {
        
    }
    
    public void testPeriodic() {
        
    }
    
}
