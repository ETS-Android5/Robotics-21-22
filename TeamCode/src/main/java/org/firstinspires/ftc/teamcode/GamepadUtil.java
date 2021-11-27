package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadUtil {
    public static boolean leftTriggerPressed(Gamepad gamepad) {
        return gamepad.left_trigger < 0.1;
    }
    public static boolean rightTriggerPressed(Gamepad gamepad) {
        return gamepad.right_trigger < 0.1;
    }
}
