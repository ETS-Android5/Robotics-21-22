package org.firstinspires.ftc.teamcode

import kotlin.math.abs

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

// This class is a TeleOp for experimenting different things.
@TeleOp(name = "Experimenting TeleOp", group = "Dev")
class ExperimentingTeleOp : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        // Default is config a
        var controllerConfig = 'a'

        while (opModeIsActive()) {
            // Controller loop

            // If any of the buttons on gamepad1 are pressed,
            // change the controller configuration to the one
            // assigned to that button.
            controllerConfig = when {
                gamepad1.a -> 'a'
                gamepad1.b -> 'b'
                gamepad1.x -> 'x'
                gamepad1.y -> 'y'
                else -> controllerConfig
            }
            when (controllerConfig) {
                'a' -> configA()
                'b' -> configB()
                'x' -> configX()
                'y' -> configY()
            }
        }
    }

    private fun configA() {
        when {
            gamepad1.leftTriggerPressed -> robot.rotate(-0.5)
            gamepad1.rightTriggerPressed -> robot.rotate(0.5)
            else -> robot.translate(
                px = gamepad1.right_stick_x.toDouble(),
                py = (-1 * gamepad1.left_stick_y).toDouble(),
            )
        }
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
    }

    private fun configX() {}
    private fun configY() {}
}
