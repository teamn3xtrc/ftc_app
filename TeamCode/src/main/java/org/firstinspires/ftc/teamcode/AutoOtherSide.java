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

@Autonomous(name = "Red Side", group = "agroup")

public class AutoOtherSide extends LinearOpMode
{
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
        Color.rgb(0,0,0);

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

        servoArmRaise1= hardwareMap.servo.get("servoArmRaise1");
        servoArmRaise2= hardwareMap.servo.get("servoArmRaise2");

        servoArmRaise1.setPosition(0.6);
        servoArmRaise2.setPosition(0.35);

        servoElevate.setPosition(0.5);

        BeaconServo.setPosition(1);

        waitForStart();

        motorShootLeft.setPower(1);
        motorShootRight.setPower(1);

        //BeaconServo.setPosition(0);

        Thread.sleep(800);
        SHOOT(8);
        Thread.sleep(100);

        DRIVEFORWARD(5000);




        SwayLeft(6500);


        baseColor.enableLed(true);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(!(baseColor.red() > 100 || baseColor.green() > 100 || baseColor.blue() >100 ))
        {
            motorFrontLeft.setPower(0.1);
            motorBackRight.setPower(0.1);
            motorBackLeft.setPower(0.1);
            motorFrontRight.setPower(0.1);
        }
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while((baseColor.red() > 110 || baseColor.green() > 110 || baseColor.blue() >110 ))
        {
            motorFrontLeft.setPower(0.1);
            motorBackRight.setPower(0.1);
            motorBackLeft.setPower(0.1);
            motorFrontRight.setPower(0.1);
        }
        motorFrontLeft.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        DRIVEFORWARD(500);

        SwayLeft(1500);

        colorSensor.enableLed(false);

        Thread.sleep(2000);

        if(((colorSensor.blue()+colorSensor.blue())/2)<=((colorSensor.red()+colorSensor.red())/2))
        {
            BeaconServo.setPosition(0.4);
            telemetry.addData("blue","true");
            telemetry.update();
        }
        else
        {
            DRIVEBACKWARD(800);
            BeaconServo.setPosition(0.4);
            telemetry.addData("red","true");
            telemetry.update();
        }

        //BeaconServo.setPosition(1);

        TURN90LEFT();
        DRIVEFORWARD(6500);


        /*
        alternative end
        SwayLeft(3000);
        TURN90LEFT();
        DRIVEFORWARD(1500);
        */

        //SwayLeft(2000);
        //Parking

        //TURN90LEFT();
        //DRIVEFORWARD(2000);




        /*colorSensor.enableLed(true);
        while(colorSensor.blue()<150 || colorSensor.red()<150 || colorSensor.green()<150)
        {//sway right till you see white
            motorFrontLeft.setPower(0.5);
            motorBackLeft.setPower(-0.5);
            motorFrontRight.setPower(-0.5);
            motorBackRight.setPower(0.5);
        }

        colorSensor.enableLed(false);

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);*/



        /*
        DiagonalLeftForward(5000);
        DRIVEFORWARD(2000);
        DiagonalRightForward(6000);
        */

        //DiagonalRightForward(1000);
        //TURN90LEFT();
        //TURN90RIGHT();
        //DRIVEFORWARD(1000);
        //TURN180LEFT();
        //TURN180RIGHT();

    }
/*
        while(ULTRASONIC>thresholdValueToStopFollowing)
        {

            FOLLOWLINE();
        }*/


/*
    public static void FOLLOWLINE()
    {

            if (color1Val > blackVal)
            {
                //turn one way
                Color.
            }
            else {
                    if (color2Val > blackVal)
                    {
                        //set motors to turn the other way : diagonal left backwards
                    }

                    else
                    {
                        //goBackStraight
                    }
                }

    }*/

    //public static void DirectionOfSensor(){}
    //public static void DirectionNotOfSensor(){}

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

