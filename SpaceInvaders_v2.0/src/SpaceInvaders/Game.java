package SpaceInvaders;

import SpaceInvaders.Characters.*;
import SpaceInvaders.Characters.Alien.AlienType;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Game extends JPanel{
    public static int score;
    public static int posX = 16 , posY = 25 , B_WIDTH = 768, B_HEIGHT = 520;
    public static boolean gameOn = true;

    private long startTime;
    private long endTime;
    private ScoreBar sB = new ScoreBar(this);

    private static int n_row=5, n_col=8;
    private Alien[][] aliens;
    public Ship ship;
    private ArrayList<Raig> raigs = new ArrayList<Raig>();
    private ArrayList<RaigAlien> raigsAlien = new ArrayList<RaigAlien>();
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();

    private final Image background;

    public Game(){
        this.score=0;
        startTime = System.currentTimeMillis();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        background = Toolkit.getDefaultToolkit().createImage("Imatges/background.png");

        initEnemys();
        initShip();

        if(gameOn){

            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ship.move(raigs);
                    move(aliens);
                    moveR(raigs);
                    moveRA(raigsAlien);
                    checkColisions();
                    repaint();
                }
            });
            timer.start();
        }
    }

    public void start() {
        gameOn=true;
        repaint();
    }

    private void moveRA(ArrayList<RaigAlien> raigsAlien){
        for (RaigAlien r: raigsAlien){
            r.move();
        }
    }

    private void moveR(ArrayList<Raig> raigs){
        for (Raig r: raigs){
            r.move();
        }
    }

    private void move(Alien[][] aliens){
        if (canMove(aliens)){
            for (Alien[] alien_array : aliens){
                for (Alien alien: alien_array){
                    alien.move();
                    if (raigsAlien.size()<Alien.maxRaig && this.getTime()<3000){
                        alien.shoot(raigsAlien);
                    }
                }
            }
        }
        else{
            for (Alien[] alien_array : aliens){
                for (Alien alien: alien_array){
                    alien.moveDown();
                    alien.changeDirection();
                    alien.increaseV();
                    alien.shoot(raigsAlien);
                }
            }
        }
    }

    private boolean canMove(Alien[][] aliens) {
        for (Alien[] alien_array : aliens){

            for (Alien alien: alien_array){
                if (alien.canMove()==false){
                    return false;
                }
            }
        }
        return true;
    }

    private void initShip(){
        ship = new Ship(posX+B_WIDTH-Ship.w,posY+B_HEIGHT);
    }

    private void initEnemys()
    {
        aliens = new Alien[n_row][n_col];

        AlienType[] rowType = new AlienType[]{ AlienType.SQUID, AlienType.CRAB, AlienType.CRAB, AlienType.OCTOPUS, AlienType.OCTOPUS};

        for (int row=0; row<n_row; row++){
            for (int col=0; col<n_col; col++){
                aliens[row][col] = new Alien(posX+col*16*4, posY+13*4+row*9*4, rowType[row]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(background,0,0,this);

        for (Raig raig: raigs){
            g2d.drawImage(raig.getImage(),raig.getX(),raig.getY(),this);
        }
        g2d.drawImage(ship.getImage(),ship.getX(),ship.getY()-12,this);

        for (RaigAlien raig: raigsAlien){
            g2d.drawImage(raig.getImage(),raig.getX(),raig.getY(),this);
        }
        paint(aliens,g2d);

        sB.paint(g2d);
        g2d.dispose();
    }

    public void paint(Graphics2D g2d){

        for (Alien alien : aliens){
            g2d.drawImage(alien.getImage(),alien.getX(),alien.getY(),this);
        }
    }

    private String getFormatedTime(long ms){
        long seconds = ms/1000;
        String secondPart = (seconds%60) < 10 ? "0" + String.valueOf(seconds%60) : String.valueOf(seconds%60);
        return String.valueOf(seconds/60) + ":" + secondPart;
    }

    public long getTime(){
        return
                this.gameOn
                        ? System.currentTimeMillis() - this.startTime
                        : this.endTime - this.startTime;

    }

    public void checkColisions(){
        for (Raig raig: raigs){
            for(Alien[] alien_array: aliens){
                for (Alien alien: alien_array){
                    if(colide(raig.getH(), alien.getH())){
                        raigsAlien.remove(raig);
                        alien.visible=false;
                    }else if(lost(raig)){
                        raigsAlien.remove(raig);
                    }
                }
            }
        }

        for (RaigAlien raig: raigsAlien){
            if(colide(raig.getH(), ship.getH())){

            }else if(lost(raig)){
                raigsAlien.remove(raig);
            }

        }
    }
    public boolean lost (Raig raig){
        float[] h = raig.getH();
        if(h[1]>Starter.HEIGHT) {
            return true;
        }else{
            return false;
        }

    }
    public boolean lost (RaigAlien raig){
        float[] h = raig.getH();
        if(h[3]<0) {
            return true;
        }else{
            return false;
        }
    }
    public boolean colide(float[] h1, float[] h2){
        if(h1[0]<=h2[0] && h2[0]<=h1[2] && h1[1]<=h2[1] && h2[1]<=h1[3]){
            return true;
        }else if(h1[0]<=h2[2] && h2[2]<=h1[2] && h1[1]<=h2[3] && h2[3]<=h1[3]){
            return true;
        }else if(h1[0]<=h2[0] && h2[0]<=h1[2] && h1[1]<=h2[3] && h2[3]<=h1[3]){
            return true;
        }else if(h1[0]<=h2[2] && h2[2]<=h1[2] && h1[1]<=h2[1] && h2[1]<=h1[3]){
            return true;
        }else{
            return false;
        }

    }


}

