package main.Characters;

import main.C;

public class Spaceship extends Character {

    private float v = 6;
    private int coolDown = 3000;
    private long timeLastAppearance;
    private boolean visible = false;

    public Spaceship(float px, float py){
        super(px-C.SPACESHIP_W/2,py-C.SPACESHIP_H/2, C.SPACESHIP_W, C.SPACESHIP_H);
        setImage(C.PATH_SPACESHIP);
        timeLastAppearance = System.currentTimeMillis();
    }

    public void move(){

        if(visible){
            if (getX()>-C.SPACESHIP_W){
                moveX(-v);
            }
            else{
                timeLastAppearance = System.currentTimeMillis();
                visible = false;
            }
        }
        else{
            if(System.currentTimeMillis()-timeLastAppearance>=coolDown){
                visible = !visible;
                setX(C.WINDOW_WIDTH);
            }
        }
    }

    public boolean isVisible(){
        return visible;
    }

    public void setNotVisible(){
        timeLastAppearance = System.currentTimeMillis();
        visible = false;
    }

    public void setNotHitten(){
        hitten = false;
    }
}
