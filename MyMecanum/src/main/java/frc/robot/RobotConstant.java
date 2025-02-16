package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotConstant {
    public static final class Drive{
        public static final double maxSpeedMPS = 4;
        public static final double wheeldiameter = 5;

        public static final int leftFrontWheel= 1;
        public static final int leftBackWheel= 2;
        public static final int rightFrontWheel= 3;
        public static final int rightBackWheel= 4;

        public static final boolean frontLeftWheelInverted = false;
        public static final boolean backLeftWheelInverted= false;
        public static final boolean frontRightWheelInverted= true;
        public static final boolean backRightWheelInverted= true;

        public static final Translation2d frontLeftLocation = new Translation2d();
        public static final Translation2d frontRightLocation = new Translation2d();
        public static final Translation2d backLeftLocation = new Translation2d();
        public static final Translation2d backRightLocation = new Translation2d();

        public static final double maxAngularVelocity = Units.rotationsToRadians(1);
    }
}
