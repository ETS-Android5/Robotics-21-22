package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Example TeleOp", group="Example Group")
public class ExampleTeleOp extends LinearOpMode {

    // Declare members
    private FourWheelRobot robot;

    @Override
    public void runOpMode() {
        robot = new FourWheelRobot(hardwareMap);
        robot.reset();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            // Controller loop
            if (GamepadUtil.leftTriggerPressed(gamepad1)) {
                robot.rotate(-0.5);
            }
            else if (GamepadUtil.rightTriggerPressed(gamepad1)) {
                robot.rotate(0.5);
            }
            else {
                robot.rotate(0);
            }
        }
    }
}
