package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.provider.CalendarContract;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nishka on 27/01/17.
 */
@TeleOp(name = "beacon tester", group = "agroup")
public class BeaconTest extends LinearOpMode
{
    Servo BeaconServo;
    ColorSensor colorSensor;

    //Drive Motors
    private static DcMotor motorFrontLeft;
    private static DcMotor motorBackLeft;
    private static DcMotor motorFrontRight;
    private static DcMotor motorBackRight;

    @Override
    public void runOpMode() throws InterruptedException
    {
        BeaconServo = hardwareMap.servo.get("beacon");
        BeaconServo.setPosition(1);

        colorSensor = hardwareMap.colorSensor.get("colorSenso");
        colorSensor.enableLed(false);
        Color.rgb(0, 0, 0);

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

        colorSensor.enableLed(false);

        while(opModeIsActive()){


            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Red", colorSensor.red());

            if(gamepad1.a)
            {

                if(colorSensor.blue()>colorSensor.red())
                {
                    BeaconServo.setPosition(0.5);


                }
                else
                {
                    DRIVEFORWARD(700);
                    BeaconServo.setPosition(0.5);
                }

            }

            if(gamepad1.b)
            {
                BeaconServo.setPosition(1);
            }

            telemetry.update();
        }

    }

    public static void DRIVEFORWARD(int degreeval) {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(1);
        motorBackLeft.setPower(1);
        motorFrontRight.setPower(1);
        motorBackRight.setPower(1);

        motorBackLeft.setTargetPosition(degreeval);
        motorFrontRight.setTargetPosition(degreeval);
        motorBackRight.setTargetPosition(degreeval);
        motorFrontLeft.setTargetPosition(degreeval);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy()) {
            //wait till motors done doing its thing
        }
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);


    }


}
