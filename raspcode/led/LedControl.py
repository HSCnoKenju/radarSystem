import sys
import RPi.GPIO as GPIO

MIN_DISTANCE = 40
LED_PIN = 25

GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN,GPIO.OUT)

try:
    
    for line in sys.stdin:        
        sys.stdout.write(line)
        v = float(line)
        
        if v <= MIN_DISTANCE :
            GPIO.output(LED_PIN,GPIO.HIGH)
        else:
            GPIO.output(LED_PIN,GPIO.LOW)

except KeyboardInterrupt:
    GPIO.cleanup()

finally:
    GPIO.cleanup()

