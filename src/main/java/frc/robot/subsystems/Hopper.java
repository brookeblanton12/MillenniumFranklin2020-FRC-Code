/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.HopperConstants;

public class Hopper extends SubsystemBase {
  /**
   * Creates a new Hopper.
   */
  private final CANSparkMax Hopper = new CANSparkMax(Constants.HopperConstants.hopperPort, MotorType.kBrushless); // 7 is port number
  // DigitalInput upCount = new DigitalInput(HopperConstants.upCount);
  // DigitalInput downCount = new DigitalInput(HopperConstants.downCount);
  int counter = 0;
  boolean lastBottomState = false;
  boolean lastTopState = false;
  double upPower;
  double downPower;

  Counter _counter = new Counter(Counter.Mode.kTwoPulse);

  public Hopper() {
    _counter.setUpSource(HopperConstants.upCount);
    _counter.setDownSource(HopperConstants.downCount);
    _counter.setUpSourceEdge(true, false);
    _counter.setDownSourceEdge(false, true);
  }

  public void HopperUp() {
    Hopper.set(upPower);
  }

  public void HopperDown() {
    Hopper.set(downPower);
  }

  public void setUpPower(double power){
    upPower = power;
  }

  public void setDownPower( double power){
    downPower = power;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("PC Count", _counter.get());
  }
}
