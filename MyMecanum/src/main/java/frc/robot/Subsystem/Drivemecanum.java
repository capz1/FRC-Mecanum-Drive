package frc.robot.Subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.RobotConstant.Drive.*;

import java.util.function.Supplier;

public class Drivemecanum extends SubsystemBase {
    private final SparkMax frontLeftMotor = new SparkMax(leftFrontWheel, MotorType.kBrushless);
    private final SparkMax frontRightMotor = new SparkMax(rightFrontWheel, MotorType.kBrushless);
    private final SparkMax backLeftMotor = new SparkMax(leftBackWheel, MotorType.kBrushless);
    private final SparkMax backRightMotor = new SparkMax(rightBackWheel, MotorType.kBrushless);
    private final MecanumDrive m_MecanumDrive = new MecanumDrive(frontLeftMotor,backLeftMotor, frontRightMotor, backRightMotor);
    private final Joystick m_stick = new Joystick(0);

    private final AHRS gyro = new AHRS(NavXComType.kMXP_SPI);

    private final MecanumDriveKinematics kinematics;
    private final MecanumDriveOdometry odometry;


    
    public Drivemecanum() {
        kinematics =new MecanumDriveKinematics(frontLeftLocation,frontRightLocation,backLeftLocation,backRightLocation);
        odometry =new MecanumDriveOdometry(kinematics, gyro.getRotation2d(), getWheelPositions());
    
    }

    @Override
    public void periodic() {
        odometry.update(gyro.getRotation2d(), getWheelPositions());
    }

    public void resetPosition(Pose2d knownPose) {
        odometry.resetPose(knownPose);
    }

    public void runSpeeds(ChassisSpeeds chassisSpeeds) {
        MecanumDriveWheelSpeeds wheelSpeeds =kinematics.toWheelSpeeds(chassisSpeeds);
        frontLeftMotor.set(wheelSpeedMPSToPercentOutput(wheelSpeeds.frontLeftMetersPerSecond));
        frontRightMotor.set(wheelSpeedMPSToPercentOutput(wheelSpeeds.frontRightMetersPerSecond));
        backLeftMotor.set(wheelSpeedMPSToPercentOutput(wheelSpeeds.rearLeftMetersPerSecond));
        backRightMotor.set(wheelSpeedMPSToPercentOutput(wheelSpeeds.rearRightMetersPerSecond));
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    private MecanumDriveWheelPositions getWheelPositions() {
        return new MecanumDriveWheelPositions(
            encoderRotationsToDistanceMeters(frontLeftMotor.getEncoder().getPosition()), 
            encoderRotationsToDistanceMeters(frontRightMotor.getEncoder().getPosition()), 
            encoderRotationsToDistanceMeters(backLeftMotor.getEncoder().getPosition()), 
            encoderRotationsToDistanceMeters(backRightMotor.getEncoder().getPosition()));
    }

    private double wheelSpeedMPSToPercentOutput(double wheelSpeedMPS) {
        return wheelSpeedMPS/maxSpeedMPS;
    }

    private double encoderRotationsToDistanceMeters(double encoderRotations) {
        return wheeldiameter*Math.PI*encoderRotations;
    }
    public double actualspeed(double xspeed,double yspeed){
        return Math.sqrt(xspeed*2+yspeed*2);

    } 
    
    public Command driveRobotCentric(Supplier<ChassisSpeeds> chassisSpeedSupplier) {
        return run(() -> runSpeeds(chassisSpeedSupplier.get()));
    }
}
