package org.firstinspires.ftc.teamcode.machines

import kotlin.math.abs

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty
import org.firstinspires.ftc.teamcode.common.leftTriggerPressed
import org.firstinspires.ftc.teamcode.common.rightTriggerPressed

// This class is a TeleOp for experimenting different things.
@TeleOp(name = "Experimenting TeleOp", group = "Dev")
class ExperimentingTeleOp : LinearOpMode() {
    // Declare members
    private var robot: MadMachinesRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = MadMachinesRobot(hardwareMap)

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

    var rightBumperPressed = false
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

        if (gamepad1.right_bumper) {
            if (!rightBumperPressed) {
                robot.carouselSpinning = !robot.carouselSpinning
                rightBumperPressed = true
            }
        }
        else {
            rightBumperPressed = false
        }

        defaultArmControl()
    }
}
