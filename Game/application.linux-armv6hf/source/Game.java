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



// array that stores all of the LED objects
// array que guarda todos os objetos LED
LED[] leds;

// array that stores all of the Button objects
// array que guarda todos os objetos Button
Button[] buttons;

// array (ArrayList) that stores the player's keyboard keys that are being pressed
// array (ArrayList) que guarda as teclas do teclado do usuário que estão sendo pressionadas
ArrayList<String> keys;

// stores the index of the LED that's currently turned on during the bounce animation
// guarda o índice do LED que está ligado durante a animação de quique
int currentLED;

// the time, in milliseconds since the last update on the bounce animation
// o tempo, em milissegundos, desde a última atualização à animação de quique
int millisOffset;

// speed of the main animation (in milliseconds)
// velocidade da animação principal (em milissegundos) 
float speed;

// direction of the main animation (-1 makes the animation start going to the left while 1 makes it start to the right)
// direção da animação principal (-1 faz a animação começar indo para a esquerda, já 1 faz a a animação começar indo pra direita)
int direction;

// if it's true, than the player can catch the ball using it's keyboard or the push buttons
// se for verdadeira, então o usuário poderá pegar a bola usando o seu teclado ou os botões
boolean canCatch;

// score being displayed on screen
// pontuação que aparece na tela
float score;

// the value the score variable interpolates with
// o valor como qual a pontuação irá se interpolar
int goalScore;

// specifies how many times the LED needs to blink before the blink animation ends
// especifica quantas vezes o LED precisa piscar antes que a animação de pisque acabe
int blink;

// the time, in milliseconds since the last update on the blink animation
// o tempo, em milissegundos, desde a última atualização à animação de pisque
int blinkMillisOffset;

// speed of the blink animation
// velocidade da animação de pisque
float blinkSpeed;

// store the player's lives
// guardam as vidas do usuário
int lives;
int newLives;

// establishes a limit for how many lives the player can have
// establece um limite na quantidade de vidas que o usuário pode ter
int lifeLimit;

// specifies how many times the life LED needs to blink before the lives blink animation ends
// especifica quantas vezes o LED da vida precisa piscar antes que a animação de pisque das vidas acabe
int blinkLives;

// the time, in milliseconds since the last update on the lives blink animation
// o tempo, em milissegundos, desde a última atualização à animação de pisque das vidas
int blinkLivesMillisOffset;

// speed of the lives blink animation
// velocidade da animação de pisque das vidas
float blinkLivesSpeed;

public void setup() {
  // the sketch will run in a 900x400 resolution
  // o sketch vai rodar em uma resolução de 900x400
  

  // here's where you can add, edit or remove LEDs from the leds array
  // the first parameter specifies the GPIO pin the LED is connected to, the second one specifies the value of it and the third one specifies how many lives it will give the player

  // aqui é onde você pode adicionar, editar ou remover os LEDs do array leds
  // o primeiro parâmetro especifica o pino GPIO no qual o LED está conectado, o segundo representa o valor que o ele vale e o terceiro especifica quantas vidas ele dará ao usuário

  leds = new LED[7];

  leds[0] = new LED(17, 0, -1);
  leds[1] = new LED(18, 0, -1);
  leds[2] = new LED(27, 10, 0);
  leds[3] = new LED(22, 50, 1);
  leds[4] = new LED(23, 10, 0);
  leds[5] = new LED(24, 0, -1);
  leds[6] = new LED(25, 0, -1);

  // here's where can add, edit or remove buttons from the buttons array
  // the first and only parameter specifies the GPIO pin the button is connected to

  // aqui é onde você pode adicionar, editar ou remover botões do array buttons
  // o primeiro e único parâmetro especifica o pino GPIO onde o botão está conectado 
  buttons = new Button[2];

  buttons[0] = new Button(4);
  buttons[1] = new Button(5);

  // keys array (ArrayList) is initialized
  // array (ArrayList) keys é inicializado

  keys = new ArrayList<String>();

  // all LEDs are turned off
  // todos os LEDs são desligados
  for (int i = 0; i < leds.length; i++)
    leds[i].off();

  // starting position is selected randomly
  // posição inicial selecionada aleatoriamente
  currentLED = round(random(leds.length-1));

  // starting LED is turned on
  // LED inicial é ligado
  leds[currentLED].on();

  speed = 1000/2.0f;

  // direction is randomly set. the extra code avoids the program to use an invalid index on the leds array
  // direção definida aleatoriamente. o código extra impede que o programa use um índice inválido no array leds
  direction = currentLED == 0 ? 1 : currentLED == leds.length-1 ? -1 : random(1) < 0.5f ? 1 : -1;

  canCatch = true;

  blinkSpeed = 500;

  // the player starts with 3 lives
  // o jogador começa com 3 vidas
  lives = 3;
  newLives = lives;

  // the lives limit is set to the amount of lives the player starts with
  // o limite de vidas é definida para a quantidade de vidas com a qual o jogador começa 
  lifeLimit = lives;

  // the lives blink animation is updated every half second
  // a animação de pisque das vidas é atualizada a cada meio segundo
  blinkLivesSpeed = 500/2.0f;

  // this function makes the LEDs turn off if the application gets closed
  // esta função faz com que todos os LEDs desliguem ao fechar do programa
  prepareExitHandler();

  // the mouse cursor won't be displayed
  // o cursor do mouse não será exibido
  noCursor();
}

