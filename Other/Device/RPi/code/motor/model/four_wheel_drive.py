#!/bin/python3

from model.motor import Motor

class FourWheelDrive:
    def __init__(self, pin_1, pin_2, pin_3, pin_4, pin_5, pin_6, pin_7, pin_8):
        self.motor_f_r = Motor(pin_1, pin_2)
        self.motor_f_l = Motor(pin_3, pin_4)
        self.motor_b_l = Motor(pin_5, pin_6)
        self.motor_b_r = Motor(pin_7, pin_8)

    def forward(self):
        print('forward')
        self.motor_f_r.forward()
        self.motor_f_l.forward()
        self.motor_b_l.forward()
        self.motor_b_r.forward()

    def backward(self):
        print('backward')
        self.motor_f_r.backward()
        self.motor_f_l.backward()
        self.motor_b_l.backward()
        self.motor_b_r.backward()

    def turn_left(self):
        print('turn left')
        self.motor_f_r.forward()
        self.motor_f_l.backward()
        self.motor_b_l.backward()
        self.motor_b_r.forward()
        
    def turn_right(self):
        print('turn right')
        self.motor_f_r.backward()
        self.motor_f_l.forward()
        self.motor_b_l.forward()
        self.motor_b_r.backward()
        
    def stop(self):
        self.motor_f_r.stop()
        self.motor_f_l.stop()
        self.motor_b_l.stop()
        self.motor_b_r.stop()
