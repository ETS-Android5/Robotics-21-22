package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty
import org.firstinspires.ftc.teamcode.common.leftTriggerPressed
import org.firstinspires.ftc.teamcode.common.rightTriggerPressed
import kotlin.math.roundToInt

@TeleOp(name = "Motor Tester", group = "Dev")
class MotorTesting : LinearOpMode() {
    // Declare members
    private var robot: GadgetsRobot by LateInitConstProperty()

    override fun runOpMode(): Unit = runBlocking {
        robot = GadgetsRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
//        robot.arm.reset()
        while (opModeIsActive()) {
            robot.armRear.power = when {
                gamepad1.left_bumper -> -0.5
                gamepad1.right_bumper -> 0.5
                else -> 0.0
            }
            robot.armRightFront.power = when {
                gamepad1.leftTriggerPressed -> -0.5
                gamepad1.rightTriggerPressed -> 0.5
                else -> 0.0
            }
            val thingsToPrint: Array<Pair<String, Any?>> = arrayOf(
                "main arm position" to robot.mainArm.position,
                "auxiliary arm position" to robot.auxiliaryArm.position,
                "arm left front current position" to robot.armLeftFront.currentPosition,
                "arm right front current position" to robot.armRightFront.currentPosition,
                "arm rear current position" to robot.armRear.currentPosition,
                // "left front wheel" to robot.leftFront.currentPosition,
                // "left trigger" to gamepad1.left_trigger,
            )
            for ((caption, content) in thingsToPrint)
                telemetry.addData(caption, content)
            telemetry.update()
        }
    }
}
