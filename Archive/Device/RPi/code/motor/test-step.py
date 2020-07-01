#!/bin/python3
# coding: utf-8

from model.step_motor import StepMotor

if __name__ == '__main__':
    motor = StepMotor(29, 31, 33, 35)
    while True:
        for _ in range(100):
            motor.forward()
        for _ in range(100):
            motor.backward()
