package SpaceInvaders.Characters;

import java.awt.*;

public abstract class Character {
    private Image image;
    public float x,y,h,w;

    public Character(){}

    public Character(float x, float y, int h, int w, String path){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        image = loadImage(path);
    }

    public void setCharacter(float x, float y, int h, int w, String path){
        this.x=x;
        this.y=y;
        this.h = h;
        this.w = w;
        image = loadImage(path);
    }


    public Image loadImage(String path) {
        return Toolkit.getDefaultToolkit().createImage(path);
    }

    public Image getImage() {
        return image;
    }

    public int getX(){
        return (int) x;
    }

    public int getY(){
        return (int) y;
    }

    public int[] hcenter(){
        int[] a = new int[] {(int) (x+w/2) ,(int) (y+h/2)} ;
        return a;
    }

    public float[] getH(){
        float[] hitbox= new float[] {x,y, x+w, y+h};
        return hitbox;
    }
}
