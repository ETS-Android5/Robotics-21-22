package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.Gamepad

TODO("TEST")

val Gamepad.leftTriggerPressed get() = left_trigger > 0.85

val Gamepad.rightTriggerPressed get() = right_trigger > 0.85
