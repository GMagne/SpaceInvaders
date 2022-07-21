package main.Characters;

import main.*;

import java.awt.event.KeyEvent;

public class Ship extends Character{


    private float v = 0;
    private float vmax = 6;
    private int coolDown = 430;
    private double lastTimeShoot;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean shotPressed = false;
    private boolean visible = true;


    public Ship(float cx, float cy){
        super(cx,cy,C.SHIP_W,C.SHIP_H);
        setImage(C.PATH_SHIP);
        lastTimeShoot = System.currentTimeMillis();
    }

    public void move(){
        if(x+v>=C.BOARD_SHIP_X && x+v<=C.BOARD_SHIP_W){
            moveX(v);
        }
    }

    public Shot shoot() {
        long time_ms = System.currentTimeMillis();
        if (shotPressed && time_ms - lastTimeShoot > coolDown) {
            lastTimeShoot = time_ms;
            return new Shot(getCenterX(), getCenterY()-C.SHOT_H/2);
        }
        return null;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            v = -vmax;
            leftPressed = true;
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            v = vmax;
            rightPressed = true;
        }
        else if (key == KeyEvent.VK_SPACE)  {
            shotPressed = true;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            if(rightPressed){
                v = vmax;
            }else{
                v = 0;
            }
            leftPressed = false;
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if(leftPressed){
                v = -vmax;
            }else{
                v = 0;
            }
            rightPressed = false;
        }
        else if (key == KeyEvent.VK_SPACE)  {
            shotPressed = false;
        }
    }

    public boolean isVisible(){
        return visible;
    }

    public void setNotVisible(){
        visible = false;
    }
}
