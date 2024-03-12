// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  public final CANSparkMax m_climbFront;
	public final CANSparkMax m_climbBack;
  private final DigitalInput limitSwitch = new DigitalInput(1);
  public static boolean isMovingUp = false;

  /** Creates a new Climber. */
  public Climber() {
    m_climbFront = new CANSparkMax(11, MotorType.kBrushed);
    m_climbBack = new CANSparkMax(12, MotorType.kBrushed);
    m_climbFront.setIdleMode(IdleMode.kBrake);
    m_climbBack.setIdleMode(IdleMode.kBrake);
  }

  public static void toggle_isMovingUp() {
    isMovingUp = !isMovingUp;
  }

  public void moveUp() {
    m_climbFront.set(0.4);
    m_climbBack.set(0.4);
  }

  public void moveDown() {
    m_climbFront.set(-1);
    m_climbBack.set(-1);
  }

  public void slowMoveDown() {
    m_climbFront.set(-0.25);
    m_climbBack.set(-0.25);
  }

  public void stopClimb() {
    m_climbFront.set(0);
    m_climbBack.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
