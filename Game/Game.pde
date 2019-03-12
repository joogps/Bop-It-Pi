import processing.io.*;

LED[] leds;

Button[] buttons;

ArrayList<String> keys;

int currentLED;
int millisOffset;

// speed of the main animation (in milliseconds)
// velocidade da animação principal (em milissegundos) 
float speed;

// direction of the main animation (-1 makes the animation start going to the left while 1 makes it start to the right)
// direção da animação principal (-1 faz a animação começar indo para a esquerda, já 1 faz a a animação começar indo pra direita)
int direction;

boolean canCatch;

float score;
int goalScore;

int blink;
int blinkMillisOffset;

// speed of the catch animation
// velocidade da animação de captura
int blinkSpeed;

int lives;
int blinklives;
int blinkLivesMillisOffset;
int blinkLivesSpeed;

void setup() {
  size(900, 400);

  // here's where you can add, edit or remove the GPIO pins to which the LEDs are connected
  // the first parameter specifies the GPIO pin the LED is connected to, while the right one specifies the value of it

  // aqui é onde você pode adicionar, editar ou remover os pinos GPIO nos quais os LEDs estão conectados
  // o primeiro parâmetro especifica o pino no qual o LED está conectado, já o segundo representa o valor que o ele vale

  leds = new LED[7];

  leds[0] = new LED(17, 0, -1);
  leds[1] = new LED(18, 0, -1);
  leds[2] = new LED(27, 10, 0);
  leds[3] = new LED(22, 50, 1);
  leds[4] = new LED(23, 10, 0);
  leds[5] = new LED(24, 0, -1);
  leds[6] = new LED(25, 0, -1);

  buttons = new Button[2];
  buttons[0] = new Button(4);
  buttons[1] = new Button(5);

  keys = new ArrayList<String>();

  for (int i = 0; i < leds.length; i++)
    leds[i].off();

  // starting position is selected randomly
  // posição inicial selecionada aleatoriamente
  currentLED = round(random(leds.length-1));

  // starting LED is turned on
  // LED inicial é ligado
  leds[currentLED].on();

  // speed is set to 5 LEDs per second
  // velocidade é setada para 5 LEDs por segundo
  speed = 1000/5.0;

  // direction is randomly set. the extra code avoids the program to use an invalid index on the leds array
  // direcao setada aleatoriamente. o código extra impede que o programa use um índice inválido no array leds
  direction = currentLED == 0 ? 1 : currentLED == leds.length-1 ? -1 : random(1) < 0.5 ? 1 : -1;

  // since the variable is set to true, the user will be able to catch the ball as soon as the program starts (it is also automatically set to true if the main animation is running and there are no keys being pressed)
  // já que a variável está setada para true, o usuário poderá pegar a bola assim que o programa começar (é também automaticamente setada para verdadeiro se a animação principal está sendo executada e nenhuma tecla está sendo pressionada)
  canCatch = true;

  // catch animation speed is set to 2 blinks per second
  // velocidade da animação de captura é setada para 2 pisques por segundo
  blinkSpeed = 500;

  lives = 3;

  // this function makes the LEDs turn off if the application gets closed
  // esta função faz com que todos os LEDs desliguem ao fechar do programa
  prepareExitHandler();

  noCursor();
}

void draw() {
  background(0);

  if (lives > 0) {
    fill(255);
    textAlign(LEFT, TOP);
    textSize(1);
    textSize(min(width*4/5.0/textWidth(str(round(score))), height/2.0));
    text(round(score), width/5.0, height/8.0-textDescent()/2.0);

    score = lerp(score, goalScore, 0.2);

    float size = min(width/10.0, height/10.0);
    fill(255, 0, 0);
    noStroke();
    for (int l = 0; l < lives; l++)
      ellipse(width*7/32.0+size/2.0+l*size*1.2, height*7/8.0-textDescent()/2.0-size/2, size, size);

    if (blink > 0) {
      if (millis()-blinkMillisOffset > blinkSpeed) {
        if (leds[currentLED].state() == 1) {
          leds[currentLED].off();
        }
        else {
          if (leds[currentLED].state() == 1)
            leds[currentLED].on();
          blink-= 1;
        }

        blinkMillisOffset = millis();
      }
    } 
    else if (blinkLives > 0){
      if (millis()-blinkLivesMillisOffset > blinkLivesSpeed) {
        if (leds[lives+1].state() == 1)
          if(lives < lives.length)
            leds[lives+1].off();
          else {
            if(lives < lives.length)
              leds[lives+1].on();
            blink-= 1;
          }

          blinkMillisOffset = millis();
        }
      }
      else {
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
        blink = 4;
        goalScore+= leds[currentLED].value;

        speed*= 1.1;

        if ((leds[currentLED].livesChange == 1 && lives < 3) || leds[currentLED].livesChange == -1){
          lives+= leds[currentLED].livesChange;
          blinkLives = 3;

          for (int i = 0; i < leds.length; i++) {
            if(i < lives)
              leds[i].off();
            else
              leds[i].on();
          }
        }

        if (lives == 0)
          for (led of leds) {
            led.exit();
          }

          canCatch = false;
        }
      } else {
        fill(255);
        textAlign(CENTER, CENTER);
        textSize(1);
        textSize(min(width*4/5.0/textWidth("GAME OVER"), height/2.0));
        text("GAME OVER", width/2.0, height/2.0-textDescent()/2.0);
      }
    }

    void keyPressed() {
      String addStr = key == CODED ? str(keyCode) : Character.toString(key);
      for (String str : keys)
        if (addStr.equals(str)) {
          addStr = null;
          break;
        }

        if (addStr != null)
          keys.add(addStr);
      }

      void keyReleased() {
        if(lives > 0) {
          String remStr = key == CODED ? str(keyCode) : Character.toString(key);
          keys.remove(remStr);
        }
        else 
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