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

        // Default is configA
        TODO("Test")
        var controlLayout = configA

        while (opModeIsActive()) {
            // Controller loop

            // If any of the buttons on gamepad1 are pressed,
            // change the controller configuration to the one
            // assigned to that button.
            controlLayout = when {
                gamepad1.a -> configA
                gamepad1.b -> configB
                gamepad1.x -> configX
                gamepad1.y -> configY
                else -> controlLayout
            }
            
            controlLayout();
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
