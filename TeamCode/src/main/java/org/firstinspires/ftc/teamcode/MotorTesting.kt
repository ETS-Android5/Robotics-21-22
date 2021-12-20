package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.DcMotorSimple

// Motor and arm tester
@TeleOp(name = "Motor Tester", group = "Dev")
class MotorTesting : LinearOpMode() {
    // Declare members
    private var robot: FourWheelRobot? = null
    private var armLeft: DcMotor? = null
    private var armRight: DcMotor? = null
    private var clawLeft: Servo? = null
    private var clawRight: Servo? = null
    override fun runOpMode() {
        robot = FourWheelRobot(hardwareMap)
        robot!!.reset()
        armLeft = hardwareMap.getDcMotor(
                "armLeft",
                DcMotorSimple.Direction.REVERSE,
                DcMotor.ZeroPowerBehavior.FLOAT,
                DcMotor.RunMode.RUN_USING_ENCODER
        )
        armRight = hardwareMap.getDcMotor(
                "armRight",
                DcMotorSimple.Direction.FORWARD,
                DcMotor.ZeroPowerBehavior.FLOAT,
                DcMotor.RunMode.RUN_USING_ENCODER
        )
        clawLeft = hardwareMap.getServo(
                "clawLeft",
                Servo.Direction.REVERSE
        )
        clawRight = hardwareMap.getServo(
                "clawRight",
                Servo.Direction.FORWARD
        )
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        // Wait for the game to start (driver presses PLAY)
        waitForStart()
        while (opModeIsActive()) {
            if (gamepad1.a) {
                clawLeft!!.position = 0.0
                clawRight!!.position = 0.0
            } else if (gamepad1.b) {
                clawLeft!!.position = 1.0
                clawRight!!.position = 1.0
            }
            telemetry.addData("arm left", armLeft!!.currentPosition)
            telemetry.addData("arm right", armRight!!.currentPosition)
            telemetry.addData("left front wheel", robot!!.leftFront.currentPosition)
            telemetry.addData("left trigger", gamepad1.left_trigger)
            telemetry.addData("left claw", clawLeft!!.position)
            telemetry.addData("right claw", clawRight!!.position)
            telemetry.update()
        }
    }

    private var armPosition = 0.0
    private val maxArmPos = 100.0
    private val minArmPos = 0.0
    private fun initArm() {
        setArmPosition(minArmPos)
        armLeft!!.mode = DcMotor.RunMode.RUN_TO_POSITION
        armRight!!.mode = DcMotor.RunMode.RUN_TO_POSITION
        armLeft!!.power = 0.5
        armRight!!.power = 0.5
    }

    private fun setArmPosition(ticks: Double) {
        // Clamp arm position between min and max.
        // ticks = Math.max(minArmPos, Math.min(ticks, maxArmPos));
        val output = Math.round(ticks).toInt()
        armLeft!!.targetPosition = output
        armRight!!.targetPosition = output
        armPosition = ticks
    }

    private fun shiftArmPosition(dTicks: Double) {
        setArmPosition(armPosition + dTicks)
    }
}