package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MOTOR_IDS.SHOOTER;
import java.util.function.Supplier;

public class ShooterSubsystem extends SubsystemBase
{

  CANSparkMax shooter;

  public ShooterSubsystem()
  {
    shooter = new CANSparkMax(SHOOTER.SHOOTER_CAN_ID, MotorType.kBrushless);
    CANSparkMax shooterFollower = new CANSparkMax(SHOOTER.SHOOTER_FOLLOWER_CAN_ID, MotorType.kBrushless);

    shooterFollower.follow(shooter);
  }

  public Command runMotors(Supplier<Boolean> run)
  {
    return run(() -> shooter.set(run.get() ? 0.675309 : 0));
  }

}
