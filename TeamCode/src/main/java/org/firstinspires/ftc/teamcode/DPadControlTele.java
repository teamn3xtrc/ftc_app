package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Nishka on 07/01/17.
 */

@TeleOp(name = "DPad control", group = "agroup")

public class DPadControlTele extends LinearOpMode
{

    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;

    private final double TURNING_SPEED = 0.8;
    //private final int TURNING_TIME_90_left = 1000;
    //private final int TURNING_TIME_90_right = 1000;
    //private final int TURNING_TIME_180 = 1000;


    @Override
    public void runOpMode() throws InterruptedException
    {
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

        //servomotor = hardwareMap.servo.get("servoM1");
        waitForStart();

        while(opModeIsActive())
        {
            //MOVING THE ROBOT


            //Stop
            if (!gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b) {
                motorFrontLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
            }
            //FORWARD
            if (gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b) {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //BACK
            if (gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b) {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //OTHER DIRECTION STUFFZ

            //LEFT
            if (gamepad1.dpad_left && !gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b) {

                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }
            //RIGHT SIDE DRAKE WHADDUP

            if (gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.a && !gamepad1.b) {

                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //DIAGONAL FORWARD LEFT
            if (gamepad1.dpad_left && gamepad1.dpad_up && !gamepad1.a && !gamepad1.b && !gamepad1.dpad_right && !gamepad1.dpad_down ) {

                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(0);
            }
            //DIAGONAL FORWARD RIGHT
            if (gamepad1.dpad_right && gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.a && !gamepad1.b) {

                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(gamepad1.right_trigger);

            }

            //DIAGONAL BACKWARD LEFT
            if (gamepad1.dpad_left && gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_right && !gamepad1.a && !gamepad1.b) {

                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(-gamepad1.right_trigger);

            }

            //DIAGONAL BACKWARD RIGHT
            if (gamepad1.dpad_down && gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up && !gamepad1.a && !gamepad1.b) {

                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(0);

            }
            //TURN LEFT

            if(gamepad1.b && !gamepad1.a && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up){
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //TURN RIGHT

            if(gamepad1.a && !gamepad1.b && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up){
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }




            //180
             if(gamepad1.left_trigger > 0.8 && !gamepad1.b && !gamepad1.a && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up)
             {
                 motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                 motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                 motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                 motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                 motorBackLeft.setTargetPosition(0);
                 motorFrontRight.setTargetPosition(0);
                 motorBackRight.setTargetPosition(0);
                 motorFrontLeft.setTargetPosition(0);

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

                 /* (using time)
                motorFrontLeft.setPower(TURNING_SPEED);
                motorBackLeft.setPower(TURNING_SPEED);
                motorFrontRight.setPower(-TURNING_SPEED);
                motorBackRight.setPower(-TURNING_SPEED);

                Thread.sleep(TURNING_TIME_180);

                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);
                */
             }

            //90 RIGHT

            if(gamepad1.right_bumper && !gamepad1.b && !gamepad1.a && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up)
            {
                motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motorBackLeft.setTargetPosition(0);
                motorFrontRight.setTargetPosition(0);
                motorBackRight.setTargetPosition(0);
                motorFrontLeft.setTargetPosition(0);

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


                /* (using time)
                motorFrontLeft.setPower(-TURNING_SPEED);
                motorBackLeft.setPower(-TURNING_SPEED);
                motorFrontRight.setPower(TURNING_SPEED);
                motorBackRight.setPower(TURNING_SPEED);

                Thread.sleep(TURNING_TIME_90_right);

                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);*/

            }

            //90 LEFT
            if(gamepad1.left_bumper && !gamepad1.b && !gamepad1.a && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_up)
            {
                motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                motorBackLeft.setTargetPosition(0);
                motorFrontRight.setTargetPosition(0);
                motorBackRight.setTargetPosition(0);
                motorFrontLeft.setTargetPosition(0);

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


                /* (using time)
                motorFrontLeft.setPower(TURNING_SPEED);
                motorBackLeft.setPower(TURNING_SPEED);
                motorFrontRight.setPower(-TURNING_SPEED);
                motorBackRight.setPower(-TURNING_SPEED);

                Thread.sleep(TURNING_TIME_90_left);

                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);
                */
            }



            // the SHOOTING MECHANISM

            if(gamepad2.dpad_up && !gamepad2.dpad_down){


            }

            if(gamepad2.dpad_down && !gamepad2.dpad_up){

            }


            idle();

        }


    }
}
