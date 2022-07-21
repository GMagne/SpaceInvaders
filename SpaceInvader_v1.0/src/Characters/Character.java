package Characters;

public class Character {
    float x, y, v;
    Hitbox hitbox;

    public Character(float x, float y, float v){
        this.x=x;
        this.y=y;
        this.v=v;
    }

    public int x(){
        return (int) x;
    }

    public int y(){
        return (int) y;
    }

    public boolean isMoveLeftLegal(){
        if( hitbox.leftPixel()-v>0) return true;
        else return false;
    }

    public boolean isMoveRightLegal(){
        if( hitbox.rightPixel()+v<=0/*B_WIDTH*/) return true;
        else return false;
    }

    public boolean isMoveDownLegal(){
        if( hitbox.bottomPixel()+hitbox.heigth()<=0/*B_HEIGHT*/) return true;
        else return false;
    }
}
