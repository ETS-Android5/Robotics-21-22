package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction as DcDirection
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot

class GadgetsRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {
    override val leftFront = getWheel("leftFront", DcDirection.FORWARD)
    override val rightFront = getWheel("rightFront", DcDirection.FORWARD)
    override val leftRear = getWheel("leftRear", DcDireckjktion.FORWARD)
    override val rightRear = getWheel("rightRear", DcDirection.FORWARD)

    override fun translate(px: Double, py: Double): FourWheelRobot {
        return this
    }
}
