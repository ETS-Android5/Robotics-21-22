package org.firstinspires.ftc.teamcode

import kotlin.math.abs
import kotlin.math.roundToInt

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

class MadMachinesRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {
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

    private fun getArm(
        name: String,
        direction: DcMotorSimple.Direction,
    ) = hardwareMap.getDcMotor(
        name,
        direction,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

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

// This class is a TeleOp for experimenting different things.
@TeleOp(name = "Experimenting TeleOp", group = "Dev")
class ExperimentingTeleOp : LinearOpMode() {
    // Declare members
    private var robot: MadMachinesRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = MadMachinesRobot(hardwareMap)
        robot.reset()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        robot.resetArm()

        val defaultScale = 0.7
        var scale = defaultScale

        while (opModeIsActive()) {
            // Controller loop

            scale = when {
                gamepad1.a -> defaultScale
                gamepad1.b -> 0.1
                else -> scale
            }
            
            normalConfig(scale)
        }
    }

    private fun defaultArmControl() {
        when {
            gamepad1.dpad_left -> robot.armPosition = 0.0
            gamepad1.dpad_right -> robot.armPosition = 1.0

            gamepad1.dpad_up -> robot.armPosition += 0.002
            gamepad1.dpad_down -> robot.armPosition -= 0.002
        }
    }

    private fun normalConfig(scale: Double) {
        if (abs(gamepad1.right_stick_x) > 0.2)
            robot.rotate(gamepad1.right_stick_x * scale)
        else {
            robot.translate(
                px = gamepad1.left_stick_x * scale,
                py = (-1 * gamepad1.left_stick_y) * scale,
            )
        }

        when {
            gamepad1.leftTriggerPressed -> robot.openClaws()
            gamepad1.rightTriggerPressed -> robot.closeClaws()
        }

        defaultArmControl()
    }
}
