#!/bin/python3

from model.four_wheel_drive import FourWheelDrive 
import time

if __name__ == '__main__':
    drive = FourWheelDrive(11, 12, 13, 15, 36, 37, 38, 40)
    drive.stop()
    while True:
        drive.forward()
        time.sleep(10)
        drive.backward()
        time.sleep(10)
        drive.turn_left()
        time.sleep(10)
        drive.turn_right()
        time.sleep(10)
