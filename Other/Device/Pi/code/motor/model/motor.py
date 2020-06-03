#!/bin/python
# coding: utf-8

import RPi.GPIO as GPIO

class Motor:
    def __init__(self, pin_1, pin_2):
        self.pin_1 = pin_1
        self.pin_2 = pin_2
        self.setup()

    def setup(self):
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        GPIO.setup(self.pin_1, GPIO.OUT)
        GPIO.setup(self.pin_2, GPIO.OUT)
        
    def set_step(self, s1, s2):
        GPIO.output(self.pin_1, s1)
        GPIO.output(self.pin_2, s2)

    def forward(self):
        self.set_step(1, 0)

    def backward(self):
        self.set_step(0, 1)

    def stop(self):
        self.set_step(0, 0)

