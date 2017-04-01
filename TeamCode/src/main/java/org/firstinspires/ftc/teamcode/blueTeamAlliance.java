package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "final_startteleOp", group = "agroup")

public class blueTeamAlliance extends LinearOpMode {

    //Drive Motors
    private static DcMotor motorLeft;
    private static DcMotor motorRight;

    @Override
    public void runOpMode() throws InterruptedException {
        //Drive Motors
        motorLeft = hardwareMap.dcMotor.get("MC1M1");
        motorRight = hardwareMap.dcMotor.get("MC1M2");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive())
        {
            //FORWARD
            if(gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.right_bumper && !gamepad1.left_bumper)
            {
                motorLeft.setPower(gamepad1.right_trigger);
                motorRight.setPower(gamepad1.right_trigger);
            }

            //BACKWARD
            if(gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.dpad_left && !gamepad1.right_bumper && !gamepad1.left_bumper)
            {
                motorLeft.setPower(-gamepad1.right_trigger);
                motorRight.setPower(-gamepad1.right_trigger);
            }

            //LEFT AXIS
            if(gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_up && !gamepad1.right_bumper && !gamepad1.left_bumper)
            {
                motorLeft.setPower(gamepad1.right_trigger);
                motorRight.setPower(-gamepad1.right_trigger);
            }

            //RIGHT AXIS
            if(gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_up && !gamepad1.right_bumper && !gamepad1.left_bumper)
            {
                motorLeft.setPower(-gamepad1.right_trigger);
                motorRight.setPower(gamepad1.right_trigger);
            }

            //LEFT_PIVOT
            if(gamepad1.left_bumper && !gamepad1.right_bumper && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_up)
            {
                motorLeft.setPower(gamepad1.right_trigger);
            }

            //RIGHT PIVOT
            if(gamepad1.right_bumper && !gamepad1.left_bumper && !gamepad1.dpad_right && !gamepad1.dpad_left && !gamepad1.dpad_down && !gamepad1.dpad_up)
            {
                motorRight.setPower(gamepad1.right_trigger);
            }
        }
    }
}
