package org.firstinspires.ftc.teamcode

import kotlin.math.abs
import kotlin.math.roundToInt

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

// This class is a TeleOp for experimenting different things.
@TeleOp(name = "Experimenting TeleOp", group = "Dev")
class ExperimentingTeleOp : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot by LateInitConstProperty()

    private var clawLeft: Servo by LateInitConstProperty()
    private var clawRight: Servo by LateInitConstProperty()

    private var armLeft: DcMotor by LateInitConstProperty()
    private var armRight: DcMotor by LateInitConstProperty()

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()

        clawLeft = hardwareMap.getServo(
            "clawLeft",
            Servo.Direction.REVERSE,
        )
        clawRight = hardwareMap.getServo(
            "clawRight",
            Servo.Direction.FORWARD,
        )

        for ((name, direction) in arrayOf(
            Pair("armLeft", DcMotorSimple.Direction.REVERSE),
            Pair("armRight", DcMotorSimple.Direction.FORWARD),
        )) {
            
        }

        armLeft = hardwareMap.getDcMotor(
            "armLeft",
            DcMotorSimple.Direction.REVERSE,
            DcMotor.ZeroPowerBehavior.BRAKE,
            DcMotor.RunMode.RUN_USING_ENCODER,
        )
        armRight = hardwareMap.getDcMotor(
            "armRight",
            DcMotorSimple.Direction.FORWARD,
            DcMotor.ZeroPowerBehavior.BRAKE,
            DcMotor.RunMode.RUN_USING_ENCODER,
        )

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        initArm()

        // Default is configA
        var controlLayout: () -> Unit = ::configA

        while (opModeIsActive()) {
            // Controller loop

            // If any of the buttons on gamepad1 are pressed,
            // change the controller configuration to the one
            // assigned to that button.
            controlLayout = when {
                gamepad1.a -> ::configA
                gamepad1.b -> ::configB
                gamepad1.x -> pass
                gamepad1.y -> pass
                else -> controlLayout
            }
            
            controlLayout()
        }
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

    private fun openClaws() {
        for ((servo, positions) in claws)
            servo.position = positions.open
    }
    private fun closeClaws() {
        for ((servo, positions) in claws)
            servo.position = positions.close
    }

    // Arm motor descriptor data class. Describes details of each arm motor.
    // Each set of bounds = (lower (lowest position), upper (highest position))
    private data class ArmMotorDescriptor(val motor: DcMotor, val bounds: IntRange)

    // Tunable parameters
    private val armMotors = listOf(
        ArmMotorDescriptor(armLeft, 783..284),
        ArmMotorDescriptor(armRight, 506..-2),
    )
    private val armPositionBounds = 0.0..1.0
    private val armInitialPosition = 0.0

    private fun initArm() {
        armPosition = armInitialPosition
        for (motor in arrayOf(armLeft, armRight)) {
            motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            motor.power = 0.3
        }
    }
    private var armPosition: Double = 0.0
        // Set arm position.
        // Parameter is a position value between (and including) 0.0 and 1.0.
        // 0.0 is arm's lowest position, 1.0 is arm's highest position.
        set(value) {
            // Clamp arm position between 0.0 and 1.0.
            field = value.coerceIn(0.0, 1.0)

            // Compute outputs based on supplied position and arm bounds.
            armMotors.map { (motor, bounds) ->
                val lower = bounds.start; val upper = bounds.endInclusive
                motor.targetPosition = (lower + field * (upper - lower)).roundToInt()
            }
        }

    private fun defaultArmControl() {
        armPosition = when {
            gamepad1.dpad_up -> 0.002
            gamepad1.dpad_down -> -0.002
            else -> return
        }
    }

    private fun configA() {
        val scale = 0.5
        when {
            gamepad1.leftTriggerPressed -> robot.rotate(-0.5 * scale)
            gamepad1.rightTriggerPressed -> robot.rotate(0.5 * scale)
            else -> robot.translate(
                px = gamepad1.right_stick_x.toDouble() * scale,
                py = (-1 * gamepad1.left_stick_y).toDouble() * scale,
            )
        }

        defaultArmControl()
    }

    private fun configB() {
        when {
            abs(gamepad1.right_stick_x) > 0.2 ->
                robot.rotate(gamepad1.right_stick_x.toDouble())
            else -> robot.translate(
                px = gamepad1.left_stick_x.toDouble(),
                py = (-1 * gamepad1.left_stick_y).toDouble(),
            )
        }

        when {
            gamepad1.leftTriggerPressed -> openClaws()
            gamepad1.rightTriggerPressed -> closeClaws()
        }

        defaultArmControl()
    }
}
