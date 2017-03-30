package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Nishka on 03/01/17.
 */

@Autonomous(name = "Left Cap Park", group = "agroup")

public class LeftCapPark extends LinearOpMode {
    //constants
    //static final int thresholdValueToStopFollowing = 12;
    static final int TIME_TO_SHOOT_PER_BALL = 1000;
    static final double TURNING_SPEED = 1.0;
    static final int DEGREES_90 = 2500;
    static final int DEGREES_180 = 5000;

    //beacon Motors
    private static Servo BeaconServo;


    //Drive Motors
    private static DcMotor motorFrontLeft;
    private static DcMotor motorBackLeft;
    private static DcMotor motorFrontRight;
    private static DcMotor motorBackRight;

    //Shooting Motors
    private static DcMotor motorShootLeft;
    private static DcMotor motorShootRight;

    //Collection motor && Lifting motor
    private static DcMotor motorCollect;
    private static Servo servoElevate;
    ColorSensor colorSensor;
    ColorSensor baseColor;

    Servo servoArmRaise1;
    Servo servoArmRaise2;


    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(false);
        Color.rgb(0, 0, 0);

        baseColor = hardwareMap.colorSensor.get("baseColor");
        colorSensor.enableLed(true);
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

        //Shooting Motors
        motorShootLeft = hardwareMap.dcMotor.get("MC3ShootLeft");
        motorShootRight = hardwareMap.dcMotor.get("MC3ShootRight");

        motorShootLeft.setDirection(DcMotor.Direction.REVERSE);

        //Collection Motor && Lifting Motor
        motorCollect = hardwareMap.dcMotor.get("collectorMotor");
        servoElevate = hardwareMap.servo.get("elevatorMotor");

        BeaconServo = hardwareMap.servo.get("beacon");

        servoArmRaise1 = hardwareMap.servo.get("servoArmRaise1");
        servoArmRaise2 = hardwareMap.servo.get("servoArmRaise2");

        servoArmRaise1.setPosition(0.6666);
        servoArmRaise2.setPosition(0.3);

        servoElevate.setPosition(0.5);

        BeaconServo.setPosition(0.74);

        waitForStart();

        motorShootLeft.setPower(1);
        motorShootRight.setPower(1);

        SwayLeft(2500);

        SHOOT(8);

