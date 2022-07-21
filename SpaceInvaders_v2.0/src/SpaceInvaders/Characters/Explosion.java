package SpaceInvaders.Characters;

import sun.text.normalizer.UCharacter;

public class Explosion extends Character {
    public static int h=32, w=48;
    public Explosion(float px, float py){
        super(px-h/2,py-w/2, h, w,"Imatges/Explosion.gif");

    }
}
