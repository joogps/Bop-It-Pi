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

  void on() {
    GPIO.digitalWrite(GPIOPin, GPIO.HIGH);
  }

  void off() {
    GPIO.digitalWrite(GPIOPin, GPIO.LOW);
  }

  int state() {
    GPIO.pinMode(GPIOPin, GPIO.INPUT);
    int state = GPIO.digitalRead(GPIOPin);
    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);
    return state;
  }

  void exit() {
    GPIO.releasePin(GPIOPin);
  }
}
