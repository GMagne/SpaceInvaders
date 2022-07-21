package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PantallaInici extends JPanel implements MouseListener{

    public JButton button;

    private Image backgroundImage;

    public PantallaInici(){
        //Panel
        setPreferredSize(new Dimension(C.WINDOW_WIDTH,C.WINDOW_HEIGHT));
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        setVisible(true);

        backgroundImage = Toolkit.getDefaultToolkit().createImage("Imatges/backgroundPI.png");

        //Button
        int buttonW = 354, buttonH = 50;
        button = new JButton("START GAME");
        button.setPreferredSize(new Dimension(buttonW,buttonH));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFont(Starter.font.deriveFont(Font.BOLD,38));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addMouseListener(this);

        //Labels
        JLabel[] labels={
                new JLabel("SPACE INVADERS",JLabel.CENTER),
                new JLabel("*SCORE ADVANCE TABLE*",JLabel.CENTER),
                new JLabel(" = ?    MYSTERY", new ImageIcon("Imatges/SpaceshipIcon.png"), JLabel.CENTER),
                new JLabel(" = "+String.valueOf(C.POINTS_SQUID)+"    POINTS", new ImageIcon("Imatges/SquidIcon.png"), JLabel.CENTER),
                new JLabel(" = "+String.valueOf(C.POINTS_CRAB)+"    POINTS", new ImageIcon("Imatges/CrabIcon.png"), JLabel.CENTER),
                new JLabel(" = "+String.valueOf(C.POINTS_OCTOPUS)+"    POINTS", new ImageIcon("Imatges/OctopusIcon.png"), JLabel.CENTER)
        };

        for (JLabel label: labels){
            label.setForeground(Color.WHITE);
            label.setFont(Starter.font.deriveFont(Font.BOLD,29));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // Adds
        add(Box.createVerticalGlue());
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,this);
    }

    @Override
    public void mouseEntered(MouseEvent e){
        button.setForeground(Color.GREEN);
    }

    @Override
    public void mouseExited(MouseEvent e){
        button.setForeground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        BorderLayout layout = (BorderLayout) Starter.parentPanel.getLayout();
        Starter.parentPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        Game g = new Game();
        Starter.parentPanel.add(g);
        Starter.parentPanel.repaint();
        Starter.parentPanel.revalidate();
        g.requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
}
