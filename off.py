import RPi.GPIO as GPIO
import time 
from time import sleep
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

GPIO.setup(18,GPIO.OUT)
print "lights off"
GPIO.output(18,GPIO.LOW)
time.sleep(5)


