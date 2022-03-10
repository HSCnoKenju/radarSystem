import sys
import RPi.GPIO as GPIO
import time

MIN_DISTANCE = 40
LED_PIN = 25

GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN,GPIO.OUT)

try:
    
    for line in sys.stdin:
        sys.stdout.write(line)
        v = float(line)
        if v <= MIN_DISTANCE :
            for i in range(1, 3):
                GPIO.output(LED_PIN,GPIO.HIGH)
                time.sleep(0.2)
                GPIO.output(LED_PIN,GPIO.LOW)
                time.sleep(0.2)

except KeyboardInterrupt:
    GPIO.cleanup()

finally:
    GPIO.cleanup()

