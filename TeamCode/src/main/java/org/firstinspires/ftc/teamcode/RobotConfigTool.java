package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/* This class is a tool that can be used to
 * figure out the settings that should be used
 * with the particular robot.
 */

@TeleOp(name="Robot Configuration Tool")
public class RobotConfigTool extends LinearOpMode {

    // Declare members
    private FourWheelRobot robot;

    @Override
    public void runOpMode() {
        robot = new FourWheelRobot(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            for (combo : new Object
                [gamepad1.dpad_up, robot.leftFront],
            ]) {
                if (combo[0]) combo[1].setPower(0.3);
            }
        }
    }
}
