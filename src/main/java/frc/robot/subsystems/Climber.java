/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  private final CANSparkMax _winch = new CANSparkMax(Constants.ClimberConstants.winch, MotorType.kBrushless);
  private final TalonSRX _sTWinch = new TalonSRX(Constants.ClimberConstants.sTWinch);
  private double _powerFast = Constants.ClimberConstants.defaultPowerFast;
  private double _powerSlow = Constants.ClimberConstants.defaultPowerSlow;

  public Climber() {
    // setDefaultCommand(new InstantCommand(this::motorOff));
  }
    public void raiseArms(){
      _sTWinch.set(ControlMode.PercentOutput, .5);
    }

    public void armsOff(){
      _sTWinch.set(ControlMode.PercentOutput, 0);
    }

    public void climbFast() {
      _winch.set(_powerFast);
    }

    public void climbSlow() {
      _winch.set(_powerSlow);
    }

    public void setSlowPower(final double p) {
      _powerSlow = p;
    }

    public void setFastPower(final double p) {
      _powerFast = p;
    }

    public double getSlowPower() {
      return _powerSlow;
    }

    public double getFastPower() {
      return _powerFast;
    }

    public void motorOff() {
      _winch.set(0);
    }

    public void motorResetFast() {
      _winch.set(_powerFast * -1);
    }

    public void motorResetSlow() {
      _winch.set(_powerSlow * -1);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    _powerFast = SmartDashboard.getNumber("Climber Fast", Constants.ClimberConstants.defaultPowerFast);
    _powerSlow = SmartDashboard.getNumber("Climber Slow", Constants.ClimberConstants.defaultPowerSlow);
  }
}
