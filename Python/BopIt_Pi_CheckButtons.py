import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)

pins = [4, 5]

for i in range(0, len(pins)):
    GPIO.setup(pins[i], GPIO.IN)

while True:
    for i in range(0, len(pins)):
        print(GPIO.input(pins[i]))
