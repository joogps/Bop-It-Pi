class Button {
  int GPIOPin;

  Button (int p) {
    GPIOPin = p;
    GPIO.pinMode(GPIOPin, GPIO.INPUT);
  }

  int state() {
    return GPIO.digitalRead(GPIOPin);
  }
}