        DRIVEFORWARD(5000);


    }

    public static void SwayLeft(int degreeval)
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(-1);
        motorBackLeft.setPower(1);
        motorFrontRight.setPower(1);
        motorBackRight.setPower(-1);

        motorBackLeft.setTargetPosition(degreeval);
        motorFrontRight.setTargetPosition(degreeval);
        motorBackRight.setTargetPosition(-degreeval);
        motorFrontLeft.setTargetPosition(-degreeval);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorBackLeft.isBusy()&& motorFrontRight.isBusy())
        {
            //wait till motors done doing its thing
        }
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

    }

    public static void SwayRight(int degreeval)
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(1);
        motorBackLeft.setPower(-1);
        motorFrontRight.setPower(-1);
        motorBackRight.setPower(1);

        motorBackLeft.setTargetPosition(-degreeval);
        motorFrontRight.setTargetPosition(-degreeval);
        motorBackRight.setTargetPosition(degreeval);
        motorFrontLeft.setTargetPosition(degreeval);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorBackLeft.isBusy() && motorFrontRight.isBusy())
        {
            //wait till motors done doing its thing
        }
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

    }

    public static void DiagonalRightForward(int degreeval)
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(1);
        //motorBackLeft.setPower(0);
        //motorFrontRight.setPower(0);
        motorBackRight.setPower(1);

        //motorBackLeft.setTargetPosition(0);
        //motorFrontRight.setTargetPosition(0);
        motorBackRight.setTargetPosition(degreeval);
        motorFrontLeft.setTargetPosition(degreeval);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() )
        {
            //wait till motors done doing its thing
        }
        motorFrontLeft.setPower(0);
        //motorBackLeft.setPower(0);
        //motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

    }

    public static void DiagonalLeftForward(int degreeval)
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //motorFrontLeft.setPower(1);
        motorBackLeft.setPower(1);
        motorFrontRight.setPower(1);
        //motorBackRight.setPower(1);

        motorBackLeft.setTargetPosition(degreeval);
        motorFrontRight.setTargetPosition(degreeval);
        //motorBackRight.setTargetPosition(degreeval);
        //motorFrontLeft.setTargetPosition(degreeval);

        //motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        while(motorFrontRight.isBusy() && motorBackLeft.isBusy() )
        {
            //wait till motors done doing its thing
        }
        //motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        //motorBackRight.setPower(0);

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
    public static void DRIVEBACKWARD(int degreeval) {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(-1);
        motorBackLeft.setPower(-1);
        motorFrontRight.setPower(-1);
        motorBackRight.setPower(-1);

        motorBackLeft.setTargetPosition(-degreeval);
        motorFrontRight.setTargetPosition(-degreeval);
        motorBackRight.setTargetPosition(-degreeval);
        motorFrontLeft.setTargetPosition(-degreeval);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorFrontRight.isBusy() &&motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);



    }

    public static void SHOOT(int howManyBalls) throws InterruptedException {
        motorShootLeft.setPower(0.8);
        motorShootRight.setPower(0.8);
        motorCollect.setPower(1);
        servoElevate.setPosition(0);

        Thread.sleep(howManyBalls*TIME_TO_SHOOT_PER_BALL);

        motorShootLeft.setPower(0);
        motorShootRight.setPower(0);
        motorCollect.setPower(0);
        servoElevate.setPosition(0.5);


    }
    public static void TURN90LEFT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(TURNING_SPEED);
        motorBackLeft.setPower(TURNING_SPEED);
        motorFrontRight.setPower(-TURNING_SPEED);
        motorBackRight.setPower(-TURNING_SPEED);

        motorFrontLeft.setTargetPosition(DEGREES_90);
        motorBackLeft.setTargetPosition(DEGREES_90);
        motorFrontRight.setTargetPosition(-DEGREES_90);
        motorBackRight.setTargetPosition(-DEGREES_90);


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);



    }


    public static void TURN90RIGHT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(-TURNING_SPEED);
        motorBackLeft.setPower(-TURNING_SPEED);
        motorFrontRight.setPower(TURNING_SPEED);
        motorBackRight.setPower(TURNING_SPEED);

        motorFrontLeft.setTargetPosition(-DEGREES_90);
        motorBackLeft.setTargetPosition(-DEGREES_90);
        motorFrontRight.setTargetPosition(DEGREES_90);
        motorBackRight.setTargetPosition(DEGREES_90);


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

    }

    public static void TURN180LEFT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(TURNING_SPEED);
        motorBackLeft.setPower(TURNING_SPEED);
        motorFrontRight.setPower(-TURNING_SPEED);
        motorBackRight.setPower(-TURNING_SPEED);

        motorFrontLeft.setTargetPosition(DEGREES_180);
        motorBackLeft.setTargetPosition(DEGREES_180);
        motorFrontRight.setTargetPosition(-DEGREES_180);
        motorBackRight.setTargetPosition(-DEGREES_180);


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);



    }

    public static void TURN180RIGHT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setPower(-TURNING_SPEED);
        motorBackLeft.setPower(-TURNING_SPEED);
        motorFrontRight.setPower(TURNING_SPEED);
        motorBackRight.setPower(TURNING_SPEED);

        motorFrontLeft.setTargetPosition(-DEGREES_180);
        motorBackLeft.setTargetPosition(-DEGREES_180);
        motorFrontRight.setTargetPosition(DEGREES_180);
        motorBackRight.setTargetPosition(DEGREES_180);


        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while(motorFrontLeft.isBusy() && motorBackRight.isBusy() && motorFrontRight.isBusy() && motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

    }

}

