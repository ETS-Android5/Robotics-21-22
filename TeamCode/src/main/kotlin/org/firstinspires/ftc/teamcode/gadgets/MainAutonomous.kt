package org.firstinspires.ftc.teamcode.gadgets

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty

@Autonomous(name = "Main Autonomous", group = "Autonomous")
class MainAutonomous : LinearOpMode() {
    // Declare members
    private var robot: GadgetsRobot by LateInitConstProperty()

    override fun runOpMode(): Unit = runBlocking {
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
