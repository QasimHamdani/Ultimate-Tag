import java.awt.*;

public class Obstacle {

    public String name;                 // name of hero
    public int xpos;                    //the x position
    public int ypos;                  //the y position
    public int dx;                      // speed of the hero in x direction
    public int dy;                      //speed of the hero in y direction
    public int width;                   //width and height of picture
    public int height;
    public boolean isAlive;             // a boolean to denote if the hero is alive
    public Rectangle rec;
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean upPressed;
    public boolean downPressed;
    public boolean isIntersecting;

    public Obstacle(int pXpos, int pYpos, int pDx, int pDy){

        xpos = pXpos;
        ypos = pYpos;
        dx=pDx;
        dy=pDy;
        width=60;
        height=80;
        isAlive=true;
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void move() {
        if(rightPressed==true){
            dx=10;

        }else if(leftPressed==true){
            dx=-10;
        }else{
            dx=10;
        }
        if(downPressed==true){
            dy=10;

        }else if(upPressed==true){
            dy=-2;
        }else {
            dy = 10;
        }
        ypos = ypos + dy;
        xpos = xpos + dx;
        rec= new Rectangle(xpos, ypos, width, height);
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
        rec = new Rectangle(xpos, ypos, width, height);
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
        rec = new Rectangle(xpos, ypos, width, height);
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

