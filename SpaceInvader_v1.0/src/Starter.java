import com.sun.deploy.net.MessageHeader;

import java.io.File;
import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class Starter {
    public static JFrame pantalla = new JFrame();
    public static JPanel pInici = new JPanel();
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    Font font;
    public int WIDTH=800, HEIGHT=600;

    public Starter(){
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADE_N.ttf"));
        } catch (IOException | FontFormatException e) {
            font = new Font(Font.MONOSPACED, Font.BOLD,20);
        }

        if (dimension.getHeight()<HEIGHT | dimension.getWidth()<WIDTH){
            if (dimension.getHeight()*4 < dimension.getWidth()*3){
                HEIGHT = (int) dimension.getHeight();
                while (HEIGHT%3!=0){ HEIGHT-=1; }
                WIDTH = HEIGHT*4/3;
            } else{
                WIDTH = (int) dimension.getWidth();
                while (WIDTH%4!=0){ WIDTH-=1; }
                HEIGHT = WIDTH*3/4;
            }
        }

        //Frame
        pantalla.setTitle("Space Invaders");
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.setResizable(false);
        pantalla.setSize(WIDTH,HEIGHT);
        pantalla.setLocation( (int) (dimension.getWidth()-WIDTH)/2 , (int) (dimension.getHeight()-HEIGHT)/2 );
        pantalla.getContentPane().setBackground(Color.BLACK);
        pantalla.setVisible(true);

        // Panel
        JPanel panelContent = new JPanel();
        panelContent.setLayout(new BorderLayout());
        JPanel pInici= new PantallaInici(font, WIDTH, HEIGHT);
        panelContent.add(pInici, BorderLayout.CENTER);
        panelContent.add(new Options(WIDTH, HEIGHT), BorderLayout.NORTH);
        panelContent.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        pantalla.add(panelContent);
        pantalla.pack();

    }

    public static void main(String[] args){
        new Starter();
    }
}
