import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel implements ActionListener {
    JButton soundB, pauseB;
    ImageIcon pause = new ImageIcon("Imatges/SoundOn.png");
    ImageIcon play = new ImageIcon("Imatges/SoundOn.png");
    ImageIcon soundOn = new ImageIcon("Imatges/SoundOn.png");
    ImageIcon soundOff = new ImageIcon("Imatges/SoundOff.png");

    public Options(int WIDTH, int HEIGHT){
        setPreferredSize(new Dimension(WIDTH,50));
        setLayout(new FlowLayout( FlowLayout.RIGHT ));
        setBackground(Color.BLACK);
        setVisible(true);

        //Sound button
        soundB = new JButton(soundOn);
        soundB.setBorderPainted(false);
        soundB.addActionListener(this);

        //adds
        add(soundB);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (soundB.getIcon() == soundOn){
            soundB.setIcon(soundOff);
        }else{
            soundB.setIcon(soundOn);
        }
        if (pauseB.getIcon() == pause){
            pauseB.setIcon(play);
        }else{
            pauseB.setIcon(play);
        }
    }
}
