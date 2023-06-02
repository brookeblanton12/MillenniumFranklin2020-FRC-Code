/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  UsbCamera upCamera;
  UsbCamera backCamera;
  VideoSink videoSink1;
  VideoSink videoSink2;

  public Camera() {
    // backCamera = CameraServer.getInstance().startAutomaticCapture("backCamera", 0);
    // backCamera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 30);
    // videoSink1 = CameraServer.getInstance().getVideo();
    // videoSink1.setSource(backCamera);
    // videoSink1.getProperty("compression").set(70);

    upCamera = CameraServer.getInstance().startAutomaticCapture("upCamera", 0);
    upCamera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 30);
    videoSink2 = CameraServer.getInstance().getVideo();
    videoSink2.setSource(upCamera);
    videoSink2.getProperty("compression").set(70);

  }
}
