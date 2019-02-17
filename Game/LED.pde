class LED {
  int GPIOPin; 
  int value;

  LED(int p, int v) {
    GPIOPin = p;
    value = v;

    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);
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
