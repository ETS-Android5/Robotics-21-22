package org.firstinspires.ftc.teamcode.machines

import kotlin.math.roundToInt

import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot
import org.firstinspires.ftc.teamcode.common.getDcMotor
import org.firstinspires.ftc.teamcode.common.getServo

class MadMachinesRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {

    override val leftFront = getWheel("leftFront", DcMotorSimple.Direction.FORWARD)
    override val rightFront = getWheel("rightFront", DcMotorSimple.Direction.REVERSE)
    override val leftRear = getWheel("leftRear", DcMotorSimple.Direction.REVERSE)
    override val rightRear = getWheel("rightRear", DcMotorSimple.Direction.REVERSE)

    val clawLeft = hardwareMap.getServo(
        "clawLeft",
        Servo.Direction.REVERSE,
    )
    val clawRight = hardwareMap.getServo(
        "clawRight",
        Servo.Direction.FORWARD,
    )

    val armLeft = hardwareMap.getDcMotor(
        "armLeft",
        DcMotorSimple.Direction.REVERSE,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )
    val armRight = hardwareMap.getDcMotor(
        "armRight",
        DcMotorSimple.Direction.FORWARD,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

    val carousel = hardwareMap.getDcMotor(
        "carousel",
        DcMotorSimple.Direction.FORWARD,
        DcMotor.ZeroPowerBehavior.FLOAT,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

    private fun getArm(
        name: String,
        direction: DcMotorSimple.Direction,
    ) = hardwareMap.getDcMotor(
        name,
        direction,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

    var carouselSpinning: Boolean = false
        set(value) {
            carousel.power = if (value) 0.5 else 0.0
            field = value
        }

    //SP stands for ServoPositions
    private data class SP(val open: Double, val close: Double)
    private data class ClawDescriptor(val servo: Servo, val positions: SP)

    private val claws = listOf(
        ClawDescriptor(
            clawLeft,
            SP(open=0.7, close=0.85),
        ),
        ClawDescriptor(
            clawRight,
            SP(open=0.3, close=0.45),
        ),
    )

    fun openClaws() {
        for ((servo, positions) in claws)
            servo.position = positions.open
    }
    fun closeClaws() {
        for ((servo, positions) in claws)
            servo.position = positions.close
    }

    // Arm motor descriptor data class. Describes details of each arm motor.
    // Each set of bounds = (lower (lowest position), upper (highest position))
    private data class ArmMotorDescriptor(val motor: DcMotor, val bounds: Pair<Int, Int>)

    // Tunable parameters
    private val armMotors = listOf(
        ArmMotorDescriptor(armLeft, 783 to 284),
        ArmMotorDescriptor(armRight, 506 to -2),
    )
    private val armPositionBounds = 0.0..1.0
    private val armInitialPosition = 0.0

    private var armInitialized = false
    // Call this function after the game has started,
    // i.e. the player has pressed play.
    fun resetArm() {
        armInitialized = true
        armPosition = armInitialPosition
        for (motor in arrayOf(armLeft, armRight)) {
            motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            motor.power = 0.2
        }
    }
    var armPosition: Double = 0.0
        // Set arm position.
        // Parameter is a position value between (and including) 0.0 and 1.0.
        // 0.0 is arm's lowest position, 1.0 is arm's highest position.
        set(value) {
            check(armInitialized) { "Arm position was set without initializing arm first." }

            // Clamp arm position between 0.0 and 1.0.
            field = value.coerceIn(0.0, 1.0)

            // Compute outputs based on supplied position and arm bounds.
            armMotors.map { (motor, bounds) ->
                val (lower, upper) = bounds
                motor.targetPosition = (lower + field * (upper - lower)).roundToInt()
            }
        }
}
