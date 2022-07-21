import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PantallaInici extends JPanel implements MouseListener {

    JButton button;
    public  PantallaInici(Font font, int WIDTH, int HEIGHT){
        //Panel
        setSize(WIDTH,HEIGHT);
        setLocation( 0 , 0 );
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        setVisible(true);
        setBackground(Color.BLACK);

        //Button
        int buttonW = 354, buttonH = 50;
        button = new JButton("START GAME");
        button.addMouseListener(this);
        button.setPreferredSize(new Dimension(buttonW,buttonH));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFont(font.deriveFont(Font.BOLD,38));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Labels
        JLabel[] labels={
                new JLabel("SPACE INVADERS",JLabel.CENTER),
                new JLabel("*SCORE ADVANCE TABLE*",JLabel.CENTER),
                new JLabel(" = ?    MYSTERY", new ImageIcon("Imatges/SpaceshipIcon.png"), JLabel.CENTER),
                new JLabel(" = 30    POINTS", new ImageIcon("Imatges/SquidIcon.png"), JLabel.CENTER),
                new JLabel(" = 20    POINTS", new ImageIcon("Imatges/CrabIcon.png"), JLabel.CENTER),
                new JLabel(" = 10    POINTS", new ImageIcon("Imatges/OctopusIcon.png"), JLabel.CENTER)
        };

        for (JLabel label: labels){
            label.setForeground(Color.WHITE);
            label.setFont(font.deriveFont(Font.BOLD,29));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // Adds
        add(labels[0]);
        add(Box.createVerticalGlue());
        add(button);
        add(Box.createVerticalGlue());
        for (int i = 1; i<labels.length; i++){
            add(labels[i]);
        }
        add(Box.createVerticalGlue());
        add(Box.createRigidArea(new Dimension(10, 0)));

    }

    public void mouseEntered(MouseEvent e){
        //if (e.getSource()==button) {}
        button.setForeground(Color.GREEN);

    }

    public void mouseExited(MouseEvent e){
        button.setForeground(Color.WHITE);
    }

    public void mouseClicked(MouseEvent e){
        Starter.pantalla.remove(Starter.pInici);
        Starter.pantalla.add(new Board(), BorderLayout.CENTER);
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}