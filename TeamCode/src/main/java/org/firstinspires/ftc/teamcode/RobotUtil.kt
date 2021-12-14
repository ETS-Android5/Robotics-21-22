package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotor.RunMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.CRServo

object RobotUtil {
    // This function gets, resets, and initializes a motor.
    // It takes a hardware map instance,
    // some motor parameters, and returns a DcMotor.
    fun getDcMotor(
        hardwareMap: HardwareMap,
        motorName: String,
        direction: DcMotorSimple.Direction,
        ZPB: ZeroPowerBehavior,
        runMode: RunMode,
    ): DcMotor {
        val motor = hardwareMap.get(DcMotor::class.java, motorName)
        motor.resetDeviceConfigurationForOpMode()
        motor.direction = direction
        motor.zeroPowerBehavior = ZPB
        motor.mode = runMode
        return motor
    }

    // This function gets, resets, and initializes a servo.
    fun getServo(
        hardwareMap: HardwareMap,
        servoName: String,
        direction: Servo.Direction,
    ): Servo {
        val servo = hardwareMap.get(Servo::class.java, servoName)
        servo.resetDeviceConfigurationForOpMode()
        servo.direction = direction
        return servo
    }

    // This function gets, resets, and initializes a CR Servo.
    fun getCRServo(
        hardwareMap: HardwareMap,
        CRServoName: String,
        direction: DcMotorSimple.Direction
    ): CRServo {
        val servo = hardwareMap.get(CRServo::class.java, CRServoName)
        servo.resetDeviceConfigurationForOpMode()
        servo.direction = direction
        return servo
    }
}
