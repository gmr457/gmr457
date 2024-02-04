package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MOTOR_IDS.INTAKE;
import java.util.function.Supplier;

public class IntakeSubsystem extends SubsystemBase
{

  CANSparkMax intake;
  DigitalInput noteInput;


  public IntakeSubsystem()
  {
    intake = new CANSparkMax(INTAKE.INTAKE_CAN_ID, MotorType.kBrushless);
    noteInput = new DigitalInput(5);
  }

  public Command intakeCommand(Supplier<Boolean> on)
  {
    return run(() -> intake.set(on.get() ? 0.8 : 0)).until(this::noteIntaked).andThen(run(() -> intake.set(0)));
  }

  public Command feedCommand()
  {
    return run(() -> intake.set(0.1)).until(() -> !this.noteIntaked());
  }

  public boolean noteIntaked()
  {
    return noteInput.get();
  }
}

