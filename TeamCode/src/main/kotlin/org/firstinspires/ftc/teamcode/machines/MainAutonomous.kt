package org.firstinspires.ftc.teamcode.machines

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty

@Autonomous(name = "Blue", group = "Autonomous")
class BlueAuto : BaseAuto() {
    override fun runOpMode() = runAutos(true)
}
@Autonomous(name = "Red", group = "Autonomous")
class RedAuto : BaseAuto() {
    override fun runOpMode() = runAutos(false)
}

abstract class BaseAuto : LinearOpMode() {
    // Declare members
    private var robot: MadMachinesRobot by LateInitConstProperty()

    fun runAutos(runBlue: Boolean): Unit = runBlocking {
        robot = MadMachinesRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        robot.arm.reset()
        robot.arm.armMotors.forEach {
            it.motor.power = 0.05
        }

        val speed = 0.1
        val refTime = 0.0

        coroutineScope {
            robot.closeClaws()
            delay(1000)
            launch {
                delay(500)
                if (runBlue)
                    robot.translate(0.0, -speed)
                else
                    robot.translate(-speed, 0.0)
                delay(if (runBlue) 300 else 700)
                robot.translate(0.0, 0.0)
                robot.carousel.power = if (runBlue) -1.0 else 1.0
                delay(5000)
                robot.spinCarousel = false
            }
            robot.arm.position = 0.5
            delay(1000)
        }

        robot.translatePolar(speed, if (runBlue) 110.0 else 0.0 )
        delay(if (runBlue) 1265 else 1065)
        robot.translate(0.0, 0.0)
        robot.arm.position = 0.075
        delay(2000)
        robot.openClaws()
        delay(500)
        robot.arm.position = 0.5

        delay(100_000)
    }
}
