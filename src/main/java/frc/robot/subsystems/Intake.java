// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  public final CANSparkMax m_intakeWheels;
	public final CANSparkMax m_intakeArm;
  public final SparkPIDController armPID;
  public final RelativeEncoder armEncoder;
  public double encoderOffset;
  public double realMotorPos;

  public Intake() {
    m_intakeWheels = new CANSparkMax(9, MotorType.kBrushless);
    m_intakeArm = new CANSparkMax(10, MotorType.kBrushed);
    m_intakeArm.setInverted(true);
    m_intakeWheels.setIdleMode(IdleMode.kBrake);
    m_intakeArm.setIdleMode(IdleMode.kBrake);
    armPID = m_intakeArm.getPIDController();
    armPID.setOutputRange(-0.5, 0.25);
    double value = SmartDashboard.getNumber("PValue", 3);
    SmartDashboard.putNumber("PValue", value);
    armEncoder = m_intakeArm.getEncoder(SparkRelativeEncoder.Type.kQuadrature, 2048);
    encoderOffset = 0;
    }

    public double realMotorPos() {
      realMotorPos = armEncoder.getPosition() - encoderOffset;
      return realMotorPos;
  }

  public void resetArmEncoder() {
    encoderOffset = armEncoder.getPosition();
  }

  public void grabNote(){
    m_intakeWheels.set(0.5);
  }

  public void moveUp(){
    m_intakeArm.set(-0.4);
  }

  public void moveUptoPos(){
    double value = SmartDashboard.getNumber("PValue", 0.05);
    armPID.setP(value);
    armPID.setReference(0.5 + encoderOffset, CANSparkMax.ControlType.kPosition); //0.5 is shooting height
  }

  public void moveDown(){
    m_intakeArm.set(0.25);
  }

  public void moveDowntoPos(){
    armPID.setReference(3 + encoderOffset, CANSparkMax.ControlType.kPosition);
  }

  public void dropNote(){
    m_intakeWheels.set(-0.5);
  }

  public void stopIntake(){
    m_intakeWheels.set(0);
  }

  public void stopArm(){
    m_intakeArm.set(0);
  }

  public void stopEverything(){
    m_intakeWheels.set(0);
    m_intakeArm.set(0);
  }

  public void resetZeroArm(){

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
