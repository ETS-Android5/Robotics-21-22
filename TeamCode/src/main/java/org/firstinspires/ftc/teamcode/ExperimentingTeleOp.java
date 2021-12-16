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
        clawLeft.setPosition(0.5);
        clawRight.setPosition(0.5);
    }
    private void closeClaws() {
        clawLeft.setPosition(1.0);
        clawRight.setPosition(1.0);
    }

    private double armPosition;
    private final double maxArmPos = 100.0;
    private final double minArmPos = 0.0;

    private void initArm() {
        setArmPosition(minArmPos);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }
    private void setArmPosition(double ticks) {
        // Clamp arm position between min and max.
        ticks = Math.max(minArmPos, Math.min(ticks, maxArmPos));

        int output = (int) Math.round(ticks);
        armLeft.setTargetPosition(output);
        armRight.setTargetPosition(output);
        armPosition = ticks;
    }
    private void shiftArmPosition(double dTicks) {
        setArmPosition(armPosition + dTicks);
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

        if (GamepadUtil.leftTriggerPressed(gamepad1)) openClaws();
        else if (GamepadUtil.rightTriggerPressed(gamepad1)) closeClaws();

        shiftArmPosition(1);
    }

    private void configX() {
    }

    private void configY() {
    }
}
