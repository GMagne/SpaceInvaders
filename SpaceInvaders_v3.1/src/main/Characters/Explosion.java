package main.Characters;

import main.C;
import main.ImageManager;

public class Explosion extends Character {

    private double timeStartExplosion;

    public Explosion(float cx, float cy){
        super(cx,cy,C.EXPLOSION_W,C.EXPLOSION_H);
        setImage(ImageManager.getImage(C.PATH_EXPLOSION));
        timeStartExplosion = System.currentTimeMillis();
    }

    public boolean ended(){
        if (System.currentTimeMillis()-timeStartExplosion>C.EXPLOSION_DURATION){
            return true;
        }else{
            return false;
        }
    }


}
