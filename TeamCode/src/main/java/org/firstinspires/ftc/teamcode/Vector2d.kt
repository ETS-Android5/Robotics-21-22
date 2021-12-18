package org.firstinspires.ftc.teamcode

data class Vector2d(val x: Double, val y: Double) {

    // Identity of a vector.
    constructor() : this(0.0, 0.0)

    // Return a new vector that is rotated by an angle.
    // Positive angle is counterclockwise.
    // Angle measured in radians.
    infix fun rotate(angle: Double) = Vector2d(
        x = x * Math.cos(angle) - y * Math.sin(angle),
        y = y * Math.cos(angle) + x * Math.sin(angle),
    )

    companion object {
        // Static helper functions
        // This will probably generate a syntax error, cause I think "this" here
        // refers to the companion object, not the Vector2d constructor.
        TODO("Testing stuff below as described above")
        // This function allows you to get a vector using polar coordinates
        // instead of cartesian coordinates.
        fun polar(r: Double, theta: Double) = this(r, 0.0).rotate(theta)
    }
}
