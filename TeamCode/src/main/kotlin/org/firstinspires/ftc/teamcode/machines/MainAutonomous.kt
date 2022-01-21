package org.firstinspires.ftc.teamcode.machines

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty

@Autonomous(name = "Main Autonomous", group = "Autonomous")
class MainAutonomous : LinearOpMode() {
    // Declare members
    private var robot: MadMachinesRobot by LateInitConstProperty()

    override fun runOpMode(): Unit = runBlocking {
        robot = MadMachinesRobot(hardwareMap)

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
