package org.firstinspires.ftc.teamcode.machines

import kotlin.math.abs

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Gamepad

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
        robot.reset()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        robot.arm.reset()
        robot.openClaws()

        val defaultScale = 0.7
        var scale = defaultScale
        var auxGamepad = gamepad2

        while (opModeIsActive()) {
            // Controller loop

            scale = when {
                gamepad1.a -> defaultScale
                gamepad1.b -> 0.1
                else -> scale
            }
            auxGamepad = when {
                gamepad1.x -> gamepad1
                gamepad1.y -> gamepad2
                else -> auxGamepad
            }
            
            normalConfig(scale, auxGamepad)
        }
    }

    private fun defaultArmControl(auxGamepad: Gamepad) {
        when {
//            gamepad1.dpad_left -> robot.arm.position -= 0.5
//            gamepad1.dpad_right -> robot.arm.position += 0.5

            auxGamepad.dpad_up -> robot.arm.position += 0.002
            auxGamepad.dpad_down -> robot.arm.position -= 0.002
        }
    }

    var rightBumperPressed = false
    private fun normalConfig(scale: Double, auxGamepad: Gamepad) {
        if (abs(gamepad1.right_stick_x) > 0.2)
            robot.rotate(gamepad1.right_stick_x * scale)
        else {
            robot.translate(
                px = gamepad1.left_stick_x * scale,
                py = (-1 * gamepad1.left_stick_y) * scale,
            )
        }

        when {
            auxGamepad.leftTriggerPressed -> robot.openClaws()
            auxGamepad.rightTriggerPressed -> robot.closeClaws()
        }

        if (auxGamepad.right_bumper) {
            if (!rightBumperPressed) {
                robot.spinCarousel = !robot.spinCarousel
                rightBumperPressed = true
            }
        }
        else {
            rightBumperPressed = false
        }

        defaultArmControl(auxGamepad)
    }
}
