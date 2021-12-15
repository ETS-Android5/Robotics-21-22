package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.Gamepad

fun Gamepad.leftTriggerPressed() = left_trigger > 0.85

fun Gamepad.rightTriggerPressed() = right_trigger > 0.85
