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
      robot.auxiliaryArm.position = 0.8
      robot.mainArm.position = 0.0
      robot.auxiliaryArm.position = 0.0

    //suspend fun spinCarousel() {
      //robot.carouselSpinner.power = 0.75
      //delay(5000)
      //robot.carouselSpinner.power = 0.0


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

        //Younjin test auntanamous

        //robot.translate(0.0, 0.25)
        //delay(1500)
        //robot.translate(0.0, -0.25)
        //delay(1500)
        //robot.translate(0.0, 0.0)

        //Avi auntanamous

        //turn left 90
        robot.rotate(-0.25)
        delay(1500)
        robot.rotate(0.0)

        //Move back
        robot.translate(0.0, -0.25)
        delay(500)
        robot.translate(0.0, 0.0)

        //turn right 90
        robot.rotate(0.25)
        delay(1500)
        robot.rotate(0.0)

        //move to hub
        robot.translate(0.0, -0.25)
        delay(500)
        robot.translate(0.0, 0.0)

        groundToHub()

        //go back
        robot.translate(0.0, 0.25)
        delay(500)
        robot.translate(0.0, 0.0)

        //turn right 90
        robot.rotate(0.25)
        delay(1500)
        robot.rotate(0.0)

        //reverse to carousel
        robot.translate(0.0, -0.25)
        delay(2500)
        robot.translate(0.0, 0.0)

        //turn right 90
        robot.rotate(0.25)
        delay(1500)
        robot.rotate(0.0)

        //spin carsouel
        //spinCarousel()

        //move to loading dock
        robot.translate(0.0, -0.5)
        delay(4500)
        robot.translate(0.0, 0.0)
        
    }
}
