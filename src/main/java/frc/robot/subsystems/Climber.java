// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class Climber extends SubsystemBase {
  public final CANSparkMax m_climbFront;
	public final CANSparkMax m_climbBack;
  public final SparkPIDController frontClimbPID;
  public final SparkPIDController backClimbPID;
  public final RelativeEncoder frontEncoder;
  public final RelativeEncoder backEncoder;
  // private final DigitalInput limitSwitch = new DigitalInput(1);
  private double frontEncoderOffset;
  private double backEncoderOffset;
  private double realMotorPos;

  /** Creates a new Climber. */
  public Climber() {
    m_climbFront = new CANSparkMax(Ports.CAN.ClimbFront, MotorType.kBrushless);
    m_climbBack = new CANSparkMax(Ports.CAN.ClimbBack, MotorType.kBrushless);
    m_climbFront.setIdleMode(IdleMode.kBrake);
    m_climbBack.setIdleMode(IdleMode.kBrake);
    frontClimbPID = m_climbFront.getPIDController();
    backClimbPID = m_climbBack.getPIDController();
    frontClimbPID.setOutputRange(-0.5, 0.3);
    backClimbPID.setOutputRange(-0.5, 0.3);
    double value = SmartDashboard.getNumber("climbPValue", 0.05);
    SmartDashboard.putNumber("climbPValue", value);
    frontEncoder = m_climbFront.getEncoder();
    backEncoder = m_climbBack.getEncoder();
    frontEncoderOffset = 0;
    backEncoderOffset = 0;
    // m_climbBack.follow(m_climbFront);
  }

  public double realMotorPos() {
      realMotorPos = frontEncoder.getPosition() - frontEncoderOffset;
      return realMotorPos;
  }

  public void resetClimbEncoder() {
    frontEncoderOffset = frontEncoder.getPosition();
    backEncoderOffset = backEncoder.getPosition();
  }

  public void moveUp() {
    m_climbFront.set(0.3);
    m_climbBack.set(0.3);
  }

  public void moveUptoPos(){
    double value = SmartDashboard.getNumber("climbPValue", 0.05);
    frontClimbPID.setP(value);
    backClimbPID.setP(value);
    frontClimbPID.setReference(54.5 + frontEncoderOffset, CANSparkMax.ControlType.kPosition);
    backClimbPID.setReference(54.5 + backEncoderOffset, CANSparkMax.ControlType.kPosition);
  }

  public void moveDown() {
    m_climbFront.set(-0.5);
    m_climbBack.set(-0.5);
  }

  public void moveDowntoPos(){
    double value = SmartDashboard.getNumber("climbPValue", 0.05);
    frontClimbPID.setP(value);
    backClimbPID.setP(value);
    frontClimbPID.setReference(0 + frontEncoderOffset, CANSparkMax.ControlType.kPosition);
    backClimbPID.setReference(0 + backEncoderOffset, CANSparkMax.ControlType.kPosition);
  }

  public void slowMoveDown() {
    m_climbFront.set(-0.1);
    m_climbBack.set(-0.1);
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
