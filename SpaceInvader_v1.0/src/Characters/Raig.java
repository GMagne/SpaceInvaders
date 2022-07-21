package Characters;

public class Raig extends Character {
    float[] dir;

    public Raig(float x, float y, float v, float[] dir){
        super(x,y,v);
        this.dir=dir;
    }

    public void move(){
        x+=v*dir[1];
        y+=v*dir[2];
    }


}
