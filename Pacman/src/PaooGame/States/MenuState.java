package PaooGame.States;

import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.Scor.Scor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State

{
   public Rectangle playButton=new Rectangle(300, 200, 100, 50); /*!< dreptunghi pentru buton de play*/
    public Rectangle quitButton=new Rectangle(300, 400, 100, 50); /*!< dreptunghi pentru buton de quit*/
    public Rectangle historyButton=new Rectangle(300, 300, 110, 50); /*!< dreptunghi pentru buton de history*/

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
        In caul in care se apasa pe butonul de play se va incarca jocul
        In cazul in care se apasa pe butonul de history se va afisa continutul bazei de date
        In cazul in care se apasa pe butonul  quit se va iesi din fereastra
     */
    @Override
    public void Update()
    {
        if(refLink.GetMouseManager().getXmouse()>=300&&refLink.GetMouseManager().getXmouse()<=400)
        {
            if(refLink.GetMouseManager().getYmouse()>=200&&refLink.GetMouseManager().getYmouse()<=350)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                    Scor.GetScoreInstance().setScore(0);
                    level=1;// setare level din joc
                    refLink.SetPlayStatus();// se trece la starea de joc


                }
            }
        }
        if(refLink.GetMouseManager().getXmouse()>=300&&refLink.GetMouseManager().getXmouse()<=400)
        {
            if(refLink.GetMouseManager().getYmouse()>=400&&refLink.GetMouseManager().getYmouse()<=450)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                    System.exit(0); //se termina jocul
                }
            }
        }
        if(refLink.GetMouseManager().getXmouse()>=300&&refLink.GetMouseManager().getXmouse()<=410)
        {
            if(refLink.GetMouseManager().getYmouse()>=300&&refLink.GetMouseManager().getYmouse()<=350)
            {
                if(refLink.GetMouseManager().isLeft())
                {
                    refLink.SetHistiryState();//setare stare history
                }

            }
        }
    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.
         Se va desena un fundal negru peste care se va desena o un logo si butoanele
        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0,0, refLink.GetWidth(), refLink.GetHeight());
        Font font0=new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.YELLOW);
        g.drawImage(ImageLoader.LoadImage("/textures/logo.jpg"), 220, 30,null);
        Font fnt1=new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x+19, playButton.y+30 );
        g2d.draw(playButton);
        g.drawString("Quit", quitButton.x+19, quitButton.y+30 );
        g2d.draw(quitButton);
        g.drawString("History", historyButton.x+5, historyButton.y+30 );
        g2d.draw(historyButton);
    }

    /*! \fn  public int getLevel()
       \brief Se va returna 0 pentru ca suntem in afara jocului.

    */
    @Override
    public int getLevel() {
        return 0;
    }
}
