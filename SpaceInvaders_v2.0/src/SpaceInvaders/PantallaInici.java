package SpaceInvaders;

import javax.swing.*;
import java.awt.*;

public class PantallaInici extends JPanel  {

        public JButton button;

        private Image backgroundImage;

        public PantallaInici(){
            //Panel
            setPreferredSize(new Dimension(WIDTH,HEIGHT));
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

}
