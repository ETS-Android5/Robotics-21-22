package org.firstinspires.ftc.teamcode.gadgets

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction as DcDirection
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior as ZPB
import com.qualcomm.robotcore.hardware.DcMotor.RunMode as DcRunMode
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot
import org.firstinspires.ftc.teamcode.common.getServo
import org.firstinspires.ftc.teamcode.common.Arm
import org.firstinspires.ftc.teamcode.common.getDcMotor

class GadgetsRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {
    override val leftFront = getWheel("leftFront", DcDirection.REVERSE)
    override val rightFront = getWheel("rightFront", DcDirection.FORWARD)
    override val leftRear = getWheel("leftRear", DcDirection.REVERSE)
    override val rightRear = getWheel("rightRear", DcDirection.FORWARD)

    data class ClawDescriptor(val motor: DcMotor, val open: Int, val close: Int)

    val claws = listOf(
        ClawDescriptor(
            getClawMotor("clawLeft", DcDirection.FORWARD),
            0, 1,
        ),
        ClawDescriptor(
            getClawMotor("clawRight", DcDirection.FORWARD),
            0, 1,
        ),
    )

    val arm = Arm(
        power = 0.2,
        armMotors =
            arrayOf(
                Triple("armLeft", DcDirection.FORWARD, 500),
                Triple("armRight", DcDirection.FORWARD, 500),
            ).map { (name, direction, range) ->
                hardwareMap.getDcMotor(
                    name, direction,
                    ZPB.BRAKE, DcRunMode.RUN_USING_ENCODER,
                ).let {
                    Arm.MotorDescriptor(it, range)
                }
            },
    )

    private fun getClawMotor(
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

    fun resetClaws() {
        claws.forEach {
            it.motor.targetPosition = it.open
            it.motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            it.motor.power = 0.2
        }
    }

    fun openClaws() = claws.forEach { it.motor.targetPosition = it.open }
    fun closeClaws() = claws.forEach { it.motor.targetPosition = it.close }
}
