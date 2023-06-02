/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class LimelightMove extends CommandBase {
  /**
   * Creates a new LimelightMove.
   */
  double lastError = 0;
  double error_sum = 0;
  double maxOutput = 0;


  private Limelight _limelight;
  
  public LimelightMove(Limelight limelight) {
    // Use a
    //addRequirements() here to declare subsystem dependencies.
    _limelight = limelight;
    addRequirements(limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _limelight.lightOn();
    _limelight.initializePID();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _limelight.limelightDrive();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _limelight.lightOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
