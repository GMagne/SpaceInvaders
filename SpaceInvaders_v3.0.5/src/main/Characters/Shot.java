package main.Characters;

import main.C;

public class Shot extends Character {

    private static float v = 8;

    public Shot(float cx, float cy){
        super(cx,cy,C.SHOT_W,C.SHOT_H);
        setImage(C.PATH_SHOT);
    }

    public void move(){
        moveY(-v);
    }


}