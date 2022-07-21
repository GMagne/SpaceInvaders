package main.Utilities;

public interface C {

    String PATH_SPACESHIP_ICON = "Imatges/SpaceshipIcon.png";
    String PATH_OCTOPUS_ICON = "Imatges/OctopusIcon.png";
    String PATH_CRAB_ICON = "Imatges/CrabIcon.png";
    String PATH_SQUID_ICON = "Imatges/SquidIcon.png";

    String PATH_BACKGROUND = "Imatges/Background.png";
    String PATH_BACKGROUNDPI = "Imatges/BackgroundPI.png";

    int WINDOW_WIDTH = 900;
    int WINDOW_HEIGHT = 650;

    int SCOREBAR_H = 25;

    int BOARD_X = 20;
    int BOARD_Y = SCOREBAR_H;
    int BOARD_WIDTH = 860;
    int BOARD_HEIGHT = 560;

    int DELAY = 17;

    enum AlienType {
        OCTOPUS,
        CRAB,
        SQUID,
        SPACESHIP
    }

    AlienType[] ALIEN_TYPES_ARRAY = new AlienType[] {AlienType.SQUID,AlienType.CRAB,AlienType.CRAB,AlienType.OCTOPUS,AlienType.OCTOPUS};
    int ALIEN_ROWS = 5;
    int ALIEN_COLS = 9;

    int ALIEN_INIT_X = 20;
    int ALIEN_INIT_Y = 50+10*4;
    int XSPACE_BTWN_ALIENS = 12*5;
    int YSPACE_BTWN_ALIENS = 12 + 8*4 ;
    int APROACH_EARTH = 8*4;
    float ALIEN_V_INCREASE = 0.3f;
    float ALIEN_V_INCREASE_ROUND = 0.35f;

    int OCTOPUS_W = 12*4;
    int OCTOPUS_H = 8*4;
    String PATH_OCTOPUS = "Imatges/Octopus.gif";
    int CRAB_W = 11*4;
    int CRAB_H = 8*4;
    String PATH_CRAB = "Imatges/Crab.gif";
    int SQUID_W = 8*4;
    int SQUID_H = 8*4;
    String PATH_SQUID = "Imatges/Squid.gif";

    int SPACESHIP_X = C.WINDOW_WIDTH;
    int SPACESHIP_Y = 60;
    int SPACESHIP_W = 16*4;
    int SPACESHIP_H = 7*4;
    String PATH_SPACESHIP = "Imatges/Spaceship.png";

    int SHIP_W = 12*4;
    int SHIP_H = 5*4;
    int SHIP_TOWER_H = 3*4;
    int SHIP_INIT_X = BOARD_WIDTH/2;
    int SHIP_INIT_Y = BOARD_Y+BOARD_HEIGHT+SHIP_H/2+SHIP_TOWER_H;
    int BOARD_SHIP_X = BOARD_X -SHIP_W/4;
    int BOARD_SHIP_W = BOARD_X +BOARD_WIDTH -SHIP_W*3/4;

    String PATH_SHIP = "Imatges/Ship.png";

    int SHOT_W = 12;
    int SHOT_H = 36;
    String PATH_SHOT = "Imatges/Shot.gif";

    int RAY_W = 3*4;
    int RAY_H = 7*4;
    String PATH_RAY = "Imatges/Ray.gif";

    int EXPLOSION_W = 48;
    int EXPLOSION_H = 32;
    float EXPLOSION_DURATION = 550;
    String PATH_EXPLOSION = "Imatges/Explosion.gif" ;
    String[] PATH_EXPLOSION_F = new String[] {"Imatges/Explosion_f1.png","Imatges/Explosion_f2.png","Imatges/Explosion_f3.png","Imatges/Explosion_f4.png","Imatges/Explosion_f5.png","Imatges/Explosion_f6.png"};

    int GROUND_Y = BOARD_Y+BOARD_HEIGHT;

    int POINTS_OCTOPUS = 10;
    int POINTS_CRAB = 20;
    int POINTS_SQUID = 30;
    int POINTS_SPACESHIP = 80;


}
