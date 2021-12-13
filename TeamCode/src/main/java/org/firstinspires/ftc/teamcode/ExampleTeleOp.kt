package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.FourWheelRobot
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.GamepadUtil
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.RobotUtil
import org.firstinspires.ftc.teamcode.Vector2d
import com.qualcomm.robotcore.hardware.Gamepad

@TeleOp(name = "Example TeleOp", group = "Example Group")
class ExampleTeleOp : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot? = null
    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot!!.reset()
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        while (opModeIsActive()) {
            // Controller loop
            if (GamepadUtil.leftTriggerPressed(gamepad1)) {
                robot!!.rotate(-0.5)
            } else if (GamepadUtil.rightTriggerPressed(gamepad1)) {
                robot!!.rotate(0.5)
            } else {
                robot!!.rotate(0.0)
            }
        }
    }
}