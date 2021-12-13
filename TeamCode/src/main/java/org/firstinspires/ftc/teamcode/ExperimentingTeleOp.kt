package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.FourWheelRobot
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.GamepadUtil
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.RobotUtil
import org.firstinspires.ftc.teamcode.Vector2d
import com.qualcomm.robotcore.hardware.Gamepad

// This class is a TeleOp for experimenting different things.
@TeleOp(name = "Experimenting TeleOp", group = "Dev")
class ExperimentingTeleOp : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot? = null
    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot!!.reset()
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
            if (gamepad1.a) controllerConfig = 'a' else if (gamepad1.b) controllerConfig = 'b' else if (gamepad1.x) controllerConfig = 'x' else if (gamepad1.y) controllerConfig = 'y'
            when (controllerConfig) {
                'a' -> configA()
                'b' -> configB()
                'x' -> configX()
                'y' -> configY()
            }
        }
    }

    private fun configA() {
        if (GamepadUtil.leftTriggerPressed(gamepad1)) {
            robot!!.rotate(-0.5)
        } else if (GamepadUtil.rightTriggerPressed(gamepad1)) {
            robot!!.rotate(0.5)
        } else {
            robot!!.translate(gamepad1.right_stick_x.toDouble(), (-1 * gamepad1.left_stick_y).toDouble())
        }
    }

    private fun configB() {
        if (Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot!!.rotate(gamepad1.right_stick_x.toDouble())
        } else {
            robot!!.translate(gamepad1.left_stick_x.toDouble(), (-1 * gamepad1.left_stick_y).toDouble())
        }
    }

    private fun configX() {}
    private fun configY() {}
}