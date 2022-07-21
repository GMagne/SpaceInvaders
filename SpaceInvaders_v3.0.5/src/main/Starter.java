package main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Starter extends JFrame {

    public static Font font;
    public static JPanel parentPanel;
    public static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public Starter() {

        //Set Font
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("ARCADE_N.ttf"));
        } catch (IOException | FontFormatException e) {
            font = new Font(Font.MONOSPACED, Font.BOLD,20);
        }


        //Frame
        setTitle("Space Invaders");
        setLayout(new BorderLayout());
        setSize(C.WINDOW_WIDTH,C.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation( (int) (dimension.getWidth()-C.WINDOW_WIDTH)/2 , (int) (dimension.getHeight()-C.WINDOW_HEIGHT)/2 );


        //Pantalla Inici
        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        PantallaInici pInici = new PantallaInici();
        parentPanel.add(pInici,BorderLayout.CENTER);
        getContentPane().add(parentPanel);

    }


    public static void main(String[] args){
        EventQueue.invokeLater(() -> {

            Starter starter = new Starter();
            starter.setVisible(true);
        });
    }

}
