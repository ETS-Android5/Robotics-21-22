package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Example TeleOp", group="Example Group")
public class ExampleTeleOp extends LinearOpMode {

    // Declare members
    private final FourWheelRobot robot;

    public ExampleTeleOp() {
        super();
        robot = new FourWheelRobot(hardwareMap);
    }

    @Override
    public void runOpMode() {
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
