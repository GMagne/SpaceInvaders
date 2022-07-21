import java.awt.*;

public class Joc {
    Finestra f;
    Cotxe c[];
    Joc(Finestra f){
        this.f=f;
    }
    void run() {
        inicialitzacio();
        while(true){
            ferMoviments();
            detectarXocs();
            pintarPantalla();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
        }
    }
    void inicialitzacio(){
        c=new Cotxe[6];
        for(int i=0; i<c.length;i++){
            c[i]=new Cotxe(30,50+80*i,2*(i+1));
        }
    }
    void ferMoviments(){
        for(Cotxe cotxe: c){
            cotxe.moure();
        }

    }
    void pintarPantalla(){
        f.g.setColor(Color.WHITE);
        f.g.fillRect(0,0,f.WIDTH,f.HEIGHT);
        for(Cotxe cotxe: c){
            cotxe.pinta(f.g);
        }
        f.repaint();
    }
    void detectarXocs(){

    }
}
