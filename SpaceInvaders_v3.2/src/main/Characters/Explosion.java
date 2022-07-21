package main.Characters;

import main.Utilities.C;
import main.Utilities.ImageManager;

import java.awt.*;

public class Explosion extends Character {

    private double timeStartExplosion;
    private Image[] imagesExplosion;

    public Explosion(float cx, float cy){
        super(cx,cy,C.EXPLOSION_W,C.EXPLOSION_H);
        imagesExplosion = ImageManager.getExplosion();
        timeStartExplosion = System.currentTimeMillis();
    }

    public boolean ended(){
        if (System.currentTimeMillis()-timeStartExplosion>C.EXPLOSION_DURATION){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Image getImage() {
        int i = Math.min( (int) (System.currentTimeMillis()-timeStartExplosion)/110,5);
        return imagesExplosion[i];
    }


}
