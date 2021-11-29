package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotUtil {

    // This function takes a hardware map instance,
    // some motor parameters, and returns a DcMotor.
    public static DcMotor getDcMotor(
        HardwareMap hardwareMap,
        String motorName,
        DcMotorSimple.Direction direction,
        DcMotor.ZeroPowerBehavior ZPB,
        DcMotor.RunMode runMode
    ) {
        DcMotor motor = hardwareMap.get(DcMotor.class, motorName);
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(ZPB);
        motor.setMode(runMode);
        return motor;
    }

}
