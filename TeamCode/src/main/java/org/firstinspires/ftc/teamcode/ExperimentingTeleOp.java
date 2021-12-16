package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

// This class is a TeleOp for experimenting different things.

@TeleOp(name="Experimenting TeleOp", group="Dev")
public class ExperimentingTeleOp extends LinearOpMode {

    // Declare members
    private FourWheelRobot robot;

    private Servo clawLeft;
    private Servo clawRight;

    private DcMotor armLeft;
    private DcMotor armRight;

    @Override
    public void runOpMode() {
        robot = new FourWheelRobot(hardwareMap);
        robot.reset();

        clawLeft = RobotUtil.getServo(
            hardwareMap,
            "clawLeft",
            Servo.Direction.REVERSE
        );
        clawRight = RobotUtil.getServo(
            hardwareMap,
            "clawRight",
            Servo.Direction.FORWARD
        );

        armLeft = RobotUtil.getDcMotor(
            hardwareMap,
            "armLeft",
            DcMotorSimple.Direction.REVERSE,
            DcMotor.ZeroPowerBehavior.BRAKE,
            DcMotor.RunMode.RUN_USING_ENCODER
        );
        armRight = RobotUtil.getDcMotor(
            hardwareMap,
            "armRight",
            DcMotorSimple.Direction.FORWARD,
            DcMotor.ZeroPowerBehavior.BRAKE,
            DcMotor.RunMode.RUN_USING_ENCODER
        );

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Default is config a
        char controllerConfig = 'a';

        initArm();

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

    private void openClaws() {
        clawLeft.setPosition(0.7);
        clawRight.setPosition(0.85);
    }
    private void closeClaws() {
        clawLeft.setPosition(0.85);
        clawRight.setPosition(1.5);
    }

    // Tunable parameters
    private final int[] armLeftBounds = {783, 284}; // {lower, upper}
    private final int[] armRightBounds = {506, -2};
    private final double armInitialPosition = 0.0;

    private double armPosition;

    private void initArm() {
        setArmPosition(armInitialPosition);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }
    private void setArmPosition(double position) {
        // Clamp arm position between 0.0 and 1.0.
        position = Math.max(0.0, Math.min(1.0, position));

        // Compute outputs based on supplied position and arm bounds.
        int[] outputs = {0, 0}; // {left_output, right_output}
        int[][] armBounds = {armLeftBounds, armRightBounds};
        for (int i = 0; i < armBounds.length; ++i) {
            outputs[i] = (int) Math.round(
                armBounds[i][0] + position*(armBounds[i][1] - armBounds[i][0])
            );
        }

        armLeft.setTargetPosition(outputs[0]);
        armRight.setTargetPosition(outputs[1]);

        armPosition = position;
    }
    private void shiftArmPosition(double dPosition) {
        setArmPosition(armPosition + dPosition);
    }

    private void configA() {
        double scale = 0.5;
        if (GamepadUtil.leftTriggerPressed(gamepad1)) {
            robot.rotate(-0.5 * scale);
        }
        else if (GamepadUtil.rightTriggerPressed(gamepad1)) {
            robot.rotate(0.5 * scale);
        }
        else {
            robot.translate(gamepad1.right_stick_x * scale, -1*gamepad1.left_stick_y * scale);
        }
    }

    private void configB() {
        if (Math.abs(gamepad1.right_stick_x) > 0.2) {
            robot.rotate(gamepad1.right_stick_x);
        }
        else {
            robot.translate(gamepad1.left_stick_x, -1*gamepad1.left_stick_y);
        }

        if (GamepadUtil.leftTriggerPressed(gamepad1)) openClaws();
        else if (GamepadUtil.rightTriggerPressed(gamepad1)) closeClaws();

        shiftArmPosition(0.002);
    }

    private void configX() {
    }

    private void configY() {
    }
}
