package SpaceInvaders.Characters;

public class RaigAlien extends Character {
    public static int h=28, w=12;
    private float v;


    public RaigAlien(float px, float py){
        super(px,py, h, w,"Imatges/RaigAlien.gif");
        this.v=12;
    }

    public void move(){
        y+=v;
    }


}