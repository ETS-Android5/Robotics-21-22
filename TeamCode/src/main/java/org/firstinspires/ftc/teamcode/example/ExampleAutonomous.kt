package org.firstinspires.ftc.teamcode.example

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty

@Autonomous(name = "Example Autonomous", group = "Example Group")
class ExampleAutonomous : LinearOpMode() {
    // Declare members
    private var robot: ExampleRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = ExampleRobot(hardwareMap)
        robot.reset()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        robot.move(translate(0.0, 0.5))
        translate(0.0, 0.5).let(robot::move)
    }
}
