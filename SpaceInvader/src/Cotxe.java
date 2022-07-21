import java.awt.*;

public class Cotxe {
    int x,y;
    float v;
    int width=30;
    int height=20;
    Cotxe(int x, int y, float v){
        this.x=x;
        this.y=y;
        this.v=v;
    }
    void moure(){
        x+=v;
    }
    void pinta(Graphics g){
        g.setColor(Color.RED);
        g.drawRect(x,y,width,height);
        g.drawLine(x+(int) (width*0.75),y,x+(int) (width*0.75), y+height);
    }
}
