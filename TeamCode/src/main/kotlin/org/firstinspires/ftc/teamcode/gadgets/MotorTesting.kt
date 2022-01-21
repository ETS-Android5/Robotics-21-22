package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty

@TeleOp(name = "Motor Tester", group = "Dev")
class MotorTesting : LinearOpMode() {
    // Declare members
    private var robot: GadgetsRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = GadgetsRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        while (opModeIsActive()) {
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
            val thingsToPrint: Array<Pair<String, Any?>> = arrayOf(
                "arm left" to robot.arm.armMotors[0].motor.currentPosition,
                "arm right" to robot.arm.armMotors[1].motor.currentPosition,
                // "left front wheel" to robot.leftFront.currentPosition,
                // "left trigger" to gamepad1.left_trigger,
                "left claw" to robot.claws[0].motor.position,
                "right claw" to robot.claws[1].motor.position,
            )
            for ((caption, content) in thingsToPrint)
                telemetry.addData(caption, content)
            telemetry.update()
        }
    }
}
