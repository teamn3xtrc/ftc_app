package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nishka on 21/01/17.
 */
@TeleOp(name = "tester shooter", group = "agroup")

public class aTesterForTheShooter extends LinearOpMode {

    private DcMotor motorShootLeft;
    private DcMotor motorShootRight;
    private Servo servomotor;



    @Override
    public void runOpMode() throws InterruptedException {

        motorShootLeft = hardwareMap.dcMotor.get("MC3M1");
        motorShootRight = hardwareMap.dcMotor.get("MC3M2");
        servomotor = hardwareMap.servo.get("servoM1");


        waitForStart();

        while(opModeIsActive()){

           servomotor.setPosition(0);


        if (gamepad1.b && gamepad1.a && gamepad1.y && gamepad1.x) {
            motorShootLeft.setPower(1);
            motorShootRight.setPower(1);
            Thread.sleep(1000);
            motorShootLeft.setPower(0);
            motorShootRight.setPower(0);
        }

        if (!gamepad1.b && !gamepad1.a && !gamepad1.y && !gamepad1.x) {

            motorShootLeft.setPower(0);
            motorShootRight.setPower(0);
        }
        idle();
        }


    }
}