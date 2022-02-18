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

        robot.arms.forEach { it.reset() }

        val defaultScale = 0.7
        var scale = defaultScale
        var rearArmButtonPressed = false
        while (opModeIsActive()) {
            // Set scale
            scale = when {
                gamepad1.a -> defaultScale
                gamepad1.b -> 0.1
                else -> scale
            }
            // Arm control
            robot.mainArm.position = when {
                gamepad2.dpad_left -> 0.0
                gamepad2.dpad_right -> 1.0

                gamepad2.dpad_up -> robot.mainArm.position + 0.002
                gamepad2.dpad_down -> robot.mainArm.position - 0.002

                else -> robot.mainArm.position
            }
            // left_bumper is a toggle.
            if (gamepad2.left_bumper) {
                if (!rearArmButtonPressed)
                    robot.auxiliaryArm.position =
                        if (robot.auxiliaryArm.position == 0.0) 1.0 else 0.0
                rearArmButtonPressed = true
            }
            else {
                rearArmButtonPressed = false
            }
            // Carousel
            robot.carousel.power = if (gamepad2.right_bumper) 1.0 else 0.0
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
