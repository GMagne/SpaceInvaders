package main.Utilities;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AudioManager {

    private static float volume = 1;

    private static Clip[] shoot = new Clip[10];
    private static Clip[] invKilled = new Clip[10];
    private static Clip explosion;
    private static Clip spaceShip;
    private static Clip fastinvader;

    static {
        try {
            //shoot = Applet.newAudioClip(new URL("file:./Audio/shoot.mp3"));
            for (int i = 0; i<10; i++){
                shoot[i] = AudioSystem.getClip();
                shoot[i].open( AudioSystem.getAudioInputStream( new URL("file:./Audio/shoot.wav") ) );
            }
            for (int i = 0; i<10; i++) {
                invKilled[i] = AudioSystem.getClip();
                invKilled[i].open(AudioSystem.getAudioInputStream(new URL("file:./Audio/invaderkilled.wav")));
            }
            explosion = AudioSystem.getClip();
            explosion.open( AudioSystem.getAudioInputStream( new URL("file:./Audio/explosion.wav") ) );

            spaceShip = AudioSystem.getClip();
            spaceShip.open( AudioSystem.getAudioInputStream( new URL("file:./Audio/ufo_lowpitch.wav") ) );

            fastinvader= AudioSystem.getClip();
            fastinvader.open( AudioSystem.getAudioInputStream( new URL("file:./Audio/fastinvader.wav") ) );


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    static int j=0;
    public static void shoot(){
        if( shoot[j] != null ){
            if (shoot[(j+9)%10].isRunning()){
                shoot[(j+9)%10].stop();
                shoot[(j+9)%10].flush();
            }
            shoot[(j+9)%10].setFramePosition( 0 );
            setVolume(shoot[j],volume*0.15f);
            shoot[j].start();
            j = (j+1)%10;
        }
    }

    static int k=0;
    public static void invaderkilled(){
        if( invKilled[k] != null ){
            if (invKilled[(k+9)%10].isRunning()){
                invKilled[(k+9)%10].stop();
                invKilled[(k+9)%10].flush();
            }
            invKilled[(k+9)%10].setFramePosition( 0 );
            setVolume(invKilled[k],volume*0.3f);
            invKilled[k].start();
            k = (k+1)%10;
        }
    }

    public static void explosion(){
        if( explosion != null ){
            if (explosion.isRunning()){
                explosion.stop();
                explosion.flush();
            }
            explosion.setFramePosition( 0 );
            setVolume(explosion,volume*0.5f);
            explosion.start();
        }
    }

    public static void spaceshipAppears(){
        if( spaceShip != null ){
            if (spaceShip.isRunning()){
                spaceShip.stop();
                spaceShip.flush();
            }
            spaceShip.setFramePosition( 0 );
            setVolume(spaceShip,volume*0.2f );
            spaceShip.start();
        }
    }

    public static void fastinvader(){
        if (fastinvader != null){
            fastinvader.setFramePosition( 0 );
            setVolume(fastinvader,volume);
            fastinvader.start();
        }
    }

    public static void setVolume(Clip clip, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    private static float volume0 ;
    public static void mute(){
        float temp = volume;
        volume = volume0;
        volume0 = temp;

    }

    private static long lastVolumeUpdate = 0 ;

    public static void increaseVolume(){
        long time = System.currentTimeMillis();
        if (time-lastVolumeUpdate > 100){
            volume +=0.2;
            lastVolumeUpdate = time;
        }
    }

    public static void decreaseVolume(){
        long time = System.currentTimeMillis();
        if (time-lastVolumeUpdate > 100){
            volume -=0.2;
            lastVolumeUpdate = time;
        }
    }







}
