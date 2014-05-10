public class HumanController
{
	private Robot robot;
	private Joystick speedStick;
	private Joystick turnStick;
	private Joystick operatorStick;

	//booleans used to make sure we don't reissue same command
	private bool accuButtonPrev;
	private bool shootButtonPrev;
	private bool warmupButtonPrev;
	private bool passButtonPrev;
	private bool lastFlushTrigger;
	private bool prevRangeButton;
	private bool prevStop;
	private bool prevZ;


	public HumanController(Robot robot) {
		prevStop = false;
		prevZ = false;
		this.robot = robot;
	}

	public void update() {
		void * argPointer = malloc(sizeof(DriveArgs));

		/*SLIGHT MOVEMENT*/
		if(getAbsTurnStick()<=.1 && getAbsSpeedStick()<=.1) {
			robot.relayCommand(new Drivetrain.SetSpeedCommand(0));
			robot.relayCommand(new Drivetrain.SetRotateCommand(0));
		}

		/*FULL MOVEMENT*/
		if(getAbsSpeedStick()>0.1) {
			robot.relayCommand(new Drivetrain.SetSpeedCommand(speedStick.getY()));
		}
		if(getAbsTurnStick()>0.1) {
			robot.relayCommand(new Drivetrain.SetRotateCommand(rotStick.getX()));
		}

		/*ACCUMULATOR*/
		if(getAccumulator()<-0.2) {
			System.out.print("accumulating\n");
			robot.relayCommand(new Accumulator.StartAccumulatingCommand());
			prevStop = false;
		}
		else if(getAccumulator()>0.2) {
			robot.relayCommand(new Shooter.EjectCommand());
			robot.relayCommand(new Accumulator.EjectCommand());
			prevStop = false;
		}
		else {
			robot.relayCommand(new Accumulator.StopCommand());
			if(!prevStop) {
				robot.relayCommand(new Shooter.IdleCommand());
			}
			prevStop = true;
		}

		/*FLUSHING*/
		if (getFlushTrigger()) {
			robot.relayCommand(new Accumulator.FlushCommand());
			robot.relayCommand(new Shooter.IdleCommand());
			robot.relayCommand(new Accumulator.StopCommand());
			robot.relayCommand(new Shooter.StopCommand());
			robot.relayCommand(new Shooter.FlushCommand());
		}
		else if (!getFlushTrigger() && lastFlushTrigger) {
			
		}
		
		/*SHOOTER*/
		if(getOperatorZ() < 0.5) {
			if(shootButtonPrev!=getShootButton() && getShootButton()) {
				//((DriveArgs*) argPointer)->driveDist = 10; ??? What is this ???
				robot.relayCommand(new Shooter.FireCommand());
			}
			else if (!prevZ) {
				
			}
			prevZ = true;
		}
		else if(getOperatorZ() > 0.5) {
			std::printf("is in manual\n");
			robot.relayCommand(new Shooter.ManualFireCommand());
			
			if(getShootButton()) {
				robot.relayCommand(new Shooter.ManualCommand());
			}
			prevZ = false;
		}
		if(!prevRangeButton && getRangeButton()){
			robot.relayCommand(new Rangefinder.FindDistCommand());
		}	
		
		shootButtonPrev = getShootButton();
		lastFlushTrigger = getFlushTrigger();
		prevRangeButton = getRangeButton();
	}

	private double getAbsSpeedStick(){
		double speed = abs(speedStick.GetY());
		return speed;
	}

	private double getAbsTurnStick() {
		double turn = abs(turnStick.GetX());
		return turn;
	}

	private double getAccumulatorStick() {
		return operatorStick.GetY(); // For adjusting the accumulator with Operator stick
	}
	
	private double getAccumulator() {
		//return operatorStick.GetRawButton((uint32_t)ACCUMULATOR_BUTTON_PORT); // Get button to start accumulator from Operator stick
		return operatorStick.GetY(); // For testing purposes
	}
	
	private bool getShootButton() {
		// Get trigger button to shoot from Operator stick
		// return false;
		return operatorStick.GetRawButton((uint_t)3);
	}
	
	private bool getFlushTrigger() {
		//flush out the ball
		return operatorStick.GetRawButton((uint32_t)FLUSH_TRIGGER);
	}
	private double getOperatorZ() {
		return operatorStick.GetThrottle();
	}
	private bool getRangeButton() {
		return operatorStick.GetRawButton((uint32_t)4);
	}	
}
