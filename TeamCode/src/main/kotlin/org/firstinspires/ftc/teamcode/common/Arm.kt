package org.firstinspires.ftc.teamcode.common

import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.roundToInt

open class Arm(
    val power: Double,
    val armMotors: List<MotorDescriptor>,
    val multiplier: Double = 1.0,
    val initialPosition: Double = 0.0,
) {
    protected var initialized = false
    protected lateinit var zeroPositions: List<Int>

    constructor(
        power: Double,
        vararg armMotors: MotorDescriptor,
        multiplier: Double = 1.0,
        initialPosition: Double = 0.0,
    ) : this(
        power,
        armMotors.toList(),
        multiplier,
        initialPosition,
    )

    // Call this function after the game has started,
    // i.e. the player has pressed play.
    open fun reset() {
        initialized = true
        zeroPositions = armMotors.map { it.motor.currentPosition }
        position = initialPosition
        armMotors.forEach {
            it.motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            it.motor.power = this.power
        }
    }
    open var position: Double = initialPosition
        set(value) {
            check(initialized) { "Arm position was set without initializing arm first." }
            field = value

            // Set outputs based on supplied position and arm ranges.
            for ((arm, zero) in armMotors zip zeroPositions) {
                val (motor, range) = arm
                motor.targetPosition = (zero + field * range * multiplier).roundToInt()
            }
        }

    // Arm motor descriptor data class. Describes details of each arm motor.
    data class MotorDescriptor(val motor: DcMotor, val range: Int)
}

class ExactArm(
    power: Double,
    val exactArmMotors: List<ExactArm.MotorDescriptor>,
    initialPosition: Double,
) : Arm(
    power,
    armMotors = exactArmMotors.map { Arm.MotorDescriptor(
        it.motor, it.bounds.second - it.bounds.first
    ) },
    initialPosition = initialPosition,
) {
    override fun reset() {
        initialized = true
        zeroPositions = exactArmMotors.map { it.bounds.first }
        position = initialPosition
        armMotors.forEach {
            it.motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            it.motor.power = this.power
        }
    }
    override var position: Double
        get() = super.position.get()
        set(value) = super.position.set(value.coerceIn(0.0, 1.0))

    data class MotorDescriptor(val motor: DcMotor, val bounds: Pair<Int, Int>)
}
