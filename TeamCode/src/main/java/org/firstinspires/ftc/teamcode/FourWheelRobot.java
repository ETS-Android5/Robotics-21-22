package org.firstinspires.ftc.teamcode;

import java.util.Objects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FourWheelRobot {
    // Declare members
    public final HardwareMap hardwareMap;

    // Declare motors
    public final DcMotor leftFront;
    public final DcMotor rightFront;
    public final DcMotor leftRear;
    public final DcMotor rightRear;

    // Stores wheel motor fields in row major order
    // (when looking at wheel positions like a matrix)
    public final DcMotor[] wheels;

    private DcMotor getWheel(
        String motorName,
        DcMotorSimple.Direction direction
    ) {
        return RobotUtil.getDcMotor(
            hardwareMap,
            motorName,
            direction,
            DcMotor.ZeroPowerBehavior.FLOAT,
            DcMotor.RunMode.RUN_USING_ENCODER
        );
    }

    public FourWheelRobot(HardwareMap hardwareMap) {
        this.hardwareMap = Objects.requireNonNull(
            hardwareMap,
            "FourWheelRobot was supplied null in its constructor, as an argument for hardwareMap. This is not allowed."
        );

        // Initialize wheels
        leftFront = getWheel("leftFront", DcMotorSimple.Direction.FORWARD);
        rightFront = getWheel("rightFront", DcMotorSimple.Direction.FORWARD);
        leftRear = getWheel("leftRear", DcMotorSimple.Direction.FORWARD);
        rightRear = getWheel("rightRear", DcMotorSimple.Direction.FORWARD);

        wheels = new DcMotor[] {leftFront, rightFront, leftRear, rightRear};
    }

    // Robot utility methods

    // This method sets the power of the wheel motors
    // to the powers specified.
    // The array specifies powers in row major order
    // (when looking at robot wheels like a matrix).
    public FourWheelRobot setWheelPowers(double ...powers) {
        for (int i = 0; i < wheels.length; ++i) {
            wheels[i].setPower(powers[i]);
        }
        return this;
    }

    // Reset all hardware on the robot.
    // Call this method to initialize the robot
    // before you use it.
    public void reset() {
        for (DcMotor wheel : wheels) {
            wheel.setPower(0);
        }
    }

    /* This method takes two power values, a px and py, and linearly translates the robot
     * with the power direction indicated by these values.
     * Imagine the robot on the origin of a coordinate plane, with the front facing positive y.
     * px is the power value in the x direction. py is the power value in the y direction.
     * They can be positive and negative, and the directions the sign indicates is the same
     * in the real world as in a coordinate plane (negative x means left, positive y means forward, etc.).
     *
     * The method returns "this", so that the user can chain together commands.
     * 
     * Disregard notes above. Hastily patched this function to work for non-mechanum wheel robots.
     */
    public FourWheelRobot translate(double px, double py) {
        // Check for NaN
        if (Double.isNaN(px) || Double.isNaN(py)) {
          throw new RuntimeException("You cannot supply NaN into the translate function.");
        }

        // Calculate values
        // double a = px + py;
        // double b = py - px;

        // Set motor powers
        leftFront.setPower(py);
        rightFront.setPower(py);
        leftRear.setPower(py);
        rightRear.setPower(py);

        return this;
    }
    public FourWheelRobot translate(Vector2d vector) {
        return translate(vector.x, vector.y);
    }
    /* This method is like the translate method except it takes a power value
     * and an angle (in degrees).
     * It translates with the power represented by the power provided,
     * in the direction represented by the angle.
     * An angle of 0 means forward. Positive is clockwise, negative is counterclockwise.
     * Power can be negative. In that case, robot will go in opposite direction.
     */
    public FourWheelRobot translatePolar(double power, double direction) {
        return translate(
            Vector2d.construct(0, power).rotate(Math.toRadians(-1*direction))
        );
    }

    /* This method takes one power value and rotates the robot
     * with the power and direction specified by the value.
     * Magnitude of power controls power of rotation.
     * If power is positive, robot rotates clockwise.
     * If power is negative, robot rotates counterclockwise.
     */
    public FourWheelRobot rotate(double power) {
        // Check for NaN
        if (Double.isNaN(power)) {
          throw new RuntimeException("You cannot supply NaN as power parameter into the rotate function.");
        }

        leftFront.setPower(power);
        rightFront.setPower(-power);
        leftRear.setPower(power);
        rightRear.setPower(-power);

        return this;
    }
}
