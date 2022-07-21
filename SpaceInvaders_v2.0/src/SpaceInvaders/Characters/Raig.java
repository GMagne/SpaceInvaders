package SpaceInvaders.Characters;

public class Raig extends Character {
    public static int h=36, w=12;

    private float v;

    public Raig(float px, float py){
        super(px,py, 4*8, 4*3,"Imatges/Raig.gif");
        this.v=12;
    }

    public void move(){
        y-=v;
    }


}