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

void setup() {
  size(900, 400);

  // here's where you can add, edit or remove the GPIO pins to which the LEDs are connected
  // the first parameter specifies the GPIO pin the LED is connected to, while the right one specifies the value of it

  // aqui é onde você pode adicionar, editar ou remover os pinos GPIO nos quais os LEDs estão conectados
  // o primeiro parâmetro especifica o pino no qual o LED está conectado, já o segundo representa o valor que o ele vale

  leds = new LED[7];

  leds[0] = new LED(17, 0);
  leds[1] = new LED(18, 0);
  leds[2] = new LED(27, 10);
  leds[3] = new LED(22, 50);
  leds[4] = new LED(23, 10);
  leds[5] = new LED(24, 0);
  leds[6] = new LED(25, 0);

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
  speed = 1000/5;

  // direction is randomly set. the extra code avoids the program to use an invalid index on the leds array
  // direcao setada aleatoriamente. o código extra impede que o programa use um índice inválido no array leds
  direction = currentLED == 0 ? 1 : currentLED == leds.length-1 ? -1 : random(1) < 0.5 ? 1 : -1;

  // since the variable is set to true, the user will be able to catch the ball as soon as the program starts (it is also automatically set to true if the main animation is running and there are no keys being pressed)
  // já que a variável está setada para true, o usuário poderá pegar a bola assim que o programa começar (é também automaticamente setada para verdadeiro se a animação principal está sendo executada e nenhuma tecla está sendo pressionada)
  canCatch = true;

  // catch animation speed is set to 2 blinks per second
  // velocidade da animação de captura é setada para 2 pisques por segundo
  blinkSpeed = 500;

  // this function makes the LEDs turn off if the application gets closed
  // esta função faz com que todos os LEDs desliguem ao fechar do programa
  prepareExitHandler();


  noCursor();
}

void draw() {
  background(0);

  textAlign(CENTER, CENTER);
  textSize(height/2);
  text(round(score), width/2, height/2-textDescent()/2);

  score = lerp(score, goalScore, 0.2);

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
    blink = 4;
    goalScore+= leds[currentLED].value;

    speed*= 1.1;

    canCatch = false;
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
  String remStr = key == CODED ? str(keyCode) : Character.toString(key);
  keys.remove(remStr);
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
