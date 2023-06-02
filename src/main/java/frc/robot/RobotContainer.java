/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final Tank_Drive _tank_Drive = new Tank_Drive();
  private final Joystick _driver = new Joystick(0);
  private final Joystick _operator = new Joystick(1);
  // private final Hopper _hopper = new Hopper();
  // private final Intake _intake = new Intake();
  // private final Shooter _shooter = new Shooter();
  private final Climber _climber = new Climber();
  private final Camera _camera = new Camera();
  //private final AutonCommand _autonCommand = new AutonCommand();
  private Preferences _preferences = Preferences.getInstance();
  private final AutonDriveForward _autonCommand = new AutonDriveForward(_tank_Drive);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    _tank_Drive.configDrive();
    _tank_Drive.setDefaultCommand(new ArcadeDrive(_tank_Drive, _driver));
  }

  public Tank_Drive getTank_Drive(){
    return _tank_Drive;
  }

/**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // final JoystickButton hopperUp = new JoystickButton(_operator, Constants.JoystickConstants.Y);
    // hopperUp.whileHeld(new HopperUp(_hopper));

    // final JoystickButton hopperDown = new JoystickButton(_operator, Constants.JoystickConstants.A);
    // hopperDown.whileHeld(new HopperDown(_hopper));

    // final JoystickButton intakeIn = new JoystickButton(_operator, Constants.JoystickConstants.BUMPER_RIGHT);
    // intakeIn.whileHeld(new IntakeIn(_intake));

    // final JoystickButton intakeOut = new JoystickButton(_operator, Constants.JoystickConstants.BUMPER_LEFT);
    // intakeOut.whileHeld(new IntakeOut(_intake));

    // final JoystickButton shooterShoot = new JoystickButton(_driver, Constants.JoystickConstants.BUMPER_RIGHT);
    // shooterShoot.whileHeld(new ShooterShoot(_shooter));
    
    new JoystickButton(_operator, Constants.JoystickConstants.B)
      .whileHeld(() -> _climber.climbFast())
      .whenReleased(() -> _climber.motorOff());
    new JoystickButton(_operator, Constants.JoystickConstants.X)
      .whileHeld(() -> _climber.climbSlow())
      .whenReleased(() -> _climber.motorOff());
    new JoystickButton(_operator, Constants.JoystickConstants.LOGO_LEFT)
      .whileHeld(() -> _climber.motorResetSlow())
      .whenReleased(() -> _climber.motorOff());
    new JoystickButton(_operator, Constants.JoystickConstants.LOGO_RIGHT)
      .whileHeld(() -> _climber.motorResetFast())
      .whenReleased(() -> _climber.motorOff());
    new JoystickButton(_operator, JoystickConstants.BUMPER_LEFT)
      .whileHeld(() -> _climber.raiseArms())
      .whenReleased(() -> _climber.armsOff());
  //   new JoystickButton(_operator, Constants.JoystickConstants.Y).whileHeld(() -> _hopper.HopperUp());
  //   new JoystickButton(_operator, Constants.JoystickConstants.A).whileHeld(() -> _hopper.HopperDown());
  //   new JoystickButton(_operator, Constants.JoystickConstants.BUMPER_RIGHT).whileHeld(() -> _intake.takeIntake());
  //   new JoystickButton(_operator, Constants.JoystickConstants.BUMPER_LEFT).whileHeld(() -> _intake.letGoIntake());
  //   new JoystickButton(_driver, Constants.JoystickConstants.BUMPER_RIGHT).whileHeld(() -> _shooter.shoot());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return _autonCommand;
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
  }

  public void loadPreferences() {
    double climberPowerFast = _preferences.getDouble("Climber.Power.Fast", Constants.ClimberConstants.defaultPowerFast);
    SmartDashboard.putNumber("Climber Fast", climberPowerFast);
    _climber.setFastPower(climberPowerFast);

    double climberPowerSlow = _preferences.getDouble("Climber.Power.Slow", Constants.ClimberConstants.defaultPowerSlow);
    SmartDashboard.putNumber("Climber Slow", climberPowerSlow);
    _climber.setSlowPower(climberPowerSlow);

    // double hopperDownPower = _preferences.getDouble("Hopper.Down.Power", Constants.HopperConstants.hopperDownPower);
    // SmartDashboard.putNumber("Hopper Down", hopperDownPower);
    // _hopper.setDownPower(hopperDownPower);

    // double hopperUpPower = _preferences.getDouble("Hopper.Up.Power", Constants.HopperConstants.hopperUpPower);
    // SmartDashboard.putNumber("Hopper Up", hopperUpPower);
    // _hopper.setUpPower(hopperUpPower);

    // double intakeInPower = _preferences.getDouble("Intake.In.Power", Constants.IntakeConstants.intakeInPower);
    // SmartDashboard.putNumber("Intake In", intakeInPower);
    // _intake.setTakeIn(intakeInPower);

    // double intakeOutPower = _preferences.getDouble("Intake.Out.Power", Constants.IntakeConstants.intakeOutPower);
    // SmartDashboard.putNumber("Intake Out", intakeOutPower);
    // _intake.setLetGo(intakeOutPower);

    double driveDistance = _preferences.getDouble("Auton.Distance", Constants.AutonConstants.defaultDriveDistance);
    SmartDashboard.putNumber("Auton Distance", driveDistance);
    _tank_Drive.setAutonDistance(driveDistance);

    double aDrivePower = _preferences.getDouble("Auton.Power", Constants.AutonConstants.defaultAutonPower);
    SmartDashboard.putNumber("Auton Power", aDrivePower);
    _tank_Drive.setAutonPower(aDrivePower);

    //TODO: Shooter - RPM
    //TODO: Shooter - kF
    //TODO: Shooter - kP
  }

  public void savePreferences() {
    _preferences.putDouble("Climber.Power.Fast", _climber.getFastPower());
    _preferences.putDouble("Climber.Power.Slow", _climber.getSlowPower());
    _preferences.putDouble("Auton.Distance", _tank_Drive.getAutonDistance());
    _preferences.putDouble("Auton.Power", _tank_Drive.getAutonPower());
    //TODO: save preferences for auton
  }
}
