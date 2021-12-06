package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// This class is a TeleOp for experimenting different things.

@TeleOp(name="Experimenting TeleOp", group="Dev")
public class ExperimentingTeleOp extends LinearOpMode {

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

        // Default is config a
        char controllerConfig = 'a';

        while (opModeIsActive()) {
            // Controller loop

            // If any of the buttons on gamepad1 are pressed,
            // change the controller configuration to the one
            // assigned to that button.
            if (gamepad1.a) controllerConfig = 'a';
            else if (gamepad1.b) controllerConfig = 'b';
            else if (gamepad1.x) controllerConfig = 'x';
            else if (gamepad1.y) controllerConfig = 'y';

            switch (controllerConfig) {
                case 'a':
                    configA();
                    break;
                case 'b':
                    configB();
                    break;
                case 'x':
                    configX();
                    break;
                case 'y':
                    configY();
                    break;
            }
        }
    }

    private void configA() {
        if (GamepadUtil.leftTriggerPressed(gamepad1)) {
            robot.rotate(-0.5);
        }
        else if (GamepadUtil.rightTriggerPressed(gamepad1)) {
            robot.rotate(0.5);
        }
        else {
            robot.translate(gamepad1.right_stick_x, -1*gamepad1.left_stick_y);
        }
    }

    private void configB() {
        if (Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot.rotate(gamepad1.right_stick_x);
        }
        else {
            robot.translate(gamepad1.left_stick_x, -1*gamepad1.left_stick_y);
        }
    }

    private void configX() {
    }

    private void configY() {
    }
}
