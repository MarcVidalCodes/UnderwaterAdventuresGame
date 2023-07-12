/*
Marc Vidal
June 13,2022
This program is a game in which the user has to move their turtle character while dodging jellyfish obstacles, more info is in the "instructions" page in game
*/

import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.event.MouseMotionListener;

//for program to work run at (1920x1080)

public class CPTUnderWaterAdventures extends Applet implements MouseListener, MouseMotionListener, KeyListener
{


    //variables used in the program
    int inGame = 0, mode = 0, startY, score = 0, mouseX, mouseY, level = 0, timer, backgroundX, backgroundY, titleX, titleY, turtleX, turtleY, lives;
    boolean gameOver = false;

    //creating variables for the images
    Image jellyfishImg, turtleImg, backgroundImg, titleImg, bigTurtleImg, bigJellyfishImg, speechBubbleImg, exitTurtleImg, redfaceImg, yellowfaceImg, greenfaceImg;

    //array variables for random speed, x coordinate, and  ycoordinates for the jellyfish obstacles
    int[] jellyfishSpeed = new int [5];
    int[] jellyfishX = new int [5];
    int[] jellyfishY = new int [5];

    //creating fonts used throughout program
    Font main = new Font ("Sans-Serif", Font.BOLD, 40);
    Font rules = new Font ("Monospaced", Font.BOLD, 20);
    Font play = new Font ("Sans-Serif", Font.BOLD, 100);
    Font menuText = new Font ("Sans-Serif", Font.BOLD, 80);
    Font instructionText = new Font ("Times New Roman", Font.BOLD, 40);
    Font gameText = new Font ("Sans-Serif", Font.BOLD, 50);

    //final int variables for dimensions of jellyfish and turtle width
    final int jellyfishHeight = 466, jellyfishWidth = 536, turtleHeight = 158, turtleWidth = 253;

    public void init ()
    {
	addKeyListener (this);
	requestFocus (true);
	addMouseListener (this);

	//getting the images

	ImageIcon img1 = new ImageIcon ("jellyfish.png");
	jellyfishImg = img1.getImage ();

	ImageIcon img2 = new ImageIcon ("background.jpg");
	backgroundImg = img2.getImage ();
	backgroundX = 0;
	backgroundY = 0;

	ImageIcon img3 = new ImageIcon ("turtle.png");
	turtleImg = img3.getImage ();
	turtleY = 100;
	turtleX = getSize ().width / 2 - turtleWidth;

	ImageIcon img4 = new ImageIcon ("title.png");
	titleImg = img4.getImage ();
	titleX = getSize ().width / 2 - (993 / 2);
	titleY = 50;

	ImageIcon img5 = new ImageIcon ("bigturtle.png");
	bigTurtleImg = img5.getImage ();

	ImageIcon img6 = new ImageIcon ("bigjellyfish.png");
	bigJellyfishImg = img6.getImage ();

	ImageIcon img7 = new ImageIcon ("speechBubble.png");
	speechBubbleImg = img7.getImage ();

	ImageIcon img8 = new ImageIcon ("exitturtle.png");
	exitTurtleImg = img8.getImage ();

	ImageIcon img9 = new ImageIcon ("greenface.png");
	greenfaceImg = img9.getImage ();

	ImageIcon img10 = new ImageIcon ("yellowface.png");
	yellowfaceImg = img10.getImage ();

	ImageIcon img11 = new ImageIcon ("redface.png");
	redfaceImg = img11.getImage ();

	//startY variable set to 0 and resetting game values
	startY = 0;
	resetGame ();
    }


    public void paint (Graphics g)  //paint method which dictates which method to call depending on what mode is selected
    {
	if (mode == 0)
	{
	    menuScreen (g);
	}
	else if (mode == 1)
	{
	    easyMode (g);
	    level = 1;
	}
	else if (mode == 2)
	{
	    ameteurMode (g);
	    level = 2;
	}
	else if (mode == 3)
	{
	    hardMode (g);
	    level = 3;
	}
	else if (mode == 4)
	{
	    exit (g);
	}
	else if (mode == 5)
	{
	    instructions (g);
	}
	else if (mode == 6)
	{
	    levelSelect (g);
	}

    }


