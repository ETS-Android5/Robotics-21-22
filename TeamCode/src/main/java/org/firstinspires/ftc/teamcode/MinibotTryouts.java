package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Minibot tryout code", group="tryouts")
public class MinibotTryouts extends LinearOpMode {

    private static class MotorInfo {
        String name;
        DcMotor.Direction direction;

        MotorInfo(String name, DcMotor.Direction direction) {
            this.name = name;
            this.direction = direction;
        }
    }

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();

    private final MotorInfo[] wheelInfo = {
        new MotorInfo("leftFront", DcMotor.Direction.FORWARD),
        new MotorInfo("rightFront", DcMotor.Direction.FORWARD),
        new MotorInfo("leftBack", DcMotor.Direction.FORWARD),
        new MotorInfo("rightBack", DcMotor.Direction.FORWARD),
    };
    private final DcMotor[] wheels = new DcMotor[wheelInfo.length];

    private void setWheelPower(double ...powers) {
        for (int i = 0; i < wheels.length; ++i) {
            wheels[i].setPower(powers[i]);
        }
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        for (int i = 0; i < wheels.length; ++i) {
            wheels[i] = hardwareMap.get(DcMotor.class, wheelInfo[i].name);
            wheels[i].setDirection(wheelInfo[i].direction);
        }

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        setWheelPower(0.5, 0.5, 0.5, 0.5);

        double start = runtime.time();
        while (runtime.time() - start < 1.5);

        setWheelPower(-0.5, -0.5, -0.5, -0.5);

        start = runtime.time();
        while (runtime.time() - start < 1.5);

        setWheelPower(0, 0, 0, 0);
    }
}
