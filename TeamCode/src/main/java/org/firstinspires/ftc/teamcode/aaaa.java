package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOP_Algorithm", group = "agroup")

public class aaaa extends LinearOpMode
{
    //Drive Motors
    private static DcMotor motorFrontLeft;
    private static DcMotor motorBackLeft;
    private static DcMotor motorFrontRight;
    private static DcMotor motorBackRight;
    //private static DcMotor motorShoot;

    private double deadzoneval;
    private double negdeadzoneval;
    private double avgmotorpwr;

    @Override
    public void runOpMode() throws InterruptedException
    {
        double deadzoneval = 0.2;
        double negdeadzoneval = -0.2;

        //Drive Motors
        motorFrontLeft = hardwareMap.dcMotor.get("MC1M1");
        motorBackLeft = hardwareMap.dcMotor.get("MC1M2");

        motorFrontRight = hardwareMap.dcMotor.get("MC2M1");
        motorBackRight = hardwareMap.dcMotor.get("MC2M2");

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while(opModeIsActive())
        {
            /**GAMEPAD 1*/
            //v1
            motorFrontLeft.setPower((((gamepad1.right_trigger)*Math.sin((((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ))));
            //v3
            motorBackLeft.setPower((((gamepad1.right_trigger)*Math.cos((((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ))));
            //v2
            motorFrontRight.setPower((((gamepad1.right_trigger)*Math.cos((((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ))));
            //v4
            motorBackRight.setPower((((gamepad1.right_trigger)*Math.sin((((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ))));
        }

        idle();
    }
}

