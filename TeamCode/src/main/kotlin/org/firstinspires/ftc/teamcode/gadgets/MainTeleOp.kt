package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty
import org.firstinspires.ftc.teamcode.common.leftTriggerPressed
import org.firstinspires.ftc.teamcode.common.rightTriggerPressed
import kotlin.math.abs

@TeleOp(name = "Main TeleOp", group = "Main")
class MainTeleOp : LinearOpMode() {
    private var robot: GadgetsRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = GadgetsRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        waitForStart()

        robot.arm.reset()

        val defaultScale = 0.7
        var scale = defaultScale
        while (opModeIsActive()) {
            // Set scale
            scale = when {
                gamepad1.a -> defaultScale
                gamepad1.b -> 0.1
                else -> scale
            }
            // Arm control
            robot.arm.position = (robot.arm.position + when {
                gamepad1.dpad_left -> -0.5
                gamepad1.dpad_right -> +0.5

                gamepad1.dpad_up -> +0.002
                gamepad1.dpad_down -> -0.002

                else -> 0.0
            }).coerceIn(0.0, 1.0)
            // Claws
            when {
                gamepad1.leftTriggerPressed -> robot.openClaws()
                gamepad1.rightTriggerPressed -> robot.closeClaws()
            }
            // Movement
            if (abs(gamepad1.right_stick_x) > 0.2)
                robot.rotate(gamepad1.right_stick_x * scale)
            else {
                robot.translate(
                    px = gamepad1.left_stick_x * scale,
                    py = (-1 * gamepad1.left_stick_y) * scale,
                )
            }
        }
    }
}
