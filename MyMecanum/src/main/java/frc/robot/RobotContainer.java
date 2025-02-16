// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Subsystem.Drivemecanum;

public class RobotContainer {
  private final XboxController driverController = new XboxController(0);
  private final Drivemecanum drive = new Drivemecanum();
  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    drive.driveRobotCentric(() -> new ChassisSpeeds(
      -driverController.getLeftY() * RobotConstant.Drive.maxSpeedMPS,
      -driverController.getLeftX() * RobotConstant.Drive.maxSpeedMPS,
      -driverController.getRightX() * RobotConstant.Drive.maxAngularVelocity
    ));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
