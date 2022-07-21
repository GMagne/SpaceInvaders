package SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Starter extends JFrame implements KeyListener {
    final static String PINICI = "Pantalla Inicial";
    final static String GAME = "Pantalla del Joc";


    public static int WIDTH=800, HEIGHT=600;
    public static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static Font font;

    private JPanel paneContent;
    private boolean gameOn = false;
    private Game game;


    public Starter() {
        //Set Font
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADE_N.ttf"));
        } catch (IOException | FontFormatException e) {
            font = new Font(Font.MONOSPACED, Font.BOLD,20);
        }

        //Frame
        setTitle("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLocation( (int) (dimension.getWidth()-WIDTH)/2 , (int) (dimension.getHeight()-HEIGHT)/2 );
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setFocusable(true);
        addKeyListener(this);

        //
        paneContent = new JPanel();
        paneContent.setLayout(new CardLayout());
        paneContent.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        PantallaInici pInici = new PantallaInici();
        pInici.button.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e){ pInici.button.setForeground(Color.GREEN); }

            @Override
            public void mouseExited(MouseEvent e){ pInici.button.setForeground(Color.WHITE); }

            @Override
            public void mouseClicked(MouseEvent e){
                CardLayout cl = (CardLayout)(paneContent.getLayout());
                cl.show(paneContent, GAME);
                game.start();
                gameOn=true;
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        });

        paneContent.add(pInici, PINICI);

        game =new Game();
        paneContent.add(game, GAME);


        add(paneContent, BorderLayout.CENTER);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOn){
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_A:
                    game.ship.goLeft();
                    break;
                case KeyEvent.VK_LEFT:
                    game.ship.goLeft();
                    break;
                case KeyEvent.VK_D:
                    game.ship.goRight();
                    break;
                case KeyEvent.VK_RIGHT:
                    game.ship.goRight();
                    break;
                case KeyEvent.VK_SPACE:
                    game.ship.shoot();
                    break;
                case KeyEvent.VK_ESCAPE:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameOn) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_A:
                    game.ship.stop();
                    break;
                case KeyEvent.VK_LEFT:
                    game.ship.stop();
                    break;
                case KeyEvent.VK_D:
                    game.ship.stop();
                    break;
                case KeyEvent.VK_RIGHT:
                    game.ship.stop();
            }
        }
    }

    public static void main(String[] args){
        new Starter();
    }

}
