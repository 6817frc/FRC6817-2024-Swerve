// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final CANSparkMax m_climbFront;
	private final CANSparkMax m_climbBack;
  private final DigitalInput limitSwitch = new DigitalInput(1);
    
  /** Creates a new Climber. */
  public Climber() {
    m_climbFront = new CANSparkMax(11, MotorType.kBrushed);
    m_climbBack = new CANSparkMax(12, MotorType.kBrushed);
  }

  public void moveUp() {

  }

  public void moveDown() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
