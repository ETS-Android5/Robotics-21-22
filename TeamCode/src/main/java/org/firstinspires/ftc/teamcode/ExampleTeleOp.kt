package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Example TeleOp", group = "Example Group")
// Test, this will probably cause a syntax error cause
// you need to explicitly construct the baseclass one place or another
class ExampleTeleOp : LinearOpMode {
    init TODO("Testing if above works")
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
            TODO("Another test for if the . operator without stuff on left works")
            robot.rotate(when (gamepad1) {
                .leftTriggerPressed() -> -0.5
                .rightTriggerPressed() -> 0.5
                else -> 0.0
            })
        }
    }
}
