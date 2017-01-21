package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Nishka on 07/01/17.
 * Edited by Shivaan and Ansh on several occasions after that.
 */

@TeleOp(name = "NewMap", group = "agroup")

public class NewMap_TeleOp extends LinearOpMode
{

    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;

    private DcMotor motorShootLeft;
    private DcMotor motorShootRight;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorFrontLeft = hardwareMap.dcMotor.get("MC1M1");
        motorBackLeft = hardwareMap.dcMotor.get("MC1M2");
        motorFrontRight = hardwareMap.dcMotor.get("MC2M1");
        motorBackRight = hardwareMap.dcMotor.get("MC2M2");

        motorShootLeft = hardwareMap.dcMotor.get("MC3M1");
        motorShootRight = hardwareMap.dcMotor.get("MC3M2");

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

        /*
        motorShootLeft.setDirection(DcMotor.Direction.REVERSE);
        motorShootRight.setDirection(DcMotor.Direction.REVERSE);
        */

        //servomotor = hardwareMap.servo.get("servoM1");
        waitForStart();

        while(opModeIsActive()) {

            //STOP
            if (!gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
            }
            //FORWARD
            if (gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //BACK
            if (gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //NON CARDINAL DIRECTIONS RIGHT STICK


            //LEFT
            if (gamepad1.left_stick_x < -0.1 && gamepad1.left_stick_y == 0 && !gamepad1.dpad_left && !gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_right && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //RIGHT

            if (gamepad1.left_stick_x > 0.1 && gamepad1.left_stick_y == 0 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }


            //NON CARDINAL DIRECTIONS LEFT STICK

            if (gamepad1.right_stick_y < -0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0) {
                if (gamepad1.right_stick_x > 0.1) //DIAGONAL FORWARD RIGHT
                {
                    motorFrontLeft.setPower(gamepad1.right_trigger);
                    motorBackLeft.setPower(0);
                    motorFrontRight.setPower(0);
                    motorBackRight.setPower(gamepad1.right_trigger);
                } else if (gamepad1.right_stick_x < -0.1)  //DIAGONAL FORWARD LEFT
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

            if (gamepad1.right_stick_y > 0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0) {
                if (gamepad1.right_stick_x > 0.1) //DIAGONAL BACKWARD RIGHT
                {
                    motorFrontLeft.setPower(0);
                    motorBackLeft.setPower(-gamepad1.right_trigger);
                    motorFrontRight.setPower(-gamepad1.right_trigger);
                    motorBackRight.setPower(0);

                } else if (gamepad1.right_stick_x < -0.1) //DIAGONAL BACKWARD LEFT
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
            //DIAGONAL FORWARD LEFT
            if (gamepad1.left_stick_x == -1 && gamepad1.left_stick_y == 1 && !gamepad1.dpad_left && gamepad1.dpad_up && !gamepad1.dpad_right && !gamepad1.dpad_down && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(0);
            }
           //DIAGONAL FORWARD RIGHT
            if (gamepad1.left_stick_x == 1 && gamepad1.left_stick_y == 1 && !gamepad1.dpad_right && gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(gamepad1.right_trigger);

            }

           //DIAGONAL BACKWARD LEFT
            if (gamepad1.left_stick_x == -1 && gamepad1.left_stick_y == -1 && !gamepad1.dpad_left && gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_right && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(-gamepad1.right_trigger);

            }

           //DIAGONAL BACKWARD RIGHT
            if (gamepad1.left_stick_x == 1 && gamepad1.left_stick_y == -1 && !gamepad1.dpad_down && gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(0);

            }
*/
            //AXIS TURNS

            //TURN LEFT

            if (gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_up && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //TURN RIGHT

            if (gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_up && gamepad1.left_trigger == 0) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //BRAKE

            if (gamepad1.left_trigger > 0) {
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);
            }


            //SHOOTING

            //SLOW
            if (gamepad1.a && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0 && !gamepad1.x && !gamepad1.y && !gamepad1.b) {
                motorShootLeft.setPower(0.25);
                motorShootRight.setPower(0.25);
            }

            //MEDIUM-SLOW
            if (gamepad1.x && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.y && !gamepad1.b) {
                motorShootLeft.setPower(0.5);
                motorShootRight.setPower(0.5);
            }
            motorShootRight.setPower(0.75);


            //MEDIUM-FAST
            if (gamepad1.y && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.x && !gamepad1.b) {
                motorShootLeft.setPower(0.75);
                motorShootRight.setPower(0.75);

            }
            //FAST
            if (gamepad1.b && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0 && !gamepad1.a && !gamepad1.y && !gamepad1.x) {
                motorShootLeft.setPower(1);
                motorShootRight.setPower(1);
            }

            idle();


        }

    }


}