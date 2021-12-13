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

object GamepadUtil {
    fun leftTriggerPressed(gamepad: Gamepad): Boolean {
        return gamepad.left_trigger > 0.85
    }

    fun rightTriggerPressed(gamepad: Gamepad): Boolean {
        return gamepad.right_trigger > 0.85
    }
}