/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /**
   * Creates a new Limelight.
   */
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  double lastError = 0;
  double error_sum = 0;
  double kP = 0.021;
  double kI = 0.0;
  double kD = 0.15;
  public Limelight() {

  }

  public void initializePID(){
    error_sum = 0;
    //TODO: figure out how to access preferences while still keeping private
    // kP = Robot.preferences.getDouble("Limelight.kP", 0.0);
    // kI = Robot.preferences.getDouble("Limelight.kI", 0.0);
    // kD = Robot.preferences.getDouble("Limelight.kD", 0.0);
  }
  
  public void lightOn(){
    table.getEntry("ledMode").setNumber(3);
  }
  
  public void lightOff(){
    table.getEntry("ledMode").setNumber(0);
  
  }
  
  public void limelightDrive(){
      
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0);
  
    error_sum += x;
  
    double P = kP * x;
    double I = kI * error_sum;
    double D = kD * (lastError - x);
  
    lastError = x;
  
    double output = P + I - D;
  
    SmartDashboard.putNumber("output", output);
    SmartDashboard.putNumber("error", lastError);
  
    //TODO: Robot.kopchassis.limelightDrive(Robot._joystick, output);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
