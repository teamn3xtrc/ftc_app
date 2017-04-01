package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "NewLayout_TeleOP", group = "agroup")

public class NewLayout_TeleOp extends LinearOpMode
{
    //Drive Motors
    private static DcMotor motorFrontLeft;
    private static DcMotor motorBackLeft;
    private static DcMotor motorFrontRight;
    private static DcMotor motorBackRight;
    //private static DcMotor motorShoot;

    private double deadzoneval;
    private double negdeadzoneval;

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

        //Shooting Motor
        //motorShoot = hardwareMap.dcMotor.get("MC3Shoot");

        /*
        //Collection Motor && Lifting Motor
        motorCollect = hardwareMap.dcMotor.get("collectorMotor");

        servoElevate = hardwareMap.servo.get("elevatorMotor");

        //arm Raise
        servoArmRaise1= hardwareMap.servo.get("servoArmRaise1");
        servoArmRaise2= hardwareMap.servo.get("servoArmRaise2");

        //motor Cap Lift
        motorCapLift= hardwareMap.dcMotor.get("capLift");

        //beacon servo
        BeaconServo= hardwareMap.servo.get("beacon");

        //set up servos
        servoArmRaise1.setPosition(0.6666);
        servoArmRaise2.setPosition(0.3);

        servoElevate.setPosition(0.5);

        BeaconServo.setPosition(0.74);

        */

        waitForStart();
        while(opModeIsActive())
        {
            /**GAMEPAD 1*/

            if (!gamepad1.left_bumper)
            {
                //v1
                motorFrontLeft.setPower(  ((gamepad1.right_trigger)*Math.sin(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x  ,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  )));
                //v3
                motorBackLeft.setPower(   ((gamepad1.right_trigger)*Math.sin(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  )) );
                //v2
                motorFrontRight.setPower( ((gamepad1.right_trigger)*Math.cos(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  )));
                //v4
                motorBackRight.setPower( ((gamepad1.right_trigger)*Math.cos(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  )));

                //CARDINAL DIRECTIONS

                //SWAY LEFT
                if (gamepad1.right_stick_x < negdeadzoneval)
                {
                    motorFrontLeft.setPower(-1);
                    motorFrontRight.setPower(1);
                    motorBackLeft.setPower(1);
                    motorBackRight.setPower(-1);
                }
                //SWAY RIGHT
                if (gamepad1.right_stick_x > deadzoneval)
                {
                    motorFrontLeft.setPower(1);
                    motorFrontRight.setPower(-1);
                    motorBackLeft.setPower(-1);
                    motorBackRight.setPower(1);
                }

                //DIAGONALS
                //NORTH-EAST
                if (!gamepad1.a && gamepad1.b && !gamepad1.x && gamepad1.y)
                {
                    motorFrontLeft.setPower(0);
                    motorFrontRight.setPower(1);
                    motorBackLeft.setPower(1);
                    motorBackRight.setPower(0);

                }
                //NORTH-WEST
                if (!gamepad1.a && !gamepad1.b && gamepad1.x && gamepad1.y)
                {
                    motorFrontLeft.setPower(1);
                    motorFrontRight.setPower(0);
                    motorBackLeft.setPower(0);
                    motorBackRight.setPower(1);
                }
                //SOUTH-EAST
                if (gamepad1.a && !gamepad1.b && gamepad1.x && !gamepad1.y)
                {
                    motorFrontLeft.setPower(-1);
                    motorFrontRight.setPower(0);
                    motorBackLeft.setPower(0);
                    motorBackRight.setPower(-1);
                }
                //SOUTH-WEST
                if (gamepad1.a && gamepad1.b && !gamepad1.x && !gamepad1.y)
                {
                    motorFrontLeft.setPower(0);
                    motorFrontRight.setPower(-1);
                    motorBackLeft.setPower(-1);
                    motorBackRight.setPower(0);
                }
            }

            /**ENDGAME SWITCH*/
            if (gamepad1.left_bumper)
            {
                //v1
                motorFrontLeft.setPower(  -(((gamepad1.right_trigger)*Math.sin(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x  ,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ) )) );
                //v3
                motorBackLeft.setPower(  -(((gamepad1.right_trigger)*Math.sin(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  )) ));
                //v2
                motorFrontRight.setPower( -(((gamepad1.right_trigger)*Math.cos(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ))));
                //v4
                motorBackRight.setPower( -(((gamepad1.right_trigger)*Math.cos(  ( ((((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI))>0? (Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI) : ((Math.atan2(gamepad1.left_stick_x,-gamepad1.left_stick_y)*180/Math.PI)+360)) *(Math.PI/180))   + (Math.PI/4))  ))));

                //CARDINAL DIRECTIONS

                //SWAY LEFT
                if (gamepad1.right_stick_x < negdeadzoneval)
                {
                    motorFrontLeft.setPower(1);
                    motorFrontRight.setPower(-1);
                    motorBackLeft.setPower(-1);
                    motorBackRight.setPower(1);
                }
                //SWAY RIGHT
                if (gamepad1.right_stick_x > deadzoneval)
                {
                    motorFrontLeft.setPower(-1);
                    motorFrontRight.setPower(1);
                    motorBackLeft.setPower(1);
                    motorBackRight.setPower(-1);
                }

                //DIAGONALS
                //NORTH-EAST
                if (!gamepad1.a && gamepad1.b && !gamepad1.x && gamepad1.y)
                {
                    motorFrontLeft.setPower(1);
                    motorFrontRight.setPower(0);
                    motorBackLeft.setPower(0);
                    motorBackRight.setPower(1);

                }
                //NORTH-WEST
                if (!gamepad1.a && !gamepad1.b && gamepad1.x && gamepad1.y)
                {
                    motorFrontLeft.setPower(0);
                    motorFrontRight.setPower(1);
                    motorBackLeft.setPower(1);
                    motorBackRight.setPower(0);
                }
                //SOUTH-EAST
                if (gamepad1.a && !gamepad1.b && gamepad1.x && !gamepad1.y)
                {
                    motorFrontLeft.setPower(0);
                    motorFrontRight.setPower(-1);
                    motorBackLeft.setPower(-1);
                    motorBackRight.setPower(0);
                }
                //SOUTH-WEST
                if (gamepad1.a && gamepad1.b && !gamepad1.x && !gamepad1.y)
                {
                    motorFrontLeft.setPower(-1);
                    motorFrontRight.setPower(0);
                    motorBackLeft.setPower(0);
                    motorBackRight.setPower(-1);
                }
            }


            /**GAMEPAD 2*/

        }

        idle();
    }
}

