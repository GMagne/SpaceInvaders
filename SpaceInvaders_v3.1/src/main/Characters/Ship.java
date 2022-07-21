package main.Characters;

import main.*;

import java.awt.event.KeyEvent;

public class Ship extends Character{


    private float v = 0;
    private float vmax = 15;
    private float a = 0.08f;
    private float af = 0.15f;
    private long lastTimeVUpdated;
    private int coolDown = 430;
    private long lastTimeShoot;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean shotPressed = false;
    private boolean visible = true;


    public Ship(float cx, float cy){
        super(cx,cy,C.SHIP_W,C.SHIP_H);
        setImage(ImageManager.getImage(C.PATH_SHIP));
        lastTimeShoot = System.currentTimeMillis();
        lastTimeVUpdated = lastTimeShoot;
    }

    public void move(){
        if(x+v>=C.BOARD_SHIP_X && x+v<=C.BOARD_SHIP_W){
            moveX(v);
            updateV();
        }
        else{
            v=0;
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
            leftPressed = true;
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightPressed = true;
        }
        else if (key == KeyEvent.VK_SPACE)  {
            shotPressed = true;
        }
    }

    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightPressed = false;
        }
        else if (key == KeyEvent.VK_SPACE)  {
            shotPressed = false;
        }
    }

    private void updateV(){
        long t = System.currentTimeMillis();

        if(leftPressed && rightPressed){
            v = Math.signum(v)*Math.max(0,Math.abs(v)-af*(t-lastTimeVUpdated));
        }
        else if (leftPressed) {
            v = Math.signum(v-a*(t-lastTimeVUpdated))*Math.min(vmax,Math.abs(v-a*(t-lastTimeVUpdated)));
        }
        else if (rightPressed){
            v = Math.signum(v+a*(t-lastTimeVUpdated))*Math.min(vmax,Math.abs(v+a*(t-lastTimeVUpdated))) ;
        }
        else{
            v = Math.signum(v)*Math.max(0,Math.abs(v)-af*(t-lastTimeVUpdated));
        }
        lastTimeVUpdated = t;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setNotVisible(){
        visible = false;
    }
}
