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
    operator fun plus(other: FourWheelBuffer) = FourWheelBuffer(
        this.leftFront + other.leftFront, this.rightFront + other.rightFront,
        this.leftRear + other.leftRear, this.rightRear + other.rightRear,
    )
    operator fun plus(other: FourWheelBuffer) = FourWheelBuffer(
        FourWheelBuffer(values.zip(other.values) { a, b -> a+b })
    )

    // This method scales this buffer by some value.
    operator fun times(value: Double) = FourWheelBuffer(
        leftFront*value, rightFront*value,
        leftRear*value, rightRear*value,
    )
    operator fun div(value: Double) = times(1/value)
}

// This function superimposes a bunch of buffers and returns a new buffer.
fun sumOf(vararg buffers: FourWheelBuffer) = buffers.sum()
fun Array<out FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer(), FourWheelBuffer::plus)
fun Iterable<FourWheelBuffer>.sum() =
    this.fold(FourWheelBuffer(), FourWheelBuffer::plus)

fun FourWheelBuffer.clampToValue(value: Double = 1.0): FourWheelBuffer {
    val scalingFactor = value/checkNotNull(values.maxOrNull())
    return this * scalingFactor
}
