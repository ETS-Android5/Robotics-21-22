package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotor.RunMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.CRServo
import org.firstinspires.ftc.teamcode.Vector2d

// This class used to use vecmath.Vector2d by extending it,
// but since the vecmath package was having trouble integrating into
// the robotics code build environment, vecmath was just removed instead.
class Vector2d /* extends javax.vecmath.Vector2d */ {
    var x = 0.0
    var y = 0.0
    operator fun set(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    constructor() {}
    constructor(x: Double, y: Double) {
        set(x, y)
    }

    // Rotate vector in place
    // Positive angle is counterclockwise
    // Angle measured in radians
    fun rotate(angle: Double): Vector2d {
        x = x * Math.cos(angle) - y * Math.sin(angle)
        y = y * Math.cos(angle) + x * Math.sin(angle)
        return this
    }

    companion object {
        // Static helper functions
        fun construct(): Vector2d {
            return Vector2d()
        }

        fun construct(x: Double, y: Double): Vector2d {
            return Vector2d(x, y)
        }

        fun polar(r: Double, theta: Double): Vector2d {
            return construct(r, 0.0).rotate(theta)
        }
    }
}