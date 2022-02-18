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
            /*
            when {
                gamepad1.a -> {
                    robot.clawLeft.position = 0.0
                    robot.clawRight.position = 0.0
                }
                gamepad1.b -> {
                    robot.clawLeft.position = 1.0
                    robot.clawRight.position = 1.0
                }
            }
            when {
                gamepad1.dpad_up -> {
                    for (motor in arrayOf(robot.armLeft, robot.armRight))
                        motor.targetPosition += 1
                }
                gamepad1.dpad_down -> {
                    for (motor in arrayOf(robot.armLeft, robot.armRight))
                        motor.targetPosition -= 1
                }
            }
            robot.arm.armMotors.forEach {
//                    it.motor.targetPosition += when {
                it.motor.power = when {
                    gamepad1.dpad_up -> 0.25
                    gamepad1.dpad_down -> -0.25
                    else -> 0.0
                }
            }
            with(robot.arm.armMotors[0].motor) {
                when {
                    gamepad1.leftTriggerPressed -> power = -0.25
                    gamepad1.left_bumper -> power = +0.25
                }
            }
            with(robot.arm.armMotors[1].motor) {
                when {
                    gamepad1.rightTriggerPressed -> power = -0.25
                    gamepad1.right_bumper -> power = +0.25
                }
            }
            */
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
