// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  public final CANSparkMax m_intakeWheels;
	public final CANSparkMax m_intakeArm;
  public final SparkPIDController armPID;

  public Intake() {
    m_intakeWheels = new CANSparkMax(9, MotorType.kBrushless);
    m_intakeArm = new CANSparkMax(10, MotorType.kBrushed);
    m_intakeWheels.setIdleMode(IdleMode.kBrake);
    m_intakeArm.setIdleMode(IdleMode.kBrake);
    armPID = m_intakeArm.getPIDController();
    armPID.setOutputRange(-0.25, 0.25);
    }

  public void grabNote(){
    m_intakeWheels.set(0.25);
  }

  public void moveUp(){
    m_intakeArm.set(0.25);
    // armPID.setReference(0, CANSparkMax.ControlType.kPosition); //0.5 is shooting height
  }

  public void moveDown(){
    m_intakeArm.set(-0.25);
    // armPID.setReference(3, CANSparkMax.ControlType.kPosition);
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
