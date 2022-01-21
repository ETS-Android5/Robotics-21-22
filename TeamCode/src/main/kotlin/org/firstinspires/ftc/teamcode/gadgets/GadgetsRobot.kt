package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction as DcDirection
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior as ZPB
import com.qualcomm.robotcore.hardware.DcMotor.RunMode as DcRunMode
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot
import org.firstinspires.ftc.teamcode.common.Arm

class GadgetsRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {
    override val leftFront = getWheel("leftFront", DcDirection.FORWARD)
    override val rightFront = getWheel("rightFront", DcDirection.FORWARD)
    override val leftRear = getWheel("leftRear", DcDirection.FORWARD)
    override val rightRear = getWheel("rightRear", DcDirection.FORWARD)

    val arm = Arm(
        power = 0.2,
        armMotors =
            arrayOf(
                Pair(),
                Pair(),
            ).map { (name, direction) ->
                hardwareMap.getDcMotor(
                    name, direction,
                    ZPB.BRAKE, DcRunMode.RUN_USING_ENCODER,
                )
            },
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
