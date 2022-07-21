import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    boolean gameOn;
    int B_HEIGHT = 500;
    int B_WIDTH = 500;

    public void Board(){
        setLocation( 0 , 0 );
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        setVisible(true);
        setBackground(Color.RED);
    }
}
