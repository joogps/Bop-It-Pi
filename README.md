<div id="page">

# Bop It! Pi
# [[en](#en)-[pt](#pt)]

<div id="en">

## English

### Index
* [The game](#game)
* [How to play it](#playit)
* [What you will need](#materials)
* [How to build it](#buildit)

<div id="game"> 

### The game
Bop It! Pi is a game made for the [Raspberry Pi](https://www.raspberrypi.org/), built on the [Processing Programming Language](https://processing.org/) and that makes use of the board's [General Purpose Input and Output (GPIO)](https://en.wikipedia.org/wiki/General-purpose_input/output) pins. It was inspired by [Bop It! Smash](https://en.wikipedia.org/wiki/Bop_It#Bop-It!_Smash), a toy made by [Hasbro](https://en.wikipedia.org/wiki/Hasbro).

</div>

<div id="playit">

### How to play it
The game consists on trying to get the greatest score you can without losing your lives on doing it. The LEDs will flash like a ball bouncing from side to side. You need to press two keys on your keyboard or press both of the connected push buttons to catch it. The yellow and red LEDs make you lose a heart, while the blue LED (the one in the center) recovers one. The score you get for each catch also depends on the color of the LED where the "ball" stops on. Ideas for a new game mechanic? Questions? You can report them to me [here](https://github.com/joogps/Bop-It-Pi/issues)!

</div>

<div id="materials">

### What you will need: 
- A Raspberry Pi of any model (the one shown in the tutorial will be the Raspberry Pi Model 2 v1.1);
- A double-sided breadboard*;
- 2 red LEDs**;
- 2 yellow LEDs**;
- 2 green LEDs**;
- 1 blue LED**;
- 7 330ohm resistors;
- 11 male to female jumper wires (3 of them are optional);
- 9 male to male jumper wires (2 of them are optional);
- The Processing code available [here](https://github.com/joogps/Bop-It-Pi/tree/master/Game);
- 2 push buttons (optional).

__\*__ You can manage to build the circuit on a single-sided breadboard as well, but the explanation will be based on a double-sided one. <br>
__\*\*__ The LEDs don't need to necessarily be red, green, yellow and blue. The only thing that matters is wether not you arrange them properly. You can also edit the code to add more LEDs to the project, remove some of them if the ones you have aren't enough or maybe even edit their conections with the GPIO interface.

</div>

<div id="buildit">

### How to build it:
_Circuit images made with [Fritzing](http://fritzing.org/home/). You can click them to see them in full screen or you can see all of the tutorial files [here](https://github.com/joogps/Bop-It-Pi/tree/master/Circuit)._

<table>
	<tr> <td> <b>1.</b> Be sure your Raspberry Pi is working, turn it off and grab your breadboard. </td> <td> <a href="Circuit/Main/Step1.png"><img src="Circuit/Main/Step1.png" alt="Explanatory image for step 1"></img></a> </td> </tr>
	<tr> <td> <b>2.</b> Simetrically place the LEDs on the bottom side of your breadboard with their anode legs (the short ones) to the right. </td> <td> <a href="Circuit/Main/Step2.png"><img src="Circuit/Main/Step2.png" alt="Explanatory image for step 2"></img></a> </td> </tr>
	<tr> <td> <b>3.</b> Using a male to female jumper wire, make a connection between any ground pin on your Raspberry Pi and the negative (blue) column on your breadboard. </td> <td> <a href="Circuit/Main/Step2.png"><img src="Circuit/Main/Step3.png" alt="Explanatory image for step 3"></img></a> </td> </tr>
	<tr> <td> <b>4.</b> Use male to male wires to connect the anode legs (the ones on the right) of the LEDs to the negative column of the breadboard. </td> <td> <a href="Circuit/Main/Step4.png"><img src="Circuit/Main/Step4.png" alt="Explanatory image for step 4"></img></a> </td> </tr>
	<tr> <td> <b>6.</b> Use all of the 7 resistors to connect the cathode legs (the ones on the left) of the LEDs to any hole on the line on top of the one where your LED cathode is connected to on your breadboard. </td> <td> <a href="Circuit/Main/Step5.png"><img src="Circuit/Main/Step5.png" alt="Explanatory image for step 5"></img></a> </td> </tr>
	<tr> <td> <b>5.</b> On the same lines, connect the resistors to your Raspberry Pi in the following order (go from the extreme left resistor to the extreme right one): 
		<ul>
			<li>GPIO17 (pin 11)</li>
			<li>GPIO18 (pin 12)</li>
			<li>GPIO27 (pin 13)</li>
			<li>GPIO22 (pin 15)</li>
			<li>GPIO23 (pin 16)</li>
			<li>GPIO24 (pin 18)</li>
			<li>GPIO25 (pin 22)</li>
		</ul> </td> <td> <a href="Circuit/Main/Step6.png"><img src="Circuit/Main/Step6.png" alt="Explanatory image for step 6"></img></a> </td> </tr>
	<tr> <td> <b>7.</b>  Once you have the Processing code on your computer, make sure you've <a href="https://pi.processing.org/download/">installed Processing for Pi</a> and run the files for the game itself. Alternatively, if you don't want to edit the code anytime soon, you can run the exported application available <a href="https://github.com/joogps/Bop-It-Pi/releases/tag/v1.0">here</a> without needing to install Processing itself. </td> <td> <a href="Circuit/Main/Step7.png"><img src="Circuit/Main/Step7.png" alt="Processing for Pi download page"></img></a> </td> </tr>
	<tr> <td> <b>8.</b> It's done! Congratulations, you did it! I hope you enjoy it a lot! Feel free to <a href="https://github.com/joogps/Bop-It-Pi/issues/new">make a question</a> and to help me develop this project even more! If you need help playing the game, check out the <a href="#playit">how to play section</a>! </td> <td> <a href="Circuit/Main/Step8.jpg"><img src="Circuit/Main/Step8.jpg" alt="Image of a physically built circuit"></img></a> </td></tr>
</table>

#### Placing the buttons (optional)
If you want to, you can use push buttons instead of keys to play the game. Below is a tutorial showing you how to put them on your circuit.

<table> 
	<tr> <td> <b>1.</b> Grab your circuit and turn off your Raspberry Pi </td> <td> <a href="Circuit/Buttons/Step1.png"><img src="Circuit/Buttons/Step1.png" alt="Explanatory image for step 1"></img></a> </td> </tr>
	<tr> <td> <b>2.</b> Simetrically place the buttons on your breadboard. Make sure they cross the frontier between the sides of your board. </td> <td> <a href="Circuit/Buttons/Step2.png"><img src="Circuit/Buttons/Step2.png" alt="Explanatory image for step 2"></img></a> </td> </tr>
	<tr> <td> <b>3.</b> Using a male to female jumper wire, make a connection between any 5V pin on your Raspberry Pi and the positive (red) column of your breadboard. </td> <td> <a href="Circuit/Buttons/Step3.png"><img src="Circuit/Buttons/Step3.png" alt="Explanatory image for step 3"></img></a> </td> </tr>
	<tr> <td> <b>4.</b> Connect one of the legs of each button with the positive column of your breadboard using male to male jumper wires. </td> <td> <a href="Circuit/Buttons/Step4.png"><img src="Circuit/Buttons/Step4.png" alt="Explanatory image for step 4"></img></a> </td> </tr>
	<tr> <td> <b>5.</b> Connect the remaining leg of each button to the Raspberry Pi on the respective pins:
		<ul>
			<li>GPIO4 (pin 7)</li>
			<li>GPIO5 (pin 29)</li>
		</ul> 
	</td> <td> <a href="Circuit/Buttons/Step5.png"><img src="Circuit/Buttons/Step5.png" alt="Explanatory image for step 5"></img></a> </td> </tr>
	<tr> <td> <b>6.</b> It's done! Now you can play Bop-It-Pi using either the keys on your keyboard or the push buttons you've just placed!</td> <td> <a href="Circuit/Buttons/Step6.jpg"><img src="Circuit/Buttons/Step6.jpg" alt="Image of a physically built circuit (with buttons)"></img></a> </td> </tr>
</table>

</div>

</div>

<div id="pt">

## Português

### Índice
* [O jogo](#pt-game)
* [Como jogar](#pt-playit)
* [O que você vai precisar](#pt-materials)
* [Como montar](#pt-buildit)

<div id="pt-game">

### O jogo
Bop-It! Pi é um jogo feito para o [Raspberry Pi](https://www.raspberrypi.org/), programado em [Processing]() e que faz uso dos pinos [GPIO (General Purpose Input and Output)](https://pt.wikipedia.org/wiki/General_Purpose_Input/Output) da placa. Ele foi inpirado no [Bop It! Smash](https://en.wikipedia.org/wiki/Bop_It#Bop-It!_Smash), um brinquedo feito pela [Hasbro](https://pt.wikipedia.org/wiki/Hasbro).

</div>

<div id="pt-playit">

### Como jogar
O jogo consiste em conseguir a melhor pontuação que você puder sem perder nenhuma vida ao fazê-lo. Os LEDs irão piscar como uma bola quicando de um lado para o outro. Você deve pressionar duas teclas simultaneamente em seu teclado para pegá-la. Os LEDs vermelhos e amarelos te fazem perder um coração, enquanto o LED azul (o do centro) te faz recuperar um (a não ser que você esteja com todos os 3). A pontuação que você consegue a cada rodada também depende do LED onde a "bola" para. Ideias para uma nova mecânica de jogo? Dúvidas? Me mostre-as [aqui](https://github.com/joogps/Bop-It-Pi/issues)!

</div>

<div id="pt-materials">

### O que você vai precisar:
- Um Raspberry Pi de qualquer modelo (o mostrado no tutorial será o Raspberry Pi Model 2 v1.1);
- Uma protoboard com dois lados*;
- 2 LEDs vermelhos**;
- 2 LEDs amarelos**;
- 2 LEDs verdes**;
- 1 LED azul**;
- 7 resistores de 330ohm;
- 11 cabos jumper macho/fêmea (3 deles são opcionais);
- 9 cabos jumper macho/macho (2 deles são opcionais);
- O código em Processing disponível [aqui](https://github.com/joogps/Bop-It-Pi/tree/master/Game);
- 2 push buttons (opcional)

__\*__ Você pode, sim, montar o circuito em uma placa com apenas um lado, mas a explicação será baseada em uma de dois. <br>
__\*\*__ Os LEDs não precisam, necessariamente, ser vermelhos, verdes, amarelos e azuis. A única coisa que importa é se você os organiza corretamente. Você ainda pode editar o código para adiconar mais LEDs, remvover alguns se você não tem o suficiente ou até talvez editar as suas conexões com a interface GPIO.

</div>

<div id="pt-buildit">

### Como montar:
_Imagens de circuito feitas com o [Fritzing](http://fritzing.org/home/). Você pode clicá-las para vê-las em tela cheia ou ver todos os arquivos de tutorial [aqui](https://github.com/joogps/Bop-It-Pi/tree/master/Circuit)._

<table>
	<tr> <td> <b>1.</b> Se certifique de que o seu Raspberry Pi está funcionando, desligue-o e pegue a sua protoboard. </td> <td><a href="Circuit/Main/Step1.png"><img src="Circuit/Main/Step1.png" alt="Explanatory image for step 1"></img></a> </td> </tr>
	<tr> <td> <b>2.</b> Simetricamente posicionar os LEDs na parte inferior de sua protoboard com as 'pernas' de anodo (as mais curtas) em sua direita </td> <td><a href="Circuit/Main/Step2.png"><img src="Circuit/Main/Step2.png" alt="Imagem explicatória do passo 2"></img></a> </td> </tr>
	<tr> <td> <b>3.</b> Usando um cabo jumper macho-fêmea, faça uma conexão entre qualquer pino terra (GND) em seu Raspberry Pi e a coluna negativa (azul) na protoboard. </td> <td><a href="Circuit/Main/Step3.png"><img src="Circuit/Main/Step3.png" alt="Imagem explicatória do passo 3"></img></a> </td> </tr>
	<tr> <td> <b>4.</b> Use cabos macho-macho para conectar as 'pernas' de anodo (as da direita) dos LEDs com a coluna negativa de sue Protoboard. </td> <td><a href="Circuit/Main/Step4.png"><img src="Circuit/Main/Step4.png" alt="Imagem explicatória do passo 4"></img></a> </td> </tr>
	<tr> <td> <b>5.</b> Use todos os 7 resistores para conectar as pernas de catodo (as da direita) dos LEDs com algum buraco da linha acima da qual o catodo de seu LED está posicionado em sua protoboard. </td> <td><a href="Circuit/Main/Step5.png"><img src="Circuit/Main/Step5.png" alt="Imagem explicatória do passo 5"></img></a> </td> </tr>
	<tr> <td> <b>6.</b> Conecte os resistores ao seu Raspberry Pi nos seguintes pinos (vá do resistor da extrema esquerda para o da extrema direita): 
		<ul>
			<li>GPIO17 (pino 11)</li>
			<li>GPIO18 (pino 12)</li>
			<li>GPIO27 (pino 13)</li>
			<li>GPIO22 (pino 15)</li>
			<li>GPIO23 (pino 16)</li>
			<li>GPIO24 (pino 18)</li>
			<li>GPIO25 (pino 22)</li>
		</ul> </td> <td><a href="Circuit/Main/Step6.png"><img src="Circuit/Main/Step6.png" alt="Imagem explicatória do passo 6"></img></a> </td> </tr>
	<tr> <td> <b>7.</b> Assim que você tiver o código em Processing no seu computador, certfique-se de que você tem o <a href="https://pi.processing.org/download/">Processing for Pi instalado</a> e execute os arquivos do jogo. Caso você não queira editar o código provido pelo repositório posteriormente, você pode simplesmente executar a aplicação exportada disponível <a href="https://github.com/joogps/Bop-It-Pi/releases/tag/v1.0">aqui</a> sem ter que instalar o Processing em si. </td> <td><a href="Circuit/Main/Step7.png"><img src="Circuit/Main/Step7.png" alt="Página de download do Processing for Pi"></img></a> </td> </tr>
	<tr> <td> <b>8.</b> Está pronto! Parabéns, você conseguiu! Espero que você aproveite muito! Sinta-se livre para <a href="https://github.com/joogps/Bop-It-Pi/issues/new">fazer perguntas ou reportar problemas</a> e me ajudar a desenvolver ainda mais esse projeto! Se você precisa de ajuda sobre a jogabilidade, visite a <a href="#pt-playit">seção como jogar</a>! </td> <td> <a href="Circuit/Main/Step8.jpg"><img src="Circuit/Main/Step8.jpg" alt="Imagem de um circuito montado fisicamente"></img></a> </td> </tr>
</table>

#### Colocando os botões (opcional)
Se você quiser, você pode usar botões ao invés de teclas para jogar o jogo.
Abaixo está um tutorial mostrando como colocá-los no seu circuito.

<table> 
	<tr> <td> <b>1.</b> Pegue o seu circuito e desligue o seu Raspberry Pi. </td> <td> <a href="Circuit/Buttons/Step1.png"><img src="Circuit/Buttons/Step1.png" alt="Imagem explicatória do passo 6"></img></a> </td> </tr>
	<tr> <td> <b>2.</b> Posicione os botões simetricamente em sua protoboard. Certifique-se de que eles atravessam a fronteira dos lados de sua placa. </td> <td> <a href="Circuit/Buttons/Step2.png"><img src="Circuit/Buttons/Step2.png" alt="Imagem explicatória do passo 2"></img></a> </td> </tr>
	<tr> <td> <b>3.</b> Usando um cabo jumper macho-fêmea, faça uma conexão entre qualquer pino 5V do seu Raspberry Pi e a coluna positiva (vermelha) da sua protoboard.</td> <td> <a href="Circuit/Buttons/Step3.png"><img src="Circuit/Buttons/Step3.png" alt="Imagem explicatória do passo 3"></img></a> </td> </tr>
	<tr> <td> <b>4.</b> Conecte uma das pernas de cada botão com a coluna positiva da protoboard usando cabos jumper macho-macho. </td> <td> <a href="Circuit/Buttons/Step4.png"><img src="Circuit/Buttons/Step4.png" alt="Imagem explicatória do passo 4"></img></a> </td> </tr>
	<tr> <td> <b>5.</b> Conecte a perna restante de cada botão no Raspberry Pi nos pinos respectivos:
		<ul>
			<li>GPIO4 (pino 7)</li>
			<li>GPIO5 (pino 29)</li>
		</ul> 
	</td> <td> <a href="Circuit/Buttons/Step5.png"><img src="Circuit/Buttons/Step5.png" alt="Imagem explicatória do passo 5"></img></a> </td> </tr>
	<tr> <td> <b>6.</b> Está pronto! Agora você pode jogar Bop-It-Pi tanto usando as teclas do seu teclado quanto os botões que você acabou de colocar! </td> <td> <a href="Circuit/Buttons/Step6.jpg"><img src="Circuit/Buttons/Step6.jpg" alt="Image of a physically built circuit (with buttons)"></img></a> </td> </tr>
</table>

</div>

</div>

## [Back to top / Voltar ao início](#page)
## [View raw](https://raw.githubusercontent.com/joogps/Bop-It-Pi/master/README.md)
</div>