    public void menuScreen (Graphics g)  //menu screen method, drawing the background, different buttons to press, etc. Also setting score and inGame to 0.
    {

	score = 0;
	inGame = 0;

	g.drawImage (backgroundImg, 0, 0, this);
	g.drawImage (titleImg, titleX, titleY, this);

	g.setFont (play);
	g.drawString ("Play", getSize ().width / 2 - 70, 500);
	g.setFont (menuText);
	g.drawString ("Instructions", getSize ().width / 2 - 185, 650);
	g.drawString ("Exit", getSize ().width / 2 - 30, 800);
	g.setFont (main);
	g.drawString ("A game by Marc Vidal", getSize ().width / 2 - 160, 980);
    }


    public void instructions (Graphics g)  //instructions page
    {
	//printing the instructions
	g.drawImage (backgroundImg, 0, 0, this);
	g.drawImage (titleImg, titleX, titleY, this);
	g.drawImage (bigTurtleImg, 100, 800, this);
	g.drawImage (bigJellyfishImg, 1600, 100, this);

	g.setFont (menuText);
	g.drawString ("Welcome to Underwater Adventures!", getSize ().width / 2 - 750, 450);
	g.setFont (main);
	g.drawString ("The aim of the game is to dodge the jellyfish!", getSize ().width / 2 - 400, 530);
	g.drawString ("Dodge the jellyfish to earn a point!", getSize ().width / 2 - 330, 630);
	g.drawString ("Use your left and right arrow keys to move your turtle", getSize ().width / 2 - 500, 730);
	g.drawString ("Good Luck!", getSize ().width / 2 - 100, 830);
	g.setColor (Color.white);
	g.drawString ("Go Back", 1600, 900);

    }


    public void levelSelect (Graphics g)  //level select, user is able to select their difficulty mode
    {
	g.setColor (Color.black);
	g.drawImage (backgroundImg, 0, 0, this);
	g.drawImage (exitTurtleImg, 300, 400, this);
	g.setFont (menuText);
	g.drawString ("Choose Your Level:", 100, 200);
	g.setFont (main);
	g.setColor (Color.white);
	g.drawString ("Go Back", 1600, 900);


	g.setFont (main);
	g.drawString ("Easy Difficulty Unlimited", 1150, 190);
	g.drawString ("Medium Mode Unlimited", 1150, 390);
	g.drawString ("Hard Mode Unlimited", 1150, 590);
	g.drawImage (greenfaceImg, 1700, 150, this);
	g.drawImage (yellowfaceImg, 1700, 350, this);
	g.drawImage (redfaceImg, 1680, 490, this);

    }


    public void easyMode (Graphics g)  //easy mode level
    {
	if (level == 0) //printing the countdown and tells this method the level value
	{
	    displayCountdown (g, "Easy");
	    level++;

	}
	else
	{
	    //drawing the background and the turtle character
	    g.drawImage (backgroundImg, 0, 0, this);
	    g.drawImage (turtleImg, turtleX, turtleY, this);

	    g.setFont (gameText);
	    g.setColor (Color.white);

	    //printing lives, score and timer
	    g.setFont (main);
	    displayTimer (g, timer);
	    g.drawString ("Lives: " + lives, 1670, 150);
	    g.drawString ("Score: " + score, 1670, 210);

	    //loop to draw set amount of jellyfish based on level, in this case 1 because level 1
	    for (int p = 0 ; p < level ; p++)
	    {
		g.drawImage (jellyfishImg, jellyfishX [p], jellyfishY [p], this);
	    }
	    //timer and delay is set to 60
	    timer += 60;
	    delay (60);

	    //calls checkTouch method to check if jellyfish and the turtle intersect
	    checkTouch ();

	    //loop to move jellyfish. Move jellyfish method is called.
	    for (int i = 0 ; i < level ; i++)
	    {
		moveJellyfish (i);
	    }

	    //brings up game over method if lives is less than 0
	    if (lives <= 0)
	    {
		gameOver (g);
	    }

	    //repaints only if the game is over
	    if (gameOver == false)
	    {
		repaint ();
	    }
	}
    }


