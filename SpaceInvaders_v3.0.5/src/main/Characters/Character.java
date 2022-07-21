package main.Characters;

import java.awt.*;

public abstract class Character {

    protected Image image;
    protected float x,y,w,h;
    protected boolean hitten = false;


    protected Character(float cx, float cy){
        x = cx;
        y = cy;
    }

    protected void setWH( float width, float height){
        x = x-width/2;
        y = y-height/2;
        w = width;
        h = height;
    }

    protected Character(float cx, float cy, float width, float height){
        x = cx-width/2;
        y = cy-height/2;
        w = width;
        h = height;
    }

    protected void setImage(String path){
        image = Toolkit.getDefaultToolkit().createImage(path);
    }

    public Image getImage() {
        return image;
    }

    public void setX(float px){
        x = px;
    }

    public void setY(float py){
        x = py;
    }

    public int getX(){
        return (int) x;
    }

    public int getY(){
        return (int) y;
    }

    public float getCenterX(){
        return (x+w/2);
    }

    public float getCenterY(){
        return (y+h/2);
    }

    public void moveX(float d){
        x += d;
    }

    public void moveY(float d){
        y += d;
    }

    public float[] getH(){
        float[] hitbox= new float[] {x,y, x+w, y+h};
        return hitbox;
    }

    public boolean isHitten(){
        return hitten;
    }

    public void hit(){
        hitten = true;
    }

}
