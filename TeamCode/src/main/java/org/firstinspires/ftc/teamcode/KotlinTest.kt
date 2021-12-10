package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="First Kotlin OpMode")
class KotlinTest : LinearOpMode() {
    // Declare members
    private lateinit var robot: FourWheelRobot

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        while (opModeIsActive()) {
            // Controller loop
            robot.rotate(when {
                GamepadUtil.leftTriggerPressed(gamepad1) -> -0.5
                GamepadUtil.rightTriggerPressed(gamepad1) -> 0.5
                else -> 0.0
            })
        }
    }
}