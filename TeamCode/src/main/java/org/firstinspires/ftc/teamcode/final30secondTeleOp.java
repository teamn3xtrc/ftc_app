package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "30SecondTeleOp", group = "agroup")

public class final30secondTeleOp extends LinearOpMode {
    //constants
    final static double SLOW_SPEED = 0.5;
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

    //THIS IS FOR THE TWO MINUTE TIME PERIOD.

    @Override
    public void runOpMode() throws InterruptedException {
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


        waitForStart();

        while (opModeIsActive()) {
            //GAMEPAD 1 : DRIVER STUFF

            //CARDINAL DIRECTIONS

            //STOP
            if (!gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1)) {
                motorFrontLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
            }

            //Left Trigger Emergency Break
            if (gamepad1.left_trigger > 0) {
                motorFrontLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
            }


            // FORWARD
            if (gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1) && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            // BACKWARD
            if (gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1) && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            // AXIS LEFT
            if (gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1) && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            // AXIS RIGHT
            if (gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1) && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }


            // SWAYING LEFT AND RIGHT


            //RIGHT SWAY
            if (gamepad1.right_stick_x > 0.1 && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1) && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //LEFT SWAY
            if (gamepad1.right_stick_x < -0.1 && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1) && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }


            // DIAGONALS

            if (gamepad1.left_stick_y < -0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y) {

                if (gamepad1.left_stick_x > 0.1) //DIAGONAL FORWARD RIGHT
                {
                    motorFrontLeft.setPower(gamepad1.right_trigger);
                    motorBackLeft.setPower(0);
                    motorFrontRight.setPower(0);
                    motorBackRight.setPower(gamepad1.right_trigger);
                } else if (gamepad1.left_stick_x < -0.1)  //DIAGONAL FORWARD LEFT
                {
                    motorFrontLeft.setPower(0);
                    motorBackLeft.setPower(gamepad1.right_trigger);
                    motorFrontRight.setPower(gamepad1.right_trigger);
                    motorBackRight.setPower(0);
                } else //FALSE ALARM
                {
                    motorFrontLeft.setPower(0);
                    motorBackLeft.setPower(0);
                    motorFrontRight.setPower(0);
                    motorBackRight.setPower(0);
                }
            }

            if (gamepad1.left_stick_y > 0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.b && !gamepad1.x && !gamepad1.y) {
                if (gamepad1.left_stick_x > 0.1) //DIAGONAL BACKWARD RIGHT
                {
                    motorFrontLeft.setPower(0);
                    motorBackLeft.setPower(-gamepad1.right_trigger);
                    motorFrontRight.setPower(-gamepad1.right_trigger);
                    motorBackRight.setPower(0);
                } else if (gamepad1.left_stick_x < -0.1) //DIAGONAL BACKWARD LEFT
                {
                    motorFrontLeft.setPower(-gamepad1.right_trigger);
                    motorBackLeft.setPower(0);
                    motorFrontRight.setPower(0);
                    motorBackRight.setPower(-gamepad1.right_trigger);
                } else //FALSE ALARM
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


            //GAMEPAD 2 : SHOOTING FOR THE 30 SECOND PERIOD

            //Shooting Different Speeds

            //SLOW
            if (gamepad2.a && !gamepad2.b && !gamepad2.x && !gamepad2.y) {
                motorShootLeft.setPower(SLOW_SPEED);
                motorShootRight.setPower(SLOW_SPEED);
            }

            //STOP WITH X
            if (!gamepad2.a && !gamepad2.b && gamepad2.x && !gamepad2.y) {
                motorShootLeft.setPower(0);
                motorShootRight.setPower(0);
            }

            //STOP WITH Y
            if (!gamepad2.a && !gamepad2.b && !gamepad2.x && gamepad2.y) {
                motorShootLeft.setPower(0);
                motorShootRight.setPower(0);
            }

            //VERY FAST
            if (!gamepad2.a && gamepad2.b && !gamepad2.x && !gamepad2.y) {
                motorShootLeft.setPower(FAST_SPEED);
                motorShootRight.setPower(FAST_SPEED);
            }


            //Collect

            if (gamepad2.dpad_right) {
                motorCollect.setPower(1);
            }

            if (gamepad2.dpad_left) {
                motorCollect.setPower(0);
            }

            //Elevation on and off
            if (gamepad2.dpad_up) //ON
            {
                servoElevate.setPosition(0);
            }

            if (gamepad2.dpad_down) //OFF
            {
                servoElevate.setPosition(0.5);
            }

            //

            //ARM RELEASE

            //OPEN
            if (gamepad2.left_bumper)
            {
                servoArmRaise1.setPosition(0.1);
                servoArmRaise2.setPosition(0.1);
                Thread.sleep(700);
                servoArmRaise2.setPosition(0.5);
                servoArmRaise1.setPosition(0.5);
            }

            //CLOSE
            if (gamepad2.right_bumper)
            {
                servoArmRaise1.setPosition(0.9);
                servoArmRaise2.setPosition(0.9);
                Thread.sleep(700);
                servoArmRaise2.setPosition(0.5);
                servoArmRaise1.setPosition(0.5);
            }

            //BALL CAPPING

            //WIND
            if (gamepad2.left_stick_y < -0.1)
            {
                motorCapLift.setPower(0.75);
            }
            else
            {
                //UNWIND
                if (gamepad2.left_stick_y > 0.1)
                {
                    motorCapLift.setPower(-0.75);
                }
                    else
                    {
                        motorCapLift.setPower(0);
                    }
            }

            idle();
        }

    }
}