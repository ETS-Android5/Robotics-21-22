package org.firstinspires.ftc.teamcode.common

import kotlin.math.abs

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
    operator fun times(value: Double) =
        FourWheelBuffer(values.map { it*value })
    operator fun div(value: Double) = times(1/value)
}

// This function superimposes a bunch of buffers and returns a new buffer.
fun sumOf(vararg buffers: FourWheelBuffer) = buffers.sum()
fun Array<out FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer(), FourWheelBuffer::plus)
fun Iterable<FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer(), FourWheelBuffer::plus)

// This function scales the buffer so that the values are constrained
// between -1*value and value.
fun FourWheelBuffer.clampToValue(value: Double = 1.0): FourWheelBuffer {
    val scalingFactor = value/checkNotNull(values.map(::abs).maxOrNull())
    return this * scalingFactor
}
