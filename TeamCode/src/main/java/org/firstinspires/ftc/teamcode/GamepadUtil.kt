package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.Gamepad

fun Gamepad.leftTriggerPressed() = gamepad.left_trigger > 0.85

fun Gamepad.rightTriggerPressed() = gamepad.right_trigger > 0.85
