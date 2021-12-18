package org.firstinspires.ftc.teamcode.main.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.main.hardware.Drive;

@Config
@TeleOp(group = "Test", name = "MainTestOp")
public class MainTestOp extends OpMode {

        private Drive d;

    @Override
    public void init() {
        Drive.deleteInstance();

        telemetry.addData("Initialized!", true);

        d = Drive.instantiate();
        d.init();
    }

    @Override
    public void start(){
        telemetry.addData("Start!", true);
    }

    @Override
    public void loop() {
        telemetry.addData("Loop!", true);
        telemetry.addData("Drive Instance Found? ", d != null);
    }

    @Override
    public void stop() {
        telemetry.addData("stop!", true);
    }
}
