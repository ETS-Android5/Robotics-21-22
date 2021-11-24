package org.firstinspires.ftc.teamcode;

public class FourWheelRobot {
    // Declare members
    public final HwMap hardwareMap;

    // Declare motors
    public final DcMotor leftFront;
    public final DcMotor leftRear;
    public final DcMotor rightFront;
    public final DcMotor rightRear;

    private DcMotor getWheel(
        String motorName,
        DcMotorSimple.Direction direction,
    ) {
        return RobotUtil.getDcMotor(
            hardwareMap,
            motorName,
            direction,
            DcMotor.ZeroPowerBehavior.FLOAT,
            DcMotor.RunMode.WITH_ENCODER,
        );
    }

    public FourWheelRobot(HwMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        // Initialize wheels
        leftFront = getWheel("leftFront", DcMotorSimple.Direction.FORWARD);
        leftRear = getWheel("leftRear", DcMotorSimple.Direction.FORWARD);
        rightFront = getWheel("rightFront", DcMotorSimple.Direction.FORWARD);
        rightRear = getWheel("rightRear", DcMotorSimple.Direction.FORWARD);
    }
}
