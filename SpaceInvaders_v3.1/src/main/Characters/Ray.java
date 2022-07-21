package main.Characters;

import main.*;
import java.awt.*;

public class Ray extends Character{

    private static float v = 10;

    public Ray(float cx, float cy){
        super(cx,cy,C.RAY_W,C.RAY_H);
        setImage(ImageManager.getImage(C.PATH_RAY));
    }

    public void move(){
        moveY(+v);
    }

}
