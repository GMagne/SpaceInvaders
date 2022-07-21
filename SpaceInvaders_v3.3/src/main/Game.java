package main;

import main.Characters.*;
import main.Utilities.C;
import main.Utilities.C.AlienType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game extends JPanel {

    public int score = 0;

    private static Image background;
    private ScoreBar sB;

    public int round = 0;
    private long startTime;
    private long endTime;
    private Timer timer;
    private boolean gameOn;
    private boolean ended;
    private Random rGen;
    private int rayCoolDown = 900;
    private long lastTimeRayGenerated;
    private int rayCoolDownDecrease = 12;
    private int[] rayCDDperRound = new int[] {12,10,9,7,5,4,2};

    private Ship ship;
    private Spaceship spaceship;
    private ArrayList<Alien> aliens;
    private ArrayList<Shot> shots;
    private ArrayList<Ray> rays;
    private ArrayList<Explosion> explosions;


    public Game() {
        initBoard();
        gameInit();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);

        rGen = new Random();
        lastTimeRayGenerated = System.currentTimeMillis();
        background = Toolkit.getDefaultToolkit().createImage("Imatges/background.png");
        sB = new ScoreBar(this);

        timer = new Timer(C.DELAY, new GameLoop());
        timer.start();
    }

    private void gameInit() {

        gameOn = true;
        ended = false;
        round = 0;

        createAliens();

        ship = new Ship(C.SHIP_INIT_X,C.SHIP_INIT_Y);
        spaceship = new Spaceship(C.SPACESHIP_X,C.SPACESHIP_Y);
        shots =  new ArrayList<Shot>();
        rays = new ArrayList<Ray>();
        explosions = new ArrayList<Explosion>();

        startTime = System.currentTimeMillis();
    }

    private void rayCoolDown(int round){
        if (round<7){
            rayCoolDownDecrease = rayCDDperRound[round];
        }
        else{
            rayCoolDownDecrease = 0;
        }

        rayCoolDown = Math.max(100*(10-round),180);
    }

    private float velocitatInici(int round){
        return 0.25f+C.ALIEN_V_INCREASE_ROUND*round;
    }

    private void createAliens(){
        round += 1;

        rayCoolDown(round);
        Alien.reset(velocitatInici(round));

        aliens = new ArrayList<Alien>();

        for (int i = 0; i < C.ALIEN_ROWS; i++) {
            for (int j = 0; j < C.ALIEN_COLS; j++) {
                Alien alien = new Alien(C.ALIEN_INIT_X +C.OCTOPUS_W/2 +C.XSPACE_BTWN_ALIENS * j,
                        C.ALIEN_INIT_Y + C.YSPACE_BTWN_ALIENS * i,
                        C.ALIEN_TYPES_ARRAY[i]);
                aliens.add(alien);
            }
        }
    }

    private class GameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(gameOn){
                checkAliens();
                moveShip();
                moveAliens();
                moveSpaceship();
                moveShots();
                moveRays();
                checkExplosions();

                // genera rays
                long ms = System.currentTimeMillis();
                if(ms-lastTimeRayGenerated>=rayCoolDown && aliens.size()!=0){
                    Alien a = aliens.get(rGen.nextInt(aliens.size()));
                    rays.add(new Ray(a.getCenterX(),a.getCenterY()));
                    lastTimeRayGenerated = ms;
                }

                //genera shots
                Shot s = ship.shoot();
                if (s!=null && aliens.size()!=0){
                    shots.add(s);
                }

                checkColisions();
                repaint();
            }
            else{
                endTime=System.currentTimeMillis();
                Alien.stop();
                moveAliens();
                moveShots();
                moveRays();
                moveSpaceship();
                checkColisions();
                checkExplosions();

                if (!animationOn()){
                    if (timer.isRunning()) {
                        timer.stop();
                        ended = true;
                    }
                }

                repaint();
            }
        }
    }


    private boolean animationOn(){
        if (shots.size()==0 && rays.size()==0 && explosions.size()==0 && !spaceship.isVisible()){
            return false;
        }
        return true;
    }

    private void moveShip(){
        if (ship.isHitten()){
            ship.setNotVisible();
            explosions.add(new Explosion(ship.getCenterX(),ship.getCenterY()));
            gameOn = false;
        }
        ship.move();
    }

    private void moveSpaceship(){
        if(spaceship.isHitten()){
            explosions.add(new Explosion(spaceship.getCenterX(),spaceship.getCenterY()));
            changeScore(AlienType.SPACESHIP);

            spaceship.setNotVisible();
            spaceship.setNotHitten();

        }
        else{
            if(gameOn || spaceship.isVisible()){

                spaceship.move();
            }
        }

    }

    private void moveAliens() {

        Iterator itr = aliens.iterator();

        while (itr.hasNext()) {
            Alien a = (Alien) itr.next();

            if(a.isHitten()){
                explosions.add(new Explosion(a.getCenterX(),a.getCenterY()));
                itr.remove();
                rayCoolDown -= rayCoolDownDecrease;
                changeScore(a.type());
            }
        }


        if(canMoveAliens()){
            for (Alien alien : aliens){
                alien.move();
            }
        }
        else{
            for (Alien alien : aliens){
                alien.moveDown();
            }
            Alien.changeDirection();

        }
    }

    private boolean canMoveAliens() {

        for (Alien alien : aliens){
            if(!alien.canMove()){
                return false;
            }
        }
        return true;
    }

    private void moveRays() {

        Iterator itr = rays.iterator();

        while (itr.hasNext()) {
            Ray ray = (Ray) itr.next();

            if(ray.isHitten() || ray.getCenterY()>C.WINDOW_HEIGHT){
                itr.remove();
            }
            else{
                ray.move();
            }
        }
    }

    private void moveShots() {
        Iterator itr = shots.iterator();

        while (itr.hasNext()) {
            Shot s = (Shot) itr.next();

            if(s.isHitten() || s.getY()+C.SHOT_H<0 ){
                itr.remove();
            }
            else{
                s.move();
            }
        }
    }

    private void checkExplosions(){
        Iterator itr = explosions.iterator();

        while (itr.hasNext()) {
            Explosion e = (Explosion) itr.next();

            if(e.ended()){
                itr.remove();
            }

        }
    }

    private void checkColisions(){
        //Aliens vs Shots
        for (Shot s : shots){
            for (Alien a : aliens){
                if (!s.isHitten()){
                    if (colide(a.getH(), s.getH())){
                        a.hit();
                        s.hit();
                    }
                }
            }
            if (spaceship.isVisible() && colide(spaceship.getH(), s.getH())){
                spaceship.hit();
                s.hit();
            }
        }

        //Ship vs Rays
        float [] shipHitbox = ship.getH();
        for (Ray r : rays){
            if (colide(shipHitbox,r.getH() )){
                ship.hit();
                r.hit();
                break;
            }
        }
    }

    private boolean colide(float[] h, float[] g){
        if(h[0]<=g[0] && g[0]<=h[2] && h[1]<=g[1] && g[1]<=h[3]){
            return true;
        }else if(h[0]<=g[2] && g[2]<=h[2] && h[1]<=g[3] && g[3]<=h[3]){
            return true;
        }else if(h[0]<=g[0] && g[0]<=h[2] && h[1]<=g[3] && g[3]<=h[3]){
            return true;
        }else if(h[0]<=g[2] && g[2]<=h[2] && h[1]<=g[1] && g[1]<=h[3]){
            return true;
        }else if(h[1]<=g[1] && g[1]<=h[3] && h[1]<=g[3] && g[3]<=h[3] && g[0]<=h[0] && h[2]<=g[2]){
            return true;
        }
        return false;
    }

    private void changeScore(AlienType type){
        switch (type){
            case OCTOPUS:
                score+=C.POINTS_OCTOPUS;
                break;
            case CRAB:
                score+=C.POINTS_CRAB;
                break;
            case SQUID:
                score+=C.POINTS_SQUID;
                break;
            case SPACESHIP:
                score+=C.POINTS_SPACESHIP+rGen.nextInt(40);
                break;
        }
    }

    private void checkAliens(){
        if(aliens.size()==0){
            if (!animationOn()){
                createAliens();
            }
        }else{
            for (Alien a : aliens){
                if(a.getY()+C.OCTOPUS_H >= C.GROUND_Y){
                    ship.hit();
                    gameOn = false;
                }
            }
        }
    }






    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            ship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (ended){
                tornaPantallaInici();
            }

            ship.keyPressed(e);

            if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                tornaPantallaInici();
            }
        }

    }

    private void tornaPantallaInici(){
        BorderLayout layout = (BorderLayout) Starter.parentPanel.getLayout();
        Starter.parentPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        Starter.parentPanel.add(Starter.pInici);
        Starter.parentPanel.repaint();
        Starter.parentPanel.revalidate();
        Starter.pInici.requestFocusInWindow();
    }





    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(background,0,0,this);

        paintShots(g2d);
        paintShip(g2d);
        paintRays(g2d);
        paintAliens(g2d);
        paintExplosions(g2d);
        paintSpaceship(g2d);

        sB.paint(g2d);

        if(ended){
            int posx= C.WINDOW_WIDTH/4, posy=C.WINDOW_HEIGHT/3, w=C.WINDOW_WIDTH/2 , h=C.WINDOW_HEIGHT/3;
            g2d.setColor(Color.BLACK);
            Area background = new Area(new Rectangle(posx,posy, w,h));
            g2d.fill(background);
            g2d.setColor(Color.GREEN);
            g2d.setFont(Starter.font.deriveFont(Font.BOLD,42));
            g2d.drawString("GAME OVER", posx+30, posy+90);
            g2d.setFont(Starter.font.deriveFont(Font.BOLD,20));
            g2d.drawString("SCORE: "+String.valueOf(score), posx+30, posy+170);
            g2d.drawLine(posx, posy,posx+w, posy);
            g2d.drawLine(posx, posy,posx, posy+h);
            g2d.drawLine(posx, posy+h,posx+w, posy+h);
            g2d.drawLine(posx+w, posy,posx+w, posy+h);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    public void paintAliens(Graphics g){
        for (Alien alien : aliens){
            g.drawImage(alien.getImage(),alien.getX(),alien.getY(),this);
        }
    }

    public void paintShip(Graphics g){
        if (ship.isVisible()){
            g.drawImage(ship.getImage(),ship.getX(),ship.getY()-C.SHIP_TOWER_H,this);
        }
    }

    public void paintShots(Graphics g){
        for( Shot shot : shots){
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    public void paintRays(Graphics g){
        for( Ray ray : rays){
            g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
        }
    }

    public void paintExplosions(Graphics g){
        for( Explosion e : explosions){
            g.drawImage(e.getImage(), e.getX(), e.getY(), this);
        }
    }

    public void paintSpaceship(Graphics g){
        if (spaceship.isVisible()){
            g.drawImage(spaceship.getImage(),spaceship.getX(),spaceship.getY(),this);
        }
    }

    public long getTime(){
        return
                gameOn
                        ? System.currentTimeMillis() - startTime
                        : endTime - startTime;

    }


}