    public void ameteurMode (Graphics g)  //ameteur mode level
    {
	if (level == 0) //displays countdown and tells this method the level value
	{
	    displayCountdown (g, "Medium");
	    level++;
	}
	else
	{
	    //draw background and turtle character
	    g.drawImage (backgroundImg, 0, 0, this);
	    g.drawImage (turtleImg, turtleX, turtleY, this);

	    g.setFont (gameText);
	    g.setColor (Color.white);

	    //draw lives, score, and timer
	    g.setFont (main);
	    displayTimer (g, timer);
	    g.drawString ("Lives: " + lives, 1670, 150);
	    g.drawString ("Score: " + score, 1670, 210);


	    //drawing 2 jellyfish because this is level 2
	    for (int p = 0 ; p < level ; p++)
	    {
		g.drawImage (jellyfishImg, jellyfishX [p], jellyfishY [p], this);
	    }
	    //delay is 60, so timer is set to 60
	    timer += 60;
	    delay (60);

	    //calls checkTouch method to see if the jellyfish or turtle intersect
	    checkTouch ();

	    for (int i = 0 ; i < level ; i++) //loop to move the jellyfish
	    {
		moveJellyfish (i);
	    }

	    if (lives <= 0) //checks if lives is below 0, if so, call gameOver method
	    {
		gameOver (g);
	    }

	    if (gameOver == false) //repaints only if gameOver is false
	    {
		repaint ();
	    }
	}
    }


    public void hardMode (Graphics g)  //hard mode level
    {
	if (level == 0) //display countdown for this level and gives this method the level value
	{
	    displayCountdown (g, "Hard");
	    level++;

	}
	else
	{
	    //draws turtle and background
	    g.drawImage (backgroundImg, 0, 0, this);
	    g.drawImage (turtleImg, turtleX, turtleY, this);

	    g.setFont (gameText);
	    g.setColor (Color.white);

	    //drawing the lives, score, and timer
	    g.setFont (main);
	    displayTimer (g, timer);
	    g.drawString ("Lives: " + lives, 1670, 150);
	    g.drawString ("Score: " + score, 1670, 210);

	    //drawing 3 turtles because level 3
	    for (int p = 0 ; p < level ; p++)
	    {
		g.drawImage (jellyfishImg, jellyfishX [p], jellyfishY [p], this);
	    }
	    //set timer and delay to 60
	    timer += 60;
	    delay (60);

	    //calls checkTouch method to check if turtle or jellyfish intersect
	    checkTouch ();

	    for (int i = 0 ; i < level ; i++) //move jellyfish method with loop
	    {
		moveJellyfish (i);
	    }

	    if (lives <= 0) //checks to see if lives is 0, if so call gameOver screen
	    {
		gameOver (g);
	    }

	    if (gameOver == false) //repaints only if game over is false
	    {
		repaint ();
	    }
	}
    }


    public void resetJellyfish (int g)  //game Over method. This sets the random x and y variables of the jellyfish using arrays. I created 3 different if statements to increase the difficulty of each mode
    {
	if (mode == 1)
	{
	    jellyfishX [g] = (int) ((getSize ().width - jellyfishWidth + 1) * Math.random ());
	    jellyfishY [g] = 1000;
	    jellyfishSpeed [g] = (int) (50 * Math.random () + 40);
	}
	else if (mode == 2)
	{
	    jellyfishX [g] = (int) ((getSize ().width - jellyfishWidth + 1) * Math.random ());
	    jellyfishY [g] = 1000;
	    jellyfishSpeed [g] = (int) (55 * Math.random () + 45);
	}
	else if (mode == 3)
	{
	    jellyfishX [g] = (int) ((getSize ().width - jellyfishWidth + 1) * Math.random ());
	    jellyfishY [g] = 1000;
	    jellyfishSpeed [g] = (int) (60 * Math.random () + 50);
	}
    }


