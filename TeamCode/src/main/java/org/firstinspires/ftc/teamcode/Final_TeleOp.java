package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "final_start_teleOp", group = "agroup")

public class Final_TeleOp extends LinearOpMode {
    //constants
    final static int TURNING_SPEED = 1;
    final static int DEGREES_90 = 1440;
    final static int DEGREES_180 = 1440;

    final static double V_SLOW_SPEED = 0.25;
    final static double SLOW_SPEED = 0.5;
    final static double FAST_SPEED = 0.75;
    final static double V_FAST_SPEED = 1.0;

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

        //Collection Motor && Lifting Motor
        motorCollect = hardwareMap.dcMotor.get("collectorMotor");
        servoElevate = hardwareMap.servo.get("elevatorMotor");


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
            if (gamepad1.a && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.b && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1)) {
                TURN90LEFT();
            }
            //B : 90 TO THE RIGHT
            if (gamepad1.b && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.a && !gamepad1.x && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1)) {
                TURN90RIGHT();
            }
            // X : 180 TO THE LEFT
            if (gamepad1.x && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.b && !gamepad1.a && !gamepad1.y && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1)) {
                TURN180LEFT();
            }
            // Y : 180 TO THE RIGHT
            if (gamepad1.y && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.b && !gamepad1.x && !gamepad1.a && (gamepad1.right_stick_x > -0.1 && gamepad1.right_stick_x < 0.1) && (gamepad1.left_stick_x > -0.1 && gamepad1.left_stick_x < 0.1) && (gamepad1.left_stick_y > -0.1 && gamepad1.left_stick_y < 0.1)) {
                TURN180RIGHT();
            }

*/
            //GAMEPAD 2 : SHOOTING STUFF

            //Elevation on and off
            if (gamepad2.dpad_left) //ON
            {
                servoElevate.setPosition(0);
            }

            if (gamepad2.dpad_right) //OFF
            {
                servoElevate.setPosition(0.5);
            }

            //Shooting Different Speeds

            //VERY SLOW
            if (gamepad2.a && !gamepad2.b && !gamepad2.x && !gamepad2.y) {
                motorShootLeft.setPower(V_SLOW_SPEED);
                motorShootRight.setPower(V_SLOW_SPEED);
            }

            //SLOW
            if (!gamepad2.a && !gamepad2.b && gamepad2.x && gamepad2.y) {
                motorShootLeft.setPower(SLOW_SPEED);
                motorShootRight.setPower(SLOW_SPEED);
            }

            //FAST
            if (!gamepad2.a && !gamepad2.b && !gamepad2.x && gamepad2.y) {
                motorShootLeft.setPower(FAST_SPEED);
                motorShootRight.setPower(FAST_SPEED);
            }

            //VERY FAST
            if (!gamepad2.a && gamepad2.b && !gamepad2.x && !gamepad2.y) {
                motorShootLeft.setPower(V_FAST_SPEED);
                motorShootRight.setPower(V_FAST_SPEED);
            }

            //NOT SHOOTING
            if (!gamepad2.a && !gamepad2.b && !gamepad2.x && !gamepad2.y) {
                motorShootLeft.setPower(0);
                motorShootRight.setPower(0);
            }


            //Collect

            if (gamepad2.right_trigger > 0) {
                motorCollect.setPower(1);
            } else {
                motorCollect.setPower(0);
            }

            idle();
        }

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
