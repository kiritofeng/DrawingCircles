import java.awt.*;
import java.util.ArrayList;

// helper to draw everything
public class Painter{
    ArrayList<Drawable>toDraw;
    private Painter(){
    }
    public void drawAll(Graphics G){
        for(Drawable D:toDraw)
            D.draw(G);
    }
}

// animals, currently implemented as a circle
class Animal implements Drawable{
    private Color col;
    private static final double EPS=1e-9;
    private final int radius;
    private double movX,movY,posX,posY,until;

    // helper to return euclidan distance
    private static double dist(double x, double y){
        return Math.sqrt(x*x,y*y);
    }

    // constructor
    public Animal(int x,int y,int r,Color c){
        radius=r;
        movX=0;
        movY=0;
        posX=x;
        posY=y;
        col=c;
    }

    // moves
    public void move(){
        if(until<=EPS) return;
        if(dist(movX,movY)<=until){
            until-=dist(movX,movY);
            posX+=movX;
            posY+=movY;
        }else{
            double ratio=dist(movX,movY)/until;
            posX+=movX*ratio;
            posY+=movY*ratio;
            posX=posY=until=0;
        }
    }

    public void changeVelocity(double x,double y,double u){
        posX=x;
        posY=y;
        until=u;
    }
    public void draw(Graphics G){
        Color tmp=G.getColor();
        G.setColor(col);
        G.fillOval((int)Math.round(posX),(int)Math.round(posY),radius,radius);
        G.setColor(tmp);
    }
}

interface Drawable{
    public void draw(Graphics G);
}