    public void moveJellyfish (int g)  //moveJellyfish method
    {

	if (jellyfishY [g] <= startY) //checks to see if the jellyfish touches the starting y coordinate, 0. If so, it resets the jellyfish at its starting position and assigns random integers to its, x and speed value. Done by calling resetJellyfish method.

	    {
		resetJellyfish (g);
	    }
	jellyfishY [g] -= jellyfishSpeed [g]; //the jellyfish speed is its randomized y coordinate to reprint in that posititon
    }


    public void exit (Graphics g)  //exit screen that the user could have clicked on to exit in the main menu
    {
	g.drawImage (backgroundImg, 0, 0, this);
	g.drawImage (speechBubbleImg, 1000, 100, this);
	g.drawImage (exitTurtleImg, 300, 500, this);
	g.setFont (main);
	g.setColor (Color.white);
	g.drawString ("Thanks For Playing!", 1100, 300);

    }


    public void checkTouch ()  // checks to see if the jellyfish and turtle intersect, if they do a life is lost, and if the jellyfish y coordinate is less than 0 a point is awarded
    {
	for (int i = 0 ; i < level ; i++)
	{
	    if (jellyfishX [i] + jellyfishWidth / 2 > turtleX + 100 && jellyfishX [i] + jellyfishWidth / 2 < turtleX + turtleWidth + 100 && jellyfishY [i] < turtleY + turtleHeight)
	    {
		resetJellyfish (i);
		lives--;
		break;
	    }
	    else if (jellyfishY [i] <= 0)
	    {
		resetJellyfish (i);
		score++;
		break;
	    }

	    //checks to see if the turtle leaves the screen and makes sure to keep them inside the window
	    else if (turtleX <= 0)
	    {
		turtleX -= turtleX;
	    }
	    else if (turtleX >= getSize ().width - turtleWidth)
	    {
		turtleX = getSize ().width - turtleWidth;
	    }

	}
    }


    public void gameOver (Graphics g)  //game Over method
    {

	gameOver = true;
	g.drawImage (backgroundImg, 0, 0, this);
	g.setFont (menuText);
	g.drawString ("GAME OVER", getSize ().width / 2 - 200, 300);
	g.setFont (gameText);
	g.drawString ("Score :" + score, getSize ().width / 2 - 50, 400);
	g.drawString ("DOUBLE CLICK MOUSE TO CONTINUE", getSize ().width / 2 - 400, 800); //I used double click instead of press any key because if the user is spamming a key during a game, they might skip the gameOver screen.
	displayTimer (g, timer);
    }


    public void resetGame ()  //reseting game values and variables
    {
	score = 0;
	lives = 3;
	level = 0;
	gameOver = false;
	timer = 0;

    }


    public void displayCountdown (Graphics g, String gameMode)  //method for the countdown before each level, done by using a for loop and making the countdown number the same colour as the screen background as if the number actually changed
    {
	//Displays gamemode
	g.setColor (Color.white);
	g.fillRect (0, 0, getSize ().width, getSize ().height);
	g.setFont (new Font ("Arial", Font.BOLD, 50));
	g.setColor (Color.red);
	g.drawString ("Gamemode: " + gameMode, getSize ().width / 2 - 150, getSize ().height / 2 - 50);

	//Counts down from 3 to 1
	for (int i = 3 ; i >= 1 ; i--)
	{
	    g.setColor (Color.blue);
	    g.drawString ("Starting in " + i, getSize ().width / 2 - 95, getSize ().height / 2 + 50);
	    delay (1000);
	    g.setColor (Color.white);
	    g.drawString ("Starting in " + i, getSize ().width / 2 - 95, getSize ().height / 2 + 50);

	    if (i == 1)
	    {
		g.setColor (Color.black);
		g.drawString ("Press any key to continue", getSize ().width / 2 - 260, getSize ().height / 2 + 100);
	    }
	}
    }


