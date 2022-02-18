package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction as DcDirection
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior as ZPB
import com.qualcomm.robotcore.hardware.DcMotor.RunMode as DcRunMode
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot
import org.firstinspires.ftc.teamcode.common.Arm
import org.firstinspires.ftc.teamcode.common.ExactArm
import org.firstinspires.ftc.teamcode.common.getDcMotor

class GadgetsRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {
    override val leftFront = getWheel("leftFront", DcDirection.REVERSE)
    override val rightFront = getWheel("rightFront", DcDirection.FORWARD)
    override val leftRear = getWheel("leftRear", DcDirection.REVERSE)
    override val rightRear = getWheel("rightRear", DcDirection.FORWARD)

    val armLeftFront = getGenericMotor("armLeftFront", DcDirection.FORWARD)
    val armRightFront = getGenericMotor("armRightFront", DcDirection.FORWARD)
    val armLeftRear = getGenericMotor("armLeftRear", DcDirection.FORWARD)
    val armRightRear = getGenericMotor("armRightRear", DcDirection.FORWARD)

    val mainArm = ExactArm(
        power = 0.2,
        initialPosition = 0.0,
        exactArmMotors = listOf(
            ExactArm.MotorDescriptor(armLeftFront, 500 to 500),
            ExactArm.MotorDescriptor(armRightFront, 500 to 500),
        ),
    )
    val auxiliaryArm = ExactArm(
        power = 0.2,
        initialPosition = 0.0,
        exactArmMotors = listOf(
            ExactArm.MotorDescriptor(armLeftRear, 500 to 500),
            ExactArm.MotorDescriptor(armRightRear, 500 to 500),
        ),
    )

    val arms = listOf(mainArm, auxiliaryArm)

    private fun getGenericMotor(
        name: String,
        direction: DcDirection,
    ) = hardwareMap.getDcMotor(
        name, direction,
        ZPB.BRAKE, DcRunMode.RUN_USING_ENCODER,
    )

    override fun translate(px: Double, py: Double): FourWheelRobot {
        // Check for NaN
        if (px.isNaN() || py.isNaN())
            throw IllegalArgumentException("You cannot supply NaN into the translate function.")

        // Set motor powers
        wheels.forEach {
            it.power = py
        }
        return this
    }

}
