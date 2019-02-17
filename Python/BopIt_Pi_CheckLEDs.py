import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)

pins = [17, 18, 27, 22, 23, 24, 25]

try:
    while True:
        for i in range(0, len(pins)):
            GPIO.setup(pins[i], GPIO.OUT)
            GPIO.output(pins[i], GPIO.HIGH)

except KeyboardInterrupt:
    GPIO.cleanup()
