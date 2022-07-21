package SpaceInvaders.Characters;


import SpaceInvaders.Game;

import java.awt.*;
import java.util.ArrayList;


public class Alien extends Character {

    public enum AlienType {
        OCTOPUS,
        CRAB,
        SQUID,
    }

    public static float maxRaig=4;
    private static double p=0.5;
    private static int h = 8*4;

    public boolean visible;
    private float v;
    private AlienType tipus;


    public Alien(float px, float py, AlienType tipus){
        this.visible=true;
        this.tipus=tipus;
        this.v=1;
        switch (tipus){
            case OCTOPUS:
                super.setCharacter(px,py,h,12*4,"Imatges/Octopus.gif");
                break;

            case CRAB:
                super.setCharacter(px+2,py,h,11*4,"Imatges/Crab.gif");
                break;

            case SQUID:
                super.setCharacter(px+8,py,h,8*4,"Imatges/Squid.gif");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + tipus);
        }
    }

    public void move(){
        x+=v;
    }

    public void moveDown(){
        y+=h;
    }

    public void changeDirection(){ v=-v;}

    public void increaseV(){
        v=Math.signum(v)*(Math.abs(v)+1);
    }

    public boolean canMove(){
        if(visible){
            if (x+48+v<=Game.B_WIDTH+Game.posX && x+v>=Game.posX ){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return true;
        }
    }

    public void shoot(ArrayList<RaigAlien> raigsAlien){
        double ms=System.currentTimeMillis();
        if(Math.random()<p){
            int[] a=this.hcenter();
            int s= a[0], t= a[1];
            raigsAlien.add(new RaigAlien(s-RaigAlien.h/2,t-RaigAlien.w/2) );
        }
    }

    public void destroy(){
        visible=false;
        p += 0.02;
    }
}
