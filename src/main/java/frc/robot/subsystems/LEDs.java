/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {
  public static enum LED_MODE {
    Off, Rainbow, Plaid
  }

  private enum LED_COLOR {
    Green, Blue, Yellow
  }
  
  private AddressableLED _led;
  private AddressableLEDBuffer _ledBuffer;
  private int _rainbowFirstPixelHue;
  private int _numLEDs = 102;
  private double _lastTime = Timer.getFPGATimestamp();

  private LED_MODE _mode = LED_MODE.Off;
  private int _index;
  private double _interval = 0.25;

  /**
   * Creates a new LEDs.
   */
  public LEDs(int numberLEDs, int port) {
    _numLEDs = numberLEDs;
    _led = new AddressableLED(port);
    // Length is expensive to set, so only set it once, then just update data
    _ledBuffer = new AddressableLEDBuffer(_numLEDs);
    _led.setLength(_ledBuffer.getLength());
    _led.setData(_ledBuffer);
    _led.start();

    for (var i = 0; i < _ledBuffer.getLength(); i++) {
      _ledBuffer.setRGB(i, 0, 0, 0);
    }

    _led.setData(_ledBuffer);
  }

  public void setMode(LED_MODE mode) {
    _mode = mode;
  }

  private void off() {
    SmartDashboard.putString("LED Mode", "off");
    for (var i = 0; i < _ledBuffer.getLength(); i++) {
      _ledBuffer.setRGB(i, 0, 0, 0);
    }
  }

  private void increment() {
    _index++;
    if (_index >= _ledBuffer.getLength()) {
      _index = 0;
    }
  }

  private void rainbow() {
    SmartDashboard.putString("LED Mode", "rainbow");
    // For every pixel
    for (var i = 0; i < _ledBuffer.getLength(); i++) {
      // Calculate the hue - hue is easier for rainbows because the color
      // shape is a circle so only one value needs to precess
      final var hue = (_rainbowFirstPixelHue + (i * 180 / _ledBuffer.getLength())) % 180;
      // Set the value
      _ledBuffer.setHSV(i, hue, 255, 128);
    }
    // Increase by to make the rainbow "move"
    _rainbowFirstPixelHue += 3;
    // Check bounds
    _rainbowFirstPixelHue %= 180;
  }

  private void plaid() {
    SmartDashboard.putString("LED Mode", "plaid");
    double currentTime = Timer.getFPGATimestamp();
    if ((currentTime - _lastTime) > _interval) {
      _lastTime = currentTime;
      for (int i = 0; i < _ledBuffer.getLength(); i++) {
        if ((i + _index) % 3 == 0) {
          _ledBuffer.setRGB(i, 0, 102, 255);
        } else if ((i + _index) % 3 == 1) {
          _ledBuffer.setRGB(i, 255, 255, 0);
        } else {
          _ledBuffer.setRGB(i, 46, 184, 46);
        }
      }

      increment();
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    switch(_mode) {
      case Rainbow: rainbow(); break;
      case Plaid: plaid(); break;
      default: off(); break;
    }
    _led.setData(_ledBuffer);
  }
}