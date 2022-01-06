package org.firstinspires.ftc.teamcode.common

// This class is a monoid.
data class FourWheelBuffer(
    val leftFront: Double,
    val rightFront: Double,
    val leftRear: Double,
    val rightRear: Double,
) {
    // Constructs the default identity buffer, which is a buffer with all 0 values.
    constructor(): this(
        0.0, 0.0,
        0.0, 0.0,
    )

    // This method superimposes this buffer and the supplied buffer.
    operator fun plus(other: FourWheelBuffer) = FourWheelBuffer(
        this.leftFront + other.leftFront, this.rightFront + other.rightFront,
        this.leftRear + other.leftRear, this.rightRear + other.rightRear,
    )

    // This method scales this buffer by some value.
    operator fun times(value: Double) = FourWheelBuffer(
        leftFront*value, rightFront*value,
        leftRear*value, rightRear*value,
    )
    operator fun div(value: Double) = times(1/value)
}

// This function superimposes a bunch of buffers and returns a new buffer.
fun sum(vararg buffers: FourWheelBuffer) = buffers.sum()
fun Array<out FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer(), FourWheelBuffer::plus)
fun Iterable<FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer(), FourWheelBuffer::plus)
