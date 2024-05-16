//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import apple.laf.JRSUIUtils;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.rmi.registry.RegistryHandler;
import java.sql.SQLOutput;
import java.util.WeakHashMap;
import javax.sound.sampled.Port;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable, KeyListener {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public boolean startScreen = true;
    public boolean isPlaying;
    public boolean gameOver;
    public long startTime;
    public long currentTime;
    public long elapsedTime;


    //Declare the objects used in the program below

    /** Step 1 Declare your object and give it a name **/
    /**
     * Declare an image for your object
     **/
    public Hero Ball;
    public Hero Pizza;
    public Hero BWTB;
    public Hero Ghost;
    public Hero Cloud;
    public Obstacle[] bananas;

    public Image BallPic;
    public Image PizzaPic;
    public Image BWTBPic;
    public Image GhostPic;
    public Image CloudPic;
    public Image BananaPic;
    public Image EndPic;

    public boolean PizzaIsIntersectingBananas;
    public boolean BallIsIntersectingBananas;

    public int count;

    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    public Image backPic;
    public Image StartPic;
    public boolean diverIsIntersectingDj;
    public boolean lightningIsIntersectingDiver;
    public boolean djIsIntersectingLightning;
    public boolean djIsIntersectingGhost;

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //        //for each object that has a picture, load in images as well
        /** Step 3 Constryct a specific Hero**/



        /** Step 4 load in the image for your object**/
        BallPic = Toolkit.getDefaultToolkit().getImage("BBALL.png");
        PizzaPic = Toolkit.getDefaultToolkit().getImage("Pizza.png");
        BWTBPic = Toolkit.getDefaultToolkit().getImage("BWTB.png");
        GhostPic = Toolkit.getDefaultToolkit().getImage("Ghost.png");
        CloudPic = Toolkit.getDefaultToolkit().getImage("cloud.png");
        backPic = Toolkit.getDefaultToolkit().getImage("back.jpeg");
        BananaPic =  Toolkit.getDefaultToolkit().getImage("banana.png");
        StartPic =  Toolkit.getDefaultToolkit().getImage("Start.png");
        EndPic = Toolkit.getDefaultToolkit().getImage("END.png");


    }// GameLand()
    public void startLevel(){
        Ball = new Hero(800, 12, 6, 4);
        Pizza = new Hero(55, 621, 6, 4);
        BWTB = new Hero(623, 232, 6, 4);
        Ghost = new Hero(555, 532, 6, 4);
        Cloud = new Hero(400, 400, 0, 0);
        Cloud.width = 100;
        Cloud.height = 100;
        BWTB.width = 150;
        BWTB.height = 150;
        Ghost.width = 100;
        Ghost.height = 100;

        bananas = new Obstacle[15];
        for (int i = 0; i < bananas.length; i = i + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = (int) (Math.random() * 700);
            bananas[i] = new Obstacle(randX, randY, 2, 3);
        }
    }

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
            collisions();//checks for intersections
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
       if(startScreen==true) {
           g.drawImage(StartPic, 0, 0, WIDTH, HEIGHT, null);
           g.drawString("press space bar to start" + "      ITS GO TIME", 400, 300);
           startTime=System.currentTimeMillis();
       }


        if (isPlaying == true) {

               //draw the image of your objects below:
               /**Step 5 draw the image of your object to the screen**/
               g.drawImage(backPic, 0, 0, WIDTH, HEIGHT, null);
               if (Ball != null) {
                   g.drawImage(BallPic, (int) Ball.xpos, (int) Ball.ypos, (int) Ball.width, (int) Ball.height, null);
               }
               if (Pizza != null) {
                   g.drawImage(PizzaPic, (int) Pizza.xpos, (int) Pizza.ypos, (int) Pizza.width, (int) Pizza.height, null);
               }
               g.drawImage(BWTBPic, (int) BWTB.xpos, (int) BWTB.ypos, (int) BWTB.width, (int) BWTB.height, null);
               g.drawImage(GhostPic, (int) Ghost.xpos, (int) Ghost.ypos, (int) Ghost.width, (int) Ghost.height, null);
               g.drawImage(CloudPic, (int) Cloud.xpos, (int) Cloud.ypos, (int) Cloud.width, (int) Cloud.height, null);

               if (bananas!= null) {
               for (int i = 0; i < bananas.length; i = i + 1) {
                   g.drawImage(BananaPic, bananas[i].xpos, bananas[i].ypos, bananas[i].width, bananas[i].height, null);
                   //g.draw image once you find a bannan pic
               }
               }




           }
       if (gameOver==true){
           g.drawImage(EndPic, 0, 0, WIDTH, HEIGHT, null);
           if( count <1) {
               currentTime=System.currentTimeMillis();
               elapsedTime=(int)((currentTime-startTime)*.001); // *.001 to convert to seconds
               System.out.println(elapsedTime);
               count++;
           }


       }
        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();
        bufferStrategy.show();
    }


    public void moveThings() {
        //call the move() method code from your object class
        if (Ball != null) {
            Ball.move();
        }
        if (Pizza != null) {
            Pizza.move();
        }
        if (bananas != null) {
            for (int i = 0; i < bananas.length; i = i + 1) {
                bananas[i].bouncingMove();
            }
            BWTB.move();
            Ball.move();
            Ghost.move();
            Pizza.move();
            for (int i = 0; i < bananas.length; i = i + 1) {
                bananas[i].bouncingMove();
            }
        }



    }

    public void collisions() {
        if (Ball != null && bananas != null) {
            for (int h = 0; h < bananas.length; h = h + 1) {
                if (bananas[h].rec.intersects(Ball.rec)) {
                    Ball.dx += 10;
                    Ball.dy += 10;
                    //System.out.println("OUCH");
                    //System.out.println("ball speed: " + Ball.dx);
                }
            }
        }
        if (Pizza != null && bananas != null) {
            for (int l = 0; l < bananas.length; l = l + 1) {
                if (bananas[l].rec.intersects(Pizza.rec) && bananas[l].isIntersecting == false) {
                    bananas[l].isIntersecting = true;
                    //System.out.println("banana pizza");
                    if (Pizza.pace > 1.5) {
                        Pizza.pace = Pizza.pace - 0.5;
                    }
                }
                if (!bananas[l].rec.intersects(Pizza.rec)) {
                    bananas[l].isIntersecting = false;
                }
            }
            for (int l = 0; l < bananas.length; l = l + 1) {
                if (bananas[l].rec.intersects(Ball.rec) && bananas[l].isIntersecting == false) {
                    bananas[l].isIntersecting = true;
                    //System.out.println("banana ball");
                    if (Ball.pace > 1.5) {
                        Ball.pace = Ball.pace - 0.5;
                    }
                }
                if (!bananas[l].rec.intersects(Ball.rec)) {
                    bananas[l].isIntersecting = false;
                }
                if (Ball.rec.intersects(Pizza.rec)) {
                    gameOver = true;
                    isPlaying = false;
                }
            }

        }
    }


        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause ( int time){
            //sleep
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }


        //Graphics setup method
        private void setUpGraphics () {
            frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);
            canvas.addKeyListener(this);

            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");
        }

        @Override
        public void keyTyped (KeyEvent e){
            //probably will stay empty
        }

        @Override
        public void keyPressed (KeyEvent e){
            char key = e.getKeyChar();
            int keyCode = e.getKeyCode();
            //System.out.println("Key:" + key + ", KeyCode: " + keyCode);
            if (keyCode == 68) {//d=68
                Ball.rightPressed = true;
            }
            if (keyCode == 65) {//a=65
                Ball.leftPressed = true;
            }
            if (keyCode == 87) {//w=68
                Ball.upPressed = true;
            }
            if (keyCode == 83) {//s=65
                Ball.downPressed = true;
            }
            if (keyCode == 39) {//R arrow=39
                Pizza.rightPressed = true;
            }
            if (keyCode == 37) {//L arrow=65
                Pizza.leftPressed = true;
            }
            if (keyCode == 38) {//R arrow=39
                Pizza.upPressed = true;
            }
            if (keyCode == 40) {//L arrow=65
                Pizza.downPressed = true;
            }
        }

        @Override
        public void keyReleased (KeyEvent e){
            char key = e.getKeyChar();
            int keyCode = e.getKeyCode();

            if (keyCode == 32) {
                startScreen = false;
                isPlaying = true;
                startLevel();
            }
            if (keyCode == 68) {//d=68
                Ball.rightPressed = false;
            }
            if (keyCode == 65) {//a=65
                Ball.leftPressed = false;
            }
            if (keyCode == 87) {//w=87
                Ball.upPressed = false;
            }
            if (keyCode == 83) {//s=83
                Ball.downPressed = false;
            }
            if (keyCode == 39) {//right arrow = 39
                Pizza.rightPressed = false;
            }
            if (keyCode == 37) {//left arrow = 37
                Pizza.leftPressed = false;
            }
            if (keyCode == 38) {//up arrow=38
                Pizza.upPressed = false;
            }
            if (keyCode == 40) {//down arrow =40
                Pizza.downPressed = false;
            }
            if(keyCode==32){
                if(startScreen==true){
                    startScreen=false;
                    isPlaying=true;
                }
            }
        }
    }
