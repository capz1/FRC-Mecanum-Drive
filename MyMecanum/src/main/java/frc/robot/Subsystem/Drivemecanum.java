package frc.robot.Subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivemecanum extends SubsystemBase {
    public static final SparkMax leftFrontMotor = new SparkMax(1, MotorType.kBrushless);
    public static final SparkMax leftBackMotor = new SparkMax(2, MotorType.kBrushless);
    public static final SparkMax rightFrontMotor = new SparkMax(3, MotorType.kBrushless);
    public static final SparkMax rightBackMotor = new SparkMax(4, MotorType.kBrushless);
    @Override
    public void periodic() {

    }

}
