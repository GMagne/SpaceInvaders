package SpaceInvaders;

import java.awt.*;
import java.awt.geom.Area;

public class ScoreBar {
    public static final int HEIGHT = 40;
    public static final int WIDTH = Starter.WIDTH;
    private final Game game;
    public ScoreBar(Game game) {
        this.game = game;
    }


    public void paint(Graphics2D graphics) {
        graphics.setColor(Color.DARK_GRAY);
        Area background = new Area(new Rectangle(0, 0,WIDTH , 25));
        graphics.fill(background);
        graphics.setFont(Starter.font.deriveFont(Font.BOLD,14));
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.valueOf(game.score), 20, 20);
        graphics.drawString(getFormatedTime(game.getTime()), WIDTH - 70, 20);
        graphics.setColor(Color.GREEN);
        graphics.drawString("Space Invaders", WIDTH/2 - 100, 20);
    }
    private String getFormatedTime(long ms){
        long seconds = ms/1000;
        String secondPart = (seconds%60) < 10 ? "0" + String.valueOf(seconds%60) : String.valueOf(seconds%60);
        return String.valueOf(seconds/60) + ":" + secondPart;
    }
}
