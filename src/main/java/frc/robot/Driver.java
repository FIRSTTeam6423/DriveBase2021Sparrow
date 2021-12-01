package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

public class Driver {
    XBOXController joy;
    Drivetrain drive;

    private SendableChooser<Byte> driveType;
    private SendableChooser<Byte> noobMode;
    private final Byte arcade = 0;
    private final Byte tank = 1;
    private final Byte sparrow = 2;

    private final Byte pro = 0;
    private final Byte noob = 1;

    private double damp = 1.0;

    public Driver(int port) {
        joy = new XBOXController(port);
        drive = new Drivetrain();

        driveType = new SendableChooser<>();
        driveType.setDefaultOption("Arcade", arcade);
        driveType.addOption("Tank", tank);
        driveType.addOption("Sparrow", sparrow);
        SmartDashboard.putData("Drive Type", driveType);

        noobMode = new SendableChooser<>();
        noobMode.setDefaultOption("Pro", pro);
        noobMode.addOption("Noob", noob);
        SmartDashboard.putData("Drive Mode", noobMode);
    }

    public void runDriveControls() {
        //DRIVE CONrTROLS
        
        if (noobMode.getSelected().equals(pro)) {
            damp = 1.0;
        }
        if (noobMode.getSelected().equals(noob)) {
            damp = 0.6;
        }

        if(driveType.getSelected().equals(arcade)) {
            drive.arcadeDrive(joy.getRightYAxis() * damp, joy.getLeftTrigger() * damp, joy.getRightTrigger() * damp);
        } else if(driveType.getSelected().equals(tank)) {
            drive.tankDrive(joy.getLeftYAxis() * damp, joy.getRightYAxis() * damp);
        } else if(driveType.getSelected().equals(sparrow)) {
            if(joy.getLeftXAxis() < 0) {
                drive.arcadeDrive((joy.getLeftTrigger() - joy.getRightTrigger()) * damp, -joy.getLeftXAxis() * damp, 0.0);
            } else {
                drive.arcadeDrive((joy.getLeftTrigger() - joy.getRightTrigger()) * damp, 0.0, joy.getLeftXAxis() * damp);
            } 
        } else {
            System.out.println("Error: No drive type chosen");
        }        
    }
}