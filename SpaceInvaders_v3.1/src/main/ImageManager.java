package main;

import javax.swing.*;
import java.awt.*;

public class ImageManager {

    private static Image imageOctopus;
    private static Image imageCrab;
    private static Image imageSquid;
    private static Image imageShip;
    private static Image imageSpaceship;
    private static Image imageShot;
    private static Image imageRay;
    private static Image imageBackground;
    private static Image imageBackgroundPI;

    private static ImageIcon iconOctopus;
    private static ImageIcon iconSpaceship;
    private static ImageIcon iconCrab;
    private static ImageIcon iconSquid;


    public static Image getImage(String path){

        switch (path){

            case C.PATH_OCTOPUS:
                if (imageOctopus == null){
                    imageOctopus = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageOctopus;


            case C.PATH_CRAB:
                if (imageCrab== null){
                    imageCrab = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageCrab;

            case C.PATH_SQUID:
                if (imageSquid == null){
                    imageSquid = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageSquid;

            case C.PATH_SPACESHIP:
                if (imageSpaceship== null){
                    imageSpaceship = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageSpaceship;

            case C.PATH_SHIP:
                if (imageShip == null){
                    imageShip = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageShip;

            case C.PATH_SHOT:
                if (imageShot == null){
                    imageShot = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageShot;

            case C.PATH_EXPLOSION:
                return Toolkit.getDefaultToolkit().createImage(path);

            case C.PATH_RAY:
                if (imageRay == null){
                    imageRay = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageRay;

            case C.PATH_BACKGROUND:
                if (imageBackground == null){
                    imageBackground  = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageBackground ;

            case C.PATH_BACKGROUNDPI:
                if (imageBackgroundPI == null){
                    imageBackgroundPI  = Toolkit.getDefaultToolkit().createImage(path);
                }
                return imageBackgroundPI;

            default:
                return null;

        }
    }

    public static ImageIcon getImageIcon(String path){
        switch (path){

            case C.PATH_OCTOPUS_ICON:
                if(iconOctopus == null){
                    iconOctopus = new ImageIcon(path);
                }
                return iconOctopus;

            case C.PATH_CRAB_ICON:
                if(iconCrab == null){
                    iconCrab = new ImageIcon(path);
                }
                return iconCrab;

            case C.PATH_SQUID_ICON:
                if(iconSquid == null){
                    iconSquid = new ImageIcon(path);
                }
                return iconSquid;

            case C.PATH_SPACESHIP_ICON:
                if(iconSpaceship == null){
                    iconSpaceship = new ImageIcon(path);
                }
                return iconSpaceship;

            default:
                return null;
        }
    }

}
