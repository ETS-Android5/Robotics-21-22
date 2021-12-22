package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotorSimple

// Motor and arm tester
@TeleOp(name = "Motor Tester", group = "Dev")
class MotorTesting : LinearOpMode() {
    // Declare members
    private var robot: MadMachinesRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()

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
            val thingsToPrint: Array<String, Any?> = arrayOf(
                "arm left" to robot.armLeft.currentPosition,
                "arm right" to robot.armRight.currentPosition,
                "left front wheel" to robot.leftFront.currentPosition,
                "left trigger" to gamepad1.left_trigger,
                "left claw" to robot.clawLeft.position,
                "right claw" to robot.clawRight.position,
            )
            for ((caption, content) in thingsToPrint)
                telemetry.addData(caption, content)
            telemetry.update()
        }
    }
}
