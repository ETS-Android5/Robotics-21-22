package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty

@Autonomous(name = "Main Autonomous", group = "Autonomous")
class MainAutonomous : LinearOpMode() {
    // Declare members
    private var robot: GadgetsRobot by LateInitConstProperty()

    override fun runOpMode() = runBlocking {
        robot = GadgetsRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        robot.translate(0.0, 0.25)
        delay(1500)
        robot.translate(0.0, -0.25)
        delay(1500)
        robot.translate(0.0, 0.0)
    }
}