public void draw() {
  background(0);

  if (lives > 0 || blink+1 > 0 || blinkLives+1 > 0) {
    if (blink+1 > 0) {
      if (millis()-blinkMillisOffset > blinkSpeed) {
        if (leds[currentLED].state() == 1) {
          leds[currentLED].off();
        } else {
          leds[currentLED].on();
          blink-= 1;
        }

        blinkMillisOffset = millis();
      }
    } else if (blinkLives+1 > 0) {
      int blinkIndex = newLives-max(leds[currentLED].livesChange, 0);

      if (lives != newLives) {
        for (int i = 0; i < leds.length; i++) {
          if (i < blinkIndex)
            leds[i].on();
          else
            leds[i].off();
        }

        lives = newLives;
      }

      if (millis()-blinkLivesMillisOffset > blinkLivesSpeed) {
        if (leds[blinkIndex].state() == 1)
          leds[blinkIndex].off();
        else {
          leds[blinkIndex].on();
          blinkLives--;
        }

        blinkLivesMillisOffset = millis();

        if (blinkLives+1 == 0) {
          for (int i = 0; i < leds.length; i++)
            leds[i].off();
        }
      }
    } else {
      if (millis()-millisOffset > speed) {
        leds[currentLED].off();
        currentLED+= direction;
        leds[currentLED].on();

        if (currentLED == 0 || currentLED == leds.length-1)
          direction*= -1;

        millisOffset = millis();
      }

      boolean noButtonsBeingPressed = true;
      for (int i = 0; i < buttons.length; i++)
        noButtonsBeingPressed = noButtonsBeingPressed && buttons[i].state() == 0;

      if (keys.size() <= 2 && noButtonsBeingPressed)
        canCatch = true;
    }

    boolean allButtonsBeingPressed = true;
    for (int i = 0; i < buttons.length; i++)
      allButtonsBeingPressed = allButtonsBeingPressed && buttons[i].state() == 1;

    if (canCatch && (allButtonsBeingPressed || keys.size() >= 2)) {
      blink = 3;
      goalScore+= leds[currentLED].value;

      speed/= 1.1f;

      if ((leds[currentLED].livesChange == 1 && lives < lifeLimit) || leds[currentLED].livesChange == -1) {
        newLives+= leds[currentLED].livesChange;

        if (lives < leds.length)
          blinkLives = 3;

        if (newLives == 0)
          for (int i = 0; i < leds.length; i++)
            leds[i].exit();
      }

      canCatch = false;
    }

    fill(255);
    textAlign(LEFT, TOP);
    textSize(1);
    textSize(min(width*4/5.0f/textWidth(str(round(score))), height/2.0f));
    text(round(score), width/5.0f, height/8.0f-textDescent()/2.0f);

    score = lerp(score, goalScore, 0.2f);

    float size = min(width/10.0f, height/10.0f);
    fill(255, 0, 0);
    noStroke();
    for (int l = 0; l < lifeLimit; l++)
      if (((blinkLives+1 <= 0 || blink+1 > 0) && l < lives) || (blinkLives+1 > 0 && blink+1 <= 0 && (l < lives-max(leds[currentLED].livesChange, 0) || leds[l].state() == 1)))
        ellipse(width*7/32.0f+size/2.0f+l*size*1.2f, height*7/8.0f-textDescent()/2.0f-size/2, size, size);
  } else {
    fill(255);
    textAlign(CENTER, CENTER);
    textSize(1);
    float size = min(width*4/5.0f/textWidth("GAME OVER"), height/2.0f);
    float size2 = min(width/5.0f/textWidth(str(round(score))), height/5.0f);

    textSize(size);
    text("GAME OVER", width/2.0f, height/2.0f-textDescent()/2.0f-size2/2.0f);

    textSize(size2);
    text(round(score), width/2.0f, height/2.0f-textDescent()/2.0f+size/2.0f);
  }
}

public void keyPressed() {
  String addStr = key == CODED ? str(keyCode) : Character.toString(key);
  for (String str : keys)
    if (addStr.equals(str)) {
      addStr = null;
      break;
    }

  if (addStr != null)
    keys.add(addStr);
}

public void keyReleased() {
  if (lives > 0) {
    String remStr = key == CODED ? str(keyCode) : Character.toString(key);
    keys.remove(remStr);
  } else if (canCatch)
    setup();
}

private void prepareExitHandler () {
  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    public void run () {
      for (int i = 0; i < leds.length; i++)
      leds[i].exit();
    }
  }
  ));
}
class Button {
  int GPIOPin;

  Button (int p) {
    GPIOPin = p;
    GPIO.pinMode(GPIOPin, GPIO.INPUT);
  }

  // retorna o estado atual do botão
  // returns the button's current state
  public int state() {
    return GPIO.digitalRead(GPIOPin);
  }
}
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
  public void on() {
    GPIO.digitalWrite(GPIOPin, GPIO.HIGH);
  }

  // turns off the LED
  // desliga o LED
  public void off() {
    GPIO.digitalWrite(GPIOPin, GPIO.LOW);
  }

  // returns the LED's current state
  // retorna o estado atual do LED
  public int state() {
    GPIO.pinMode(GPIOPin, GPIO.INPUT);
    int state = GPIO.digitalRead(GPIOPin);
    GPIO.pinMode(GPIOPin, GPIO.OUTPUT);
    return state;
  }

  // removes the LED from the GPIO object
  // remove o LED do objeto GPIO
  public void exit() {
    GPIO.releasePin(GPIOPin);
  }
}
  public void settings() {  size(900, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
