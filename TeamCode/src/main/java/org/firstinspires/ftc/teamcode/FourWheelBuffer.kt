package org.firstinspires.ftc.teamcode

import org.firstinspires.ftc.teamcode.FourWheelBuffer

/* This class is partially a monoid.
 * At the moment, this class is mutable.
 * However, the only difference between the mutable and immutable
 * versions of this class is the add method.
 * Everything else (concat, construct) behaves the exact same way
 * in the mutable and immutable versions of this class.
 */
class FourWheelBuffer {
    var leftFront = 0.0
    var rightFront = 0.0
    var leftRear = 0.0
    var rightRear = 0.0

    // By default, all values are 0, corresponding to the identity monoid.
    constructor() {}
    constructor(leftFront: Double, rightFront: Double, leftRear: Double, rightRear: Double) {
        setValues(leftFront, rightFront, leftRear, rightRear)
    }

    fun setValues(
            leftFront: Double,
            rightFront: Double,
            leftRear: Double,
            rightRear: Double
    ) {
        this.leftFront = leftFront
        this.rightFront = rightFront
        this.leftRear = leftRear
        this.rightRear = rightRear
    }

    // This method is the monoid mappend function.
    // It superimposes (adds together) this buffer and the supplied buffer,
    // storing the result in this buffer.
    // This works in place.
    fun add(buffer: FourWheelBuffer): FourWheelBuffer {
        leftFront += buffer.leftFront
        rightFront += buffer.rightFront
        leftRear += buffer.leftRear
        rightRear += buffer.rightRear
        return this
    }

    companion object {
        // This function is the monoid mempty function.
        // It returns the identity monoid. The identity monoid is a buffer with all 0s.
        // This just calls the no args constructor, therefore it can be used
        // as a shorthand for constructing a default instance of this class.
        fun construct(): FourWheelBuffer {
            return FourWheelBuffer()
        }

        // This function allows you to construct an instance of this monoid.
        fun construct(leftFront: Double, rightFront: Double, leftRear: Double, rightRear: Double): FourWheelBuffer {
            return FourWheelBuffer(leftFront, rightFront, leftRear, rightRear)
        }

        // This function is the monoid mconcat function.
        // It takes a list of buffers, superimposes them together, and returns a new buffer.
        // This works out of place.
        fun concat(vararg buffers: FourWheelBuffer): FourWheelBuffer {
            val newBuffer = construct()
            for (i in buffers) {
                newBuffer.add(i)
            }
            return newBuffer
        }
    }
}