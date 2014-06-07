/*
 * See JFenrir.java for documentation
 * Coded by the 2013-14 Paly Robotics Programming Team
 */

package com.palyrobotics;

import edu.wpi.first.wpilibj.*;

/**
 * Runs the accumulator subsystem
 * 
 * @author Neelay Junnarkar
 */
public class Accumulator extends Subsystem {

        private Victor accuVic;

        private int state;
        private double speed;

        public void setState(int state) { 
                this.state = state; 
        }

        public int getState() { 
                return state;
        }

        private static final int IDLE = 0, ACCUMULATING = 1, PASSING = 2; 

        public Accumulator() {
                accuVic = new Victor(Constants.PORT_ACCUMULATOR_VIC_7);
                speed = 0.0;
        }

        public void init() {
                state = IDLE;
        }

        public void disable() {
                state = IDLE;
        }

        public void update() {
                //System.out.println("accuVic: "+accuVic.get());
                System.out.println(state);
                switch(state) {

                        case IDLE:
                                accuVic.set(0.0);
                                break;
                        case ACCUMULATING:
                                accuVic.set(speed);
                                break;
                        case PASSING:
                            System.out.println("PASSING");
                                accuVic.set(1.0);
                                break;
                }
        }

        public static class AccumulateCommand extends RobotCommand {
                private double arg;
                public AccumulateCommand(double speed) {
                        subsystemType = RobotCommand.ACCUMULATOR;
                        this.arg = speed;
                }

                public void execute(Subsystem accu) {
                        ((Accumulator)accu).speed = arg;
                        ((Accumulator)accu).setState(ACCUMULATING);
                }
        }

        public static class PassCommand extends RobotCommand {

               public PassCommand() {
                        subsystemType  = RobotCommand.ACCUMULATOR;
                }
                public void execute(Subsystem accu) {
                    System.out.println("");
                        ((Accumulator)accu).setState(PASSING);
                }
    }

        public static class FlushCommand extends RobotCommand {
                public FlushCommand() {
                        subsystemType  = RobotCommand.ACCUMULATOR;
                }
                public void execute(Subsystem accu) {
                        ((Accumulator)accu).setState(PASSING);
                        System.out.println("Accumulator is Passing");
                }
        }

        public static class SetIdleCommand extends RobotCommand {

                public SetIdleCommand() {
                        subsystemType = RobotCommand.ACCUMULATOR;
                }
                public void execute(Subsystem accu) {
                        ((Accumulator)accu).setState(IDLE);
                }
        }

        public static class EjectCommand extends RobotCommand {

                public EjectCommand() {
                        subsystemType = RobotCommand.ACCUMULATOR;
                }
                public void execute(Subsystem accu) {
                        ((Accumulator)accu).setState(PASSING);
                }
        }
}
