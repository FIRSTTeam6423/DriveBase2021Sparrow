package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class Drivetrain {

    private static WPI_TalonSRX leftFront;
    private static WPI_VictorSPX leftBack;
    private static WPI_TalonSRX rightFront;
    private static WPI_VictorSPX rightBack;
    
    final int kTimeoutMs = 30;
    int ARCADE_LEFT_DAMPENING = 60;
    int ARCADE_RIGHT_DAMPENING = 60;
    
    public Drivetrain() {
        leftFront = new WPI_TalonSRX(4);
        leftBack = new WPI_VictorSPX(0);
        rightFront = new WPI_TalonSRX(5);
        rightBack = new WPI_VictorSPX(2);

        leftBack.follow(leftFront);
        rightBack.follow(rightFront);
        
        leftFront.setNeutralMode(NeutralMode.Brake);
        rightFront.setNeutralMode(NeutralMode.Brake);

        leftFront.configOpenloopRamp(0.3);
        rightFront.configOpenloopRamp(0.3);
    }
        
    public void arcadeDrive(double straight, double left, double right) {
        left = left*ARCADE_LEFT_DAMPENING/100;
        right = right*ARCADE_RIGHT_DAMPENING/100;
        leftFront.set(ControlMode.PercentOutput, straight + left - right);
        rightFront.set(ControlMode.PercentOutput, -(straight - left + right)); 
    }

    public void tankDrive(double lspeed, double rspeed){
        leftFront.set(ControlMode.PercentOutput, -lspeed);
        rightFront.set(ControlMode.PercentOutput, rspeed);
    }
}