    public void displayTimer (Graphics g, int timerCalc)  //Calculates and displays timer
    {
	int min, sec; //creating variables used in the method
	//Converts ms to seconds
	timerCalc = timerCalc / 1000;
	//Calculates minutes
	min = timerCalc / 60;
	//Calculates seconds
	sec = timerCalc % 60;

	//Displays timer
	g.setColor (Color.white);
	g.setFont (gameText);
	if (gameOver == false) //displaying timer DURING game
	{
	    g.drawString (min + ":", 1750, 100);
	    if (sec >= 10)
	    {
		g.drawString ("" + sec, 1800, 100);
	    }
	    else
	    {
		g.drawString ("0" + sec, 1800, 100);
	    }
	}

	if (gameOver == true) //displays timer AFTER game
	{
	    g.drawString (min + ":", getSize ().width / 2, 500);
	    g.setFont (gameText);

	    if (sec >= 10)
	    {
		g.drawString ("" + sec, getSize ().width / 2 + 50, 500);
	    }
	    else
	    {
		g.drawString ("0" + sec, getSize ().width / 2 + 50, 500);
	    }
	}
    }


    public void keyPressed (KeyEvent e)  //key pressed methods. Dictates that right arrow key makes the turtle go right and left makes it go left
    {

	if (mode == 1 || mode == 2)
	{
	    if (e.getKeyCode () == KeyEvent.VK_RIGHT)
	    {
		turtleX = turtleX + 30;
	    }
	    else if (e.getKeyCode () == KeyEvent.VK_LEFT)
	    {
		turtleX = turtleX - 30;
	    }
	    repaint ();

	}
	if (mode == 3) //for hard mode, so that the user would be able to move their turtle "faster"
	{
	    if (e.getKeyCode () == KeyEvent.VK_RIGHT)
	    {
		turtleX = turtleX + 40;

	    }
	    else if (e.getKeyCode () == KeyEvent.VK_LEFT)
	    {
		turtleX = turtleX - 40;
	    }

	    repaint ();
	}
    }


    public void keyReleased (KeyEvent e)  //keyreleased method
    {

    }


    public void keyTyped (KeyEvent e)  //key typed method
    {

    }


    public void mousePressed (MouseEvent e)  //mouse pressed method. Essentially the "pressable" buttons. Checks the mode and the set pixels that the "button" is on
    {
	mouseX = e.getX ();
	mouseY = e.getY ();

	if (mode == 0 && mouseX > 750 && mouseX < 1250 && mouseY > 570 && mouseY < 660)
	{
	    mode = 5;
	}
	else if (mode == 5 || mode == 6 && mouseX > 1650 && mouseX < 1850 && mouseY > 850 && mouseY < 950)
	{
	    mode = 0;
	}
	else if (mode == 0 && mouseX > 900 && mouseX < 1095 && mouseY > 725 && mouseY < 820)
	{
	    mode = 4;
	}
	else if (mode == 0 && mouseX > 860 && mouseX < 1124 && mouseY > 401 && mouseY < 518)
	{
	    mode = 6;
	}
	else if (mode == 6 && mouseX > 1128 && mouseX < 1641 && mouseY > 140 && mouseY < 214)
	{
	    mode = 1;

	}
	else if (mode == 6 && mouseX > 1130 && mouseX < 1624 && mouseY > 345 && mouseY < 425)
	{
	    mode = 2;

	}
	else if (mode == 6 && mouseX > 1130 && mouseX < 1641 && mouseY > 530 && mouseY < 605)
	{
	    mode = 3;
	}


	repaint ();
    }


    public void mouseReleased (MouseEvent e)  //mouse released method
    {

    }


    public void mouseClicked (MouseEvent e)  //mouse clicked method for the gameOver screen. Makes user double click to reset the game and go back to main menu
    {
	if (gameOver == true)
	{
	    gameOver = false;
	    mode = 0;
	    resetGame ();
	}
    }


    public void mouseEntered (MouseEvent e)  //mouse entered method
    {

    }


    public void mouseExited (MouseEvent e)  //mouse exited method
    {

    }


    public void mouseMoved (MouseEvent e)  //mouse moved method
    {

    }


    public void mouseDragged (MouseEvent e)
    {
    }


    public void delay (int time)  //delay
    {
	try
	{
	    Thread.sleep (time);
	}
	catch (InterruptedException e)
	{
	}
    }
} //end of class
