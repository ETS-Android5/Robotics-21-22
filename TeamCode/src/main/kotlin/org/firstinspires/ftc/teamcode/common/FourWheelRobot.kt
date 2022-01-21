package org.firstinspires.ftc.teamcode.common

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

abstract class FourWheelRobot(val hardwareMap: HardwareMap) {

    // Declare motors
    abstract val leftFront: DcMotor
    abstract val rightFront: DcMotor
    abstract val leftRear: DcMotor
    abstract val rightRear: DcMotor

    // Stores wheel motor fields in row major order
    // (when looking at wheel positions like a matrix)
    val wheels = listOf(leftFront, rightFront, leftRear, rightRear)

    protected fun getWheel(
        motorName: String,
        direction: DcMotorSimple.Direction,
    ): DcMotor = hardwareMap.getDcMotor(
        motorName,
        direction,
        DcMotor.ZeroPowerBehavior.FLOAT,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

    // Robot utility methods
    // This method sets the power of the wheel motors
    // to the powers specified.
    // The array specifies powers in row major order
    // (when looking at robot wheels like a matrix).
    fun setWheelPowers(vararg powers: Double): FourWheelRobot {
        for ((power, wheel) in powers zip wheels) {
            wheel.power = power
        }
        return this
    }

    // Reset all hardware on the robot.
    // Call this method to initialize the robot
    // before you use it.
    fun reset() {
        for (wheel in wheels) {
            wheel.power = 0.0
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
     */
    open fun translate(px: Double, py: Double): FourWheelRobot {
        // Check for NaN
        if (px.isNaN() || py.isNaN())
            throw IllegalArgumentException("You cannot supply NaN into the translate function.")

        // Calculate values
        val a = px + py
        val b = py - px

        // Set motor powers
        leftFront.power = a
        rightFront.power = b
        leftRear.power = b
        rightRear.power = a
        return this
    }

    fun translate(vector: Vector2d) = translate(vector.x, vector.y)

    /* This method is like the translate method except it takes a power value
     * and an angle (in degrees).
     * It translates with the power represented by the power provided,
     * in the direction represented by the angle.
     * An angle of 0 means forward. Positive is clockwise, negative is counterclockwise.
     * Power can be negative. In that case, robot will go in opposite direction.
     */
    fun translatePolar(power: Double, direction: Double) = translate(
        Vector2d(0.0, power).rotate(Math.toRadians(-1 * direction))
    )

    /* This method takes one power value and rotates the robot
     * with the power and direction specified by the value.
     * Magnitude of power controls power of rotation.
     * If power is positive, robot rotates clockwise.
     * If power is negative, robot rotates counterclockwise.
     */
    fun rotate(power: Double): FourWheelRobot {
        // Check for NaN
        if (power.isNaN())
            throw IllegalArgumentException("You cannot supply NaN as power parameter into the rotate function.")

        leftFront.power = power
        rightFront.power = -power
        leftRear.power = power
        rightRear.power = -power
        return this
    }

}
