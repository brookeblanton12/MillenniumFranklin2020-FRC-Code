/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


    public static final class DriveConstants{
        public static final int frDrive = 1;
        public static final int flDrive = 2;
        public static final int brDrive = 3;
        public static final int blDrive = 4;
        public static final double deadband = 0.1;
    }

    public static final class AutonConstants{
        public static final double defaultAutonPower = .25;
        public static final int defaultDriveDistance = 3;
    }

    public static final class IntakeConstants{
        public static final int intakePort = 5;
        public static final double intakeInPower = .5;
        public static final double intakeOutPower = -.5;
    }

    public static final class ShooterConstants{
        public static final int shooterPort = 2;
        public static final double defaultTargetRPM = 5000;
        public static final double defaultKf = 0;
        public static final double defaultKp = 0;
    }

    public static final class ClimberConstants{
        public static final int winch = 5;
        public static final double defaultPowerFast = .7;
        public static final double defaultPowerSlow = .4;
        public static final int sTWinch = 6;
    }

    public static final class HopperConstants{
        public static final int upCount = 0;
        public static final int downCount = 1;
        public static final int hopperPort = 7;
        public static final double hopperUpPower = .5;
        public static final double hopperDownPower = -.5;
    }

    public static final class JoystickConstants {
        //Controllers
        public static final int DRIVER_PORT = 0;
        public static final int OPERATOR_PORT = 1;
            
        //XboxOne Joysticks (axes)
        public static final int LEFT_STICK_X = 0;
        public static final int LEFT_STICK_Y = 1;
        public static final int LEFT_TRIGGER = 2;
        public static final int RIGHT_TRIGGER = 3;
        public static final int RIGHT_STICK_X = 4;
        public static final int RIGHT_STICK_Y = 5;
        
        //XboxOne Buttons
        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int BUMPER_LEFT = 5;
        public static final int BUMPER_RIGHT = 6;
        public static final int LOGO_LEFT = 7;
        public static final int LOGO_RIGHT = 8;
        public static final int LEFT_STICK_BUTTON = 9;
        public static final int RIGHT_STICK_BUTTON = 10;
    }
}
