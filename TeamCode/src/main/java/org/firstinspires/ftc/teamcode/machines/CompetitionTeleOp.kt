package org.firstinspires.ftc.teamcode.machines

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

// This class contains the code for the main competition TeleOp.
@TeleOp(name = "Main TeleOp", group = "Main")
class CompetitionTeleOp : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot.reset()

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        while (opModeIsActive()) {
            // Controller loop
            when {
                gamepad1.leftTriggerPressed -> robot.rotate(-0.5)
                gamepad1.rightTriggerPressed -> robot.rotate(0.5)
                else -> robot.translate(
                    px = gamepad1.right_stick_x.toDouble(),
                    py = (-1 * gamepad1.left_stick_y).toDouble(),
                )
            }
        }
    }
}
