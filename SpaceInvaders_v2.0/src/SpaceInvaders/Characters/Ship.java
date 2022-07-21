package SpaceInvaders.Characters;


import SpaceInvaders.Game;

import java.util.ArrayList;


public class Ship extends Character{

    public static int h=5*4, w=12*4;

    private float v, vmax;
    private float cd=600;
    private boolean shoot;
    private double lastTimeShoot;


    public Ship(float px, float py){
        super(px,py,h,w,"Imatges/Ship.png");
        this.lastTimeShoot= System.currentTimeMillis();
        this.v=0;
        this.vmax=10;
    }

    public void move(ArrayList<Raig> raigs){
        if (x+w+v<=Game.B_WIDTH+Game.posX && x+v>=Game.posX ){ x += v;}
        v=0;

        double ms = System.currentTimeMillis();
        if(shoot && ms-lastTimeShoot>cd){
            lastTimeShoot=ms;
            shoot=false;
            int[] a=this.hcenter();
            int s= a[0], t= a[1];
            raigs.add(new Raig(s-Raig.w/2,t-Raig.h/2) );
        }else{
            shoot=false;
        }

    }

    public void goRight(){
         v = vmax;
    }

    public void goLeft(){
        v = -vmax;
    }

    public void stop(){
        v=0;
    }

    public void shoot(){
        shoot = true;
    }
}
