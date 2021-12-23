package org.firstinspires.ftc.teamcode.common

import com.qualcomm.robotcore.hardware.Gamepad

val Gamepad.leftTriggerPressed get() = left_trigger > 0.85
val Gamepad.rightTriggerPressed get() = right_trigger > 0.85
