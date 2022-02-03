package org.firstinspires.ftc.teamcode.common

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.CRServo

// This function gets, resets, and initializes a motor.
// It takes a hardware map instance,
// some motor parameters, and returns a DcMotor.
fun HardwareMap.getDcMotor(
    motorName: String,
    direction: DcMotorSimple.Direction,
    ZPB: DcMotor.ZeroPowerBehavior,
    runMode: DcMotor.RunMode,
): DcMotor {
    val motor = this.get(DcMotor::class.java, motorName)
    motor.resetDeviceConfigurationForOpMode()
    motor.direction = direction
    motor.zeroPowerBehavior = ZPB
    motor.mode = runMode
    return motor
}

// This function gets, resets, and initializes a servo.
fun HardwareMap.getServo(
    servoName: String,
    direction: Servo.Direction,
): Servo {
    val servo = this.get(Servo::class.java, servoName)
    servo.resetDeviceConfigurationForOpMode()
    servo.direction = direction
    return servo
}

// This function gets, resets, and initializes a CR Servo.
fun HardwareMap.getCRServo(
    CRServoName: String,
    direction: DcMotorSimple.Direction,
): CRServo {
    val servo = this.get(CRServo::class.java, CRServoName)
    servo.resetDeviceConfigurationForOpMode()
    servo.direction = direction
    return servo
}