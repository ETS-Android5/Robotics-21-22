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

    suspend fun groundToHub() {
      robot.mainArm.position = 0.8
      delay(4000)
      robot.auxilliaryArm.position = 0.8
    }

    override fun runOpMode(): Unit = runBlocking {
        robot = GadgetsRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        launch {
          delay(5000)
          telemetry.addData("Test", )h
        }

        // Wait for the game to start (driver presses PLAY)

        waitForStart()

        //Younjin auntanamous

        //robot.translate(0.0, 0.25)
        //delay(1500)
        //robot.translate(0.0, -0.25)
        //delay(1500)
        //robot.translate(0.0, 0.0)

        //Avi auntanamous

        //Move to carasol
        robot.translate(0.0, -0.25)
        delay(1500)
        robot.translate(0.0, 0.0)

        //Insert place on shipping hub

        robot.rotate(-0.25)
        delay(1500)
        robot.rotate(0.0)

        //Reverse to carasol
        robot.translate(0.0, -0.25)
        delay(1500)
        robot.translate(0.0, 0.0)

        groundToHub()

        //Spin carasol

        //Move to shipping hub
        robot.translate(0.0, 0.25)
        delay(4500)



        
    }
}
