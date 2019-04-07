class Button {
  int GPIOPin;

  Button (int p) {
    GPIOPin = p;
    GPIO.pinMode(GPIOPin, GPIO.INPUT_PULLDOWN);
  }

  // retorna o estado atual do botão
  // returns the button's current state
  int state() {
    return GPIO.digitalRead(GPIOPin);
  }
}
