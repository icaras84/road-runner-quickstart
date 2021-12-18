package org.firstinspires.ftc.teamcode.main.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.main.MathLib.mathLib.Vector3f;

@Config
@Autonomous(name = "Drive", group = "Test")
public class Drive extends OpMode {

    //Static for singleton
    private static Drive dInstance = null;

    public enum MotorStatus {
        IDLE,
        RUNNING, //this can be BUSY, but I like this syntax
        STOPPED
    }

    public enum DriveMotorSelection{
        LEFT_DRIVE,
        RIGHT_DRIVE
    }

    //DcMotors
    private DcMotor _0Motor = null, leftDrive = null, rightDrive = null;

    private MotorStatus leftDriveState = MotorStatus.IDLE, rightDriveState = MotorStatus.IDLE;

    private Drive(){}

    public static Drive instantiate(){
        if (dInstance == null) {
            dInstance = new Drive(); //instantiate new drive and keep it in a private static var
            return Drive.dInstance; //return Drive after creation
        } else {
            return null;
        }
    }

    @Override
    public void init() {
        //assign motor in a try catch block to avoid stopping the entire runtime


        try {
            //get motors from the hardwareMap
            _0Motor = hardwareMap.get(DcMotor.class, "0");
            leftDrive = hardwareMap.get(DcMotor.class, "1");
            rightDrive = hardwareMap.get(DcMotor.class, "2");

            //set directions
            _0Motor.setDirection(DcMotorSimple.Direction.FORWARD);
            leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

            //set RUN_MODES
            _0Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        } catch (Exception e){
            telemetry.log().add(this.getClass().getSimpleName() + ": Could not initialize");
        }
    }

    public static void deleteInstance(){
        Drive.dInstance = null;
    }

    @Override
    public void start(){

    }

    @Override
    public void loop() {
        telemetry.addData("Button X Pressed? ", gamepad1.x);
    }

    @Override
    public void stop(){
        rightDrive.setPower(0);
    }

    public boolean isMotorBusy(DriveMotorSelection motorSelection){
        boolean busyState = true; //always assume it is busy so we don't

        switch (motorSelection){
            case LEFT_DRIVE:

                break;
            case RIGHT_DRIVE:

                break;
            default:
                break;
        }

        return true;
    }
    
    public void driveTo(double power, Vector3f localPos){
    
    }
    
    public void turnTo(double power, double localAngle){
    
    }

}
