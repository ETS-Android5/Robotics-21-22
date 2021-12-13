package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.FourWheelRobot
import org.firstinspires.ftc.teamcode.GamepadUtil

// This class contains the code for the main competition TeleOp.
@TeleOp(name = "Main TeleOp", group = "Main")
class CompetitionTeleOp : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot? = null
    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot!!.reset()
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        while (opModeIsActive()) {
            // Controller loop
            if (GamepadUtil.leftTriggerPressed(gamepad1)) {
                robot!!.rotate(-0.5)
            } else if (GamepadUtil.rightTriggerPressed(gamepad1)) {
                robot!!.rotate(0.5)
            } else {
                robot!!.translate(gamepad1.right_stick_x.toDouble(), (-1 * gamepad1.left_stick_y).toDouble())
            }
        }
    }
}