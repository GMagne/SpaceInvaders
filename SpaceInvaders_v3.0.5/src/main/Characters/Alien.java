package main.Characters;

import main.*;

import main.C.AlienType;

import java.awt.*;

public class Alien extends Character{

    private AlienType type;
    private static float v = 0.0f;

    public Alien(int px, int py, AlienType type){
        super(px,py);
        this.type=type;
        switch (type){
            case OCTOPUS:
                setImage(C.PATH_OCTOPUS);
                setWH(C.OCTOPUS_W,C.OCTOPUS_H);
                break;
            case CRAB:
                setImage(C.PATH_CRAB);
                setWH(C.CRAB_W,C.CRAB_H);
                break;
            case SQUID:
                setImage(C.PATH_SQUID);
                setWH(C.SQUID_W,C.SQUID_H);
                break;
        }
    }

    public boolean canMove(){
        if(x+v>=C.BOARD_X && x+v+w<=C.BOARD_X+C.BOARD_WIDTH){
            return true;
        }
        return false;
    }

    public void move(){
        x+=v;
    }

    public void moveDown(){
        y+=C.APROACH_EARTH;
    }

    public static void changeDirection(){
        v = -v;
        increaseV();
    }

    public static void increaseV(){
        v = Math.signum(v)*(Math.abs(v)+C.ALIEN_V_INCREASE);
    }

    public AlienType type(){
        return type;
    }

    public static void stop(){
        v=0;
    }

    public static void reset(){
        v=0.5f;
    }


}
