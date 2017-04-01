package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nishka on 22/01/17.
 */

@TeleOp(name = "final tele op", group = "agroup")

public class finalStartingTele extends LinearOpMode
{
    //constants
    final static double FAST_SPEED = 1;

/* useless constants
*   final static int TURNING_SPEED =1;
*   final static int DEGREES_90 = 1440;
*   final static int DEGREES_90 = 1440;
*   final static int DEGREES_180 = 1440;
*/
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

    //release arm motors
    private static Servo servoArmRaise1;
    private static Servo servoArmRaise2;

    //cap ball lifter
    private static DcMotor motorCapLift;

    //Beacon Servo

    private static Servo BeaconServo;

    //THIS IS FOR THE TWO MINUTE TIME PERIOD.

    @Override
    public void runOpMode() throws InterruptedException
    {
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

        //Shooting Motors
        motorShootLeft = hardwareMap.dcMotor.get("MC3ShootLeft");
        motorShootRight = hardwareMap.dcMotor.get("MC3ShootRight");

        motorShootLeft.setDirection(DcMotor.Direction.REVERSE);

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

        waitForStart();
        while(opModeIsActive())
        {
            //GAMEPAD 1 : DRIVER STUFF

                //CARDINAL DIRECTIONS

                //STOP
                if(!gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1))
                {
                    motorFrontLeft.setPower(0);
                    motorFrontRight.setPower(0);
                    motorBackLeft.setPower(0);
                    motorBackRight.setPower(0);
                }

                //Left Trigger Emergency Break
                if(gamepad1.left_trigger>0)
                {
                    motorFrontLeft.setPower(0);
                    motorFrontRight.setPower(0);
                    motorBackLeft.setPower(0);
                    motorBackRight.setPower(0);
                }

                // FORWARD
                if(gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1)&& gamepad1.left_trigger==0)
                {
                    motorFrontLeft.setPower(gamepad1.right_trigger);
                    motorFrontRight.setPower(gamepad1.right_trigger);
                    motorBackLeft.setPower(gamepad1.right_trigger);
                    motorBackRight.setPower(gamepad1.right_trigger);
                }

