package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Scor.Scor;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea

 */
public class Hero extends Character
{
    private ArrayList<Star> listofstars=new ArrayList<>();/*!<Lista ce contine stelele de pe fiecare nivel>*/
    private ArrayList<Ghost> listofgost=new ArrayList<>();/*!<Lista ce contine fantomele>*/
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    public static  int EAT;   /*!< Variabila statica in care este pus un punctaj pentru desenarea dreptunghiului de finalizare nivel */



    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y, ArrayList<Star> lista, ArrayList<Ghost> gosts)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
        image = Assets.heroRight;
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 32;
        bounds.height = 32;
        listofstars.addAll(lista);
        listofgost.addAll(gosts);
        EAT=0;


    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
            ///Verifica daca a fost apasata o tasta
        GetInput();
            ///Actualizeaza pozitia
        checkCollision();
        eating();


        gostinersection();
        Move();
            ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left == true)
        {
            image = Assets.heroLeft;
        }
        if(refLink.GetKeyManager().right == true) {
            image = Assets.heroRight;
        }

        if(refLink.GetKeyManager().up==true)
        {
            image=Assets.heroUp;
        }
        if(refLink.GetKeyManager().down==true)
        {
            image=Assets.heroDown;
        }

    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }
            ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
     //   System.out.println("x="+x +" y="+y);
      //  g.setColor(Color.blue);
      //  g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
    /*! \fn public void eating()
       \brief Verifica daca intra in coliziune cu o stea in acest caz este eliminata de pe harta

     */
    public void eating()
    {
        //rectangle hero
        Rectangle r=new Rectangle((int)GetX(), (int)GetY(), 32, 32);
        for(Star s :listofstars)
         {

             //rectangle star
            Rectangle t=new Rectangle((int)s.x+8, (int)s.y+8, 8, 8);
            if(r.intersects(t))
            {
                if(s.isEat()==false) {
                    s.setEat(true);
                    s.Update();
                 //   System.out.println("Eat");
                    EAT=EAT+30;
                    Scor.GetScoreInstance().incrementScore(30);


                }
                else
                {
                  //  System.out.println("It was eat");
                }

            }
        }
    }
    /*! \fn publicvoid gostinersection()
      \brief Verifica daca intra in coliziune cu o fantoma in acest caz jocul se termina

    */
    private void gostinersection()
    {
        //rectangle hero

        Rectangle r=new Rectangle((int)GetX(), (int)GetY(), 32, 32);
        for(Ghost g :listofgost)
        {

            //rectangle gost
            Rectangle t=new Rectangle((int)g.x, (int)g.y, 32, 32);

            if(r.intersects(t))
            {
              //  System.out.println("S-a intersectat cu o fantoma!");
                refLink.GetGame().SetGameOverState();
            }
        }

    }

    /*! \fn publicvoid  getEAT()
     \brief Returneza punctele pe nivel

   */
    public int getEAT() {
        return EAT;
    }


}
