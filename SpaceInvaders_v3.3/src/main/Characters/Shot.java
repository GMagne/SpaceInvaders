package main.Characters;

import main.Utilities.C;
import main.Utilities.ImageManager;

public class Shot extends Character {

    private static float v = 8;

    public Shot(float cx, float cy){
        super(cx,cy,C.SHOT_W,C.SHOT_H);
        setImage(ImageManager.getImage(C.PATH_SHOT));
    }

    public void move(){
        moveY(-v);
    }


}
