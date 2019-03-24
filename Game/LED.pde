class LED {
  int GPIOPin;

  int value;
  int livesChange;

  LED(int p, int v, int l) {
    GPIOPin = p;
    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);

    value = v;
    livesChange = l;
  }

  // turns on the LED
  // liga o LED
  void on() {
    GPIO.digitalWrite(GPIOPin, GPIO.HIGH);
  }

  // turns off the LED
  // desliga o LED
  void off() {
    GPIO.digitalWrite(GPIOPin, GPIO.LOW);
  }

  // returns the LED's current state
  // retorna o estado atual do LED
  int state() {
    GPIO.pinMode(GPIOPin, GPIO.INPUT);
    int state = GPIO.digitalRead(GPIOPin);
    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);
    return state;
  }

  // removes the LED from the GPIO object
  // remove o LED do objeto GPIO
  void exit() {
    GPIO.releasePin(GPIOPin);
  }
}
