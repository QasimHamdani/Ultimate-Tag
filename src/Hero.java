import java.awt.*;

public class Hero {

    public String name;                 // name of hero
    public double xpos;                    //the x position
    public double ypos;                  //the y position
    public double dx;                      // speed of the hero in x direction
    public double dy;                      //speed of the hero in y direction
    public double width;                   //width and height of picture
    public double height;
    public boolean isAlive;             // a boolean to denote if the hero is alive
    public Rectangle rec;
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean upPressed;
    public boolean downPressed;
    public double acceleration=-0.1;
    public double pace = 6;


    public Hero(int pXpos, int pYpos, int pDx, int pDy){

        xpos = pXpos;
        ypos = pYpos;
        dx=pDx;
        dy=pDy;
        width=35;
        height=30;
        isAlive=true;
        rec = new Rectangle((int)xpos, (int)ypos, (int)width, (int)height);


    }

    public void move() {

        if(rightPressed==true&& xpos<965){
            dx=pace;

        }else if(leftPressed==true && xpos>0){
            dx=-pace;
        }else{
            dx=0;
        }
        if(downPressed==true&& ypos<665){
            dy=pace;

        }else if(upPressed==true&& ypos>5){
            dy=-pace;
        }else {
            dy = 0;
        }
        ypos = ypos + dy;
        xpos = xpos + dx;
        rec= new Rectangle((int)xpos, (int)ypos, (int)width, (int)height);
    }
    public void bouncingMove(){
        if(xpos>940) {
            dx = -dx;
        }
        if(xpos<0){
            dx=-dx;

        }
        if(ypos>620){
            dy=-dy;
        }
        if(ypos<0){
            dy=-dy;
        }
        ypos = ypos + dy;
        xpos = xpos + dx;
        rec = new Rectangle((int)xpos, (int)ypos, (int)width, (int)height);
    }
    public void wrappingMove(){
        if (xpos > 1000) {
            xpos=0;
        }
        if (ypos > 700) {
            ypos = 0;
        }
        if (xpos < 0) {
            xpos = 1000;
        }
        if (ypos < 0) {
            ypos = 700;
        }
        ypos = ypos + dy;
        xpos = xpos + dx;
        rec = new Rectangle((int)xpos, (int)ypos, (int)width, (int)height);
    }
    public void printInfo(){
        System.out.println("(x,y): ("+xpos+", "+ypos+")" );
        System.out.println("x speed: "+ dx);
        System.out.println("y speed: "+ dy);
        System.out.println("width: "+ width);
        System.out.println("height: "+ height);
        System.out.println("isAlive: "+ isAlive);
    }

}

