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

@Autonomous(name = "Example Autonomous", group = "Example Group")
class ExampleAutonomous : LinearOpMode() {
    // Declare members
    private lateinit var robot: FourWheelRobot

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        robot.translate(0.0, 0.5)
        sleep(1500)
        robot.translate(0.0, -0.5)
        sleep(1500)
        robot.translate(0.0, 0.0)
    }
}
