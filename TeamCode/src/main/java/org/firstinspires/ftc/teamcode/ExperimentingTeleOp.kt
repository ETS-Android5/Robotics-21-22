package org.firstinspires.ftc.teamcode

import kotlin.math.abs

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

    private var clawLeft: Servo? = null
    private var clawRight: Servo? = null

    private var armLeft: DcMotor? = null
    private var armRight: DcMotor? = null

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()

        clawLeft = RobotUtil.getServo(
                hardwareMap,
                "clawLeft",
                Servo.Direction.REVERSE
        )
        clawRight = RobotUtil.getServo(
                hardwareMap,
                "clawRight",
                Servo.Direction.FORWARD
        )

        armLeft = RobotUtil.getDcMotor(
                hardwareMap,
                "armLeft",
                DcMotorSimple.Direction.REVERSE,
                DcMotor.ZeroPowerBehavior.BRAKE,
                DcMotor.RunMode.RUN_USING_ENCODER
        )
        armRight = RobotUtil.getDcMotor(
                hardwareMap,
                "armRight",
                DcMotorSimple.Direction.FORWARD,
                DcMotor.ZeroPowerBehavior.BRAKE,
                DcMotor.RunMode.RUN_USING_ENCODER
        )

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        // Default is configA
        var controlLayout: () -> Unit = ::configA

        initArm()

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

    private fun openClaws() {
        clawLeft!!.position = 0.7
        clawRight!!.position = 0.3
    }
    private fun closeClaws() {
        clawLeft!!.position = 0.85
        clawRight!!.position = 0.45
    }

    // Tunable parameters
    // Each set of bounds = {lower (lowest position), upper (highest position)}
    private val armLeftBounds = intArrayOf(783, 284)
    private val armRightBounds = intArrayOf(506, -2)
    private val armMinPosition = 0.0
    private val armMaxPosition = 1.0
    private val armInitialPosition = 0.0

    private var armPosition = 0.0

    private fun initArm() {
        setArmPosition(armInitialPosition)
        armLeft!!.mode = DcMotor.RunMode.RUN_TO_POSITION
        armRight!!.mode = DcMotor.RunMode.RUN_TO_POSITION
        armLeft!!.power = 0.3
        armRight!!.power = 0.3
    }
    // Set arm position.
    // Parameter is a position value between (and including) 0.0 and 1.0.
    // 0.0 is arm's lowest position, 1.0 is arm's highest position.
    private fun setArmPosition(position: Double) {
        // Clamp arm position between 0.0 and 1.0.
        var position = position
        position = Math.max(0.0, Math.min(1.0, position))

        // Compute outputs based on supplied position and arm bounds.
        val outputs = intArrayOf(0, 0) // {left_output, right_output}
        val armBounds = arrayOf(armLeftBounds, armRightBounds)
        for (i in armBounds.indices) {
            outputs[i] = Math.round(
                    armBounds[i][0] + position * (armBounds[i][1] - armBounds[i][0])
            ).toInt()
        }

        armLeft!!.targetPosition = outputs[0]
        armRight!!.targetPosition = outputs[1]

        armPosition = position
    }

    // Shift arm position by some value.
    // Positive value moves arm up,
    // negative value moves arm down.
    private fun shiftArmPosition(dPosition: Double) {
        setArmPosition(armPosition + dPosition)
    }

    private fun defaultArmControl() {
        if (gamepad1.dpad_up) shiftArmPosition(0.002) else if (gamepad1.dpad_down) shiftArmPosition(-0.002)
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

        if (gamepad1.leftTriggerPressed) openClaws()
        else if (gamepad1.rightTriggerPressed) closeClaws()

        defaultArmControl()
    }
}