                // BACKWARD
                if(gamepad1.dpad_down &&!gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1)&& gamepad1.left_trigger==0)
                {
                    motorFrontLeft.setPower(-gamepad1.right_trigger);
                    motorFrontRight.setPower(-gamepad1.right_trigger);
                    motorBackLeft.setPower(-gamepad1.right_trigger);
                    motorBackRight.setPower(-gamepad1.right_trigger);
                }

                // AXIS LEFT
                if(gamepad1.dpad_left && !gamepad1.dpad_down &&!gamepad1.dpad_up && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1)&& gamepad1.left_trigger==0)
                {
                    motorFrontLeft.setPower(-gamepad1.right_trigger);
                    motorBackLeft.setPower(-gamepad1.right_trigger);
                    motorFrontRight.setPower(gamepad1.right_trigger);
                    motorBackRight.setPower(gamepad1.right_trigger);
                }

                // AXIS RIGHT
                if(gamepad1.dpad_right && !gamepad1.dpad_down &&!gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1)&& gamepad1.left_trigger==0)
                {
                    motorFrontLeft.setPower(gamepad1.right_trigger);
                    motorBackLeft.setPower(gamepad1.right_trigger);
                    motorFrontRight.setPower(-gamepad1.right_trigger);
                    motorBackRight.setPower(-gamepad1.right_trigger);
                }

                // SWAYING LEFT AND RIGHT


                //RIGHT SWAY
                if(gamepad1.right_stick_x>0.1 && !gamepad1.dpad_right && !gamepad1.dpad_down &&!gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1)&& gamepad1.left_trigger==0)
                {
                    motorFrontLeft.setPower(gamepad1.right_trigger);
                    motorBackLeft.setPower(-gamepad1.right_trigger);
                    motorFrontRight.setPower(-gamepad1.right_trigger);
                    motorBackRight.setPower(gamepad1.right_trigger);
                }

                //LEFT SWAY
                if(gamepad1.right_stick_x<-0.1 && !gamepad1.dpad_right && !gamepad1.dpad_down &&!gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1)&& gamepad1.left_trigger==0)
                {
                    motorFrontLeft.setPower(-gamepad1.right_trigger);
                    motorBackLeft.setPower(gamepad1.right_trigger);
                    motorFrontRight.setPower(gamepad1.right_trigger);
                    motorBackRight.setPower(-gamepad1.right_trigger);
                }


                // DIAGONALS

                if (gamepad1.left_stick_y < -0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y)
                {

                    if (gamepad1.left_stick_x > 0.1) //DIAGONAL FORWARD RIGHT
                    {
                        motorFrontLeft.setPower(gamepad1.right_trigger);
                        motorBackLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackRight.setPower(gamepad1.right_trigger);
                    }

                    else if (gamepad1.left_stick_x < -0.1)  //DIAGONAL FORWARD LEFT
                    {
                        motorFrontLeft.setPower(0);
                        motorBackLeft.setPower(gamepad1.right_trigger);
                        motorFrontRight.setPower(gamepad1.right_trigger);
                        motorBackRight.setPower(0);
                    }

                    else //FALSE ALARM
                    {
                        motorFrontLeft.setPower(0);
                        motorBackLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }

                if (gamepad1.left_stick_y > 0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y)
                {
                    if (gamepad1.left_stick_x > 0.1) //DIAGONAL BACKWARD RIGHT
                    {
                        motorFrontLeft.setPower(0);
                        motorBackLeft.setPower(-gamepad1.right_trigger);
                        motorFrontRight.setPower(-gamepad1.right_trigger);
                        motorBackRight.setPower(0);
                    }

                    else if (gamepad1.left_stick_x < -0.1) //DIAGONAL BACKWARD LEFT
                    {
                        motorFrontLeft.setPower(-gamepad1.right_trigger);
                        motorBackLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackRight.setPower(-gamepad1.right_trigger);
                    }

                    else //FALSE ALARM
                    {
                        motorFrontLeft.setPower(0);
                        motorBackLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }




                    /*
                // Standard Turning Buttons

                // A : 90 TO THE LEFT
                if(gamepad1.a &&!gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1))
                {
                    TURN90LEFT();
                }
                //B : 90 TO THE RIGHT
                if(gamepad1.b &&!gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.a && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1))
                {
                    TURN90RIGHT();
                }
                // X : 180 TO THE LEFT
                if(gamepad1.x &&!gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.b && !gamepad1.a && !gamepad1.y && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1))
                {
                    TURN180LEFT();
                }
                // Y : 180 TO THE RIGHT
                if(gamepad1.y &&!gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.b && !gamepad1.x && !gamepad1.a && (gamepad1.right_stick_x>-0.1 && gamepad1.right_stick_x<0.1) && (gamepad1.left_stick_x>-0.1 && gamepad1.left_stick_x<0.1) && (gamepad1.left_stick_y>-0.1 && gamepad1.left_stick_y<0.1))
                {
                    TURN180RIGHT();
                }

*/
            //GAMEPAD 2 : SHOOTING FOR THE 2 MINUTE PERIOD

                //Shooting

                    //on
                    if(gamepad2.a)
                    {
                        motorShootLeft.setPower(1);
                        motorShootRight.setPower(1);
                    }

                    //off
                    if(gamepad2.b)
                    {
                        motorShootLeft.setPower(0);
                        motorShootRight.setPower(0);
                    }


                //Collect

                    if(gamepad2.right_bumper)
                    {
                        motorCollect.setPower(1);
                    }

                    if(gamepad2.left_bumper)
                    {
                        motorCollect.setPower(-1);
                    }
                    if(gamepad2.x)
                    {
                        motorCollect.setPower(0);
                    }




            //Elevation on (up and down) and off
                    if(gamepad2.dpad_up) //ON-UP
                    {
                        servoElevate.setPosition(0);
                    }

                    if(gamepad2.dpad_down) //ON-DOWN
                    {
                        servoElevate.setPosition(1);
                    }
                    if(gamepad2.y) //OFF
                    {
                        servoElevate.setPosition(0.5);
                    }

            //ARM RELEASE

            //OPEN
            if (gamepad2.left_trigger>0)
            {
                servoArmRaise1.setPosition(0.9);
                servoArmRaise2.setPosition(0.1);
            }

            //CLOSE
            if (gamepad2.right_trigger>0)
            {
                servoArmRaise1.setPosition(0.6666);
                servoArmRaise2.setPosition(0.3);
            }

            //BALL CAPPING
            if(gamepad2.left_stick_y<0.1 && gamepad2.left_stick_y>-0.1)
            {
                motorCapLift.setPower(0);

            }
            else
            {
                motorCapLift.setPower(-gamepad2.left_stick_y);
            }

            //Beacon

            if (gamepad2.right_stick_x < -0.1)
            { //close
                BeaconServo.setPosition(0.74);
            }
            else
            {
                //open
                if (gamepad2.right_stick_x > 0.1)
                {
                    BeaconServo.setPosition(0);
                }
            }



                    /*if(gamepad1.right_bumper)
                    {
                        motorCollect.setPower(1);

                    }
                    if(gamepad1.left_bumper)
                    {
                        motorCollect.setPower(0);
                    }*/

                idle();
        }

    }
