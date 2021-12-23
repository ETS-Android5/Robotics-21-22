package org.firstinspires.ftc.teamcode.common

import kotlin.math.cos
import kotlin.math.sin

data class Vector2d(val x: Double, val y: Double) {

    // Identity of a vector.
    constructor() : this(0.0, 0.0)

    // Return a new vector that is rotated by an angle.
    // Positive angle is counterclockwise.
    // Angle measured in radians.
    infix fun rotate(angle: Double) = Vector2d(
        x = x * cos(angle) - y * sin(angle),
        y = y * cos(angle) + x * sin(angle),
    )

    companion object {
        // Static helper functions
        // This function allows you to get a vector using polar coordinates
        // instead of cartesian coordinates.
        fun polar(r: Double, theta: Double) = Vector2d(r, 0.0).rotate(theta)
    }
}
