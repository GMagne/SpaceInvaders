package Characters;

public class Nau extends Character {
    float v_max, a; // 0<a<v_max

    public Nau(float x, float y, float v_max, float a){
        super(x,y,0);
        this.v_max=v_max;
        this.a=a;
    }

    public void moveRight(){
        if (v>0) {
            v = v_max -a*(1-v/v_max);
        }
        else{
            v = v_max -a;
        }
        x+=v;
    }

    public void moveLeft(){
        if (v<0) {
            v = a*(1-v/v_max)-v_max;
        }
        else{
            v = a-v_max;
        }
        x+=v;
    }

}

