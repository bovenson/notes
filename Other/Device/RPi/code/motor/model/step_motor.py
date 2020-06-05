#!/bin/python3
# coding: utf-8

import RPi.GPIO as GPIO
import time

class StepMotor:
    def __init__(self, pin_1, pin_2, pin_3, pin_4, delay=0.005):
        self.pin_1 = pin_1
        self.pin_2 = pin_2
        self.pin_3 = pin_3
        self.pin_4 = pin_4
        self.delay = delay
        self.setup()

    def setup(self):
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        GPIO.setup(self.pin_1, GPIO.OUT)
        GPIO.setup(self.pin_2, GPIO.OUT)
        GPIO.setup(self.pin_3, GPIO.OUT)
        GPIO.setup(self.pin_4, GPIO.OUT)

    def set_step(self, w1, w2, w3, w4):
        GPIO.output(self.pin_1, w1)
        GPIO.output(self.pin_2, w2)
        GPIO.output(self.pin_3, w3)
        GPIO.output(self.pin_4, w4)

    def forward(self, steps=1):
        for _ in range(steps):
            self.set_step(1, 0, 0, 0)
            time.sleep(self.delay)
            self.set_step(0, 1, 0, 0)
            time.sleep(self.delay)
            self.set_step(0, 0, 1, 0)
            time.sleep(self.delay)
            self.set_step(0, 0, 0, 1)
            time.sleep(self.delay)

    def backward(self, steps=1):
        for _ in range(steps):
            self.set_step(0, 0, 0, 1)
            time.sleep(self.delay)
            self.set_step(0, 0, 1, 0)
            time.sleep(self.delay)
            self.set_step(0, 1, 0, 0)
            time.sleep(self.delay)
            self.set_step(1, 0, 0, 0)
            time.sleep(self.delay)


