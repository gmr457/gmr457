// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem   m_driveSubsystem   = new DriveSubsystem();
  private final ArmSubsystem     m_armSubsystem     = new ArmSubsystem();
  private final IntakeSubsystem  m_intakeSubsystem  = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController = new Joystick(OperatorConstants.kDriverControllerPort);
  private final Joystick m_operatorController = new Joystick(OperatorConstants.kOperatorControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings()
  {
    m_driveSubsystem.setDefaultCommand(m_driveSubsystem.driveCommand(m_driverController::getLeftY,
                                                              m_driverController::getRightY));
   m_armSubsystem.setDefaultCommand(m_armSubsystem.armLocation(m_operatorController::getYButton, m_operatorController::getXButton, m_operatorController::getAbutton,
                                                               m_operatorController::getBButton));
    m_intakeSubsystem.setDefaultCommand(m_intakeSubsystem.intakeCommand(m_operatorController::getLeftBumper));
    m_shooterSubsystem.setDefaultCommand(m_shooterSubsystem.runMotors(m_operatorController::getRightBumper));

    new Trigger(m_driverController::getBackButton).whileTrue(m_intakeSubsystem.feedCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_driveSubsystem);
  }
}
