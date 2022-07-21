package Characters;

public abstract class Alien extends Character {

    public Alien(float x, float y, float v){
        super(x,y,v);
    }

    public void moveRight(){
        x+=v;
    }

    public void moveLeft(){
        x-=v;
    }

    public void moveDown(){
        y+=hitbox.heigth();
    }

}
