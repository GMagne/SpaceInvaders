package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PantallaInici extends JPanel implements MouseListener{


    private Image backgroundImage;
    private JButton button;

    public PantallaInici(){
        //Panel
        setPreferredSize(new Dimension(C.WINDOW_WIDTH,C.WINDOW_HEIGHT));
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        setVisible(true);

        backgroundImage = ImageManager.getImage(C.PATH_BACKGROUND);

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
                new JLabel(" = ?    MYSTERY", ImageManager.getImageIcon(C.PATH_SPACESHIP_ICON), JLabel.CENTER),
                new JLabel(" = "+String.valueOf(C.POINTS_SQUID)+"    POINTS", ImageManager.getImageIcon(C.PATH_SQUID_ICON), JLabel.CENTER),
                new JLabel(" = "+String.valueOf(C.POINTS_CRAB)+"    POINTS", ImageManager.getImageIcon(C.PATH_CRAB_ICON), JLabel.CENTER),
                new JLabel(" = "+String.valueOf(C.POINTS_OCTOPUS)+"    POINTS", ImageManager.getImageIcon(C.PATH_OCTOPUS_ICON), JLabel.CENTER)
        };

        for (JLabel label: labels){
            label.setForeground(Color.WHITE);
            label.setFont(Starter.font.deriveFont(Font.BOLD,29));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // Adds
        add(Box.createVerticalGlue());
        labels[0].setFont(Starter.font.deriveFont(Font.BOLD,48));
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
        button.setForeground(Color.WHITE);
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
