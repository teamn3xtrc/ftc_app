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

public class ShootingTest extends LinearOpMode
{

    private DcMotor MC3Shoot;

    @Override
    public void runOpMode() throws InterruptedException
    {
        MC3Shoot = hardwareMap.dcMotor.get("MC3Shoot");

        waitForStart();

        while (opModeIsActive())
        {
            if (gamepad1.a)
            {
                MC3Shoot.setPower(0.2);
            }
            MC3Shoot.setPower(gamepad1.right_trigger);

            telemetry.addData("Power:", gamepad1.right_trigger);
            telemetry.update();
        }
    }
}