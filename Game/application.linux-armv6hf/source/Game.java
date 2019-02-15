import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.io.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Game extends PApplet {



LED[] leds;

int currentLED;
int millisOffset;

//speed of the main animation (in milliseconds)
float speed;

//direction of the speed animation (-1 makes the animation start going to the left while 1 makes it start to the right)
int direction;

float score;
int goalScore;

int blink;
int blinkMillisOffset;
int blinkSpeed;

public void setup() {
  

  leds = new LED[7];

  //the first parameter specifies the GPIO pin you're connecting the LED to. the right one specifies the value of it.
  leds[0] = new LED(17, 0);
  leds[1] = new LED(18, 0);
  leds[2] = new LED(27, 1);
  leds[3] = new LED(22, 5);
  leds[4] = new LED(23, 1);
  leds[5] = new LED(24, 0);
  leds[6] = new LED(25, 0);

  noCursor();

  for (int i = 0; i < leds.length; i++)
    leds[i].off();

  currentLED = round(random(leds.length-1));
  speed = 1000/5.0f;
  direction = currentLED == 0 ? 1 : currentLED == leds.length-1 ? -1 : random(1) < 0.5f ? 1 : -1;
  leds[currentLED].on();

  blinkSpeed = 500;

  prepareExitHandler();
}

public void draw() {
  background(0);
  textAlign(CENTER, CENTER);
  textSize(height/2);
  text(round(score), width/2, height/2-textDescent()/2);

  score = lerp(score, goalScore, 0.2f);

  if (blink > 0) {
    if (millis()-blinkMillisOffset > blinkSpeed) {
      if (leds[currentLED].state() == 1)
        leds[currentLED].off();
      else {
        leds[currentLED].on();
        blink-= 1;
      }

      blinkMillisOffset = millis();
    }
  } else if (millis()-millisOffset > speed) {
    leds[currentLED].off();
    currentLED+= direction;
    leds[currentLED].on();

    if (currentLED == 0 || currentLED == leds.length-1)
      direction*= -1;

    millisOffset = millis();
  }
}

public void keyPressed() {
  if (blink == 0) {
    blink = 4;
    goalScore+= leds[currentLED].value;
  }
}

private void prepareExitHandler () {
  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    public void run () {
      for (int i = 0; i < leds.length; i++)
        leds[i].off();
    }
  }
  ));
}
class LED {
  int GPIOPin; 
  int value;

  LED(int p, int v) {
    GPIOPin = p;
    value = v;

    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);
  }

  public void on() {
    GPIO.digitalWrite(GPIOPin, GPIO.HIGH);
  }

  public void off() {
    GPIO.digitalWrite(GPIOPin, GPIO.LOW);
  }

  public int state() {
    GPIO.pinMode(GPIOPin, GPIO.INPUT);
    int state = GPIO.digitalRead(GPIOPin);
    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);
    return state;
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
