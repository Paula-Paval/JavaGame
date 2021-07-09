package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft; /*!< Retina imagine erou stanga .*/
    public static BufferedImage heroRight; /*!< Retina imagine erou  dreapta.*/
    public static BufferedImage heroUp;  /*!< Retina imagine erou sus.*/
    public static BufferedImage heroDown;   /*!< Retina imagine erou  jos .*/
    public static BufferedImage bluegrass;   /*!< Retina imagine iarba albastra.*/
    public static BufferedImage greengrass;  /*!< Retina imagine iarba verde.*/
    public static BufferedImage wall;  /*!< Retina imagine perete  .*/
    public static BufferedImage pinkgost;  /*!< Retina imagine fantoma roz .*/
    public static BufferedImage bluegost;  /*!< Retina imagine fantoma albastra .*/
    public static BufferedImage star;  /*!< Retina imagine stea.*/

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));


            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        bluegrass = sheet.crop(1, 0);
        greengrass = sheet.crop(2, 0);
        wall = sheet.crop(0, 0);
        star = sheet.crop(3, 0);
        heroLeft = sheet.crop(5, 0);
        heroRight = sheet.crop(6, 0);
        heroUp = sheet.crop(7 ,0);
        heroDown = sheet.crop(4, 0);
        pinkgost = sheet.crop(8, 0);
        bluegost = sheet.crop(9, 0);
    }
}
