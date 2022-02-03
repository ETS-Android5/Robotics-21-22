package org.firstinspires.ftc.teamcode.machines

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

import org.firstinspires.ftc.teamcode.common.LateInitConstProperty
import org.firstinspires.ftc.teamcode.common.leftTriggerPressed
import org.firstinspires.ftc.teamcode.common.rightTriggerPressed

// This class contains the code for the main competition TeleOp.
@TeleOp(name = "Main TeleOp", group = "Main")
class CompetitionTeleOp : LinearOpMode() {
    // Declare members
    private var robot: MadMachinesRobot by LateInitConstProperty()

    override fun runOpMode() {
        robot = MadMachinesRobot(hardwareMap)

        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()

        while (opModeIsActive()) {
            // Controller loop
            val buffer = when {
                gamepad1.leftTriggerPressed -> robot.rotate(-0.5)
                gamepad1.rightTriggerPressed -> robot.rotate(0.5)
                else -> robot.translate(
                    px = gamepad1.right_stick_x.toDouble(),
                    py = (-1 * gamepad1.left_stick_y).toDouble(),
                )
            }
	    robot.move(buffer)
        }
    }
}
