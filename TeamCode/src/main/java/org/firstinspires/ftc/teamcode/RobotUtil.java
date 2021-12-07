package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotUtil {

    // This function gets, resets, and initializes a motor.
    // It takes a hardware map instance,
    // some motor parameters, and returns a DcMotor.
    public static DcMotor getDcMotor(
        HardwareMap hardwareMap,
        String motorName,
        DcMotorSimple.Direction direction,
        DcMotor.ZeroPowerBehavior ZPB,
        DcMotor.RunMode runMode
    ) {
        DcMotor motor = hardwareMap.get(DcMotor.class, motorName);
        motor.resetDeviceConfigurationForOpMode();
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(ZPB);
        motor.setMode(runMode);
        return motor;
    }

    // This function gets, resets, and initializes a servo.
    public static Servo getServo(
        HardwareMap hardwareMap,
        String servoName,
        Servo.Direction direction
    ) {
        Servo servo = hardwareMap.get(Servo.class, servoName);
        servo.resetDeviceConfigurationForOpMode();
        servo.setDirection(direction);
        return servo;
    }

}
