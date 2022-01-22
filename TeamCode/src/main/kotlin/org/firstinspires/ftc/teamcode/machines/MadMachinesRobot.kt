package org.firstinspires.ftc.teamcode.machines

import kotlin.math.roundToInt

import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.common.FourWheelRobot
import org.firstinspires.ftc.teamcode.common.LateInitConstProperty
import org.firstinspires.ftc.teamcode.common.getDcMotor
import org.firstinspires.ftc.teamcode.common.getServo
import org.firstinspires.ftc.teamcode.common.Arm

class MadMachinesRobot(hardwareMap: HardwareMap) : FourWheelRobot(hardwareMap) {

    override val leftFront = getWheel("leftFront", DcMotorSimple.Direction.FORWARD)
    override val rightFront = getWheel("rightFront", DcMotorSimple.Direction.REVERSE)
    override val leftRear = getWheel("leftRear", DcMotorSimple.Direction.REVERSE)
    override val rightRear = getWheel("rightRear", DcMotorSimple.Direction.REVERSE)

    val clawLeft = hardwareMap.getServo(
        "clawLeft",
        Servo.Direction.REVERSE,
    )
    val clawRight = hardwareMap.getServo(
        "clawRight",
        Servo.Direction.REVERSE,
    )

    val armLeft = hardwareMap.getDcMotor(
        "armLeft",
        DcMotorSimple.Direction.FORWARD,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )
    val armRight = hardwareMap.getDcMotor(
        "armRight",
        DcMotorSimple.Direction.REVERSE,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

    val carousel = hardwareMap.getDcMotor(
            "carousel",
            DcMotorSimple.Direction.REVERSE,
            DcMotor.ZeroPowerBehavior.FLOAT,
            DcMotor.RunMode.RUN_USING_ENCODER,
    )

    private fun getArm(
        name: String,
        direction: DcMotorSimple.Direction,
    ) = hardwareMap.getDcMotor(
        name,
        direction,
        DcMotor.ZeroPowerBehavior.BRAKE,
        DcMotor.RunMode.RUN_USING_ENCODER,
    )

    var spinCarousel: Boolean = false
        set(value) {
            carousel.power = if (value) 0.75 else 0.0
            field = value
        }

    //SP stands for ServoPositions
    private data class SP(val open: Double, val close: Double)
    private data class ClawDescriptor(val servo: Servo, val positions: SP)

    private val claws = listOf(
        ClawDescriptor(
            clawLeft,
            SP(open=0.68, close=0.81),
        ),
        ClawDescriptor(
            clawRight,
            SP(open=0.64, close=0.50),
        ),
    )

    fun openClaws() {
        for ((servo, positions) in claws)
            servo.position = positions.open
    }
    fun closeClaws() {
        for ((servo, positions) in claws)
            servo.position = positions.close
    }

    // Arm
    val arm = Arm(
        power = 0.1,
        Arm.MotorDescriptor(armLeft, 500),
        Arm.MotorDescriptor(armRight, 500),
    )
}
