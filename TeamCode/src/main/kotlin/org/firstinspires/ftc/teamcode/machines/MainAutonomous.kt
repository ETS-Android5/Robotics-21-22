package org.firstinspires.ftc.teamcode.machines

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        robot.arm.reset()

        val speed = 0.1
        val refTime = 0.0

        coroutineScope {
            launch {
                robot.spinCarousel = true
                delay(5000)
                robot.spinCarousel = false
            }
            robot.closeClaws()
            delay(1500)
            robot.arm.position = 0.5
            delay(1000)
        }

        robot.translate(speed, 0.0)
        delay(1265)
        robot.translate(0.0, 0.0)
        robot.arm.position = 0.0
    }
}
