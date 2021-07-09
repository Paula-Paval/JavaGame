package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    public static final int DEFAULT_LIFE            = 10;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 2.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 32;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 32;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int life;     /*!< Retine viata caracterului.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    protected boolean down;  /*!< Flag pentru coliziunea cu un perete aflat deasupra sau dedeseupt caracterului*/
    protected boolean right;  /*!< Flag pentru coliziunea cu un perete aflat la dreapta  sau la stanga caracterului*/
    protected boolean collide;   /*!< Flag ce arata daca a fost lovit un perete sau nu*/

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
        collide=false;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {

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

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        x += xMove;
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
    }

    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
     /*! \fn public void checkCollision()
        \brief Functie ce verifica coliziunea dintre doua dreptunghiuri
        Aceasta functie verifica coliziunea unui caracter cu elementele solide desenate pe harta
     */

    public void checkCollision(){
        for(int i = 0; i < refLink.GetMap().getWidth(); i++){
            for(int j = 0; j < refLink.GetMap().getHeight(); j++){
                if(refLink.GetMap().GetTile(i, j).IsSolid()){
                    Rectangle r=new Rectangle((int)GetX(), (int)GetY(), 32, 32);
                    Rectangle t=new Rectangle(i*32, j*32, 32, 32);
                    if(r.intersects(t)){
                        setColliding(true);
                        if(r.x<t.x)
                        {
                            right=false;

                        }
                        else
                        {
                            right=true;
                        }
                        if(r.y<t.y)
                        {
                            down=false;
                        }
                        else
                        {
                            down=true;
                        }
                 //       System.out.println("Avem coliziune");
                 //       System.out.println("xtile="+32*i+"ytile="+32*j);
                    }
                }
            }
        }
    }
    /*! \fn public void setColliding(boolean x)
        \brief Functie ce seteaza campul collide;
     */
    public void setColliding(boolean x)
    {
        this.collide=x;
    }
}

