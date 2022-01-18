package org.firstinspires.ftc.teamcode.common

import kotlin.math.abs

// This class is a monoid.
data class FourWheelBuffer(
    val leftFront: Double,
    val rightFront: Double,
    val leftRear: Double,
    val rightRear: Double,
) {
    companion object {
        // This is the identity buffer, a buffer with all 0 values.
        val empty = FourWheelBuffer(
            0.0, 0.0,
            0.0, 0.0,
        )
    }

    private constructor(values: List<Double>): this(
        values[0], values[1],
        values[2], values[3],
    )

    val values: DoubleArray
        get() = doubleArrayOf(
            leftFront, rightFront,
            leftRear, rightRear,
        )

    // This method superimposes this buffer and the supplied buffer.
    operator fun plus(other: FourWheelBuffer) =
        FourWheelBuffer(values.zip(other.values, ::sum))

    // This method scales this buffer by some value.
    operator fun times(value: Double) = when (value) {
        1.0 -> this
        else -> FourWheelBuffer(values.map { it*value })
    }
    operator fun div(value: Double) = times(1/value)
}

// This function superimposes a bunch of buffers and returns a new buffer.
// If the argument array is empty, then it returns the identity buffer.
fun sumOf(vararg buffers: FourWheelBuffer) = buffers.sum()
fun Array<out FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer.empty, FourWheelBuffer::plus)
fun Iterable<FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer.empty, FourWheelBuffer::plus)

// This function ensures that all values are between -1*value and value
// by scaling the buffer if one or more values are outside of the range.
// value cannot be negative.
// Scales the buffer so that the maximum wheel value is +-1.0.
fun FourWheelBuffer.clampToValue(value: Double): FourWheelBuffer {
    require(value >= 0.0) { "Value supplied to clampToValue cannot be negative." }
    val maxValue = checkNotNull(values.map(::abs).maxOrNull())
    return this * min(value/maxValue, 1.0)
}
