package PaooGame.States;

import PaooGame.DB.DataBase;
import PaooGame.Items.Ghost;
import PaooGame.Items.Hero;
import PaooGame.Items.Star;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static PaooGame.Items.Character.DEFAULT_CREATURE_HEIGHT;
import static PaooGame.Items.Character.DEFAULT_CREATURE_WIDTH;


/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private ArrayList<Star> list=new ArrayList<>();  /*!< Referinta catre stele.*/
    private ArrayList<Ghost> gosts=new ArrayList<>();/*!<Referinta catre ghosts*/




    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink) throws IOException {
            ///Apel al constructorului clasei de baza

        super(refLink);
            ///Construieste harta jocului


        map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);


        DataBase.getInstance().setarecoordonate();
        //construire lista de stea

        list.add(new Star(refLink, DataBase.getXstar1(), DataBase.getYstar1(), DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT));
        list.add(new Star(refLink, DataBase.getXstar2(), DataBase.getYstar2(), DEFAULT_CREATURE_WIDTH , DEFAULT_CREATURE_HEIGHT));
        list.add(new Star(refLink, DataBase.getXstar3(), DataBase.getYstar3(), DEFAULT_CREATURE_WIDTH , DEFAULT_CREATURE_HEIGHT));
        gosts.add(new Ghost(refLink, DataBase.getXforblueinamic(), DataBase.getYforblueinamic(), DEFAULT_CREATURE_WIDTH , DEFAULT_CREATURE_HEIGHT,"blue", hero));

            ///Construieste eroul
        hero = new Hero(refLink,DataBase.getXforhero(), DataBase.getYforhero(), list, gosts);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() throws IOException {
        int currentlevel=level;
        map.Update();
        hero.Update();
        for(Ghost gost:gosts)
        {
            gost.Update();
        }
        int eat=0;
        for(Star star:list)
        {
            if(!star.isEat())
                eat=1;
        }

        if(eat==0)
        {
            level++;
        }
        if(level==3)
        {

            refLink.SetWinState();
        }
        if(currentlevel!=level)
        {
            map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
            refLink.SetMap(map);
            list.add(new Star(refLink, DataBase.getXstar1(), DataBase.getYstar1(), DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT));
            list.add(new Star(refLink, DataBase.getXstar2(), DataBase.getYstar2(), DEFAULT_CREATURE_WIDTH , DEFAULT_CREATURE_HEIGHT));
            list.add(new Star(refLink, DataBase.getXstar3(), DataBase.getYstar3(), DEFAULT_CREATURE_WIDTH , DEFAULT_CREATURE_HEIGHT));
            gosts.add(new Ghost(refLink, DataBase.getXforpinkinamic(), DataBase.getYforpinkinamic(),  DEFAULT_CREATURE_WIDTH , DEFAULT_CREATURE_HEIGHT, "pink", hero));

            ///Construieste eroul
            hero = new Hero(refLink,100, 100, list, gosts);
        }


    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {

        map.Draw(g);
        hero.Draw(g);
        for(Ghost gost:gosts)
        {
            gost.Draw(g);
        }



        //desenarea fiecarie stele
       for(Star star:list)
       {
           star.Draw(g);
       }
        g.setColor(Color.gray);
        g.fillRect(5, 5, 90, 30);
        g.setColor(Color.green);
        g.fillRect(5, 5, hero.getEAT(), 30);

        g.setColor(Color.white);
        g.drawRect(5, 5, 90, 30);




    }

    /*! \fn public int getLevel()
      \brief  Se va returna  0 pentru ca suntem in afara jocului

   */
    public int getLevel() {
        return level;
    }
}
