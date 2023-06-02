/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.*;

public class Tank_Drive extends SubsystemBase {

  public Tank_Drive() {

  }

  /**
   * Creates a new Tank_Drive.
   */
  private final CANSparkMax flDrive = new CANSparkMax(DriveConstants.flDrive, MotorType.kBrushless);
  private final CANSparkMax frDrive = new CANSparkMax(DriveConstants.frDrive, MotorType.kBrushless);
  private final CANSparkMax blDrive = new CANSparkMax(DriveConstants.blDrive, MotorType.kBrushless);
  private final CANSparkMax brDrive = new CANSparkMax(DriveConstants.brDrive, MotorType.kBrushless);

  private final DifferentialDrive _drive = new DifferentialDrive(flDrive, frDrive);

  private final CANEncoder _encoderLeft = flDrive.getEncoder();
  private final CANEncoder _encoderRight = frDrive.getEncoder();

  Timer timer = new Timer();
  private double _distance;
  private double _power;

  private final double _deadband = Constants.DriveConstants.deadband;

  public void configDrive() {
    blDrive.follow(flDrive);
    brDrive.follow(frDrive);

    flDrive.setInverted(false);
    frDrive.setInverted(false);
    blDrive.setInverted(false);
    brDrive.setInverted(false);

    flDrive.setOpenLoopRampRate(0.5);
    frDrive.setOpenLoopRampRate(0.5);
    blDrive.setOpenLoopRampRate(0.5);
    brDrive.setOpenLoopRampRate(0.5);
    // _encoderLeft.setPositionConversionFactor(25);
    _encoderLeft.setPosition(0);

    // _encoderRight.setPositionConversionFactor(25);
    _encoderRight.setPosition(0);

    _drive.setDeadband(_deadband);
  }

  public void teleopDrive(final Joystick driveControl) {
    final double forward = driveControl.getRawAxis(JoystickConstants.LEFT_STICK_Y);
    double turn = driveControl.getRawAxis(JoystickConstants.RIGHT_STICK_X);
    turn = turn*-1;

    // _drive.arcadeDrive(forward, turn);
    _drive.curvatureDrive(forward, turn, Math.abs(forward) <= _deadband);

  
    
  }

  @Override
  public void periodic() {
    _distance = SmartDashboard.getNumber("Auton Distance", Constants.AutonConstants.defaultDriveDistance);
    _power = SmartDashboard.getNumber("Auton Power", Constants.AutonConstants.defaultAutonPower);
  }

  public void limelightDrive(final Joystick driver, final double output) {
    final double forward = driver.getRawAxis(1);
    _drive.arcadeDrive(-forward, output);
  }

  public double getLeftEncoderPosition() {
    return _encoderLeft.getPosition();
  }

  public double getRightEncoderPosition() {
    return _encoderRight.getPosition() * -1;
  }

  public void autoMoveForward() {
    _drive.arcadeDrive(_power, 0);
  }
  // public void forwardAuto(double driveDistance) {
  // timer.reset();
  // timer.start();
  // double currentTime = timer.get();
  // if (getCurrentPosition() < driveDistance) {
  // flDrive.set(1);
  // frDrive.set(1);
  // }
  // else if (getCurrentPosition() >= driveDistance) {
  // flDrive.set(0);
  // frDrive.set(0);
  // }
  // }
  // public double getCurrentPosition(){
  // double currentDistance = getRightEncoderPosition() * timer.get() / 60 * 6 *
  // Math.PI;
  // return currentDistance;
  // }

  public void setAutonDistance(double driveDistance) {
    _distance = driveDistance;
  }

  public void setAutonPower(double pow) {
    _power = pow;
  }
 
  public double getAutonDistance(){
    return _distance;
  }

  public double getAutonPower(){
    return _power;
  }
}
