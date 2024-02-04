// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MOTOR_IDS.ARM;
import java.util.function.Supplier;

public class ArmSubsystem extends SubsystemBase 
{
  CANSparkMax          arm;
  SparkPIDController   armPID;
  SparkAbsoluteEncoder armThroughbore;
  public ArmSubsystem() 
  {
    arm = new CANSparkMax(ARM.ARMRIGHT_CAN_ID, MotorType.kBrushless);
    arm = new CANSparkMax(ARM.ARMLEFT_FOLLOWER_CAN_ID, MotorType.kBrushless);
    armPID = arm.getPIDController();
    armThroughbore = arm.getAbsoluteEncoder(Type.kDutyCycle);
    armPID.setFeedbackDevice(armThroughbore);

    armPID.setP(0);
    armPID.setI(0);
    armPID.setD(0);
    armPID.setFF(0);

    // 1 Throughbore rotation = 360deg
    armThroughbore.setPositionConversionFactor(360);

    arm.burnFlash();
  
  }
  public Command armLocation(Supplier<Boolean> down, Supplier<Boolean> middle)
  {
    return run(() ->
                   armPID.setReference
                   (Amp.get() ? 90 : (Source.get() ? 45 : (Close.get() ? 50 : 
                   (Mid.get() ? 55 : (Safe.get() ? 60 :  80), ControlType.kPosition)))));
  }

}
