import java.awt.*;

public class Finestra extends Frame {
    int y;
    Joc j;
    Graphics g;
    Image im;

    public static int WIDTH=800, HEIGHT=600;
    public static void main(String[] args) {
        new Finestra();
    }

    Finestra(){
        setSize(WIDTH,HEIGHT);
        setVisible(true);

        im = createImage(WIDTH,HEIGHT);
        g = im.getGraphics();


        Joc j=new Joc(this);
        j.run();
    }
    public void paint(Graphics g) {
        g.drawImage(im,0,0,null);
    }
    public void update( Graphics g) {
        paint(g);
    }
}
