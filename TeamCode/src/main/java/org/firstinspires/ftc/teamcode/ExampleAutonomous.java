package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Example Autonomous", group="Example Group")
public class ExampleAutonomous extends LinearOpMode {

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

        robot.translate(0, 0.5);
        sleep(1500);
        robot.translate(0, -0.5);
        sleep(1500);
        robot.translate(0, 0);
    }
}
