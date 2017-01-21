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

@TeleOp(name = "ShootingTest", group = "agroup")

public class ShootingTest extends LinearOpMode {

    private DcMotor motorFrontLeft;
    private DcMotor motorBackLeft;
    private DcMotor motorFrontRight;
    private DcMotor motorBackRight;

    private DcMotor motorShootLeft;
    private DcMotor motorShootRight;

    @Override
    public void runOpMode() throws InterruptedException {
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

        while (opModeIsActive()) {
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