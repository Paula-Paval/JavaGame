package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public  class Ghost extends Character
    \brief Implementeaza notiunea de inamic.

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
 */
public class Ghost extends Character{

    private BufferedImage image;    /*!< Referinta catre imaginea curenta a gost.*/
    private int nextmove; /*!< this show us how gost must move 0 stay 1 right 2 down 3 left 4 up .*/
    private Hero h;  /*!< Referinta la erou*/


    /*! \fn  public Ghost(RefLinks refLink, float x, float y, int width, int height, String type, Hero h)
        \brief Constructor clasa Ghost.
        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
     */
    public Ghost(RefLinks refLink, float x, float y, int width, int height, String type, Hero h) {
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        this.h=h;
        if(type=="blue") {
            image = Assets.pinkgost;
        }
        else
        {
            image=Assets.bluegost;
        }
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 32;
        bounds.height = 32;
        nextmove=1;
    }
       /*! \fn  void Update()
        \brief Metoda Update in care este implementat miscarea inamicului conform starilor stabilite dar pastrand conceptul de coliziune
     */

    @Override
    public void Update() {
        switch (nextmove)
        {
            case 0:  SetX(GetX()+speed);

                        break;
            case 1:     SetY(GetY()+speed);


                        break;
            case 2:  SetX(GetX()-speed);

                break;

            case 3:     SetY(GetY()-speed);


                break;
        }

        checkCollision();
        float oldx = this.GetX();
        float oldy = this.GetY();

        if(collide == false){
            MoveX();
            MoveY();
        }else if(collide== true){
            setColliding(false);
            if(right==false)
            {
                  SetX(oldx-2);

            }
            else
            {
                SetX(oldx+2);
           }
            if(down==false)
            {
                SetY(oldy-2);


            }
            else
            {
                SetY(oldy+2);


            }
            switch (nextmove)
            {
                case 0:  nextmove=1;
                            break;
                case 1:    nextmove=2;
                            break;
                case 2:   nextmove=3;
                            break;
                case 3:     nextmove=0;
                             break;
            }


        }

        if (this.GetX() <= 32) {
            SetX(32);
        }
        if (this.GetX() >= 736 - DEFAULT_CREATURE_WIDTH-32) {
            this.SetX(736 - DEFAULT_CREATURE_WIDTH-32);
        }
        if (this.GetY() <= 32) {
            this.SetY(32);
        }
        if (this.GetY() >= 480) {
            this.SetY(480);
        }
    }
  /*! \fn  void  Draw(Graphics g)
        \brief Metoda de desenare a inamicului pe harta
     */

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

}
