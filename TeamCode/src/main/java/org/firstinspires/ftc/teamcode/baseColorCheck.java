package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LED;

/**
 * Created by Nishka on 30/01/17.
 */
@TeleOp(name = "base sensor check", group = "agroup")

public class baseColorCheck extends LinearOpMode
{
    ColorSensor colorSensor;
    boolean CSLedOn=false;
    boolean check = false;

    @Override
    public void runOpMode() throws InterruptedException
    {

        colorSensor = hardwareMap.colorSensor.get("baseColor");
        colorSensor.enableLed(CSLedOn);

        waitForStart();
        while(opModeIsActive()) {

            if (gamepad1.a) {
                check = !check;
                if (check==true) {
                    CSLedOn = true;
                    colorSensor.enableLed(CSLedOn);
                }
                if (check==false) {
                    CSLedOn = false;
                    colorSensor.enableLed(CSLedOn);
                }
            }


            Color.rgb(0, 0, 0);

            telemetry.addData("LED", CSLedOn ? "On" : "Off");
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());

            telemetry.update();


        }

        }

    }

