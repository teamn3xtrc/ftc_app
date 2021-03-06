package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nishka on 07/01/17.
 * Edited by Shivaan and Ansh on several occasions after that.
 */

@TeleOp(name = "EndGame_30sec", group = "agroup")

public class EndGameTeleOp extends LinearOpMode
{
    //DRIVING
    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;

    //ARM RELEASE
    private Servo ArmRelease1;
    private Servo ArmRelease2;

    //CAPPING
    private DcMotor BallVertical;

    //SHOOTING
    private DcMotor motorShootLeft;
    private DcMotor motorShootRight;

    //COLLECTOR
    private DcMotor motorCollector;

    //ELEVATOR
    private Servo ElevatorMech;

    @Override
    public void runOpMode() throws InterruptedException
    {
        //DRIVING MOTORS
        motorFrontLeft = hardwareMap.dcMotor.get("MC1M1");
        motorBackLeft = hardwareMap.dcMotor.get("MC1M2");
        motorFrontRight = hardwareMap.dcMotor.get("MC2M1");
        motorBackRight = hardwareMap.dcMotor.get("MC2M2");

        //SHOOTING
        motorShootLeft = hardwareMap.dcMotor.get("MC3ShootLeft");
        motorShootRight = hardwareMap.dcMotor.get("MC3ShootRight");

        //COLLECTOR
        motorCollector = hardwareMap.dcMotor.get("collectorMotor");

        //CAPPING MOTOR
        BallVertical = hardwareMap.dcMotor.get("cappingMotor");

        //ARM RELEASE MOTOR
        ArmRelease1 = hardwareMap.servo.get("armRelease1");
        ArmRelease2 = hardwareMap.servo.get("armRelease2");

        //ELEVATOR
        ElevatorMech = hardwareMap.servo.get("elevatorMotor");

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive())
        {

            //CARDINAL DIRECTIONS

            //STOP
            if (!gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackLeft.setPower(0);
                motorBackRight.setPower(0);
            }
            //FORWARD
            if (gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //BACK
            if (gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_right && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //NON CARDINAL DIRECTIONS RIGHT STICK

            //LEFT
            if (gamepad1.right_stick_x < -0.1 && gamepad1.right_stick_y == 0 && !gamepad1.dpad_left && !gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_right && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //RIGHT

            if (gamepad1.right_stick_x > 0.1 && gamepad1.right_stick_y == 0 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //NON CARDINAL DIRECTIONS LEFT STICK

            if (gamepad1.left_stick_y < -0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0)
            {
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

            if (gamepad1.left_stick_y > 0.1 && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && gamepad1.left_trigger == 0) {
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

            //AXIS TURNS

            //TURN LEFT

            if (gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_up && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(gamepad1.right_trigger);
                motorBackLeft.setPower(gamepad1.right_trigger);
                motorFrontRight.setPower(-gamepad1.right_trigger);
                motorBackRight.setPower(-gamepad1.right_trigger);
            }

            //TURN RIGHT

            if (gamepad1.dpad_right && !gamepad1.dpad_down && !gamepad1.dpad_left && !gamepad1.dpad_up && gamepad1.left_trigger == 0)
            {
                motorFrontLeft.setPower(-gamepad1.right_trigger);
                motorBackLeft.setPower(-gamepad1.right_trigger);
                motorFrontRight.setPower(gamepad1.right_trigger);
                motorBackRight.setPower(gamepad1.right_trigger);
            }

            //BRAKE

            if (gamepad1.left_trigger > 0)
            {
                motorFrontLeft.setPower(0);
                motorBackLeft.setPower(0);
                motorFrontRight.setPower(0);
                motorBackRight.setPower(0);
            }

            //ARM RELEASE (need to test)

            //OPEN
            if (gamepad2.left_bumper)
            {
                ArmRelease1.setPosition(0.25);
                ArmRelease2.setPosition(0.25);
            }

            //CLOSE
            if (gamepad2.right_bumper)
            {
                ArmRelease1.setPosition(0.75);
                ArmRelease2.setPosition(0.75);
            }

            //BALL CAPPING

            //WIND
            if (gamepad2.left_stick_y < 0)
            {
                BallVertical.setPower(0.75);
            }

            //UNWIND
            if (gamepad2.left_stick_y > 0)
            {
                BallVertical.setPower(-0.75);
            }

            //SHOOTING

            //SLOW
            if (gamepad2.a)
            {
                motorShootLeft.setPower(0.5);
                motorShootRight.setPower(0.5);
            }

            //FAST
            if (gamepad2.b)
            {
                motorShootLeft.setPower(1);
                motorShootRight.setPower(1);
            }

            //STOP
            if (gamepad2.x)
            {
                motorShootLeft.setPower(0);
                motorShootRight.setPower(0);
            }
            if (gamepad2.y)
            {
                motorShootLeft.setPower(0);
                motorShootRight.setPower(0);
            }

            //COLLECT

            //START
            if (gamepad2.dpad_right)
            {
                motorCollector.setPower(1);
            }

            //STOP
            if (gamepad2.dpad_left)
            {
                motorCollector.setPower(0);
            }

            //BALL ELEVATOR

            //START
            if (gamepad2.dpad_up)
            {
                ElevatorMech.setPosition(1);
            }

            //STOP
            if (gamepad2.dpad_down)
            {
                ElevatorMech.setPosition(0.5);
            }

            idle();

        }

    }

}