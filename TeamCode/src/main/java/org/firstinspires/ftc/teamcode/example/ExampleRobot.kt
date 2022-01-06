package org.firstinspires.ftc.teamcode.example

import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot

class ExampleRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {
    override val leftFront = getWheel("leftFront", Direction.FORWARD)
    override val rightFront = getWheel("rightFront", Direction.FORWARD)
    override val leftRear = getWheel("leftRear", Direction.FORWARD)
    override val rightRear = getWheel("rightRear", Direction.FORWARD)
}