/*
    public static void TURN90LEFT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBackLeft.setTargetPosition(DEGREES_90);
        motorFrontRight.setTargetPosition(DEGREES_90);
        motorBackRight.setTargetPosition(DEGREES_90);
        motorFrontLeft.setTargetPosition(DEGREES_90);

        motorFrontLeft.setPower(TURNING_SPEED);
        motorBackLeft.setPower(TURNING_SPEED);
        motorFrontRight.setPower(-TURNING_SPEED);
        motorBackRight.setPower(-TURNING_SPEED);


        while(motorFrontLeft.isBusy() || motorBackRight.isBusy() || motorFrontRight.isBusy() || motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    public static void TURN90RIGHT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBackLeft.setTargetPosition(DEGREES_90);
        motorFrontRight.setTargetPosition(DEGREES_90);
        motorBackRight.setTargetPosition(DEGREES_90);
        motorFrontLeft.setTargetPosition(DEGREES_90);

        motorFrontLeft.setPower(-TURNING_SPEED);
        motorBackLeft.setPower(-TURNING_SPEED);
        motorFrontRight.setPower(TURNING_SPEED);
        motorBackRight.setPower(TURNING_SPEED);


        while(motorFrontLeft.isBusy() || motorBackRight.isBusy() || motorFrontRight.isBusy() || motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public static void TURN180LEFT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBackLeft.setTargetPosition(DEGREES_180);
        motorFrontRight.setTargetPosition(DEGREES_180);
        motorBackRight.setTargetPosition(DEGREES_180);
        motorFrontLeft.setTargetPosition(DEGREES_180);

        motorFrontLeft.setPower(TURNING_SPEED);
        motorBackLeft.setPower(TURNING_SPEED);
        motorFrontRight.setPower(-TURNING_SPEED);
        motorBackRight.setPower(-TURNING_SPEED);


        while(motorFrontLeft.isBusy() || motorBackRight.isBusy() || motorFrontRight.isBusy() || motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }

    public static void TURN180RIGHT()
    {
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBackLeft.setTargetPosition(DEGREES_180);
        motorFrontRight.setTargetPosition(DEGREES_180);
        motorBackRight.setTargetPosition(DEGREES_180);
        motorFrontLeft.setTargetPosition(DEGREES_180);

        motorFrontLeft.setPower(-TURNING_SPEED);
        motorBackLeft.setPower(-TURNING_SPEED);
        motorFrontRight.setPower(TURNING_SPEED);
        motorBackRight.setPower(TURNING_SPEED);


        while(motorFrontLeft.isBusy() || motorBackRight.isBusy() || motorFrontRight.isBusy() || motorBackLeft.isBusy())
        {
            //wait till motors done doing its thing
        }

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
*/
}